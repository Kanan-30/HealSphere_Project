import axios from "axios";
import { getToken } from "../auth";

const BASE_URL = '/api/letter/api';

const headers = () => ({
  Authorization: `Bearer ${getToken()}`
});

export const postLetter = async (letterData) => {
  const response = await axios.post(`${BASE_URL}/letters/post`, letterData, {
    headers: headers()
  });
  return response.data;
};

export const getAllLetters = async () => {
  const response = await axios.get(`${BASE_URL}/letters/all`, {
    headers: headers()
  });
  return response.data;
};

export const getLettersByCategory = async (category) => {
  const response = await axios.get(`${BASE_URL}/letters/category/${category}`, {
    headers: headers()
  });
  return response.data;
};

export const getAllLettersCategories = async () => {
  const response = await axios.get(`${BASE_URL}/letters/categories`, {
    headers: headers()
  });
  return response.data;
};