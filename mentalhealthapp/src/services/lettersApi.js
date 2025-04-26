// import axios from "axios";
// import { getToken } from "../auth";

// //const BASE_URL = 'http://localhost:9095/api';

// const BASE_URL = '/api/letter/api';

// const headers = () => ({
//   Authorization: `Bearer ${getToken()}`
// });

// export const postLetter = async (letterData) => {
//   const response = await axios.post(`${BASE_URL}/letters/post`, letterData, {
//     headers: headers()
//   });
//   return response.data;
// };

// export const getAllLetters = async () => {
//   const response = await axios.get(`${BASE_URL}/letters/all`, {
//     headers: headers()
//   });
//   return response.data;
// };

// export const getLettersByCategory = async (category) => {
//   const response = await axios.get(`${BASE_URL}/letters/category/${category}`, {
//     headers: headers()
//   });
//   return response.data;
// };

// export const getAllLettersCategories = async () => {
//   const response = await axios.get(`${BASE_URL}/letters/categories`, {
//     headers: headers()
//   });
//   return response.data;
// };
import axios from "axios";
import { getToken } from "../auth"; // Assuming auth.js exists and works

// This BASE_URL should match the path prefix routed by Ingress to letter-service
// Your Ingress rule uses /api/letter(/|$)(.*) and rewrites to /$2
// The backend controller is @RequestMapping("/api/letters")
// So, the request needs to start with /api/letter/api/letters/...
const BASE_URL = '/api/letter/api/letters'; // Adjusted based on Ingress and Controller mapping

const headers = () => {
    const token = getToken();
    if (!token) {
        console.warn("No token found for API request");
        // Handle missing token? Redirect to login? Or let backend return 401/403
        // return {}; // Or throw error?
    }
    return {
        Authorization: `Bearer ${token}`
    };
};

// Use a helper for requests to handle errors consistently
const apiRequest = async (method, url, data = null) => {
    try {
        const response = await axios({
            method: method,
            url: url,
            data: data,
            headers: headers() // Get fresh headers for each request
        });
        return response.data;
    } catch (error) {
        console.error(`API ${method} request to ${url} failed:`, error.response || error.message || error);
        // Re-throw the error so calling components can handle it
        throw error;
    }
};


// --- API Functions ---

export const postLetter = async (letterData) => {
    return apiRequest('post', `${BASE_URL}/post`, letterData);
};

export const getAllLetters = async () => {
    return apiRequest('get', `${BASE_URL}/all`);
};

export const getLettersByCategory = async (category) => {
    // Ensure category is URL-encoded if it can contain special characters
    return apiRequest('get', `${BASE_URL}/category/${encodeURIComponent(category)}`);
};

// This is the function needed by CategoryFilter.js
export const getAllLettersCategories = async () => {
    return apiRequest('get', `${BASE_URL}/categories`);
};