import { useEffect, useState } from 'react';
import { fetchCrisisDescriptions, fetchCrisisSteps, fetchHelplines } from '../services/CrisisService';
import CrisisDescription from '../components/CrisisDescription';
import CrisisStepCard from '../components/CrisisStepCard';
import HelplineCard from '../components/HelplineCard';
import { useAuth } from '../context/AuthContext';
import '../styles/CrisisManagement.css';
import DashboardButton from '../components/DashboardButton';
const CrisisManagement = () => {
    const [descriptions, setDescriptions] = useState([]);
    const [steps, setSteps] = useState([]);
    const [helplines, setHelplines] = useState([]);
    const { token } = useAuth();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [descData, stepsData, helplineData] = await Promise.all([
                    fetchCrisisDescriptions(token),
                    fetchCrisisSteps(token),
                    fetchHelplines(token),
                ]);
                setDescriptions(descData);
                setSteps(stepsData);
                setHelplines(helplineData);
            } catch (err) {
                console.error('Failed to load crisis data:', err);
            }
        };

        if (token) {
            fetchData();
        }
    }, [token]);

    return (
        <div className="crisis-management-container">
            <h2>Crisis Management</h2>
            <div className="card-grid">
                <div className="card-section">
                    <h3>Understanding Crisis</h3>
                    {descriptions.map(d => (
                        <CrisisDescription key={d.id} title={d.title} description={d.description} />
                    ))}
                </div>

                <div className="card-section">
                    <h3>Steps to Handle Crisis</h3>
                    {steps.map(s => (
                        <CrisisStepCard key={s.id} question={s.question} options={s.options} pdf_link={s.pdf_link} />
                    ))}
                </div>

                <div className="card-section">
                    <h3>Helplines</h3>
                    {helplines.map(h => (
                        <HelplineCard key={h.id} {...h} />
                    ))}
                </div>
            </div>
            <DashboardButton />
        </div>
    );
};

export default CrisisManagement;
