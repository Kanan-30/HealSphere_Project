// src/components/LetterForm.js
import React, { useState } from "react";
import { postLetter } from "../services/lettersApi";
import '../styles/Letters.css';
import DashboardButton from '../components/DashboardButton';
const LetterForm = ({ onSuccess }) => {
  const [form, setForm] = useState({
    date: "",
    category: "",
    heading: "",
    content: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await postLetter(form);
      alert("Letter posted successfully!");
      onSuccess(); // Refresh list
    } catch (error) {
      alert("Error posting letter");
      console.error(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type="date" name="date" value={form.date} onChange={handleChange} required />
      <input type="text" name="category" placeholder="Category" value={form.category} onChange={handleChange} required />
      <input type="text" name="heading" placeholder="Heading" value={form.heading} onChange={handleChange} required />
      <textarea name="content" placeholder="Content" value={form.content} onChange={handleChange} required />
      <button type="submit">Post Letter</button>
      <DashboardButton />
    </form>
    
  );
};

export default LetterForm;
