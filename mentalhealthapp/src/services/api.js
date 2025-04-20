import axios from 'axios';

// const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:9091/api/v1/user';

const API_BASE_URL = 'http://localhost:9090/api/v1/user';

export const registerUser = async (userData) => {
    return axios.post(`${API_BASE_URL}/register`, userData);
};

export const loginUser = async (loginData) => {
    return axios.post(`${API_BASE_URL}/login`, loginData);
};

export const fetchUserDetails = async (token) => {
    return axios.get(API_BASE_URL, {
        headers: { Authorization: `Bearer ${token}` }
    });
};
