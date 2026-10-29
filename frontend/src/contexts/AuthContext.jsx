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
    // Backend now returns UserResponse object: { userId, username, role }
    if (response.data.userId) {
      const userData = { username, userId: response.data.userId }
      setUser(userData)
      sessionStorage.setItem('user', username)
      sessionStorage.setItem('userId', response.data.userId)
      return { success: true, data: response.data }
    }
    // Error case - return the response so the component can handle it
    return { success: false, response }
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
    // Backend now returns UserResponse object on success: { userId, username, role }
    // On error, it returns ErrorResponse: { status, message, errors }
    if (response.data.userId) {
      return { success: true, data: response.data }
    }
    // Error case - return the response so the component can handle it
    return { success: false, response }
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
