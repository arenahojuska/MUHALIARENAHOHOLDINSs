import React from 'react';
import Spotlight from '../assets/images/spotlight.jpeg';
import KidsCategory from '../assets/images/kids.png';
import MenCategory from '../assets/images/men.png';
import WomenCategory from '../assets/images/woman.png';
import { Categories } from '../assets/mockData';
import { ArrowRight } from 'lucide-react';

const categories = [
  {
    title: 'Men',
    imageUrl: MenCategory,
  },
  {
    title: 'Women',
    imageUrl: WomenCategory,
  },
  {
    title: 'Kids',
    imageUrl: KidsCategory,
  },
];

const CatergorySection = () => {
  return (
    <div className='container mx-auto px-4 md:px-8 lg:px-16 py-16'>
      {/* Section Header */}
      <div className="text-center mb-12">
        <h2 className="text-3xl md:text-4xl font-black text-gray-900 mb-3">
          Shop by Category
        </h2>
        <div className="w-24 h-1 bg-gradient-to-r from-red-600 to-red-400 mx-auto rounded-full"></div>
        <p className="text-gray-600 mt-4 text-lg">Explore our curated collections</p>
      </div>

      {/* Category Grid */}
      <div className='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8'>
        {categories.map((category, index) => (
          <div 
            key={index}
            className='group relative h-80 rounded-2xl overflow-hidden shadow-xl hover:shadow-2xl transform transition-all duration-500 hover:-translate-y-3 cursor-pointer'
          >
            {/* Image with zoom effect */}
            <img 
              src={category.imageUrl} 
              alt={category.title}
              className='w-full h-full object-cover transform group-hover:scale-110 transition-transform duration-700'
            />
            
            {/* Gradient Overlay */}
            <div className='absolute inset-0 bg-gradient-to-t from-black/80 via-black/40 to-transparent opacity-80 group-hover:opacity-90 transition-opacity duration-500'></div>
            
            {/* Content */}
            <div className='absolute bottom-0 left-0 right-0 p-8 transform transition-all duration-500'>
              <h3 className='text-3xl font-black text-white mb-2 transform group-hover:translate-y-0 transition-transform duration-500'>
                {category.title}
              </h3>
              
              <div className='flex items-center gap-2 text-white/90 group-hover:text-white transition-colors duration-300'>
                <span className='text-sm font-semibold'>View All</span>
                <ArrowRight className='w-5 h-5 transform group-hover:translate-x-2 transition-transform duration-300' />
              </div>

              {/* Animated underline */}
              <div className='h-1 bg-gradient-to-r from-red-500 to-red-600 rounded-full mt-3 w-0 group-hover:w-20 transition-all duration-500'></div>
            </div>

            {/* Hover border glow effect */}
            <div className='absolute inset-0 border-4 border-red-500/0 group-hover:border-red-500/30 rounded-2xl transition-all duration-500'></div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CatergorySection;
