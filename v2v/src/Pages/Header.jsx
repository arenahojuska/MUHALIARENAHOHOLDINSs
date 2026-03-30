import { useState, useEffect } from 'react';
import { Bus, Menu, X } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const Header = ({ handleTransport, handleBookNow }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [isScrolled, setIsScrolled] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 10);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const handleNavClick = (id) => {
    setIsOpen(false); // close mobile menu
    switch (id) {
      case 'transport':
        handleTransport(); // scroll to Transport
        break;
      case 'booking':
        handleBookNow(); // scroll to Transport (Book Transport)
        break;
      case 'accommodation':
        navigate('/accommodation'); // navigate to accommodation page
        break;
      case 'contact':
        navigate('/contact'); // navigate to contact page
        break;
      case 'hero':
        navigate('/');
        break;
      default:
        break;
    }
  };

  const navItems = [
    { label: 'Transport', id: 'transport' },
    { label: 'Book Now', id: 'booking' },
    { label: 'Accommodation', id: 'accommodation' },
    { label: 'Contact', id: 'contact' },
  ];

  return (
    <header
      className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 ${
        isScrolled
          ? 'bg-white shadow-md'
          : 'bg-white/80 backdrop-blur-md shadow-sm'
      }`}
    >
      <nav className="container mx-auto px-4 sm:px-6 lg:px-8 py-4">
        <div className="flex items-center justify-between">
          {/* Logo */}
          <button
            onClick={() => handleNavClick('hero')}
            className="flex items-center space-x-3 group hover:opacity-80 transition"
          >
            <div className="p-2 rounded-lg bg-gradient-to-br from-emerald-600 to-emerald-700 group-hover:shadow-lg transition-shadow">
              <Bus className="w-6 h-6 text-white" />
            </div>
            <div className="flex flex-col">
              <span className="text-2xl font-bold bg-gradient-to-r from-emerald-600 to-teal-600 bg-clip-text text-transparent">
                V2V
              </span>
              <span className="text-xs font-semibold text-gray-500 leading-none">Student Transport</span>
            </div>
          </button>

          {/* Desktop Menu */}
          <div className="hidden md:flex items-center space-x-8">
            {navItems.map((item) => (
              <button
                key={item.id}
                onClick={() => handleNavClick(item.id)}
                className="relative text-gray-700 font-medium text-sm hover:text-emerald-600 transition-colors group"
              >
                {item.label}
                <span className="absolute bottom-0 left-0 w-0 h-0.5 bg-gradient-to-r from-emerald-600 to-teal-600 group-hover:w-full transition-all duration-300" />
              </button>
            ))}
          </div>

          {/* Desktop Book Now button */}
          <button
            onClick={() => handleNavClick('booking')}
            className="hidden sm:block px-6 py-2.5 bg-gradient-to-r from-emerald-600 to-emerald-700 text-white font-semibold rounded-lg hover:shadow-lg hover:shadow-emerald-200 transition-all duration-300 transform hover:scale-105"
          >
            Book Now
          </button>

          {/* Mobile Hamburger */}
          <button
            onClick={() => setIsOpen(!isOpen)}
            className="md:hidden p-2 hover:bg-gray-100 rounded-lg transition"
          >
            {isOpen ? <X className="w-6 h-6 text-gray-900" /> : <Menu className="w-6 h-6 text-gray-900" />}
          </button>
        </div>

        {/* Mobile Menu */}
        {isOpen && (
          <div className="md:hidden mt-4 pb-4 animate-in fade-in slide-in-from-top-2 duration-200">
            <div className="flex flex-col space-y-3">
              {navItems.map((item) => (
                <button
                  key={item.id}
                  onClick={() => handleNavClick(item.id)}
                  className="text-left px-4 py-3 text-gray-700 hover:text-emerald-600 hover:bg-emerald-50 rounded-lg transition-all font-medium"
                >
                  {item.label}
                </button>
              ))}
              <button
                onClick={() => handleNavClick('booking')}
                className="w-full mt-2 px-4 py-3 bg-gradient-to-r from-emerald-600 to-emerald-700 text-white font-semibold rounded-lg hover:shadow-lg transition-all"
              >
                Book Now
              </button>
            </div>
          </div>
        )}
      </nav>
    </header>
  );
};

export default Header;
