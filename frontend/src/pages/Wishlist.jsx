import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { itemAPI } from '../services/api'
import { useAuth } from '../contexts/AuthContext'
import Navigation from '../components/Navigation'

function Wishlist() {
  const [items, setItems] = useState([])
  const [searchTerm, setSearchTerm] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const navigate = useNavigate()
  const { userId, logout, loading: authLoading } = useAuth()
  const finalUserId = userId || '1'

  useEffect(() => {
    // Only load items when auth context is done loading
    if (!authLoading) {
      loadItems()
    }
  }, [userId, authLoading])

  const loadItems = async () => {
    setLoading(true)
    setError('')
    try {
      const response = await itemAPI.getItemByUser(finalUserId)
      setItems(response.data)
    } catch (err) {
      setError('Failed to load items')
      console.error('Error loading items:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleSearch = async () => {
    if (!searchTerm.trim()) {
      loadItems()
      return
    }

    setLoading(true)
    setError('')
    try {
      const response = await itemAPI.getItemByList(searchTerm, finalUserId)
      setItems(response.data)
    } catch (err) {
      setError('Failed to search items')
      console.error('Error searching items:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleLogout = async () => {
    await logout()
    navigate('/')
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Wishlist</h1>

        <Navigation />

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-bold py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 mb-6"
        >
          Logout
        </button>

        <div className="bg-white rounded-lg shadow-md p-6 mb-6">
          <div className="flex gap-4 mb-4">
            <input
              type="text"
              className="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter list name to search"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button
              onClick={handleSearch}
              className="bg-blue-500 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              Search
            </button>
          </div>
        </div>

        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6">
            {error}
          </div>
        )}

        {loading ? (
          <div className="text-center py-8">
            <p className="text-gray-600">Loading...</p>
          </div>
        ) : items.length === 0 ? (
          <div className="bg-white rounded-lg shadow-md p-6">
            <p className="text-gray-600">
              {searchTerm
                ? "There don't seem to be any items in this list"
                : "You don't appear to have any items, go add some!"}
            </p>
          </div>
        ) : (
          <div className="grid gap-4">
            {items.map((item) => (
              <div key={item.id} className="bg-white rounded-lg shadow-md p-6">
                <p className="text-sm text-gray-500 mb-2">Item ID: {item.id}</p>
                <h3 className="text-xl font-semibold mb-2">{item.name}</h3>
                {item.item_link && (
                  <p className="mb-2">
                    Link:{' '}
                    <a
                      href={item.item_link}
                      target="_blank"
                      rel="noopener noreferrer"
                      className="text-blue-500 hover:text-blue-700"
                    >
                      {item.item_link}
                    </a>
                  </p>
                )}
                {item.img_url && (
                  <img
                    src={item.img_url}
                    alt={item.name}
                    className="max-w-full h-auto mb-2 rounded"
                  />
                )}
                <p className="text-gray-600 mb-2">{item.description}</p>
                <p className="text-sm text-gray-500">List: {item.list_name}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  )
}

export default Wishlist
