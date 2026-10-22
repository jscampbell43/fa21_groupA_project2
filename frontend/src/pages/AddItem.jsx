import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { itemAPI, userAPI } from '../services/api'
import Navigation from '../components/Navigation'

function AddItem() {
  const [name, setName] = useState('')
  const [description, setDescription] = useState('')
  const [link, setLink] = useState('')
  const [image, setImage] = useState('')
  const [list, setList] = useState('')
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  // For now, we'll use a placeholder user ID
  // In a real app, this would come from authentication state
  const userId = sessionStorage.getItem('user') || '1'

  const validateForm = () => {
    if (!name.trim()) {
      setError('Item Name is required')
      return false
    }
    if (!list.trim()) {
      setError('List Name is required')
      return false
    }
    setError('')
    return true
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setSuccess('')

    if (!validateForm()) {
      return
    }

    setLoading(true)

    try {
      await itemAPI.addItem({
        id: userId,
        name,
        desc: description,
        link,
        image,
        list,
      })
      setSuccess('Item successfully added!')
      // Clear form
      setName('')
      setDescription('')
      setLink('')
      setImage('')
      setList('')
    } catch (err) {
      setError('Something went wrong, please try again')
      console.error('Error adding item:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleLogout = async () => {
    try {
      await userAPI.logout()
      navigate('/')
    } catch (err) {
      console.error('Logout error:', err)
      navigate('/')
    }
  }

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-6xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Add Item</h1>

        <Navigation />

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white font-bold py-2 px-4 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 mb-6"
        >
          Logout
        </button>

        <div className="bg-white rounded-lg shadow-md p-6">
          <h2 className="text-2xl font-semibold mb-4">Please enter item details</h2>

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
              <label htmlFor="name" className="block text-gray-700 font-bold mb-2">
                Name:
              </label>
              <input
                type="text"
                id="name"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Enter item name"
                required
              />
            </div>

            <div className="mb-4">
              <label htmlFor="description" className="block text-gray-700 font-bold mb-2">
                Description (Optional):
              </label>
              <textarea
                id="description"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                placeholder="Enter item description"
                rows="3"
              />
            </div>

            <div className="mb-4">
              <label htmlFor="link" className="block text-gray-700 font-bold mb-2">
                Link to Item (Optional):
              </label>
              <input
                type="text"
                id="link"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={link}
                onChange={(e) => setLink(e.target.value)}
                placeholder="Enter item link"
              />
            </div>

            <div className="mb-4">
              <label htmlFor="image" className="block text-gray-700 font-bold mb-2">
                Link to Image (Optional):
              </label>
              <input
                type="text"
                id="image"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={image}
                onChange={(e) => setImage(e.target.value)}
                placeholder="Enter image URL"
              />
            </div>

            <div className="mb-6">
              <label htmlFor="list" className="block text-gray-700 font-bold mb-2">
                Name of the list:
              </label>
              <input
                type="text"
                id="list"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                value={list}
                onChange={(e) => setList(e.target.value)}
                placeholder="Enter list name"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:opacity-50"
              disabled={loading}
            >
              {loading ? 'Adding Item...' : 'Add Item'}
            </button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddItem
