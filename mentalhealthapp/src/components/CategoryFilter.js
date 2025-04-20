
import React, { useEffect, useState } from "react";
import { getLettersByCategory, getAllLetters } from "../services/lettersApi";
import axios from "axios";
import { getToken } from "../auth";
import '../styles/Letters.css';
import DashboardButton from '../components/DashboardButton';

const CategoryFilter = ({ setLetters }) => {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");

  const BASE_URL = 'http://localhost:9095/api';

  const fetchCategories = async () => {
    try {
      const response = await axios.get(`${BASE_URL}/letters/categories`, {
        headers: {
          Authorization: `Bearer ${getToken()}`
        }
      });
      setCategories(response.data);
    } catch (error) {
      console.error("Failed to fetch categories", error);
    }
  };

  const handleFilter = async () => {
    if (selectedCategory === "") {
      const all = await getAllLetters();
      setLetters(all);
    } else {
      const filtered = await getLettersByCategory(selectedCategory);
      setLetters(filtered);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <div className="category-filter">
      <select
        className="form-select"
        value={selectedCategory}
        onChange={(e) => setSelectedCategory(e.target.value)}
      >
        <option value="">All Categories</option>
        {categories.map((cat, idx) => (
          <option key={idx} value={cat}>{cat}</option>
        ))}
      </select>
      <button onClick={handleFilter}>Filter</button>
      <DashboardButton />
    </div>
  );
};

export default CategoryFilter;
