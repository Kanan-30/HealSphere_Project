import React, { useEffect, useState } from 'react';
import { getToolkits, getLevels, getContent, getAssessments, submitProgress } from '../services/selfHelpService';
import { useAuth } from '../context/AuthContext';
import ToolkitCard from './ToolkitCard';
import LevelCard from './LevelCard';
import ContentCard from './ContentCard';
import AssessmentCard from './AssessmentCard';
import '../styles/SelfHelp.css';
import { useNavigate } from 'react-router-dom';
import DashboardButton from '../components/DashboardButton';

export default function SelfHelp() {
  const { token } = useAuth();
  const [toolkits, setToolkits] = useState([]);
  const [selectedToolkit, setSelectedToolkit] = useState(null);
  const [levels, setLevels] = useState([]);
  const [selectedLevel, setSelectedLevel] = useState(null);
  const [content, setContent] = useState([]);
  const [assessments, setAssessments] = useState([]);

  useEffect(() => {
    getToolkits(token).then(res => setToolkits(res.data));
  }, [token]);

  const loadLevels = (toolkit) => {
    setSelectedToolkit(toolkit);
    getLevels(toolkit.id, token).then(res => setLevels(res.data));
  };

  const loadLevelDetails = (level) => {
    setSelectedLevel(level);
    getContent(level.id, token).then(res => setContent(res.data));
    getAssessments(level.id, token).then(res => setAssessments(res.data));
  };
  const navigate = useNavigate();
  const handleProgressSubmit = async (score) => {
    const progressData = {
      toolkitId: selectedToolkit.id,
      levelId: selectedLevel.id,
      isCompleted: true,
      score,
    };
    await submitProgress(progressData, token);
    navigate('/selfhelp/progress');
  };
  

  return (
    <div className="self-help-container">
      <h2>Self Help Toolkits</h2>

      {!selectedToolkit && toolkits.map(toolkit => (
        <ToolkitCard key={toolkit.id} toolkit={toolkit} onSelect={loadLevels} />
      ))}

      {selectedToolkit && !selectedLevel && (
        <>
          <button onClick={() => setSelectedToolkit(null)}>⬅ Back to Toolkits</button>
          {levels.map(level => (
            <LevelCard key={level.id} level={level} onSelect={loadLevelDetails} />
          ))}
        </>
      )}

      {selectedLevel && (
        <>
          <button onClick={() => setSelectedLevel(null)}>⬅ Back to Levels</button>
          {content.map(c => <ContentCard key={c.id} content={c} />)}
          {assessments.map(a => (
            <AssessmentCard key={a.id} assessment={a} />
          ))}
          <button onClick={() => handleProgressSubmit(80)}>Mark Completed</button>
        </>
      )}
      <DashboardButton />
    </div>
  );
}
