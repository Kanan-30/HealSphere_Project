
// import React, { useEffect, useState } from "react";
// import { getLettersByCategory, getAllLetters } from "../services/lettersApi";
// import axios from "axios";
// import { getToken } from "../auth";
// import '../styles/Letters.css';
// import DashboardButton from '../components/DashboardButton';

// const CategoryFilter = ({ setLetters }) => {
//   const [categories, setCategories] = useState([]);
//   const [selectedCategory, setSelectedCategory] = useState("");

//   const BASE_URL = 'http://localhost:9095/api';

//   const fetchCategories = async () => {
//     try {
//       const response = await axios.get(`${BASE_URL}/letters/categories`, {
//         headers: {
//           Authorization: `Bearer ${getToken()}`
//         }
//       });
//       setCategories(response.data);
//     } catch (error) {
//       console.error("Failed to fetch categories", error);
//     }
//   };

//   const handleFilter = async () => {
//     if (selectedCategory === "") {
//       const all = await getAllLetters();
//       setLetters(all);
//     } else {
//       const filtered = await getLettersByCategory(selectedCategory);
//       setLetters(filtered);
//     }
//   };

//   useEffect(() => {
//     fetchCategories();
//   }, []);

//   return (
//     <div className="category-filter">
//       <select
//         className="form-select"
//         value={selectedCategory}
//         onChange={(e) => setSelectedCategory(e.target.value)}
//       >
//         <option value="">All Categories</option>
//         {categories.map((cat, idx) => (
//           <option key={idx} value={cat}>{cat}</option>
//         ))}
//       </select>
//       <button onClick={handleFilter}>Filter</button>
//       <DashboardButton />
//     </div>
//   );
// };

// export default CategoryFilter;
import React, { useEffect, useState } from "react";
// Import the specific function that uses the correct base URL
import { getAllLettersCategories, getLettersByCategory, getAllLetters } from "../services/lettersApi";
// Removed: import axios from "axios";
// Removed: import { getToken } from "../auth";
import '../styles/Letters.css';
import DashboardButton from '../components/DashboardButton';

const CategoryFilter = ({ setLetters }) => {
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState("");

    // Removed: const BASE_URL = 'http://localhost:9095/api'; // DELETE THIS LINE

    const fetchCategories = async () => {
        try {
            // Use the imported function which has the correct URL setup
            const responseData = await getAllLettersCategories();
            setCategories(responseData); // Assuming responseData is the array of strings
        } catch (error) {
            console.error("Failed to fetch categories", error);
            // Handle error appropriately (e.g., show message to user)
            setCategories([]); // Set to empty on error
        }
    };

    const handleFilter = async () => {
        try { // Add try-catch for robustness
            if (selectedCategory === "") {
                const all = await getAllLetters();
                setLetters(all);
            } else {
                const filtered = await getLettersByCategory(selectedCategory);
                setLetters(filtered);
            }
        } catch (error) {
            console.error("Failed to filter letters:", error);
            // Handle error (e.g., clear letters, show message)
            setLetters([]);
        }
    };

    useEffect(() => {
        fetchCategories();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []); // Added dependency array comment if needed

    return (
        <div className="category-filter">
            <select
                className="form-select"
                value={selectedCategory}
                onChange={(e) => setSelectedCategory(e.target.value)}
            >
                <option value="">All Categories</option>
                {/* Ensure categories is always an array before mapping */}
                {Array.isArray(categories) && categories.map((cat, idx) => (
                    <option key={idx} value={cat}>{cat}</option>
                ))}
            </select>
            <button onClick={handleFilter}>Filter</button>
            <DashboardButton />
        </div>
    );
};

export default CategoryFilter;