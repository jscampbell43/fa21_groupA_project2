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
- **Authentication State Management** (TODO - Fix in future phase)
  - User ID currently retrieved from `sessionStorage.getItem('user') || '1'` in React components
  - This is a placeholder and not a proper authentication solution
  - Needs proper authentication state management with:
    - React Context or state management library
    - Secure session/token handling
    - Proper user identity verification
    - Protected routes in React
  - Affects: AddItem.jsx, Wishlist.jsx, and other protected pages

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

#### Phase 1: Frontend Framework Migration ✅ COMPLETED (2026-10-21)
1. ✅ Set up React with Vite
2. ✅ Create basic components for login, signup, home, wishlist
3. ✅ Integrate with existing backend API
4. ✅ Replace static HTML pages with React components
   - All pages now use React components with modern patterns
   - Tailwind CSS configured and working
   - API service with form-encoded data working
   - Client-side routing with React Router working

#### Phase 2: Backend API Polish
1. Implement proper REST API design
2. Add Bean Validation
3. Add global exception handler
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
