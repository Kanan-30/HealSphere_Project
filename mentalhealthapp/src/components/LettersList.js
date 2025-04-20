
import React from "react";
import '../styles/Letters.css';
import DashboardButton from '../components/DashboardButton';
const LettersList = ({ letters }) => {
  return (
    <div>
      <h3>All Letters</h3>
      {letters.length === 0 ? (
        <p className="text-muted">No letters found.</p>
      ) : (
        letters.map((letter) => (
          <div className="letter-card shadow-sm" key={letter.id}>
            <div className="letter-card-header">
              <h4>{letter.heading}</h4>
              <span className="badge bg-primary">{letter.category}</span>
            </div>
            <p className="letter-date"><strong>Date:</strong> {letter.date}</p>
            <p>{letter.content}</p>
            <DashboardButton />
          </div>
        ))
      )}
    </div>
  );
};

export default LettersList;

