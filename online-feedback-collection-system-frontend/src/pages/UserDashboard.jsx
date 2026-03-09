import { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Form,
  Table,
} from "react-bootstrap";
import { toast } from "react-toastify";

import { useNavigate } from "react-router-dom";

import { viewAllProducts } from "../service/productService";
import {
  submitFeedback,
  viewFeedbackByUserId,
} from "../service/feedbackService";

function UserDashboard() {
  const user = JSON.parse(localStorage.getItem("user"));

  const navigate = useNavigate();

  const [products, setProducts] = useState([]);
  const [feedbackList, setFeedbackList] = useState([]);

  const [productId, setProductId] = useState("");
  const [rating, setRating] = useState("");
  const [comment, setComment] = useState("");

  useEffect(() => {
    if (!user) {
      toast.error("Please login first");
      return;
    }
    loadProducts();
    loadFeedback();
  }, []);

  const loadProducts = async () => {
    try {
      const res = await viewAllProducts();
      setProducts(res.data.record);
    } catch {
      toast.error("Failed to load products");
    }
  };

  const loadFeedback = async () => {
    try {
      const res = await viewFeedbackByUserId(user.userId);
      setFeedbackList(res.data.record);
    } catch {
      toast.error("Failed to load feedback");
    }
  };

  const validateFeedback = () => {
    if (!productId) {
      toast.error("Please select a product");
      return false;
    }

    if (!rating || rating < 1 || rating > 5) {
      toast.error("Rating must be between 1 and 5");
      return false;
    }

    if (!comment.trim()) {
      toast.error("Comment is required");
      return false;
    }

    if (comment.length > 500) {
      toast.error("Comment cannot exceed 500 characters");
      return false;
    }

    return true;
  };

  const handleSubmitFeedback = async (e) => {
    e.preventDefault();

    if (!validateFeedback()) return;

    const feedbackData = {
      userId: user.userId,
      productId: Number(productId),
      feedbackRating: Number(rating),
      feedbackComment: comment,
    };

    try {
      const res = await submitFeedback(feedbackData);
      if (!res.data.error) {
        toast.success("Feedback submitted successfully");
        setProductId("");
        setRating("");
        setComment("");
        loadFeedback();
      }
    } catch (err) {
      toast.error(err.response?.data?.message || "Failed to submit feedback");
    }
  };

  return (
    <Container className="mt-4">
      <h3 className="mb-4">Welcome, {user?.userName}</h3>

      <Button
        variant="danger"
        className="mb-3"
        onClick={() => {
          localStorage.removeItem("user");
          navigate("/login");
        }}
      >Logout</Button>

      <Card className="mb-4">
        <Card.Body>
          <h5>Submit Feedback</h5>

          <Form onSubmit={handleSubmitFeedback}>
            <Row>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>Product</Form.Label>
                  <Form.Select
                    value={productId}
                    onChange={(e) => setProductId(e.target.value)}
                  >
                    <option value="">Select Product</option>
                    {products.map((p) => (
                      <option key={p.productId} value={p.productId}>
                        {p.productName}
                      </option>
                    ))}
                  </Form.Select>
                </Form.Group>
              </Col>

              <Col md={2}>
                <Form.Group className="mb-3">
                  <Form.Label>Rating</Form.Label>
                  <Form.Control
                    type="number"
                    min="1"
                    max="5"
                    value={rating}
                    onChange={(e) => setRating(e.target.value)}
                  />
                </Form.Group>
              </Col>

              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Comment</Form.Label>
                  <Form.Control
                    as="textarea"
                    rows={2}
                    value={comment}
                    onChange={(e) => setComment(e.target.value)}
                  />
                </Form.Group>
              </Col>
            </Row>

            <Button type="submit" variant="primary">
              Submit Feedback
            </Button>
          </Form>
        </Card.Body>
      </Card>

      <Card>
        <Card.Body>
          <h5>Your Feedback</h5>

          <Table bordered hover>
            <thead>
              <tr>
                <th>Product</th>
                <th>Comment</th>
                <th>Rating</th>
                <th>Date</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {feedbackList.length === 0 && (
                <tr>
                  <td colSpan="5" className="text-center">
                    No feedback submitted yet
                  </td>
                </tr>
              )}

              {feedbackList.map((f) => (
                <tr key={f.feedbackId}>
                  <td>{f.product ? f.product.productName : "Product Removed"}</td>
                  <td>{f.feedbackComment}</td>
                  <td>{f.feedbackRating}</td>
                  <td>{new Date(f.feedbackDate).toLocaleString()}</td>
                  <td>
                    {f.feedbackReviewed ? (
                      <span className="text-success">Reviewed</span>
                    ) : (
                      <span className="text-warning">Under Review</span>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    </Container>
  );
}

export default UserDashboard;
