import React, { useEffect, useState } from 'react';
import { getQuestions, getOptions, submitResponse } from '../services/SelfDiscoveryService';
import QuestionCard from './QuestionCard';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';
import '../styles/SelfDiscovery.css';
import DashboardButton from '../components/DashboardButton';

export default function SelfDiscovery() {
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState({});
  const [optionsMap, setOptionsMap] = useState({});
  const { token } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchQuestions = async () => {
      const res = await getQuestions('self_discovery_1', token);
      setQuestions(res.data);

      for (let q of res.data) {
        const optRes = await getOptions(q.id, token);
        setOptionsMap((prev) => ({ ...prev, [q.id]: optRes.data }));
      }
    };
    fetchQuestions();
  }, [token]);

  const handleSubmit = async () => {
    const userId = "1"; // You can fetch this from token/user context later
    for (let [questionId, selectedOption] of Object.entries(answers)) {
      await submitResponse({ user_id: userId, questionId, selectedOption }, token);
    }
    navigate('/report');
  };

  return (
    <div className="container mt-4">
      <h2>Self Discovery - Step 1</h2>
      {questions.map((q) => (
        <QuestionCard
          key={q.id}
          question={q}
          options={optionsMap[q.id] || []}
          selected={answers[q.id]}
          setSelected={(val) => setAnswers({ ...answers, [q.id]: val })}
        />
      ))}
      <button className="btn btn-success" onClick={handleSubmit}>
        Submit
      </button>
      <DashboardButton />
    </div>
  );
}
