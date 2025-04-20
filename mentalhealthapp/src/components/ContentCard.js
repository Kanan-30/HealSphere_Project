import React from 'react';
import DashboardButton from '../components/DashboardButton';
export default function ContentCard({ content }) {
  return (
    <div className="question">
      <p><strong>Type:</strong> {content.contentType}</p>
      <p>{content.content}</p>
      <DashboardButton />
    </div>
  );
}
