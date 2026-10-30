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

## Current State (Last Updated: 2026-10-21)

### Recently Completed Work
1. **Frontend Migration to React + Vite** (2026-10-21 session)
   - Created React frontend with Vite build system in `frontend/` directory
   - Installed modern dependencies: React 19.3.1, React Router 7.18.4, Axios, Tailwind CSS 3.4.19
   - Configured Vite dev server with proxy to Spring Boot backend
   - Fixed Tailwind CSS compatibility (downgraded from v4 to v3.4.19)
   - Fixed API data format: backend uses @RequestParam (form-encoded), not JSON
   - Created all React components: Login, Signup, Home, Wishlist, AddItem, EditAccount
   - Created shared Navigation component for consistent UI
   - All components use modern React patterns (hooks, functional components)
   - All components styled with Tailwind CSS
   - Backend remains unchanged (Spring Boot + SQLite + manual authentication)
   - Authentication flow working: signup → login → access protected pages → logout

2. **Authentication System Fixed** (2026-10-14 session)
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
   - Add items to wishlist ✅ WORKING (React frontend)
   - Update existing items ✅ WORKING (legacy HTML)
   - Delete items ✅ WORKING (legacy HTML)
   - View all user items ✅ WORKING (React frontend)
   - Search/filter items by list name ✅ WORKING (React frontend)
   - Search items by item name ✅ WORKING (legacy HTML)
   - CSS styling with Tailwind CSS ✅ NEW (React frontend)
   - CSS styling with Bootstrap ✅ LEGACY (static HTML - being replaced)

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

### Frontend Structure
**React Frontend (NEW - 2026-10-21):**
- Location: `frontend/` directory
- Build tool: Vite
- Framework: React 19.3.1
- Routing: React Router 7.18.4
- HTTP Client: Axios
- Styling: Tailwind CSS 3.4.19
- Pages:
  - `Login.jsx` - Login page with form validation
  - `Signup.jsx` - Signup page with password confirmation
  - `Home.jsx` - User dashboard
  - `Wishlist.jsx` - View wishlist items with search
  - `AddItem.jsx` - Add new item to wishlist
  - `EditAccount.jsx` - Edit user account
- Components:
  - `Navigation.jsx` - Shared navigation bar
- Services:
  - `api.js` - Axios API service for backend communication
- Dev server: http://localhost:5173 (proxies API calls to http://localhost:8080)

**Legacy Frontend (OLD - being replaced):**
- Location: `src/main/resources/public/`
- jQuery/Ajax for frontend
- Bootstrap for styling
- Static HTML files:
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
- **Authentication State Management** (COMPLETED - 2026-10-22)
  - ✅ Implemented React Context for authentication state management
  - ✅ AuthContext stores user object with username and userId
  - ✅ Proper user ID retrieval from backend on login
  - ✅ Auth state persists across page refreshes via sessionStorage
  - ⚠️ Still needs: Protected routes in React (route guards)
  - Affects: All protected pages now use auth context instead of sessionStorage placeholder
- **Password Matching Validation** (FRONTEND-ONLY - 2026-10-29)
  - ✅ Password matching validation is implemented in Signup.jsx (client-side)
  - ⚠️ Backend does NOT validate password matching (UX concern, not security)
  - Note: Backend validation prevents empty passwords and duplicates
  - Future enhancement: Add `passwordAgain` field to SignupRequest DTO and manual validation in UserController
  - Priority: Low - Frontend validation is sufficient for current scope

### Future Functionality (To Implement After Modernization Complete)
These features should be added after completing all modernization phases (Phases 1-5).

**1. Edit Account Page Completion**
- Current: EditAccount.jsx has UI but backend endpoints are not implemented
- Needed:
  - Backend endpoint: PUT `/users/update` - Update user username and/or password
  - Backend endpoint: DELETE `/users/delete` - Delete user account
  - Frontend: Wire up update and delete buttons to backend endpoints
  - Frontend: Add proper validation and confirmation dialogs
- Note: Should be implemented during Phase 2 (Backend API Polish) or after

