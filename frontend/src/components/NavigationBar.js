import React from 'react';
import { Navbar, Nav, Container, Badge, OverlayTrigger, Tooltip } from 'react-bootstrap';
import { Link, useLocation, useNavigate } from 'react-router-dom';

const getInitials = (user) => {
  if (!user) return '';
  if (user.username) return user.username[0].toUpperCase();
  if (user.email) return user.email[0].toUpperCase();
  return '';
};

const NavigationBar = ({ loggedIn, setLoggedIn }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const user = JSON.parse(localStorage.getItem('user'));

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setLoggedIn(false);
    navigate('/login');
  };

  return (
    <Navbar bg="dark" variant="dark" expand="lg" sticky="top" className="shadow-sm">
      <Container>
        <Navbar.Brand as={Link} to="/">JournalApp</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link
              as={Link}
              to="/"
              active={location.pathname === '/'}
              aria-current={location.pathname === '/' ? 'page' : undefined}
            >
              Home
            </Nav.Link>
            {loggedIn && (
              <Nav.Link
                as={Link}
                to="/journal"
                active={location.pathname === '/journal'}
                aria-current={location.pathname === '/journal' ? 'page' : undefined}
              >
                My Journal
              </Nav.Link>
            )}
            {loggedIn && user?.roles?.includes('ADMIN') && (
              <Nav.Link
                as={Link}
                to="/admin"
                active={location.pathname === '/admin'}
                aria-current={location.pathname === '/admin' ? 'page' : undefined}
              >
                Admin
              </Nav.Link>
            )}
          </Nav>
          <Nav>
            {!loggedIn ? (
              <>
                <Nav.Link as={Link} to="/login" active={location.pathname === '/login'}>Login</Nav.Link>
                <Nav.Link as={Link} to="/signup" active={location.pathname === '/signup'}>Sign Up</Nav.Link>
              </>
            ) : (
              <>
                <OverlayTrigger
                  placement="bottom"
                  overlay={<Tooltip id="user-tooltip">{user?.username || user?.email}</Tooltip>}
                >
                  <Badge
                    bg="secondary"
                    className="me-2"
                    style={{ fontSize: '1rem', borderRadius: '50%', width: 32, height: 32, display: 'inline-flex', alignItems: 'center', justifyContent: 'center' }}
                    aria-label="User initials"
                  >
                    {getInitials(user)}
                  </Badge>
                </OverlayTrigger>
                <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavigationBar; 