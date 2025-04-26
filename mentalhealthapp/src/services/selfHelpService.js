import axios from 'axios';

//const BASE_URL = 'http://localhost:9093/api/selfhelp';

const BASE_URL = '/api/help/api/selfhelp';

export const getToolkits = (token) =>
  axios.get(`${BASE_URL}/toolkits`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const getLevels = (toolkitId, token) =>
  axios.get(`${BASE_URL}/levels/${toolkitId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const getContent = (levelId, token) =>
  axios.get(`${BASE_URL}/content/${levelId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const getAssessments = (levelId, token) =>
  axios.get(`${BASE_URL}/assessments/${levelId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });

export const submitProgress = (progress, token) =>
  axios.post(`${BASE_URL}/progress`, progress, {
    headers: { Authorization: `Bearer ${token}` },
  });
