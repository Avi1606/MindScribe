import React, { useState, useEffect } from 'react';
import { Container, Table, Spinner, Toast, ToastContainer, Alert } from 'react-bootstrap';
import api from '../services/api';

const AdminPage = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [showToast, setShowToast] = useState(false);

  useEffect(() => {
    fetchUsers();
    // eslint-disable-next-line
  }, []);

  const fetchUsers = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await api.get('/admin/get-alluser');
      setUsers(response.data);
    } catch (err) {
      setError('You do not have permission to view this page or an error occurred.');
      setShowToast(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4 text-center">Admin - All Users</h2>
      {loading ? (
        <div className="d-flex justify-content-center align-items-center" style={{ minHeight: 200 }}>
          <Spinner animation="border" />
        </div>
      ) : error ? (
        <Alert variant="danger" className="text-center">{error}</Alert>
      ) : (
        <div className="table-responsive">
          <Table striped bordered hover className="shadow-sm fade-in">
            <thead className="table-dark">
              <tr>
                <th>#</th>
                <th>Username</th>
                <th>Email</th>
                <th>Roles</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user, idx) => (
                <tr key={user.id || user._id}>
                  <td>{idx + 1}</td>
                  <td>{user.username}</td>
                  <td>{user.email}</td>
                  <td>{user.roles ? user.roles.join(', ') : ''}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      )}
      <ToastContainer position="top-center" className="mt-3">
        <Toast
          bg={error ? 'danger' : 'success'}
          onClose={() => setShowToast(false)}
          show={showToast}
          delay={2500}
          autohide
        >
          <Toast.Body className="text-white text-center">
            {error ? error : 'Users loaded successfully!'}
          </Toast.Body>
        </Toast>
      </ToastContainer>
      <style>{`
        .fade-in {
          animation: fadeIn 0.7s;
        }
        @keyframes fadeIn {
          from { opacity: 0; transform: translateY(20px); }
          to { opacity: 1; transform: none; }
        }
      `}</style>
    </Container>
  );
};

export default AdminPage; 