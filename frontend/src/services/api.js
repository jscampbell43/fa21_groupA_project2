import axios from 'axios'

// Create an Axios instance configured for the backend
const api = axios.create({
  baseURL: '', // Will be proxied to http://localhost:8080 during development
})


// User API calls
export const userAPI = {
  login: (username, password) =>
    api.post('/users/login', { username, password }),

  signup: (username, password, role) =>
    api.post('/users/signup', { username, password, role }),

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

  addItem: (item) =>
    api.post('/items/additem', item),

  updateItem: (item) =>
    api.put('/items/updateItem', item),

  deleteItem: (item) =>
    api.delete('/items/deleteItem', item),
}

export default api
