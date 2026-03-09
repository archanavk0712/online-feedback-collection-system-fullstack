import axios from "axios";

const API_BASE_URL = "http://localhost:5080/product";

export const viewAllProducts = () => {
  return axios.get(`${API_BASE_URL}/viewall`);
};

export const addProduct = (productData) => {
  return axios.post(`${API_BASE_URL}/add`, productData);
};

export const updateProduct = (productData) => {
  return axios.put(`${API_BASE_URL}/update`, productData);
};

export const deleteProduct = (productId) => {
  return axios.delete(
    `${API_BASE_URL}/delete`,
    {
      params: {
        productId: productId
      }
    }
  );
};
