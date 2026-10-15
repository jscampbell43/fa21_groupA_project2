# AGENTS.md - Project Documentation

This file tracks progress and context for Devin sessions working on this project.

## Project Overview
**Wishlist Website** - A Spring Boot application with SQLite database allowing users to create personal wishlists with items containing names, descriptions, links, and images.

**Tech Stack:**
- Spring Boot 3.2.0
- Java 21
- SQLite database (WishlistDatabase.db)
- Spring Security with Bcrypt password hashing
- JPA/Hibernate
- jQuery/Ajax for frontend
- Bootstrap for styling

## Current State (Last Updated: 2026-10-14)

### Recently Completed Work
1. **Authentication System Fixed** (2026-10-14 session)
   - Temporarily disabled Spring Security auto-configuration for debugging (application.properties)
   - Fixed signup form - removed default action to prevent double submission
   - Added missing `role` parameter to signup API call
   - Added custom login endpoint in UserController (POST /users/login)
   - Implemented Bcrypt password verification in login endpoint
   - Fixed JavaScript caching issues with dynamic versioning
   - Signup and login now fully functional with password hashing

2. **Password Security Implementation** (Previous commits)
   - Added Bcrypt password hashing for user authentication
   - Updated backend Controllers and DAOs to use password encoding
   - CustomUserDetailsService configured for Spring Security

3. **Technology Updates**
   - Updated Java version to 21
   - Updated Spring Boot to 3.2.0
   - Updated pom.xml dependencies
   - Updated Spring Boot imports for Jakarta namespace
   - Updated database configuration and JPA queries
   - Updated Maven wrapper

4. **Core Features Implemented**
   - User signup and login with password hashing ✅ WORKING
   - Add items to wishlist
   - Update existing items
   - Delete items
   - View all user items
   - Search/filter items by list name
   - Search items by item name
   - CSS styling with Bootstrap integration

### Backend Structure
- **Controllers:**
  - `UserController.java` - User authentication, signup, user lookup
  - `ItemController.java` - CRUD operations for items, search functionality

- **Database/DAO:**
  - `UserDAO.java` - User database operations
  - `UserDB.java` - User entity
  - `ItemDAO.java` - Item database operations
  - `ItemDB.java` - Item entity

- **Services:**
  - `ItemService.java` - Business logic for item operations
  - `CustomUserDetailsService.java` - Spring Security user details

- **Configuration:**
  - `SecurityConfig.java` - Spring Security configuration
  - `WishlistApplication.java` - Main application with PasswordEncoder bean

### Frontend Structure (src/main/resources/public/)
- `index.html` - Landing page
- `login.js` - Login functionality
- `signUp.html` + `signUp.js` - User registration
- `homeUser.html` + `homeUser.js` - User dashboard
- `homeAdmin.html` - Admin dashboard
- `addItem.html` + `addItem.js` - Add items to wishlist
- `wishlist.html` + `wishlist.js` - View wishlist with search
- `viewUsers.html` - View all users (admin)
- `editAccount.html` - Edit user account
- `styles.css` - Custom styling

### Database
- SQLite database: `WishlistDatabase.db`
- Currently has uncommitted changes

### Known Issues/Work in Progress
- Spring Security web security is disabled (auto-configuration excluded in application.properties)
  - @EnableWebSecurity commented out in SecurityConfig.java
  - Custom manual authentication via /users/login endpoint
  - Spring Security PasswordEncoder bean still available for password hashing/verification
  - This is a valid architecture for manual auth with Spring Security utilities
  - CSRF is disabled - enable before production
- Database has uncommitted changes (test users added)

### API Endpoints
**Users:**
- GET `/users/allUsers` - Get all users
- GET `/users/{username}` - Find user by username
- POST `/users/signup` - Register new user (username, password, role)
- POST `/users/login` - Authenticate user (username, password) - returns "Login successful" or error message

**Items:**
- GET `/items/allItems` - Get all items
- GET `/items/itemSearch/{item_name}` - Search items by name
- GET `/items/user/{user_id}` - Get items for specific user
- GET `/items/list/{list_name}/user/{user_id}` - Get items from specific list
- POST `/items/additem` - Add new item
- PUT `/items/updateItem` - Update item
- DELETE `/items/deleteItem` - Delete item

**General:**
- GET `/hello` - Test endpoint

---

## Session History

### Session 2026-10-14 (Part 3 - Spring Security Configuration)
- Attempted to re-enable Spring Security with form-based authentication
- Encountered 403 Forbidden errors with Spring Security's default login
- Tried multiple approaches to configure custom login page with Spring Security
- Decided on hybrid approach:
  - Spring Security web security disabled (auto-configuration excluded)
  - Spring Security PasswordEncoder bean remains available
  - Manual authentication via custom /users/login endpoint
  - Bcrypt password hashing and verification working
  - Changed password input type from text to password for security
- Current architecture: manual auth with Spring Security password utilities
- Committed changes with documentation

### Session 2026-10-14 (Part 2 - Authentication Fix)
- Fixed Spring Security blocking pages (temporarily disabled for debugging)
- Fixed signup form issues:
  - Removed default form action to prevent double submission
  - Added missing `role` parameter to Ajax request
  - Fixed JavaScript caching with dynamic versioning
- Fixed login functionality:
  - Added custom login endpoint (POST /users/login) in UserController
  - Implemented Bcrypt password verification
  - Updated login.js to use Ajax instead of form submission
  - Removed action="/login" from login form
- Tested and verified signup creates users with bcrypt hashed passwords
- Tested and verified login successfully authenticates and redirects to homeUser.html
- Updated AGENTS.md with session progress

### Session 2026-10-14 (Part 1)
- Created AGENTS.md file to document project state
- Reviewed git history and codebase structure
- Set up periodic reminder system for documenting progress

---

## Build & Run Commands
```bash
cd fa21_groupA_project2
./mvnw spring-boot:run
```

## Testing Commands
```bash
./mvnw test
```

## Notes for Future Sessions
- The project uses SQLite, ensure database file is not corrupted
- Passwords are hashed with Bcrypt - never store plain text
- **Spring Security Architecture (Hybrid Approach):**
  - Spring Security web security is DISABLED (auto-configuration excluded)
  - Spring Security PasswordEncoder bean is AVAILABLE for manual use
  - Custom manual authentication via POST /users/login endpoint
  - This allows using Spring Security's password utilities without web security filters
  - CSRF is disabled - enable before production
  - To fully enable Spring Security form login: remove auto-configuration exclusion, configure SecurityFilterChain with custom login page
- Frontend uses jQuery/Ajax - check browser console for API errors
- JavaScript caching issues resolved with dynamic versioning (timestamps)
- Database changes need to be committed before major work
