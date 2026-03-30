import { useState } from 'react';
import { Menu, X, Phone } from 'lucide-react';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  const navLinks = [
    { name: 'Home', href: '#home' },
    { name: 'About', href: '#about' },
    { name: 'Rooms', href: '#rooms' },
    { name: 'Gallery', href: '#gallery' },
    { name: 'Pricing', href: '#pricing' },
    { name: 'Contact', href: '#contact' },
  ];

  return (
    <nav className="sticky top-0 z-50 bg-white/95 backdrop-blur-md border-b-2 border-[#E8D4BA] shadow-lg">
      <div className="container mx-auto px-4">
        <div className="flex justify-between items-center py-4">
          <div className="flex items-center space-x-2">
            <div className="w-12 h-12 bg-gradient-to-br from-[#C4A574] to-[#8B6F47] rounded-full flex items-center justify-center">
              <span className="text-white font-bold text-lg">ML</span>
            </div>
            <div>
              <h1 className="text-2xl font-serif italic text-[#8B6F47] font-bold">
                Maungani
              </h1>
              <p className="text-xs text-[#6B5744]">Lodge</p>
            </div>
          </div>

          <div className="hidden md:flex items-center space-x-8">
            {navLinks.map((link) => (
              <a
                key={link.name}
                href={link.href}
                className="text-[#6B5744] hover:text-[#8B6F47] transition-colors font-medium text-sm"
              >
                {link.name}
              </a>
            ))}
          </div>

          <div className="hidden md:flex items-center space-x-4">
            <a
              href="tel:0715803599"
              className="flex items-center bg-[#8B6F47] hover:bg-[#6B5744] text-white px-6 py-2 rounded-full text-sm font-semibold transition-all duration-300 transform hover:scale-105"
            >
              <Phone className="w-4 h-4 mr-2" />
              071 580 3599
            </a>
          </div>

          <button
            onClick={() => setIsOpen(!isOpen)}
            className="md:hidden text-[#8B6F47] hover:text-[#6B5744] transition-colors"
            aria-label="Toggle menu"
          >
            {isOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
          </button>
        </div>

        {isOpen && (
          <div className="md:hidden pb-4 space-y-3 border-t border-[#E8D4BA] pt-4">
            {navLinks.map((link) => (
              <a
                key={link.name}
                href={link.href}
                className="block text-[#6B5744] hover:text-[#8B6F47] transition-colors font-medium py-2"
                onClick={() => setIsOpen(false)}
              >
                {link.name}
              </a>
            ))}
            <a
              href="tel:0715803599"
              className="flex items-center bg-[#8B6F47] hover:bg-[#6B5744] text-white px-6 py-3 rounded-full font-semibold transition-all duration-300 w-full justify-center mt-4"
            >
              <Phone className="w-4 h-4 mr-2" />
              071 580 3599
            </a>
          </div>
        )}
      </div>
    </nav>
  );
}
