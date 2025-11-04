import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Prism from './Prism'; // make sure the path is correct

const Register = ({ switchToLogin, closeModal }) => {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [contact, setContact] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const [message, setMessage] = useState('');
  const [isError, setIsError] = useState(false);
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    const form = {
      fullName: `${firstName} ${lastName}`.trim(),
      email,
      contact,
      password,
      confirmPassword,
    };

    const newErrors = {
      fullName:
        form.fullName && !/^[A-Za-z\s]{1,50}$/.test(form.fullName)
          ? 'Name must be alphabetic and under 50 characters.'
          : '',
      email:
        form.email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)
          ? 'Enter a valid email address.'
          : '',
      contact:
        form.contact && !/^\+27\d{9}$/.test(form.contact)
          ? 'Contact must be +27 followed by 9 digits.'
          : '',
      password:
        !form.password
          ? 'Password is required.'
          : form.password.length < 6
          ? 'Password must be at least 6 characters.'
          : '',
      confirmPassword:
        form.password !== form.confirmPassword
          ? 'Passwords do not match.'
          : '',
    };

    setErrors(newErrors);
    return !Object.values(newErrors).some((error) => error);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setIsError(false);

    if (!validateForm()) {
      setMessage('Please correct the highlighted errors.');
      setIsError(true);
      return;
    }

    const payload = { firstName, lastName, email, password };

    try {
      const response = await fetch('http://localhost:8080/api/employees', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        const data = await response.json();
        setMessage(`Success! Employee ID: ${data.id} created.`);
        setIsError(false);

        setTimeout(() => {
          if (typeof switchToLogin === 'function') {
            switchToLogin(); // modal mode
          } else {
            navigate('/'); // public page mode
          }
        }, 1500);
      } else {
        const status = response.status;
        if (status === 409 || status === 500) {
          setMessage('Registration Failed: That email already exists.');
        } else {
          let errorDetail = `Failed with status ${status}.`;
          try {
            const errorData = await response.json();
            if (errorData.message) errorDetail = errorData.message;
          } catch {}
          setMessage(`Registration Failed: ${errorDetail}`);
        }
        setIsError(true);
      }
    } catch (error) {
      console.error('Network Error:', error);
      setMessage('Error: Could not connect to the server.');
      setIsError(true);
    }
  };

  const handleCancel = () => {
    if (typeof closeModal === 'function') closeModal();
    else navigate('/');
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-900 relative overflow-hidden">
      {/* Prism background */}
      <div className="absolute inset-0 z-0">
        <Prism animationType="rotate" timeScale={0.5} />
      </div>

      {/* Register form */}
      <div className="z-10 w-full max-w-md mx-auto p-6 rounded-2xl shadow-lg bg-white/10 backdrop-blur-md">
        <h2 className="text-2xl font-bold text-center text-green-700 mb-6">Create Account</h2>

        {message && (
          <div
            className={`p-3 rounded text-center font-medium mb-4 ${
              isError
                ? 'bg-red-100 text-red-700 border border-red-400'
                : 'bg-green-100 text-green-700 border border-green-400'
            }`}
          >
            {message}
          </div>
        )}

        <form className="space-y-4" onSubmit={handleSubmit}>
          <div>
            <label className="block mb-1 font-medium text-white">First Name</label>
            <input
              type="text"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="Enter Your First Name"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              required
            />
          </div>

          <div>
            <label className="block mb-1 font-medium text-white">Last Name</label>
            <input
              type="text"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="Enter Your Last Name"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              required
            />
          </div>
          {errors.fullName && <p className="text-red-600 text-sm">{errors.fullName}</p>}

          <div>
            <label className="block mb-1 font-medium text-white">Contact Number</label>
            <input
              type="tel"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="+27XXXXXXXXX"
              value={contact}
              onChange={(e) => setContact(e.target.value)}
            />
            {errors.contact && <p className="text-red-600 text-sm">{errors.contact}</p>}
          </div>

          <div>
            <label className="block mb-1 font-medium text-white">Email</label>
            <input
              type="email"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="Enter Your Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            {errors.email && <p className="text-red-600 text-sm">{errors.email}</p>}
          </div>

          <div>
            <label className="block mb-1 font-medium text-white">Password</label>
            <input
              type="password"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="Enter a secure password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            {errors.password && <p className="text-red-600 text-sm">{errors.password}</p>}
          </div>

          <div>
            <label className="block mb-1 font-medium text-white">Confirm Password</label>
            <input
              type="password"
              className="w-full border p-2 rounded bg-white/20 text-white placeholder-gray-300"
              placeholder="Re-enter your password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
            {errors.confirmPassword && (
              <p className="text-red-600 text-sm">{errors.confirmPassword}</p>
            )}
          </div>

          <button
            type="submit"
            className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700 transition"
          >
            Register
          </button>
        </form>

        <div className="text-center text-sm text-white mt-4">
          <span>Already have an account? </span>
          <button
            onClick={() => {
              if (typeof switchToLogin === 'function') switchToLogin();
              else navigate('/'); 
            }}
            className="text-green-600 font-medium hover:underline"
          >
            Login
          </button>
        </div>
      </div>
    </div>
  );
};

export default Register;
