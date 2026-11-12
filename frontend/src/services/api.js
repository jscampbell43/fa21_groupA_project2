import axios from 'axios'

// Create an Axios instance configured for the backend
const api = axios.create({
  baseURL: '', // Will be proxied to http://localhost:8080 during development
  withCredentials: true // Send session cookies for Spring Security
})


// Auth API calls (removed - using manual authentication in UserController)
// export const authAPI = {
//   login: (username, password) =>
//     api.post('/auth/login', { username, password }),
//   getCurrentUser: () =>
//     api.get('/auth/me'),
//   logout: () =>
//     api.post('/auth/logout'),
// }

// User API calls
export const userAPI = {
  login: (username, password) =>
    api.post('/users/login', { username, password }),

  signup: (username, password, role) =>
    api.post('/users/signup', { username, password, role }),

  logout: () =>
    api.get('/users/logout'),

  findUserByName: (username) =>
    api.get(`/users/${username}`),

  getAllUsers: () =>
    api.get('/users/allUsers'),

  updateUser: (userId, username, password) =>
    api.put('/users/update', { userId: userId, username: username, password: password }),

  deleteUser: (userId, username, password) =>
    api.delete('/users/delete', {data: {userId: userId, password: password}}),
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
    api.delete('/items/deleteItem', {data: {id: item.id}}),
}

export default api
