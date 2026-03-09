import axios from "axios";

const API_BASE_URL = "http://localhost:5080/admin";

export const adminLogin = (adminData) => {
  return axios.post(`${API_BASE_URL}/login`, adminData);
};

export const viewAllFeedback = () => {
  return axios.get(`${API_BASE_URL}/viewall`);
};

export const viewFeedbackByReviewed = (feedbackReviewed) => {
  return axios.get(
    `${API_BASE_URL}/feedbackreviewed`,
    {
      params: {
        feedbackReviewed: feedbackReviewed
      }
    }
  );
};

export const viewFeedbackByProduct = (productId) => {
  return axios.get(
    `${API_BASE_URL}/viewbyproduct`,
    {
      params: {
        productId: productId
      }
    }
  );
};

export const viewFeedbackByRating = (feedbackRating) => {
  return axios.get(
    `${API_BASE_URL}/viewbyrating`,
    {
      params: {
        feedbackRating: feedbackRating
      }
    }
  );
};

export const markFeedbackAsReviewed = (feedbackId) => {
  return axios.put(
    `${API_BASE_URL}/markfeedbackasreviewed`,
    null,
    {
      params: {
        feedbackId: feedbackId
      }
    }
  );
};
