# MindScribe Frontend

MindScribe is a modern journaling web application that allows users to securely write, reflect, and manage their personal journal entries. This is the frontend (React) part of the project, designed with a clean, responsive, and user-friendly interface.

## Features
- User authentication (signup, login) with JWT
- Create, view, and manage journal entries
- Admin dashboard to view all users
- Responsive design with modern Bootstrap styling
- Toast notifications and loading spinners for better UX
- Accessible and mobile-friendly

## Getting Started

### Prerequisites
- Node.js (v14 or higher recommended)
- npm (comes with Node.js)
- The MindScribe backend running (see backend README for setup)

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Avi1606/MindScribe.git
   cd MindScribe/frontend
   ```
2. Install dependencies:
   ```sh
   npm install
   ```

### Running the App
Start the development server:
```sh
npm start
```
The app will run at [http://localhost:3000](http://localhost:3000).

> **Note:** The frontend expects the backend API to be running at `http://localhost:8080`. You can change this in `src/services/api.js` if needed.

### Building for Production
```sh
npm run build
```
The optimized build will be in the `build/` folder.

## Project Structure
- `src/pages/` — Main pages (Login, Signup, Journal, Admin, Home)
- `src/components/` — Reusable UI components (NavigationBar, ProtectedRoute)
- `src/services/api.js` — Axios instance for API calls

## Customization
- Theming and styles use React Bootstrap. You can further customize the look by editing `src/index.css` or overriding Bootstrap variables.

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](../LICENSE)

---
This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).
