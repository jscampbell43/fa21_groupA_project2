import axios from 'axios'

// Create an Axios instance configured for the backend
const api = axios.create({
  baseURL: '', // Will be proxied to http://localhost:8080 during development
})

// Helper function to convert object to URL-encoded form data
const toFormData = (data) => {
  const formData = new URLSearchParams()
  Object.keys(data).forEach(key => {
    formData.append(key, data[key])
  })
  return formData
}

// User API calls
export const userAPI = {
  login: (username, password) =>
    api.post('/users/login', toFormData({ username, password }), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    }),

  signup: (username, password, role) =>
    api.post('/users/signup', toFormData({ username, password, role }), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    }),

  logout: () =>
    api.get('/users/logout'),

  getUser: (username) =>
    api.get(`/users/${username}`),

  getAllUsers: () =>
    api.get('/users/allUsers'),
}

// Item API calls
export const itemAPI = {
  getAllItems: () =>
    api.get('/items/allItems'),

  getItemByUser: (userId) =>
    api.get(`/items/user/${userId}`),

  getItemByList: (listName, userId) =>
    api.get(`/items/list/${listName}/user/${userId}`),

  searchItem: (itemName) =>
    api.get(`/items/itemSearch/${itemName}`),

  addItem: (itemData) =>
    api.post('/items/additem', toFormData(itemData), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    }),

  updateItem: (itemData) =>
    api.put('/items/updateItem', toFormData(itemData), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    }),

  deleteItem: (itemId) =>
    api.delete('/items/deleteItem', { data: toFormData({ id: itemId }) }),
}

export default api
