// import axios from 'axios';

// const API_BASE_URL = 'http://localhost:8080/api/self-discovery'; // Adjust to your actual URL

// export const fetchQuestions = async () => {
//     try {
//         const response = await axios.get(`${API_BASE_URL}/questions`);
//         return response.data;
//     } catch (error) {
//         throw new Error('Failed to fetch questions');
//     }
// };

// export const submitUserResponses = async (responses, token) => {
//     try {
//         await axios.post(
//             `${API_BASE_URL}/responses`,
//             { responses },
//             { headers: { Authorization: `Bearer ${token}` } }
//         );
//     } catch (error) {
//         throw new Error('Failed to submit responses');
//     }
// };

// export const fetchSelfDiscoveryReport = async (token) => {
//     try {
//         const response = await axios.get(`${API_BASE_URL}/report`, {
//             headers: { Authorization: `Bearer ${token}` },
//         });
//         return response.data;
//     } catch (error) {
//         throw new Error('Failed to fetch self-discovery report');
//     }
// };


import axios from 'axios';

// const BASE_URL = 'http://localhost:9091/api/self-discovery';

// const BASE_URL = process.env.REACT_APP_DISCOVERY_API;

const BASE_URL = '/api/discovery/api/self-discovery';

export const getQuestions = (step, token) =>
  axios.get(`${BASE_URL}/questions/${step}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const getOptions = (questionId, token) =>
  axios.get(`${BASE_URL}/options/${questionId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const submitResponse = (response, token) =>
  axios.post(`${BASE_URL}/user-response`, response, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const getReport = (token) =>
  axios.get(`${BASE_URL}/report`, {
    headers: { Authorization: `Bearer ${token}` },
  });
