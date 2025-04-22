import axios from 'axios';

const BASE_URL = '/api/coping/api';

export const fetchCrisisDescriptions = async (token) => {
    const res = await axios.get(`${BASE_URL}/crisis/descriptions`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return res.data;
};

export const fetchCrisisSteps = async (token) => {
    const res = await axios.get(`${BASE_URL}/crisis/steps`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return res.data;
};

export const fetchHelplines = async (token) => {
    const res = await axios.get(`${BASE_URL}/helplines`, {
        headers: { Authorization: `Bearer ${token}` },
    });
    return res.data;
};
