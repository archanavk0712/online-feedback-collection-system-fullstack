import axios from "axios";

const API_BASE_URL = "http://localhost:5080/feedback";

export const submitFeedback = (feedbackData) => {
  return axios.post(`${API_BASE_URL}/submit`, feedbackData);
};

export const viewFeedbackByUserId = (userId) => {
  return axios.get(
    `${API_BASE_URL}/byuser`,
    {
      params: {
        userId: userId
      }
    }
  );
};
