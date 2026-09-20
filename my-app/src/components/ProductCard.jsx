import React, { useState } from 'react';
import { FaStar } from 'react-icons/fa';
import { addToCart } from '../redux/cartSlice';
import { useDispatch } from 'react-redux';
import { ShoppingCart, Check, X } from 'lucide-react';

const ProductCard = ({ product }) => {
  const dispatch = useDispatch();

  // 🔹 State for showing “Added to Cart” toast
  const [added, setAdded] = useState(false);

  // 🔹 State to toggle product details modal
  const [showDetails, setShowDetails] = useState(false);

  // 🔹 State for selected color and size
  const [selectedColor, setSelectedColor] = useState('Red');
  const [selectedSize, setSelectedSize] = useState('M');

  // ✅ Add to Cart functionality
  const handleAddToCart = (e, product) => {
    e.stopPropagation();
    e.preventDefault();
    // Include selected options in cart payload
    dispatch(addToCart({ ...product, color: selectedColor, size: selectedSize }));
    setAdded(true);
    setTimeout(() => setAdded(false), 2000);
  };

  return (
    <>
      {/* 🟩 PRODUCT CARD */}
      <div
        className="group relative bg-white rounded-2xl shadow-lg hover:shadow-2xl border-2 border-gray-100 hover:border-red-200 transform transition-all duration-500 hover:-translate-y-2 overflow-hidden cursor-pointer"
        onClick={() => setShowDetails(true)} // opens product detail modal
      >
        {/* 🖼️ IMAGE SECTION */}
        <div className="relative overflow-hidden bg-gray-50 rounded-t-2xl">
          <img
            src={product.image}
            alt={product.name}
            className="w-full h-56 object-contain p-4 transform group-hover:scale-110 transition-transform duration-500"
          />
          {/* Gradient overlay when hovering */}
          <div className="absolute inset-0 bg-gradient-to-t from-black/10 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
        </div>

        {/* 🧾 PRODUCT INFO */}
        <div className="p-5 space-y-3">
          <h3 className="text-lg font-bold text-gray-900 line-clamp-2 group-hover:text-red-600 transition-colors duration-300">
            {product.name}
          </h3>

          <div className="flex items-center justify-between">
            <p className="text-2xl font-black text-red-600">R{product.price}</p>

            {/* Star rating display */}
            <div className="flex gap-1">
              {[...Array(4)].map((_, i) => (
                <FaStar key={i} className="text-yellow-400 w-4 h-4" />
              ))}
            </div>
          </div>

          {/* 🛒 ADD TO CART BUTTON */}
          <button
            onClick={(e) => handleAddToCart(e, product)}
            className="w-full bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white font-bold py-3 rounded-xl shadow-md hover:shadow-xl transform transition-all duration-300 hover:scale-105 flex items-center justify-center gap-2 group/btn"
          >
            <ShoppingCart className="w-5 h-5 transform group-hover/btn:rotate-12 transition-transform duration-300" />
            <span>Add to Cart</span>
          </button>
        </div>

        {/* ✅ ADDED TO CART TOAST */}
        {added && (
          <div className="absolute top-4 left-1/2 -translate-x-1/2 bg-green-500 text-white text-sm font-semibold px-4 py-2 rounded-full shadow-xl flex items-center gap-2 animate-fade-in z-10">
            <Check className="w-4 h-4" />
            <span>Added to cart!</span>
          </div>
        )}

        {/* Decorative corner accent */}
        <div className="absolute top-0 right-0 w-20 h-20 bg-gradient-to-br from-red-500/10 to-transparent rounded-bl-full opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
      </div>

      {/* 🟥 PRODUCT DETAIL MODAL */}
      {showDetails && (
        <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50 p-4">
          <div className="bg-white rounded-2xl shadow-2xl max-w-2xl w-full relative overflow-hidden max-h-[90vh] flex flex-col">
            {/* ❌ Close button */}
            <button
              className="absolute top-4 right-4 text-gray-500 hover:text-red-600 transition-colors"
              onClick={() => setShowDetails(false)}
            >
              <X className="w-6 h-6" />
            </button>

            {/* Scrollable content */}
            <div className="overflow-y-auto p-6 md:p-8">
              <div className="grid md:grid-cols-2 gap-6 items-center">
                {/* 🖼️ Larger Product Image */}
                <div className="flex items-center justify-center bg-gray-50 rounded-xl p-4">
                  <img
                    src={product.image}
                    alt={product.name}
                    className="w-full h-64 object-contain"
                  />
                </div>

                {/* 🧾 Product Details */}
                <div className="flex flex-col justify-between">
                  <div>
                    <h2 className="text-2xl font-bold text-gray-900 mb-2">{product.name}</h2>
                    <p className="text-red-600 text-3xl font-black mb-3">R{product.price}</p>
                    <p className="text-gray-600 text-sm mb-4 leading-relaxed">{product.description}</p>

                    {/* Star rating display inside modal */}
                    <div className="flex gap-1 mb-4">
                      {[...Array(4)].map((_, i) => (
                        <FaStar key={i} className="text-yellow-400 w-5 h-5" />
                      ))}
                    </div>

                    {/* 🔹 Product Options */}
                    <div className="flex flex-col gap-4 mb-4">
                      {/* Color selection */}
                      <div>
                        <label className="text-gray-700 font-medium mb-1 block">Color</label>
                        <select
                          value={selectedColor}
                          onChange={(e) => setSelectedColor(e.target.value)}
                          className="border border-gray-300 rounded-xl p-2 w-full focus:ring-2 focus:ring-red-300"
                        >
                          <option>Red</option>
                          <option>Blue</option>
                          <option>Black</option>
                          <option>White</option>
                        </select>
                      </div>

                      {/* Size selection */}
                      <div>
                        <label className="text-gray-700 font-medium mb-1 block">Size</label>
                        <select
                          value={selectedSize}
                          onChange={(e) => setSelectedSize(e.target.value)}
                          className="border border-gray-300 rounded-xl p-2 w-full focus:ring-2 focus:ring-red-300"
                        >
                          <option>S</option>
                          <option>M</option>
                          <option>L</option>
                          <option>XL</option>
                        </select>
                      </div>
                    </div>
                  </div>

                  {/* 🛒 Add to Cart (inside modal) */}
                  <button
                    onClick={(e) => handleAddToCart(e, product)}
                    className="mt-4 bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white font-bold py-3 rounded-xl shadow-md hover:shadow-xl transition-all duration-300 flex items-center justify-center gap-2"
                  >
                    <ShoppingCart className="w-5 h-5" />
                    <span>Add to Cart</span>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default ProductCard;
