import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Login from './pages/Login'
import Signup from './pages/Signup'
import Home from './pages/Home'
import Wishlist from './pages/Wishlist'
import AddItem from './pages/AddItem'
import EditAccount from './pages/EditAccount'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/home" element={<Home />} />
        <Route path="/wishlist" element={<Wishlist />} />
        <Route path="/add-item" element={<AddItem />} />
        <Route path="/edit-account" element={<EditAccount />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
