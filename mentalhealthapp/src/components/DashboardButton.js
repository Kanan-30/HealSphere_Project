// src/components/DashboardButton.js
import React from "react";
import { useNavigate } from "react-router-dom";
import '../styles/DashboardButton.css';

const DashboardButton = () => {
  const navigate = useNavigate();

  return (
    <div className="dashboard-button-container">
      <button onClick={() => navigate("/dashboard")} className="dashboard-btn">
        ⬅ Go to Dashboard
      </button>
    </div>
  );
};

export default DashboardButton;
