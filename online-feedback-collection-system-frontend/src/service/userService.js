import axios from "axios";

const API_BASE_URL = "http://localhost:5080/user";

export const registerUser = (userData) => {
  return axios.post(`${API_BASE_URL}/register`, userData);
};


export const loginUser = async (userEmail, userPassword) => {
  try {
    const res = await axios.post("http://localhost:5080/user/login", {
      userEmail,
      userPassword
    });
    return res.data.record;
  } catch (err) {
    throw err.response?.data || { message: "Login failed" };
  }
};
