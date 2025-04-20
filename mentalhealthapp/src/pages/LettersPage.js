
import React, { useEffect, useState } from "react";
import { getAllLetters } from "../services/lettersApi";
import LettersList from "../components/LettersList";
import CategoryFilter from "../components/CategoryFilter";
import '../styles/Letters.css';
import DashboardButton from '../components/DashboardButton';
const LettersPage = () => {
  const [letters, setLetters] = useState([]);

  const fetchLetters = async () => {
    const data = await getAllLetters();
    setLetters(data);
  };

  useEffect(() => {
    fetchLetters();
  }, []);

  return (
    <div className="letters-container">
      <h2 className="text-center mb-4">Letters Archive</h2>
      <CategoryFilter setLetters={setLetters} />
      <LettersList letters={letters} />
      <DashboardButton />
    </div>
  );
};

export default LettersPage;
