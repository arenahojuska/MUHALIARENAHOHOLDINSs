import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { FaSearch, FaShoppingCart, FaUser, FaTimes } from 'react-icons/fa';
import { useSelector, useDispatch } from 'react-redux';
import Modal from '../components/modal';
import Login from './Login';
import Register from './Register';
import { filterProducts } from '../redux/productSlice';

const Navbar = () => {
  const products = useSelector((state) => state.cart.products);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [searchTerm, setSearchTerm] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isLogin, setIsLogin] = useState(true);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser =
      JSON.parse(localStorage.getItem('user')) ||
      JSON.parse(sessionStorage.getItem('user'));
    setUser(storedUser);
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    dispatch(filterProducts(searchTerm));
    navigate('/shop');
  };

  const handleClear = () => {
    setSearchTerm('');
    dispatch(filterProducts(''));
    navigate('/shop');
  };

  const handleLogout = () => {
    localStorage.removeItem('user');
    sessionStorage.removeItem('user');
    setUser(null);
    navigate('/');
  };

  return (
    <nav className="bg-white shadow-md">
      <div className="container mx-auto px-4 md:px-16 lg:px-24 py-4 flex flex-wrap justify-between items-center">
        {/* Logo */}
        <div className="text-lg font-bold text-red-600">
          <Link to={user ? "/home" : "/"}>Muhali E-Shop</Link>
        </div>

        {/* Search */}
        <div className="relative flex-1 mx-4 max-w-md">
          <form onSubmit={handleSearch} className="relative">
            <input
              type="text"
              placeholder="Search Product"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full border py-2 px-4 rounded pr-10"
            />
            {searchTerm && (
              <button
                type="button"
                onClick={handleClear}
                className="absolute top-3 right-8 text-gray-500 hover:text-red-600"
              >
                <FaTimes />
              </button>
            )}
            <button type="submit">
              <FaSearch className="absolute top-3 right-3 text-red-500" />
            </button>
          </form>
        </div>

        {/* Cart & Auth */}
        <div className="flex items-center space-x-4">
          <Link to="/cart" className="relative">
            <FaShoppingCart className="text-lg" />
            {products.length > 0 && (
              <span className="absolute -top-2 -right-2 bg-red-600 text-white text-xs rounded-full px-1">
                {products.length}
              </span>
            )}
          </Link>

          {user ? (
            <div className="flex items-center space-x-3">
              <span className="text-sm font-medium text-gray-700">
                Welcome, {user.name || user.email || 'User'}
              </span>
              <button
                onClick={handleLogout}
                className="text-red-600 text-sm font-semibold hover:underline"
              >
                Logout
              </button>
            </div>
          ) : (
            <>
              <button
                className="hidden md:block"
                onClick={() => {
                  setIsModalOpen(true);
                  setIsLogin(true);
                }}
              >
                Login | Register
              </button>
              <button
                className="block md:hidden"
                onClick={() => {
                  setIsModalOpen(true);
                  setIsLogin(true);
                }}
              >
                <FaUser />
              </button>
            </>
          )}
        </div>
      </div>

      {/* Navigation Links */}
      <div className="flex items-center justify-center space-x-10 py-4 text-sm font-bold">
        <Link to={user ? "/home" : "/"} className="hover:underline">Home</Link>
        <Link to="/shop" className="hover:underline">Shop</Link>
        <Link to="/contact" className="hover:underline">Contact</Link>
        <Link to="/about" className="hover:underline">About</Link>
      </div>

      {/* Modal with Login/Register */}
      <Modal isModelOpen={isModalOpen} setIsModelOpen={setIsModalOpen}>
        {isLogin ? (
          <Login
            switchToRegister={() => setIsLogin(false)}
            onLoginSuccess={(userData) => {
              setUser(userData);
              setIsModalOpen(false);
            }}
          />
        ) : (
          <Register
            switchToLogin={() => setIsLogin(true)}
            closeModal={() => setIsModalOpen(false)}
          />
        )}
      </Modal>
    </nav>
  );
};

export default Navbar;
