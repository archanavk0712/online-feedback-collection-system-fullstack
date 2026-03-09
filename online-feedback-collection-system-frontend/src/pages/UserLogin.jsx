import { useState } from "react";
import { Form, Button, Card, Container, Row, Col } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import { loginUser } from "../service/userService";

function UserLogin() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const validate = () => {
    if (!email.trim()) {
      toast.error("Email is required");
      return false;
    }

    const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
    if (!emailRegex.test(email)) {
      toast.error("Invalid email format");
      return false;
    }

    if (!password.trim()) {
      toast.error("Password is required");
      return false;
    }

    return true;
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!validate()) return;

    try {
      const user = await loginUser(email, password);

      if (user) {
        toast.success("Login successful");

        localStorage.setItem("user", JSON.stringify(user));

        navigate("/user/dashboard");
      }
    } catch (err) {
      toast.error(err.message || "User details not found");
    }
  };

  return (
    <Container className="mt-5">
      <h1 className="text-center text-primary mb-4">Customer Feedback Management System</h1>
      <Row className="justify-content-center">
        <Col md={5}>
          <Card>
            <Card.Body>
              <h3 className="text-center mb-4">User Login</h3>

              <Form onSubmit={handleLogin}>
                <Form.Group className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </Form.Group>

                <Button type="submit" variant="primary" className="w-100">
                  Login
                </Button>
              </Form>

              <div className="text-center mt-3">
                <span>
                  Not registered yet? <Link to="/register">Register here</Link>
                </span>
              </div>

              <div className="text-center mt-2">
                <Button variant="link" onClick={() => navigate("/admin/login")}>
                  Admin Login
                </Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default UserLogin;
