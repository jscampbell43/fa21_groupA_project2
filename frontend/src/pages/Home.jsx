import { useNavigate } from 'react-router-dom'
import { userAPI } from '../services/api'
import Navigation from '../components/Navigation'

function Home() {
  const navigate = useNavigate()

  const handleLogout = async () => {
    try {
      await userAPI.logout()
      navigate('/')
    } catch (err) {
      console.error('Logout error:', err)
      // Even if API fails, navigate to login
      navigate('/')
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Home</h1>

        <Navigation />

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-bold py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 mb-6"
        >
          Logout
        </button>

        <div className="bg-white rounded-lg shadow-md p-6">
          <h2 className="text-2xl font-semibold mb-4">Welcome to Wishlist!</h2>
          <p className="text-gray-600">
            Use the navigation above to manage your wishlist items.
          </p>
        </div>
      </div>
    </div>
  )
}

export default Home
