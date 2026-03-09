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
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

import {
  viewAllProducts,
  addProduct,
  updateProduct,
  deleteProduct,
} from "../service/productService";

import {
  viewAllFeedback,
  markFeedbackAsReviewed,
} from "../service/adminService";

function AdminDashboard() {
  const isAdmin = localStorage.getItem("admin");

  const navigate = useNavigate();

  const [products, setProducts] = useState([]);

  const [allFeedback, setAllFeedback] = useState([]);
  const [feedbackList, setFeedbackList] = useState([]);

  const [productName, setProductName] = useState("");
  const [productDescription, setProductDescription] = useState("");
  const [editingProductId, setEditingProductId] = useState(null);

  const [filterReviewed, setFilterReviewed] = useState("");
  const [filterProduct, setFilterProduct] = useState("");
  const [filterRating, setFilterRating] = useState("");

  useEffect(() => {
    if (!isAdmin) {
      toast.error("Unauthorized access");
      navigate("/admin/login");
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

  const handleAddOrUpdateProduct = async (e) => {
    e.preventDefault();

    if (!productName.trim() || !productDescription.trim()) {
      toast.error("All product fields are required");
      return;
    }

    try {
      if (editingProductId) {
        await updateProduct({
          productId: editingProductId,
          productName,
          productDescription,
        });
        toast.success("Product updated");
      } else {
        await addProduct({ productName, productDescription });
        toast.success("Product added");
      }

      setProductName("");
      setProductDescription("");
      setEditingProductId(null);
      loadProducts();
    } catch {
      toast.error("Product operation failed");
    }
  };

  const handleEdit = (product) => {
    setEditingProductId(product.productId);
    setProductName(product.productName);
    setProductDescription(product.productDescription);
  };

  const handleDelete = async (productId) => {
    if (!window.confirm("Delete this product?")) return;

    try {
      await deleteProduct(productId);
      toast.success("Product deleted successfully");
      loadProducts();
    } catch (err) {
      console.error("Delete error:", err);

      const message =
        err?.response?.data?.message ||
        err?.response?.data?.error_message ||
        "Cannot delete product";

      toast.error(message);
    }
  };

  const loadFeedback = async () => {
    try {
      const res = await viewAllFeedback();
      setAllFeedback(res.data.record);
      setFeedbackList(res.data.record);
    } catch {
      toast.error("Failed to load feedback");
    }
  };

  const applyFilters = () => {
    let filtered = [...allFeedback];

    if (filterReviewed !== "") {
      const reviewed = filterReviewed === "true";
      filtered = filtered.filter((f) => f.feedbackReviewed === reviewed);
    }

    if (filterProduct !== "") {
      filtered = filtered.filter(
        (f) => f.product?.productId === Number(filterProduct)
      );
    }

    if (filterRating !== "") {
      filtered = filtered.filter(
        (f) => f.feedbackRating === Number(filterRating)
      );
    }

    setFeedbackList(filtered);
  };

  const clearFilters = () => {
    setFilterReviewed("");
    setFilterProduct("");
    setFilterRating("");
    setFeedbackList(allFeedback);
  };

  const handleReview = async (feedbackId) => {
    try {
      await markFeedbackAsReviewed(feedbackId);
      toast.success("Feedback marked as reviewed");
      loadFeedback();
    } catch {
      toast.error("Operation failed");
    }
  };

  return (
    <Container className="mt-4">
      <h3 className="mb-4">Admin Dashboard</h3>

      <Button
        variant="danger"
        className="mb-3"
        onClick={() => {
          localStorage.removeItem("admin");
          navigate("/admin/login");
        }}
      >
        Logout
      </Button>

      <Card className="mb-4">
        <Card.Body>
          <h5>Manage Products</h5>

          <Form onSubmit={handleAddOrUpdateProduct}>
            <Row>
              <Col md={4}>
                <Form.Control
                  placeholder="Product Name"
                  value={productName}
                  onChange={(e) => setProductName(e.target.value)}
                />
              </Col>

              <Col md={5}>
                <Form.Control
                  placeholder="Product Description"
                  value={productDescription}
                  onChange={(e) => setProductDescription(e.target.value)}
                />
              </Col>

              <Col md={3}>
                <Button type="submit" className="w-100">
                  {editingProductId ? "Update" : "Add"} Product
                </Button>
              </Col>
            </Row>
          </Form>

          <Table bordered className="mt-3">
            <thead>
              <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {products.map((p) => (
                <tr key={p.productId}>
                  <td>{p.productName}</td>
                  <td>{p.productDescription}</td>
                  <td>
                    <Button
                      size="sm"
                      variant="warning"
                      onClick={() => handleEdit(p)}
                    >
                      Edit
                    </Button>{" "}
                    <Button
                      size="sm"
                      variant="danger"
                      onClick={() => handleDelete(p.productId)}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Card.Body>
      </Card>

      <Card>
        <Card.Body>
          <h5>Feedback Management</h5>

          <Row className="mb-3">
            <Col>
              <Form.Select
                value={filterReviewed}
                onChange={(e) => setFilterReviewed(e.target.value)}
              >
                <option value="">All</option>
                <option value="true">Reviewed</option>
                <option value="false">Unreviewed</option>
              </Form.Select>
            </Col>

            <Col>
              <Form.Select
                value={filterProduct}
                onChange={(e) => setFilterProduct(e.target.value)}
              >
                <option value="">Product</option>
                {products.map((p) => (
                  <option key={p.productId} value={p.productId}>
                    {p.productName}
                  </option>
                ))}
              </Form.Select>
            </Col>

            <Col>
              <Form.Select
                value={filterRating}
                onChange={(e) => setFilterRating(e.target.value)}
              >
                <option value="">Rating</option>
                {[1, 2, 3, 4, 5].map((r) => (
                  <option key={r} value={r}>
                    {r}
                  </option>
                ))}
              </Form.Select>
            </Col>

            <Col className="d-flex gap-2">
              <Button onClick={applyFilters}>Apply</Button>
              <Button variant="secondary" onClick={clearFilters}>
                Clear
              </Button>
            </Col>
          </Row>

          <Table bordered hover>
            <thead>
              <tr>
                <th>User</th>
                <th>Product</th>
                <th>Comment</th>
                <th>Rating</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {feedbackList.map((f) => (
                <tr key={f.feedbackId}>
                  <td>{f.user?.userName}</td>
                  <td>{f.product?.productName}</td>
                  <td>{f.feedbackComment}</td>
                  <td>{f.feedbackRating}</td>
                  <td>{f.feedbackReviewed ? "Reviewed" : "Pending"}</td>
                  <td>
                    {!f.feedbackReviewed && (
                      <Button
                        size="sm"
                        onClick={() => handleReview(f.feedbackId)}
                      >
                        Mark Reviewed
                      </Button>
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

export default AdminDashboard;
