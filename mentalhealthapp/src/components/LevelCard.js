import React from 'react';
import DashboardButton from '../components/DashboardButton';
export default function LevelCard({ level, onSelect }) {
  return (
    <div className="service-card" onClick={() => onSelect(level)}>
      <h4>{level.levelName}</h4>
      <p>Order: {level.levelOrder} | {level.unlocked ? 'Unlocked' : 'Locked'}</p>
      <DashboardButton />
    </div>
  );
}
