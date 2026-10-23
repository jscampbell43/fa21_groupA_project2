import { createContext, useContext, useState, useEffect } from 'react'
import { userAPI } from '../services/api'

// Create the authentication context
const AuthContext = createContext(null)

// Custom hook to use the auth context
export const useAuth = () => {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}

// AuthProvider component to wrap the application
export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null) // Will store user object { username, userId }
  const [loading, setLoading] = useState(true)

  // Check for existing session on app load
  useEffect(() => {
    const storedUser = sessionStorage.getItem('user')
    const storedUserId = sessionStorage.getItem('userId')
    if (storedUser && storedUserId) {
      setUser({ username: storedUser, userId: storedUserId })
    }
    setLoading(false)
  }, [])

  // Login function
  const login = async (username, password) => {
    const response = await userAPI.login(username, password)
    if (response.data === 'Login successful') {
      // Fetch user details to get the user ID
      const userResponse = await userAPI.getUser(username)
      if (userResponse.data) {
        const userData = { username, userId: userResponse.data.user_id }
        setUser(userData)
        sessionStorage.setItem('user', username)
        sessionStorage.setItem('userId', userResponse.data.user_id)
        return { success: true }
      }
    }
    return { success: false, message: response.data || 'Login failed' }
  }

  // Logout function
  const logout = async () => {
    await userAPI.logout()
    setUser(null)
    sessionStorage.removeItem('user')
    sessionStorage.removeItem('userId')
  }

  // Signup function
  const signup = async (username, password, role) => {
    const response = await userAPI.signup(username, password, role)
    if (response.data === 'Username already exists') {
      return { success: false, message: 'Username already exists' }
    } else {
      return { success: true, message: 'Signup successful' }
    }
  }

  const value = {
    user,
    loading,
    login,
    logout,
    signup,
    isAuthenticated: !!user,
    userId: user?.userId || '1'
  }

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  )
}