**2. Complete CRUD Operations for Items**
- Current: Can add items, but update and delete exist on backend but not fully integrated
- Needed:
  - **Update Items:**
    - Frontend: Edit button on each item in Wishlist.jsx
    - Frontend: Pre-fill AddItem form with existing item data
    - Frontend: Or create dedicated EditItem component
    - Backend: Ensure PUT `/items/updateItem` works correctly
  - **Update List Names:**
    - Frontend: Ability to rename list names
    - Backend: Endpoint to update list_name for multiple items at once
  - **Delete Items:**
    - Frontend: Delete button on each item in Wishlist.jsx
    - Frontend: Confirmation dialog before deletion
    - Backend: Ensure DELETE `/items/deleteItem` works correctly
  - **Delete Entire Lists:**
    - Frontend: Delete button on list view
    - Frontend: Confirmation dialog before deletion
    - Backend: Endpoint to delete all items with a specific list_name
- Note: Should be implemented after Phase 2 (Backend API Polish)

**3. Complete CRUD Operations for Users**
- Current: Skeleton in place but not fully functional
- Needed:
  - **Update User:**
    - Frontend: EditAccount.jsx already has UI
    - Backend: PUT `/users/update` endpoint
  - **Delete User:**
    - Frontend: EditAccount.jsx already has UI
    - Backend: DELETE `/users/delete` endpoint
    - Note: Should also delete all associated items when user is deleted
- Note: Should be implemented during Phase 2 (Backend API Polish)

**Backend Technical Debt (TODO):**
- **ItemDAO.addItem() Refactoring:**
  - Current: Uses native SQL INSERT that doesn't return the auto-generated ID
  - Issue: addItem() in ItemController returns ID=0 as placeholder in ItemResponse
  - Solution: Refactor ItemDAO to return generated ID using one of:
    - @Query with RETURNING clause (SQLite-specific)
    - Use JPA save() method instead of native SQL
    - Add a separate query to fetch the last inserted ID
  - Priority: Medium - affects ItemController.addItem() response quality

**4. Admin User Flows**
- Current: No admin-specific functionality
- Needed:
  - **Admin Role System:**
    - Backend: Use existing role field in UserDB (USER vs ADMIN)
    - Backend: Role-based access control in controllers
    - Frontend: Conditional UI based on user role
  - **Admin User Management:**
    - Frontend: Admin-only page to view all users
    - Frontend: List view with update and delete buttons for each user
    - Backend: Existing GET `/users/allUsers` endpoint (already available)
    - Backend: Update/delete user endpoints (see item 3 above)
  - **Admin Item Management:**
    - Frontend: Admin-only page to view all items
    - Frontend: List view with update and delete buttons for each item
    - Backend: Existing GET `/items/allItems` endpoint (already available)
    - Backend: Update/delete item endpoints (see item 2 above)
  - **Admin Dashboard:**
    - Frontend: Dedicated admin dashboard page
    - Frontend: Links to user management and item management
    - Frontend: Overview statistics (total users, total items, etc.)
- Note: Should be implemented after Phase 3 (Full Spring Security) to ensure proper authorization

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

### Session 2026-10-27 (Phase 2: Backend API Polish - Step 3 Complete)
- Completed ItemController updates to use DTOs and JSON
- Fixed compilation errors in ItemController (removed old validation imports)
- Fixed validation annotations in UserDB and ItemDB entities
- Created UpdateItemRequest.java DTO for update operations
  - Added validation annotations (removed @NotBlank from int id)
  - Added @NotBlank to name and listName
- Created DeleteItemRequest.java DTO for delete operations
  - Simple DTO with just id field and @Positive validation
- Updated ItemController methods:
  - getItemsByList() - changed to return List<ItemResponse> with HTTP 200
  - addItem() - changed to use @RequestBody AddItemRequest, returns HTTP 201
    - Returns ID=0 as placeholder (documented as technical debt)
    - Fixed DAO parameter order and field mappings
  - updateItem() - changed to use @RequestBody UpdateItemRequest, returns HTTP 200
    - Added item existence check (throws ItemNotFoundException)
    - Reconstructs ItemDB from DTO (sets ID separately due to constructor limitations)
    - Fixed constructor parameter mismatch and variable naming issues
  - deleteItem() - changed to use @RequestBody DeleteItemRequest, returns HTTP 200
    - Added item existence check (throws ItemNotFoundException)
    - Returns Map<String, String> with success message
