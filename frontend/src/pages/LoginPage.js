import React, { useState } from 'react';
import { Container, Form, Button, Alert, Card, Spinner, Toast, ToastContainer, InputGroup } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import api from '../services/api';

const LoginPage = ({ setLoggedIn }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showToast, setShowToast] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const validateForm = () => {
    if (!username.trim() || !password.trim()) {
      setError('Username and password are required.');
      setShowToast(true);
      return false;
    }
    return true;
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');
    if (!validateForm()) return;
    setLoading(true);
    try {
      const response = await api.post('/public/login', { username, password });
      localStorage.setItem('token', response.data);
      setLoggedIn(true);
      setShowToast(true);
      setTimeout(() => navigate('/journal'), 1000);
    } catch (err) {
      setError('Invalid credentials. Please try again.');
      setShowToast(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Card className="p-4 shadow" style={{ minWidth: 350, maxWidth: 400, width: '100%' }}>
        <Card.Body>
          <h2 className="mb-4 text-center">Login</h2>
          <Form onSubmit={handleLogin}>
            <Form.Group className="mb-3">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                placeholder="Enter your username"
                autoFocus
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Password</Form.Label>
              <InputGroup>
                <Form.Control
                  type={showPassword ? 'text' : 'password'}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  placeholder="Enter your password"
                />
                <Button
                  variant="outline-secondary"
                  onClick={() => setShowPassword((prev) => !prev)}
                  tabIndex={-1}
                  aria-label={showPassword ? 'Hide password' : 'Show password'}
                >
                  {showPassword ? 'Hide' : 'Show'}
                </Button>
              </InputGroup>
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100" disabled={loading}>
              {loading ? <Spinner animation="border" size="sm" /> : 'Login'}
            </Button>
          </Form>
        </Card.Body>
      </Card>
      <ToastContainer position="top-center" className="mt-3">
        <Toast
          bg={error ? 'danger' : 'success'}
          onClose={() => setShowToast(false)}
          show={showToast}
          delay={2500}
          autohide
        >
          <Toast.Body className="text-white text-center">
            {error ? error : 'Login successful! Redirecting...'}
          </Toast.Body>
        </Toast>
      </ToastContainer>
    </Container>
  );
};

export default LoginPage; 