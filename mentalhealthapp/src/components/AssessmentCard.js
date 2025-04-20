import React from 'react';
import DashboardButton from '../components/DashboardButton';
export default function AssessmentCard({ assessment }) {
  return (
    <div className="question">
      <p>{assessment.question}</p>
      {assessment.options.map((option, idx) => (
        <div key={idx} className="options">
          <label><input type="radio" name={`q-${assessment.id}`} value={option} /> {option}</label>
          <DashboardButton />
        </div>
      ))}
    </div>
  );
}
