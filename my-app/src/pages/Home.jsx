import React, { useEffect } from 'react';
import { Categories, mockData } from '../assets/mockData';
import Spotlight from '../assets/images/spotlight.jpeg';
import InfoSection from '../components/infoSection';
import CatergorySection from '../components/CatergorySection';
import { setProducts } from '../redux/productSlice';
import { useDispatch, useSelector } from 'react-redux';
import ProductCard from '../components/ProductCard';
import { Link } from 'react-router-dom';
import TextType from '../components/TextType';

const Home = () => {
  const dispatch = useDispatch();
  const products = useSelector(state => state.product);

  useEffect(() => {
    dispatch(setProducts(mockData));
  }, [dispatch]);

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-white">
      <div className="container mx-auto px-4 md:px-8 lg:px-16 py-8">
        <div className="flex flex-col lg:flex-row gap-6">
          {/* Sidebar Categories */}
          <aside className="w-full lg:w-64 flex-shrink-0">
            <div className="sticky top-4">
              <div className="bg-gradient-to-r from-red-600 to-red-700 text-white text-sm font-bold px-4 py-3 rounded-t-lg shadow-lg">
                <span className="flex items-center gap-2">
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                  </svg>
                  SHOP BY CATEGORIES
                </span>
              </div>
              <ul className="bg-white border border-gray-200 rounded-b-lg shadow-md overflow-hidden">
                {Categories.map((category, index) => (
                  <li 
                    key={index} 
                    className="group hover:bg-red-50 transition-all duration-200 border-b border-gray-100 last:border-b-0"
                  >
                    <button className="w-full flex items-center text-sm font-medium px-4 py-3 text-gray-700 group-hover:text-red-600 group-hover:translate-x-1 transition-all duration-200">
                      <div className="w-2 h-2 bg-red-500 rounded-full mr-3 group-hover:scale-125 transition-transform duration-200"></div>
                      {category}
                    </button>
                  </li>
                ))}
              </ul>
            </div>
          </aside>

          {/* Spotlight Banner */}
          <div className="flex-1">
            <div className="relative rounded-2xl overflow-hidden shadow-2xl group">
              <img 
                src={Spotlight} 
                alt="Featured products" 
                className="w-full h-[400px] md:h-[500px] object-cover transform group-hover:scale-105 transition-transform duration-700" 
              />
              <div className="absolute inset-0 bg-gradient-to-t from-black/70 via-black/30 to-transparent"></div>
              
              <div className="absolute bottom-0 left-0 right-0 p-8 md:p-12">
                <p className="text-red-400 mb-3 text-sm font-semibold tracking-wider uppercase">
                  Code with MUHALI A.J
                </p>

                {/* Typing Animation for Heading */}
                <TextType
                  text={["WELCOME TO MUHALI ONLINE STORE"]}
                  typingSpeed={70}
                  pauseDuration={3000}
                  loop={false}
                  className="text-3xl md:text-4xl lg:text-5xl font-black text-white mb-3 drop-shadow-lg"
                />

                {/* Typing Animation for Tagline */}
                <TextType
                  text={[
                    "MILLIONS + PRODUCTS",
                    "BEST DEALS EVERYDAY",
                    "FAST & RELIABLE DELIVERY 🚚"
                  ]}
                  typingSpeed={60}
                  deletingSpeed={40}
                  pauseDuration={1500}
                  textColors={["#f87171", "#60a5fa", "#34d399"]}
                  loop={true}
                  className="text-xl md:text-2xl font-bold mb-6 h-8"
                />

                <Link to="/shop">
                  <button className="bg-gradient-to-r from-red-600 to-red-700 hover:from-red-700 hover:to-red-800 px-10 py-3.5 text-white font-bold rounded-full shadow-xl hover:shadow-2xl transform transition-all duration-300 hover:scale-105 hover:-translate-y-1 uppercase tracking-wide text-sm">
                    SHOP NOW
                    <svg className="inline-block ml-2 w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7l5 5m0 0l-5 5m5-5H6" />
                    </svg>
                  </button>
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Info Section */}
      <div className="bg-white">
        <InfoSection />
      </div>

      {/* Category Section */}
      <div className="bg-gradient-to-b from-white to-gray-50">
        <CatergorySection />
      </div>

      {/* Products */}
      <div className='container mx-auto px-4 md:px-8 lg:px-16 py-16'>
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-black text-gray-900 mb-3">
            Most Selling Products
          </h2>
          <div className="w-24 h-1 bg-gradient-to-r from-red-600 to-red-400 mx-auto rounded-full"></div>
          <p className="text-gray-600 mt-4 text-lg">Discover our top-rated items loved by thousands</p>
        </div>
        
        <div className='grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6'>
          {products.products.slice(0, 5).map((product, index) => (
            <div 
              key={index} 
              className="transform transition-all duration-300 hover:-translate-y-2"
            >
              <ProductCard product={product} />
            </div>
          ))}
        </div>
      </div>
    </div> 
  );
};

export default Home;
