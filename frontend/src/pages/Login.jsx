import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'

function Login() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()
  const { login } = useAuth()

  const validateForm = () => {
    if (!username.trim()) {
      setError('Username is required')
      return false
    }
    if (!password) {
      setError('Password is required')
      return false
    }
    setError('')
    return true
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setSuccess('')

    // Prevent multiple submissions
    if (loading) {
      return
    }

    if (!validateForm()) {
      return
    }

    setLoading(true)

    try {
      const result = await login(username, password)

      if (result.success) {
        // Success - AuthContext handles state and sessionStorage
        setSuccess('User successfully Logged In! Redirecting to Home...')
        // Keep button disabled during redirect
        setTimeout(() => {
          navigate('/home')
        }, 2000)
      } else {
        // Error - Handle the response from AuthContext
        const response = result.response
        if (response.data.errors) {
          const firstError = Object.values(response.data.errors)[0]
          setError(firstError)
        } else if (response.data.message) {
          if (response.data.message.includes("not found")) {
            setError("Invalid username or password")
          } else {
            setError(response.data.message)
          }
        } else if (response.status === 401) {
          setError("Invalid username or password")
        } else {
          setError('Something went wrong, please try again.')
        }
        setLoading(false)
      }
    } catch (err) {
      // Handle network errors or unexpected errors
      if (err.response && err.response.data) {
        if (err.response.data.errors) {
          const firstError = Object.values(err.response.data.errors)[0]
          setError(firstError)
        } else if (err.response.data.message) {
          setError(err.response.data.message)
        } else {
          setError('Something went wrong, please try again.')
        }
      } else {
        setError('Something went wrong, please try again!')
      }
      console.error('Login error:', err)
      setLoading(false)
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold mb-6 text-center">Login</h1>

        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            {error}
          </div>
        )}

        {success && (
          <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
            {success}
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="username" className="block text-gray-700 font-bold mb-2">
              Username:
            </label>
            <input
              type="text"
              id="username"
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter username"
            />
          </div>

          <div className="mb-6">
            <label htmlFor="password" className="block text-gray-700 font-bold mb-2">
              Password:
            </label>
            <input
              type="password"
              id="password"
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter password"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:opacity-50 disabled:cursor-not-allowed disabled:bg-gray-400"
            disabled={loading}
          >
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </form>

        <div className="text-center mt-4">
          <p className="text-gray-600">
            Don't have an account?{' '}
            <a href="/signup" className="text-blue-500 hover:text-blue-700">
              Sign up
            </a>
          </p>
        </div>
      </div>
    </div>
  )
}

export default Login
