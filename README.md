# Synapse

A full-stack social platform with content sharing, real-time messaging, and AI assistance.

## Project Structure

```
synapse/
├── backend/          # Spring Boot backend (Java 17+)
├── frontend/         # React frontend (TypeScript + Vite)
└── README.md
```

## Tech Stack

### Backend
- Java 17+
- Spring Boot 3.2
- Spring Security with JWT
- Spring Data JPA
- H2 Database (in-memory)
- WebSockets (coming soon)

### Frontend
- React 18 with TypeScript
- Vite
- React Router
- TanStack Query
- Zustand (state management)
- React Hook Form + Zod

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 20 or higher
- Maven 3.8+

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

The backend will start at http://localhost:8080

- API Docs: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:synapsedb`)

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend will start at http://localhost:5173

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login with username/email and password
- `POST /api/auth/refresh` - Refresh access token
- `POST /api/auth/logout` - Logout (invalidate refresh token)
- `GET /api/auth/me` - Get current user info

### Admin (requires ADMIN role)
- `GET /api/admin/users` - Get all users
- `GET /api/admin/users/{id}` - Get user by ID
- `POST /api/admin/users/{id}/promote-creator` - Promote user to CREATOR
- `POST /api/admin/users/{id}/promote-admin` - Promote user to ADMIN
- `DELETE /api/admin/users/{id}/roles/{role}` - Remove role from user

### Users
- `GET /api/users/{username}` - Get user profile by username

## Roles
- **USER**: Default role for all registered users
- **CREATOR**: Can upload and manage content (Phase 2)
- **ADMIN**: Full access to admin APIs

## Development Phases

- [x] **Phase 1**: Foundation (Auth, Roles, JWT, Secure APIs)
- [ ] **Phase 2**: Social Content (Video uploads, likes, comments, feed)
- [ ] **Phase 3**: Messaging (WebSocket chat, encryption)
- [ ] **Phase 4**: AI Assistant (Content help, recommendations)
- [ ] **Phase 5**: Polish & Deployment
