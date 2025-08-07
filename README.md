# MindScribe

MindScribe is a modern, full-stack journaling web application that empowers users to securely write, reflect, and manage their personal journal entries. The project features a robust Spring Boot backend and a beautiful, responsive React frontend.

---

## Features
- **User Authentication:** Secure signup and login with JWT
- **Personal Journaling:** Create, view, and manage your own journal entries
- **Admin Dashboard:** Admins can view all users and manage the app
- **Responsive UI:** Modern, mobile-friendly design using React Bootstrap
- **Notifications:** Toasts and spinners for feedback and loading states
- **Accessibility:** Semantic HTML and accessible components

---

## Tech Stack
- **Frontend:** React, React Bootstrap, Axios
- **Backend:** Spring Boot, Spring Security (JWT), MongoDB
- **Deployment:** Heroku (example), or any cloud/server

---

## Getting Started

### Prerequisites
- **Node.js** (v14 or higher)
- **npm** (comes with Node.js)
- **Java 17+** (for Spring Boot backend)
- **MongoDB** (local or cloud instance)

---

## Setup Instructions

### 1. Clone the Repository
```sh
git clone https://github.com/Avi1606/MindScribe.git
cd MindScribe
```

### 2. Backend Setup (Spring Boot)
- Go to the backend directory (usually the root or `src/main/java/...`)
- Configure MongoDB connection in `src/main/resources/application-dev.yml` or `application-prod.yml`
- Build and run the backend:
  ```sh
  ./mvnw spring-boot:run
  # or
  mvn spring-boot:run
  ```
- The backend will run at `http://localhost:8080` by default.

### 3. Frontend Setup (React)
- Go to the frontend directory:
  ```sh
  cd frontend
  npm install
  npm start
  ```
- The frontend will run at [http://localhost:3000](http://localhost:3000)
- The frontend expects the backend API at `http://localhost:8080` (change in `src/services/api.js` if needed)

---

## Usage
- **Sign Up:** Create a new account
- **Login:** Access your journal
- **Journal:** Add, view, and manage your entries
- **Admin:** (If you have admin role) view all users

---

## Project Structure
- `frontend/` — React app (UI)
- `src/main/java/com/myproject/journalApp/` — Spring Boot backend (API, services, security)
- `src/main/resources/` — Backend configuration files

---

## Deployment
- You can deploy the backend to Heroku, AWS, or any Java-supporting server
- The frontend can be deployed to Vercel, Netlify, or served by the backend

---

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## License
[MIT](LICENSE)

---

**Made with ❤️ by Avi1606 and contributors.**