- **ItemController now 100% complete** - all 6 methods updated to use DTOs and JSON
- Backend Phase 2 Step 3 (REST API Design) backend portion complete
- Next steps: Update frontend API service and components to use JSON

### Session 2026-10-23 (Phase 2: Backend API Polish - Steps 1, 2, & Partial 3)
- Started Phase 2: Backend API Polish
- **Step 1: Bean Validation** - COMPLETED
  - Added `spring-boot-starter-validation` dependency to pom.xml
  - Added validation annotations to UserDB entity (@NotBlank on username, password)
  - Added validation annotations to ItemDB entity (@NotBlank on name, list_name)
  - Added `@Validated` annotation to UserController class (Spring annotation)
  - Added `@Validated` annotation to ItemController class
  - Added `@NotBlank` annotations to UserController signup/login methods
  - Added `@NotBlank` annotations to ItemController addItem method
  - Fixed import issue: @Validated is from Spring (org.springframework.validation.annotation.Validated)
- **Step 2: Global Exception Handler** - COMPLETED
  - Created GlobalExceptionHandler.java in exceptions package
  - Implemented handler for ConstraintViolationException (validation errors from @RequestParam)
  - Implemented handler for MethodArgumentNotValidException (validation errors from @RequestBody - for future use)
  - Implemented handler for generic Exception
  - Implemented handler for UserNotFoundException (returns HTTP 404)
  - Implemented handler for ItemNotFoundException (returns HTTP 404)
  - Returns structured JSON error responses with status code, message, and errors object
  - Added "[GlobalExceptionHandler]" prefix to error messages for identification
  - Returns HTTP 400 Bad Request for validation errors
  - Returns HTTP 500 Internal Server Error for generic exceptions
  - Returns HTTP 404 Not Found for custom exceptions
