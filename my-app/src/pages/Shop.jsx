import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ProductCard from '../components/ProductCard';
import { sortProducts } from '../redux/productSlice';
import { ChevronDown } from "lucide-react";
import TextType from '../components/TextType'; // 👈 import the typing effect

const Shop = () => {
  const products = useSelector(state => state.product.filteredProducts);
  const dispatch = useDispatch();

  const handleSort = (e) => {
    dispatch(sortProducts({ type: e.target.value }));
  };

  return (
    <div className="mx-auto py-12 px-4 md:px-16 lg:px-24">
      <h2 className="text-2xl font-bold mb-2 text-center">Shop</h2>
      
      {/* Typing Animation */}
      <div className="text-center mb-6">
        <TextType
          text={["Best deals every day!", "Shop with confidence.", "Fast delivery 🚀"]}
          typingSpeed={60}
          deletingSpeed={40}
          pauseDuration={1500}
          textColors={["#f87171", "#60a5fa", "#34d399"]}
          loop={true}
          className="text-lg font-medium"
        />
      </div>

    {/* Filter Dropdown */}
<div className="flex justify-end mb-6">
  <div className="relative group">
    <select
      onChange={handleSort}
      className="appearance-none bg-white border-2 border-gray-200 hover:border-red-400 rounded-xl pl-4 pr-10 py-3 text-sm font-medium text-gray-700 focus:ring-4 focus:ring-red-100 focus:border-red-500 transition-all duration-300 cursor-pointer shadow-md hover:shadow-lg z-20"
    >
      <option value="" className="bg-white">Sort By</option>
      <option value="name-asc" className="bg-white hover:bg-red-50">Name (A–Z)</option>
      <option value="name-desc" className="bg-white hover:bg-red-50">Name (Z–A)</option>
      <option value="price-asc" className="bg-white hover:bg-red-50">Price (Low to High)</option>
      <option value="price-desc" className="bg-white hover:bg-red-50">Price (High to Low)</option>
    </select>
    <div className="absolute inset-y-0 right-0 flex items-center pr-3 pointer-events-none">
      <ChevronDown className="w-5 h-5 text-red-500 group-hover:text-red-600 transition-colors duration-300" />
    </div>
    {/* Decorative gradient border effect */}
    <div className="absolute inset-0 bg-gradient-to-r from-red-400 to-red-600 rounded-xl opacity-0 group-hover:opacity-10 transition-opacity duration-300 -z-10"></div>
  </div>
</div>


      {/* Product Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 lg:grid-cols-5 gap-6 cursor-pointer">
        {products.length > 0 ? (
          products.map((product, index) => (
            <ProductCard key={index} product={product} />
          ))
        ) : (
          <p className="text-center text-gray-500 col-span-full">No products found.</p>
        )}
      </div>
    </div>
  );
};

export default Shop;
