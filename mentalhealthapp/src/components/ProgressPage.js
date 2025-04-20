// ProgressPage.js
import React from 'react';
import '../styles/SelfHelp.css';
import DashboardButton from '../components/DashboardButton';
export default function ProgressPage() {
  return (
    <div className="self-discovery-container">
      <h2>🎉 Progress Updated Successfully!</h2>
      <p>Your progress has been saved. You can continue exploring other toolkits.</p>
      <DashboardButton />
    </div>
  );
}
