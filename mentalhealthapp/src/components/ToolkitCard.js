import React from 'react';
import DashboardButton from '../components/DashboardButton';
export default function ToolkitCard({ toolkit, onSelect }) {
  return (
    <div className="toolkit-list" onClick={() => onSelect(toolkit)}>
      <h4>{toolkit.name}</h4>
      <p>{toolkit.description}</p>
      <DashboardButton />
    </div>
  );
}
