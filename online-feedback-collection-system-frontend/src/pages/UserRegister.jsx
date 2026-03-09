import { useState } from "react";
import { Form, Button, Card, Container, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import { registerUser } from "../service/userService";

function UserRegister() {
  const navigate = useNavigate();

  const [userName, setUserName] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");

  const validate = () => {
    if (!userName.trim()) {
      toast.error("Name is required");
      return false;
    }

    if (!userEmail.trim()) {
      toast.error("Email is required");
      return false;
    }

    const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
    if (!emailRegex.test(userEmail)) {
      toast.error("Invalid email format");
      return false;
    }

    if (!userPassword.trim()) {
      toast.error("Password is required");
      return false;
    }

    if (userPassword.length < 6) {
      toast.error("Password must be at least 6 characters long");
      return false;
    }

    return true;
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (!validate()) return;

    const userData = {
      userName,
      userEmail,
      userPassword,
    };

    try {
      const res = await registerUser(userData);

      if (!res.data.error) {
        toast.success("Registration successful! Please login.");
        navigate("/login");
      } else {
        toast.error(res.data.message || "Registration failed");
      }
    } catch (err) {
      toast.error(err.response?.data?.message || "Email already exists");
    }
  };

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col md={5}>
          <Card>
            <Card.Body>
              <h3 className="text-center mb-4">User Registration</h3>

              <Form onSubmit={handleRegister}>
                <Form.Group className="mb-3">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter name"
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    value={userEmail}
                    onChange={(e) => setUserEmail(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                  />
                </Form.Group>

                <Button type="submit" variant="success" className="w-100">
                  Register
                </Button>
              </Form>

              <div className="text-center mt-3">
                <Button variant="link" onClick={() => navigate("/login")}>
                  Already have an account? Login
                </Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default UserRegister;