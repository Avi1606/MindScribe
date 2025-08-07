import React from 'react';
import { Container, Button, Row, Col, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <Container className="d-flex flex-column justify-content-center align-items-center min-vh-100">
      <Row className="w-100 justify-content-center mb-5">
        <Col md={8} lg={6}>
          <Card className="p-4 shadow-lg text-center bg-light border-0">
            <Card.Body>
              <h1 className="display-4 mb-3 fw-bold">Welcome to <span className="text-primary">JournalApp</span></h1>
              <p className="lead mb-4">Your personal space to write, reflect, and grow. Secure, simple, and always with you.</p>
              <div>
                <Link to="/login">
                  <Button variant="primary" size="lg" className="m-2">Login</Button>
                </Link>
                <Link to="/signup">
                  <Button variant="outline-primary" size="lg" className="m-2">Sign Up</Button>
                </Link>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
      <Row className="w-100 justify-content-center">
        <Col md={8} lg={6} className="text-center">
          <p className="text-muted mt-4">Start your journey of self-reflection and growth today.</p>
        </Col>
      </Row>
    </Container>
  );
};

export default HomePage; 