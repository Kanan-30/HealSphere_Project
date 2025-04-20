import React, { useEffect, useState } from 'react';
import { getReport } from '../services/SelfDiscoveryService';
import { useAuth } from '../context/AuthContext';
import '../styles/SelfDiscovery.css';
import DashboardButton from '../components/DashboardButton';
export default function ReportPage() {
  const [reports, setReports] = useState([]);
  const { token } = useAuth();

  useEffect(() => {
    const fetchReport = async () => {
      const res = await getReport(token);
      setReports(res.data);
    };
    fetchReport();
  }, [token]);

  return (
    <div className="container mt-4">
      <h2>Your Personalized Report</h2>
      <ul className="list-group">
        {reports.map((r) => (
          <li key={r.id} className="list-group-item">
            <strong>Q{r.questionId} - {r.selectedOption}:</strong><br />
            {r.reportText}
          </li>
        ))}
      </ul>
      <DashboardButton />
    </div>
  );
}
