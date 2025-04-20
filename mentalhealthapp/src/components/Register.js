import { useState } from 'react';
import { registerUser } from '../services/api';
import { useNavigate, Link } from 'react-router-dom';
import '../styles/Register.css';
import '../styles/App.css';

const Register = () => {
    const [user, setUser] = useState({ firstName: '', lastName: '', email: '', password: '' });
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();

        // Convert camelCase to snake_case before sending to backend
        const userPayload = {
            first_name: user.firstName,
            last_name: user.lastName,
            email: user.email,
            password: user.password,
        };

        try {
            await registerUser(userPayload);
            setMessage('User registered successfully. Please log in.');
            navigate('/login');
        } catch (error) {
            setMessage('Registration failed');
        }
    };

    return (
        <div className="register-container">
            <div className="register-box">
                <h2>Register</h2>
                {message && <p className="message">{message}</p>}
                <form onSubmit={handleRegister}>
                    <input
                        type="text"
                        className="form-control"
                        placeholder="First Name"
                        onChange={(e) => setUser({ ...user, firstName: e.target.value })}
                        required
                    />
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Last Name"
                        onChange={(e) => setUser({ ...user, lastName: e.target.value })}
                    />
                    <input
                        type="email"
                        className="form-control"
                        placeholder="Email"
                        onChange={(e) => setUser({ ...user, email: e.target.value })}
                        required
                    />
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
                        onChange={(e) => setUser({ ...user, password: e.target.value })}
                        required
                    />
                    <button type="submit" className="btn btn-dark">Register</button>
                </form>
                <p className="redirect-text">Already have an account? <Link to="/login">Login</Link></p>
            </div>
        </div>
    );
};

export default Register;
