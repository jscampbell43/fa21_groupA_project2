import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { userAPI } from '../services/api'
import Navigation from '../components/Navigation'

function EditAccount() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const handleLogout = async () => {
    try {
      await userAPI.logout()
      navigate('/')
    } catch (err) {
      console.error('Logout error:', err)
      navigate('/')
    }
  }

  const handleUpdate = async () => {
    setError('')
    setSuccess('Update functionality needs to be implemented in the backend first.')
  }

  const handleDelete = async () => {
    setError('')
    setSuccess('Delete functionality needs to be implemented in the backend first.')
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Edit Account</h1>

        <Navigation />

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-bold py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 mb-6"
        >
          Logout
        </button>

        <div className="bg-white rounded-lg shadow-md p-6">
          <h2 className="text-2xl font-semibold mb-4">
            Enter new information and click "Update Account"
          </h2>

          {error && (
            <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
              {error}
            </div>
          )}

          {success && (
            <div className="bg-yellow-100 border border-yellow-400 text-yellow-700 px-4 py-3 rounded mb-4">
              {success}
            </div>
          )}

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
              placeholder="Enter new username"
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
              placeholder="Enter new password"
            />
          </div>

          <button
            onClick={handleUpdate}
            className="w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 mb-6"
            disabled={loading}
          >
            {loading ? 'Updating...' : 'Update Account'}
          </button>

          <hr className="my-6" />

          <p className="text-gray-600 mb-4">
            If you would like to delete your account click here
          </p>

          <button
            onClick={handleDelete}
            className="w-full bg-red-500 text-white font-bold py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500"
          >
            Delete Account
          </button>
        </div>
      </div>
    </div>
  )
}

export default EditAccount
