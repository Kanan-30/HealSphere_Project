import React from 'react';
import SelfDiscoveryForm from '../components/QuestionCard';
import '../styles/SelfDiscovery.css';
import DashboardButton from '../components/DashboardButton';
const SelfDiscovery = ({ token }) => {
    return (
        <div className="self-discovery-page">
            <SelfDiscoveryForm token={token} />
            <DashboardButton />
        </div>
    );
};

export default SelfDiscovery;
