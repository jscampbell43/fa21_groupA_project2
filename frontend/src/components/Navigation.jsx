import { useLocation } from 'react-router-dom'
import { userAPI } from '../services/api'

function Navigation() {
  const location = useLocation()

  const handleLogout = async () => {
    try {
      await userAPI.logout()
      window.location.href = '/'
    } catch (err) {
      console.error('Logout error:', err)
      window.location.href = '/'
    }
  }

  const getLinkClass = (path) => {
    const isActive = location.pathname === path
    return isActive
      ? 'text-blue-600 font-semibold hover:text-blue-800'
      : 'text-gray-600 hover:text-gray-800'
  }

  return (
    <nav className="bg-white rounded-lg shadow-md p-4 mb-6">
      <div className="flex space-x-6">
        <a href="/home" className={getLinkClass('/home')}>
          Home
        </a>
        <a href="/wishlist" className={getLinkClass('/wishlist')}>
          Wishlist
        </a>
        <a href="/add-item" className={getLinkClass('/add-item')}>
          Add Item
        </a>
        <a href="/edit-account" className={getLinkClass('/edit-account')}>
          Edit Account
        </a>
      </div>
    </nav>
  )
}

export default Navigation
