import React, { useState } from 'react';
import { FaStar } from 'react-icons/fa';
import { addToCart } from '../redux/cartSlice';
import { useDispatch } from 'react-redux';
import { ShoppingCart, Check } from 'lucide-react';

const ProductCard = ({ product }) => {
  const dispatch = useDispatch();
  const [added, setAdded] = useState(false);

  const handleAddToCart = (e, product) => {
    e.stopPropagation();
    e.preventDefault();
    dispatch(addToCart(product));
    setAdded(true);
    setTimeout(() => setAdded(false), 2000); 
  };

  return (
    <div className="group relative bg-white rounded-2xl shadow-lg hover:shadow-2xl border-2 border-gray-100 hover:border-red-200 transform transition-all duration-500 hover:-translate-y-2 overflow-hidden">
      {/* Image Container with overlay */}
      <div className="relative overflow-hidden bg-gray-50 rounded-t-2xl">
        <img
          src={product.image}
          alt={product.name}
          className="w-full h-56 object-contain p-4 transform group-hover:scale-110 transition-transform duration-500"
        />
        
        {/* Gradient overlay on hover */}
        <div className="absolute inset-0 bg-gradient-to-t from-black/10 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
      </div>

      {/* Content */}
      <div className="p-5 space-y-3">
        <h3 className="text-lg font-bold text-gray-900 line-clamp-2 group-hover:text-red-600 transition-colors duration-300">
          {product.name}
        </h3>
        
        <div className="flex items-center justify-between">
          <p className="text-2xl font-black text-red-600">
            R{product.price}
          </p>
          
          {/* Star Rating */}
          <div className="flex gap-1">
            {[...Array(4)].map((_, i) => (
              <FaStar key={i} className="text-yellow-400 w-4 h-4" />
            ))}
          </div>
        </div>

        {/* Add to Cart Button */}
        <button
          onClick={(e) => handleAddToCart(e, product)}
          className="w-full bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 text-white font-bold py-3 rounded-xl shadow-md hover:shadow-xl transform transition-all duration-300 hover:scale-105 flex items-center justify-center gap-2 group/btn"
        >
          <ShoppingCart className="w-5 h-5 transform group-hover/btn:rotate-12 transition-transform duration-300" />
          <span>Add to Cart</span>
        </button>
      </div>

      {/* Success Notification */}
      {added && (
        <div className="absolute top-4 left-1/2 -translate-x-1/2 bg-green-500 text-white text-sm font-semibold px-4 py-2 rounded-full shadow-xl flex items-center gap-2 animate-fade-in z-10">
          <Check className="w-4 h-4" />
          <span>Added to cart!</span>
        </div>
      )}

      {/* Decorative corner accent */}
      <div className="absolute top-0 right-0 w-20 h-20 bg-gradient-to-br from-red-500/10 to-transparent rounded-bl-full opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
    </div>
  );
};

export default ProductCard;
