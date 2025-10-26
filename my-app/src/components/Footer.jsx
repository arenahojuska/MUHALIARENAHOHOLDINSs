import React from 'react';
import { FaFacebook, FaGithub, FaLinkedin, FaTwitter } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const Footer = () => {
  return (
    <footer className="bg-gray-200 text-gray-800 py-8 px-4 md:px-16 lg:px-24">
      <div className="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-8">
        {/* Company Info */}
        <div>
          <h3 className="text-xl font-bold mb-2">MUHALI HOLDINGS SHOP</h3>
          <p>
            Your one stop for all your shopping needs. Shop with us and experience the best online shopping experience.
          </p>
        </div>

        {/* Quick Links */}
        <div>
          <h4 className="text-lg font-semibold mb-2">Quick Links</h4>
          <ul className="space-y-1">
            <li><Link className="hover:underline" to="/">Home</Link></li>
            <li><Link className="hover:underline" to="/shop">Shop</Link></li>
            <li><Link className="hover:underline" to="/contact">Contact</Link></li>
            <li><Link className="hover:underline" to="/about">About</Link></li>
          </ul>
        </div>

        {/* Social + Subscribe */}
        <div className="flex flex-col md:items-center">
          <h4 className="text-lg font-semibold mb-2">Follow Us</h4>
          <div className="flex space-x-4 mb-4">
            <a href="#"><FaFacebook className="text-blue-600 text-xl hover:text-gray-500 transition" /></a>
            <a href="#"><FaTwitter className="text-blue-400 text-xl hover:text-gray-500 transition" /></a>
            <a href="#"><FaLinkedin className="text-blue-700 text-xl hover:text-gray-500 transition" /></a>
            <a href="#"><FaGithub className="text-gray-700 text-xl hover:text-gray-500 transition" /></a>
          </div>

          <form className="flex flex-col space-y-2 w-full md:w-auto">
            <input type="email" placeholder="Enter your email" className="p-2 border rounded" />
            <button className="bg-red-600 text-white py-2 rounded hover:bg-red-700 transition">Contact Us</button>
          </form>

          <div className="mt-8 border-t border-gray-700 pt-4 flex flex-col md:flex-row justify-between items-center w-full">
            <p>&copy; 2025 Muhali E-shop. All rights reserved.</p>
            <div className="flex space-x-4 mt-2 md:mt-0">
              <a href="#" className="hover:underline">Privacy Policy</a>
              <a href="#" className="hover:underline">Terms & Conditions</a>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
