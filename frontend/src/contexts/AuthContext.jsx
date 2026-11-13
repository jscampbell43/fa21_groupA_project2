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
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const storedUser = sessionStorage.getItem('user')
    const storedUserId = sessionStorage.getItem('userId')
    if (storedUser && storedUserId) {
      setUser({ username: storedUser, userId: storedUserId })
    }
    setLoading(false)
  }, [])

  const login = async (username, password) => {
    const response = await userAPI.login(username, password)
    if (response.data.userId) {
      const userData = { username, userId: response.data.userId }
      setUser(userData)
      sessionStorage.setItem('user', username)
      sessionStorage.setItem('userId', response.data.userId)
      return { success: true, data: response.data }
    }
    return { success: false, response }
  }

  const logout = async () => {
    await userAPI.logout()
    setUser(null)
    sessionStorage.removeItem('user')
    sessionStorage.removeItem('userId')
  }

  const signup = async (username, password, role) => {
    const response = await userAPI.signup(username, password, role)
    if (response.data.userId) {
      return { success: true, data: response.data }
    }
    return { success: false, response }
  }

  const update = async (username, password) => {
    const userId = user.userId
    try {
      const response = await userAPI.updateUser(userId, username, password)
      if (response.data.userId) {
        setUser({username: response.data.username, userId: response.data.userId })
        sessionStorage.setItem('user', response.data.username)
        sessionStorage.setItem('userId', response.data.userId)
        return { success: true, data: response.data}
      }
      return {success: false, response}
    } catch (err) {
      return { success: false, response: err.response }
    }
  }

  const deleteUser = async (username, password) => {
    const userId = user.userId
    try{
      const response = await userAPI.deleteUser(userId, username, password)
      if(response.status === 200 || response.data.message){
        setUser(null)
        sessionStorage.removeItem('user')
        sessionStorage.removeItem('userId')
        return { success: true, data: response.data}
      }
      return {success: false, response}
    }
    catch (err){
      return { success: false, response: err.response }
    }
  }

  const value = {
    user,
    loading,
    login,
    logout,
    signup,
    update,
    deleteUser,
    isAuthenticated: !!user,
    userId: user?.userId || '1'
  }

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  )
}
