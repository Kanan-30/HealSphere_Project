import React from 'react';
import '../styles/SelfDiscovery.css';
import DashboardButton from '../components/DashboardButton';
export default function QuestionCard({ question, options, selected, setSelected }) {
  return (
    <div className="card my-3">
      <div className="card-body">
        <h5>{question.questionText}</h5>
        {options.map((opt) => (
          <div className="form-check" key={opt.id}>
            <input
              className="form-check-input"
              type="radio"
              name={`question-${question.id}`}
              value={opt.optionText}
              checked={selected === opt.optionText}
              onChange={() => setSelected(opt.optionText)}
            />
            <label className="form-check-label">{opt.optionText}</label>
            <DashboardButton />
          </div>
        ))}
      </div>
    </div>
  );
}
