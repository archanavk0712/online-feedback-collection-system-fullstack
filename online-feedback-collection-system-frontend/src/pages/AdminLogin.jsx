import { useState } from "react";
import { Form, Button, Card, Container, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import { adminLogin } from "../service/adminService";

function AdminLogin() {
  const navigate = useNavigate();

  const [adminEmail, setAdminEmail] = useState("");
  const [adminPassword, setAdminPassword] = useState("");

  const validate = () => {
    if (!adminEmail.trim()) {
      toast.error("Email is required");
      return false;
    }

    const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/;
    if (!emailRegex.test(adminEmail)) {
      toast.error("Invalid email format");
      return false;
    }

    if (!adminPassword.trim()) {
      toast.error("Password is required");
      return false;
    }

    if (adminPassword.length < 6) {
      toast.error("Password must be at least 6 characters");
      return false;
    }

    return true;
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!validate()) return;

    const adminData = {
      adminEmail,
      adminPassword,
    };

    try {
      const res = await adminLogin(adminData);

      if (!res.data.error) {
        toast.success("Admin login successful");

        localStorage.setItem("admin", "true");

        navigate("/admin/dashboard");
      } else {
        toast.error("Invalid admin login details");
      }
    } catch (err) {
      toast.error("Invalid admin login details");
    }
  };

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col md={5}>
          <Card>
            <Card.Body>
              <h3 className="text-center mb-4">Admin Login</h3>

              <Form onSubmit={handleLogin}>
                <Form.Group className="mb-3">
                  <Form.Label>Email</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter admin email"
                    value={adminEmail}
                    onChange={(e) => setAdminEmail(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={adminPassword}
                    onChange={(e) => setAdminPassword(e.target.value)}
                  />
                </Form.Group>

                <Button type="submit" variant="dark" className="w-100">
                  Login
                </Button>
              </Form>

              <div className="text-center mt-3">
                <Button variant="link" onClick={() => navigate("/login")}>
                  Back to User Login
                </Button>
              </div>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}

export default AdminLogin;
