import React, { useState, useEffect } from 'react';
import { Container, Form, Button, Card, Spinner, Toast, ToastContainer, InputGroup, Alert, Row, Col } from 'react-bootstrap';
import api from '../services/api';

const JournalPage = () => {
  const [entries, setEntries] = useState([]);
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [showToast, setShowToast] = useState(false);
  const [success, setSuccess] = useState('');

  useEffect(() => {
    fetchEntries();
    // eslint-disable-next-line
  }, []);

  const fetchEntries = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await api.get('/journal');
      setEntries(response.data);
    } catch (err) {
      setError('Could not fetch journal entries.');
    } finally {
      setLoading(false);
    }
  };

  const validateForm = () => {
    if (!title.trim() || !content.trim()) {
      setError('Title and content are required.');
      setShowToast(true);
      return false;
    }
    return true;
  };

  const handleAddEntry = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    if (!validateForm()) return;
    setSubmitting(true);
    try {
      await api.post('/journal', { title, content });
      setTitle('');
      setContent('');
      setSuccess('Entry added successfully!');
      setShowToast(true);
      fetchEntries();
    } catch (err) {
      setError('Could not add new entry.');
      setShowToast(true);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4 text-center">My Journal</h2>
      <Card className="mb-4 p-3 shadow-sm">
        <Form onSubmit={handleAddEntry}>
          <Form.Group className="mb-3">
            <Form.Label>Title</Form.Label>
            <Form.Control
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              required
              placeholder="Entry title"
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Content</Form.Label>
            <Form.Control
              as="textarea"
              rows={3}
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
              placeholder="Write your thoughts..."
            />
          </Form.Group>
          <Button variant="primary" type="submit" className="w-100" disabled={submitting}>
            {submitting ? <Spinner animation="border" size="sm" /> : 'Add Entry'}
          </Button>
        </Form>
      </Card>
      {loading ? (
        <div className="d-flex justify-content-center align-items-center" style={{ minHeight: 200 }}>
          <Spinner animation="border" />
        </div>
      ) : error ? (
        <Alert variant="danger" className="text-center">{error}</Alert>
      ) : (
        <Row xs={1} md={2} lg={3} className="g-4">
          {entries.map((entry) => (
            <Col key={entry.id} className="fade-in">
              <Card className="shadow-sm h-100">
                <Card.Body>
                  <Card.Title>{entry.title}</Card.Title>
                  <Card.Text>{entry.content}</Card.Text>
                </Card.Body>
                <Card.Footer>
                  <small className="text-muted">
                    {entry.date ? new Date(entry.date).toLocaleString() : ''}
                  </small>
                </Card.Footer>
              </Card>
            </Col>
          ))}
        </Row>
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
            {error ? error : success}
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

export default JournalPage; 