- Tested validation by temporarily disabling frontend validation
- Verified GlobalExceptionHandler returns proper JSON error responses
- Restored frontend validation after testing
- Backend and frontend validation both working correctly
- **Step 3: REST API Design** - IN PROGRESS
  - Added Lombok dependency to pom.xml (version 1.18.30)
  - Created DTO classes:
    - UserResponse.java - user response DTO
    - ItemResponse.java - item response DTO
    - ErrorResponse.java - error response DTO
    - SignupRequest.java - signup request DTO with validation
    - LoginRequest.java - login request DTO with validation
    - AddItemRequest.java - add item request DTO with validation
  - Created custom exception classes:
    - UserNotFoundException.java - thrown when user not found
    - ItemNotFoundException.java - thrown when item not found
  - Updated GlobalExceptionHandler with handlers for custom exceptions
  - Updated UserController:
    - Changed from @RequestParam to @RequestBody with DTOs
    - Changed return types to ResponseEntity with DTOs
    - getAllUsers() - returns List<UserResponse> with HTTP 200
    - findUserByName() - throws UserNotFoundException, returns UserResponse with HTTP 200
    - insertNewUser() - uses SignupRequest, returns HTTP 201 on success, HTTP 400 on duplicate
    - loginUser() - uses LoginRequest, returns HTTP 200 on success, HTTP 401 on invalid password, throws UserNotFoundException
    - logoutUser() - returns JSON response with HTTP 200
    - Removed @Validated from class level
    - Removed @ResponseBody from methods
  - Partially updated ItemController:
    - Added imports for DTOs, exceptions, ResponseEntity
    - Removed @Validated from class level
    - Removed duplicate imports
    - Added AddItemRequest, UpdateItemRequest, DeleteItemRequest and ItemNotFoundException imports
    - getAllItems() - returns List<ItemResponse> with HTTP 200
    - getitemByUser() - returns List<ItemResponse> with HTTP 200
    - getItemsByList() - returns List<ItemResponse> with HTTP 200
    - addItem() - uses @RequestBody AddItemRequest, returns ResponseEntity<ItemResponse> with HTTP 201
      - Returns ID=0 as placeholder (ItemDAO doesn't return generated ID)
      - Validation errors handled automatically by @Valid and GlobalExceptionHandler
    - updateItem() - uses @RequestBody UpdateItemRequest, returns ResponseEntity<ItemResponse> with HTTP 200
      - Checks if item exists, throws ItemNotFoundException if not found
      - Reconstructs ItemDB from DTO, sets ID separately
      - Validation errors handled automatically by @Valid and GlobalExceptionHandler
    - deleteItem() - uses @RequestBody DeleteItemRequest, returns ResponseEntity<Map<String, String>> with HTTP 200
      - Checks if item exists, throws ItemNotFoundException if not found
      - Returns success message in Map
      - Validation errors handled automatically by @Valid and GlobalExceptionHandler
  - Backend and frontend still use different data formats (backend now JSON, frontend still form-encoded)
  - Frontend API service needs to be updated to send JSON instead of form-encoded
  - Frontend components need to handle JSON error responses

### Session 2026-10-29 (Phase 2 Frontend Migration & Additional Error Handling)
- **Phase 2 Frontend Migration - COMPLETED**
  - Updated frontend/src/services/api.js to send JSON instead of form-encoded
  - Removed toFormData() helper function
  - Removed Content-Type headers (Axios handles automatically)
  - Updated AuthContext.jsx to handle JSON responses from backend
    - login() now checks for response.data.userId instead of plain text
    - signup() now checks for response.data.userId instead of plain text
    - Both functions return { success: true, data: response.data } on success
    - Both functions return { success: false, response } on error
  - Updated Signup.jsx to handle JSON error responses
    - Checks result.success first
    - On error: parses response.data.errors and response.data.message
    - Handles duplicate username errors from GlobalExceptionHandler
  - Updated Login.jsx to handle JSON error responses
    - Checks result.success first
    - On error: parses response.data.errors and response.data.message
    - Handles 401 status for invalid password
    - Added success state and message display
  - Updated AddItem.jsx to handle JSON error responses
    - Fixed field names: userId, description, listName (was id, desc, list)
    - Added defensive response validation
    - On error: parses response.data.errors and response.data.message
  - Updated Wishlist.jsx to handle JSON error responses
    - Fixed field names: link, image, listName (was item_link, img_url, list_name)
    - Updated loadItems() error handling
    - Updated handleSearch() error handling
    - Removed fallback user ID (now requires proper auth)
  - Added setId() method to ItemDB.java (needed for updateItem operations)
  - All frontend components now properly handle JSON responses from GlobalExceptionHandler

- **Additional Error Handling Improvements**
  - Created DuplicateUsernameException.java for duplicate username errors
  - Created PasswordNotFoundException.java for invalid password errors
  - Updated GlobalExceptionHandler.java with handlers for new exceptions
    - handleDuplicateUsernameException() - returns HTTP 400 with specific error message
    - handlePasswordNotFoundException() - returns HTTP 401 with specific error message
  - Updated UserController.java to throw DuplicateUsernameException for duplicate usernames
  - Updated UserController.java to throw PasswordNotFoundException for invalid passwords
  - Added password verification using passwordEncoder.matches() in login method
  - Removed "[GlobalExceptionHandler]" prefix from UserNotFoundException handler (cleaner messages)
  - Fixed semicolon issue in ItemController.java
  - Backend compilation successful after all changes

- **Testing**
  - Tested complete signup flow with backend validation errors
  - Tested duplicate username error (now returns specific message)
  - Tested login flow with invalid password (now returns 401 status)
  - Tested add item flow with JSON requests
  - Tested wishlist loading with correct field names
  - Verified sessionStorage contains user and userId after login
  - All Phase 2 tests passing

### Session 2026-10-29 (Swagger/OpenAPI Documentation)
- **Phase 2 Step 4: Add OpenAPI/Swagger Documentation - COMPLETED**
  - Added SpringDoc OpenAPI dependency to pom.xml (version 2.3.0)
  - Added Swagger configuration to application.properties
    - Swagger UI path: /swagger-ui.html
    - API docs path: /api-docs
    - Custom API info (title, description, version, contact)
  - Backend restarted successfully with Swagger enabled
  - Swagger UI accessible at http://localhost:8080/swagger-ui.html
  - All API endpoints automatically documented
  - Request/response models (DTOs) automatically documented
  - Interactive testing interface available
  - No code changes needed to controllers or DTOs for basic documentation

- **Future Swagger Enhancements (Optional)**
  - Add @Tag annotations to controllers for better organization
  - Add @Operation annotations to endpoints for detailed descriptions
  - Add @Schema annotations to DTOs for field descriptions
  - Add @Parameter annotations for parameter descriptions
  - Add @ApiResponse annotations for response documentation
  - Add example request/response examples
  - Add API security documentation (when Spring Security is re-enabled)
  - Add grouping and sorting of endpoints
  - Add external documentation links

### Session 2026-10-22 (Authentication Context & Testing)
- Started Phase 2: Backend API Polish
- **Step 1: Bean Validation**
  - Added `spring-boot-starter-validation` dependency to pom.xml
  - Added validation annotations to UserDB entity (@NotBlank on username, password)
  - Added validation annotations to ItemDB entity (@NotBlank on name, list_name)
  - Added `@Validated` annotation to UserController class (Spring annotation)
  - Added `@Validated` annotation to ItemController class
  - Added `@NotBlank` annotations to UserController signup/login methods
  - Added `@NotBlank` annotations to ItemController addItem method
  - Fixed import issue: @Validated is from Spring (org.springframework.validation.annotation.Validated)
- **Step 2: Global Exception Handler**
  - Created GlobalExceptionHandler.java in exceptions package
  - Implemented handler for ConstraintViolationException (validation errors from @RequestParam)
  - Implemented handler for MethodArgumentNotValidException (validation errors from @RequestBody - for future use)
  - Implemented handler for generic Exception
  - Returns structured JSON error responses with status code, message, and errors object
  - Added "[GlobalExceptionHandler]" prefix to error messages for identification
  - Returns HTTP 400 Bad Request for validation errors
  - Returns HTTP 500 Internal Server Error for generic exceptions
- Tested validation by temporarily disabling frontend validation
- Verified GlobalExceptionHandler returns proper JSON error responses
- Restored frontend validation after testing
- Backend and frontend validation both working correctly

### Session 2026-10-22 (Authentication Context & Testing)
- Implemented proper authentication state management using React Context
- Created AuthContext.jsx with user object storing {username, userId}
- Updated AuthContext to fetch user details from backend on login to get numeric userId
- Wrapped entire app with AuthProvider in App.jsx
- Updated Login.jsx to use auth context login function
- Updated Signup.jsx to use auth context signup function
- Updated Home.jsx to display username from auth context
- Updated AddItem.jsx to use userId from auth context
- Updated Wishlist.jsx to use userId from auth context
- Fixed Wishlist.jsx to wait for auth context loading before fetching items
- Fixed loading states in Signup.jsx to keep button disabled during redirect
- Removed manual sessionStorage handling from components (now managed by auth context)
- Tested all authentication flows: signup, login, home, wishlist, add item
- Verified user-specific data loading (items for correct user)
- Documented future functionality requirements (Edit Account, CRUD operations, Admin flows)

### Session 2026-10-21 (Frontend Migration to React)
- Created React frontend with Vite build system in `frontend/` directory
- Installed dependencies: React, React Router, Axios, Tailwind CSS v3, PostCSS, Autoprefixer
- Configured Vite dev server on port 5173 with proxy to Spring Backend (port 8080)
- Fixed Tailwind CSS v4 compatibility issue by downgrading to v3.4.19
- Fixed API data format issue: backend uses @RequestParam (form-encoded), not JSON
  - Updated API service to send form-encoded data instead of JSON
  - Added Content-Type: application/x-www-form-urlencoded header
- Created React components:
  - Login.jsx - login form with validation, error handling, redirects to /home
  - Signup.jsx - signup form with password confirmation, validation, auto-redirect
  - Home.jsx - user dashboard with navigation and logout
  - Wishlist.jsx - displays items, search by list name, loading/error states
  - AddItem.jsx - add item form with validation, success message
  - EditAccount.jsx - edit account form (update/delete marked as needs backend implementation)
  - Navigation.jsx - shared navigation component with active page highlighting
- All components use modern React patterns: useState, useEffect, useNavigate
- All components styled with Tailwind CSS
- Backend remains unchanged (Spring Boot + SQLite + manual authentication)
- Frontend and backend running in parallel: React on 5173, Spring Boot on 8080
- Authentication flow working: signup → login → access protected pages → logout

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

### Session 2026-10-21 (Frontend Migration to React)
- Created React frontend with Vite build system in `frontend/` directory
- Installed dependencies: React, React Router, Axios, Tailwind CSS v3, PostCSS, Autoprefixer
- Configured Vite dev server on port 5173 with proxy to Spring Backend (port 8080)
- Fixed Tailwind CSS v4 compatibility issue by downgrading to v3.4.19
- Fixed API data format issue: backend uses @RequestParam (form-encoded), not JSON
  - Updated API service to send form-encoded data instead of JSON
  - Added Content-Type: application/x-www-form-urlencoded header
- Created React components:
  - Login.jsx - login form with validation, error handling, redirects to /home
  - Signup.jsx - signup form with password confirmation, validation, auto-redirect
  - Home.jsx - user dashboard with navigation and logout
  - Wishlist.jsx - displays items, search by list name, loading/error states
  - AddItem.jsx - add item form with validation, success message
  - EditAccount.jsx - edit account form (update/delete marked as needs backend implementation)
  - Navigation.jsx - shared navigation component with active page highlighting
- All components use modern React patterns: useState, useEffect, useNavigate
- All components styled with Tailwind CSS
- Backend remains unchanged (Spring Boot + SQLite + manual authentication)
- Frontend and backend running in parallel: React on 5173, Spring Boot on 8080
- Authentication flow working: signup → login → access protected pages → logout

---

## Build & Run Commands
**Backend (Spring Boot):**
```bash
cd fa21_groupA_project2
./mvnw spring-boot:run
```

**Frontend (React + Vite):**
```bash
cd fa21_groupA_project2/frontend
npm run dev          # Development server with hot reload
npm run build        # Production build
npm run preview      # Preview production build
```

**For Development (both running):**
- Terminal 1: Run Spring Boot backend on port 8080
- Terminal 2: Run React frontend on port 5173
- React proxies API calls to Spring Boot backend

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
- **Frontend Migration (React + Vite):**
  - New React frontend in `frontend/` directory replacing jQuery/static HTML
  - React dev server runs on port 5173, proxies API calls to Spring Boot on port 8080
  - Uses form-encoded data for API calls (backend uses @RequestParam)
  - Tailwind CSS v3.4.19 configured with PostCSS
  - React Router for client-side routing
  - User ID currently stored in sessionStorage as placeholder (needs proper auth state)
  - Legacy static HTML files in `src/main/resources/public/` still exist but not used by React app
- **API Data Format:**
  - Backend endpoints use @RequestParam (form-encoded data)
  - Frontend Axios service sends form-encoded data (application/x-www-form-urlencoded)
  - Do NOT send JSON - it will result in 400 Bad Request errors
- **Known Limitations:**
  - EditAccount update/delete functionality needs backend endpoints to be implemented
  - User authentication state needs proper implementation (currently using sessionStorage placeholder)
  - EditAccount update/delete buttons show message "needs backend implementation"

---

## Modernization Plan (2026-10-21)

### Overview
The application needs modernization to align with current best practices. The backend is mostly modern (Spring Boot 3.2.0, Java 21) but the frontend needs significant updates.

### Frontend Modernization (High Priority)
1. **Replace jQuery with Modern JavaScript Framework**
   - Current: jQuery + static HTML files
   - Modern: React, Vue, Angular, or Svelte
   - Recommendation: React (most popular, great ecosystem)

2. **Modern Build System**
   - Current: No build process, static files served directly
   - Modern: Vite (recommended), Webpack, or Next.js
   - Benefits: Hot module replacement, minification, tree-shaking, modern JavaScript/TypeScript support

3. **Modern CSS Framework**
   - Current: Bootstrap 4.3.1 + custom CSS
   - Modern: Tailwind CSS, CSS Modules, or Styled Components
   - Benefits: Utility-first CSS, scoped styles, better maintainability

4. **Client-Side Routing**
   - Current: Page navigation via HTML links
   - Modern: React Router, Vue Router, etc.
   - Benefits: Single Page Application (SPA) experience, better performance, better UX

5. **Modern HTTP Client**
   - Current: jQuery Ajax
   - Modern: Fetch API or Axios
   - Benefits: Promise-based, interceptors, better error handling

6. **Modern JavaScript**
   - Current: ES5-style code
   - Modern: ES6+ (arrow functions, async/await, destructuring, modules)
   - Benefits: Cleaner code, better performance, modern syntax

7. **TypeScript**
   - Current: Plain JavaScript
   - Modern: TypeScript
   - Benefits: Type safety, better IDE support, catch errors at compile time

8. **Component Architecture**
   - Current: Monolithic HTML files
   - Modern: Reusable components (LoginForm, SignupForm, ItemCard, etc.)
   - Benefits: Reusability, maintainability, testability

### Backend Modernization (Medium Priority)
1. **Proper REST API Design**
   - Current: Mixed REST endpoints, some inconsistent patterns
   - Modern: Proper RESTful API with proper HTTP methods, status codes, and resource naming

2. **API Documentation**
   - Current: No API documentation
   - Modern: OpenAPI/Swagger documentation

3. **Spring Security Full Implementation**
   - Current: Hybrid approach with manual authentication
   - Modern: Full Spring Security with proper form login, CSRF, session management

4. **Validation**
   - Current: Manual validation in JavaScript
   - Modern: Bean Validation (JSR-380) with @Valid annotations

5. **Error Handling**
   - Current: Basic try-catch in some places
   - Modern: Global exception handler with @ControllerAdvice

6. **Database Migrations**
   - Current: Hibernate auto-DDL (`hibernate.ddl-auto=update`)
   - Modern: Flyway or Liquibase migrations

7. **Configuration Management**
   - Current: Hardcoded values in application.properties
   - Modern: Environment variables, Spring Profiles, Spring Cloud Config

8. **Logging**
   - Current: System.out.println for debugging
   - Modern: SLF4J with Logback or Log4j2

9. **Testing**
   - Current: No automated tests
   - Modern: JUnit 5, Mockito, Spring Boot Test, Integration tests

10. **Containerization**
    - Current: Running locally without containerization
    - Modern: Docker, Docker Compose

### DevOps & Deployment (Low Priority for Now)
1. **CI/CD Pipeline** - GitHub Actions, GitLab CI, Jenkins
2. **Monitoring & Observability** - Prometheus, Grafana, ELK stack
3. **Security Enhancements** - Enable CSRF, proper session management, rate limiting, HTTPS/TLS

### Recommended Phased Approach

#### Phase 1: Frontend Framework Migration ✅ COMPLETED (2026-10-21 & 2026-10-22)
1. ✅ Set up React with Vite
2. ✅ Create basic components for login, signup, home, wishlist
3. ✅ Integrate with existing backend API
4. ✅ Replace static HTML pages with React components
   - All pages now use React components with modern patterns
   - Tailwind CSS configured and working
   - API service with form-encoded data working
   - Client-side routing with React Router working
5. ✅ **Bonus:** Implemented authentication context with proper user state management
   - AuthContext stores user object with username and userId
   - All components use auth context instead of sessionStorage placeholders
   - User-specific data loading verified

#### Phase 2: Backend API Polish (IN PROGRESS - 2026-10-27)
1. ✅ Add Bean Validation
   - Added validation dependency to pom.xml
   - Added validation annotations to UserDB and ItemDB entities
   - Added @Validated to UserController and ItemController
   - Added @NotBlank annotations to request parameters
2. ✅ Add global exception handler
   - Created GlobalExceptionHandler.java in exceptions package
   - Handles ConstraintViolationException for @RequestParam validation
   - Handles MethodArgumentNotValidException for @RequestBody validation (future use)
   - Handles generic Exception
   - Handles UserNotFoundException (returns HTTP 404)
   - Handles ItemNotFoundException (returns HTTP 404)
   - Returns structured JSON error responses with status, message, and errors
   - Added "[GlobalExceptionHandler]" prefix for identification
3. ⏳ Implement proper REST API design (IN PROGRESS - Backend Complete, Frontend Pending)
   - ✅ Added Lombok dependency to pom.xml
   - ✅ Created DTOs: UserResponse, ItemResponse, ErrorResponse, SignupRequest, LoginRequest, AddItemRequest, UpdateItemRequest, DeleteItemRequest
   - ✅ Created custom exceptions: UserNotFoundException, ItemNotFoundException
   - ✅ Updated GlobalExceptionHandler with custom exception handlers
   - ✅ Updated UserController to use DTOs and JSON (all methods complete)
   - ✅ Updated ItemController to use DTOs and JSON (all 6 methods complete):
     - getAllItems() - returns List<ItemResponse> with HTTP 200
     - getitemByUser() - returns List<ItemResponse> with HTTP 200
     - getItemsByList() - returns List<ItemResponse> with HTTP 200
     - addItem() - uses AddItemRequest, returns HTTP 201
     - updateItem() - uses UpdateItemRequest, returns HTTP 200
     - deleteItem() - uses DeleteItemRequest, returns HTTP 200
   - ⏳ **FRONTEND UPDATES (pending):**
     - Update frontend API service to send JSON instead of form-encoded
     - Remove toFormData() helper function
     - Update Signup.jsx to handle JSON responses
     - Update Login.jsx to handle JSON responses
     - Update AddItem.jsx to handle JSON responses
     - Update Wishlist.jsx to handle JSON responses
     - Handle errors object from GlobalExceptionHandler
4. Add OpenAPI/Swagger documentation

#### Phase 3: Full Spring Security
1. Re-enable full Spring Security
2. Implement proper form login with CSRF
3. Add proper session management
4. Use template engine (Thymeleaf) or keep SPA approach

#### Phase 4: Testing & Quality
1. Add unit tests for controllers
2. Add integration tests
3. Add API tests

#### Phase 5: DevOps
1. Add Docker configuration
2. Set up CI/CD pipeline
3. Add monitoring

### Technology Recommendations
**Frontend Stack:** React + Vite + TypeScript + Tailwind CSS + React Router + Axios
**Backend Stack:** Keep Spring Boot (already modern), add SpringDoc OpenAPI, Bean Validation, Flyway migrations, proper logging

---

## 🎯 NEXT STEPS (Priority Order)

### Immediate - Complete Phase 2 Frontend Updates
1. **Update Frontend API Service** (`frontend/src/services/api.js`)
   - Remove `toFormData()` helper function
   - Remove `Content-Type: application/x-www-form-urlencoded` headers
   - Change all API calls to send JSON objects directly
   - Update methods: signup, login, addItem, updateItem, deleteItem, getItemByUser, getItemByList

2. **Update Frontend Components to Handle JSON Responses**
   - **Signup.jsx** - Parse JSON error responses from GlobalExceptionHandler
   - **Login.jsx** - Parse JSON error responses from GlobalExceptionHandler
   - **AddItem.jsx** - Parse JSON error responses from GlobalExceptionHandler
   - **Wishlist.jsx** - Handle JSON responses and errors object
   - Handle both successful responses and validation errors

3. **Test Complete Flow**
   - Test signup → login → add item → view items → update item → delete item
   - Verify frontend and backend are using JSON consistently
   - Verify validation errors display correctly

### After Phase 2 Complete
4. **Step 4: Add OpenAPI/Swagger Documentation**
   - Add SpringDoc OpenAPI dependency to pom.xml
   - Configure Swagger UI
   - Add API descriptions to controllers
   - Document request/response models
   - Test Swagger UI at `/swagger-ui.html`

### Phase 3: Full Spring Security
5. Re-enable full Spring Security
6. Implement proper form login with CSRF
7. Add proper session management
8. Decide: Thymeleaf or keep SPA approach

### Phase 4: Testing & Quality
9. Add unit tests for controllers
10. Add integration tests
11. Add API tests

### Phase 5: DevOps
12. Add Docker configuration
13. Set up CI/CD pipeline
14. Add monitoring

### Future Functionality (After Modernization Complete)
15. **Edit Account Page** - Backend endpoints for update/delete user
16. **Complete CRUD for Items** - Update/delete operations in frontend
17. **Complete CRUD for Users** - Update/delete operations in frontend
18. **Admin User Flows** - Role-based access control and admin dashboard
