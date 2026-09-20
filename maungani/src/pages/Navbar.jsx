import { Menu, X, Scale } from 'lucide-react';
import { useState } from 'react';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="bg-white text-gray-900 sticky top-0 z-50 shadow-sm border-b border-gray-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <div className="flex items-center space-x-2">
            <div className="w-8 h-8 rounded-lg bg-gradient-to-br from-blue-600 to-blue-800 flex items-center justify-center">
              <Scale size={20} className="text-white" />
            </div>
            <span className="text-lg font-bold text-gray-900">PM Mahlathi</span>
          </div>

          <div className="hidden md:flex space-x-8">
            <a href="#home" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">Home</a>
            <a href="#about" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">About</a>
            <a href="#services" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">Services</a>
            <a href="#team" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">Team</a>
            <a href="#testimonials" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">Testimonials</a>
            <a href="#contact" className="text-sm font-medium text-gray-700 hover:text-blue-600 transition">Contact</a>
          </div>

          <button
            className="md:hidden"
            onClick={() => setIsOpen(!isOpen)}
          >
            {isOpen ? <X size={24} /> : <Menu size={24} />}
          </button>
        </div>
      </div>

      {isOpen && (
        <div className="md:hidden bg-gray-50 border-t border-gray-200">
          <div className="px-2 pt-2 pb-3 space-y-1">
            <a href="#home" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">Home</a>
            <a href="#about" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">About</a>
            <a href="#services" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">Services</a>
            <a href="#team" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">Team</a>
            <a href="#testimonials" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">Testimonials</a>
            <a href="#contact" className="block px-3 py-2 text-gray-700 hover:bg-gray-100 rounded">Contact</a>
          </div>
        </div>
      )}
    </nav>
  );
}
