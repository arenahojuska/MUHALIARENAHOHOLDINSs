import React from 'react';
import { Scale, MapPin, Phone, Mail } from 'lucide-react';

const Footer = () => {
  const currentYear = new Date().getFullYear();

  const navLinks = [
    { name: 'Home', href: '#home' },
    { name: 'About', href: '#about' },
    { name: 'Services', href: '#services' },
    { name: 'Team', href: '#team' },
    { name: 'Contact', href: '#contact' },
  ];

  const scrollToSection = (href) => {
    const element = document.querySelector(href);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <footer className="bg-slate-900 text-gray-300 pt-16 pb-8 px-4">
      <div className="max-w-7xl mx-auto">
        <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-12 mb-12">
          <div>
            <div className="flex items-center space-x-2 mb-4">
              <Scale className="w-8 h-8 text-amber-500" />
              <div>
                <h3 className="text-white font-bold text-lg">Tshikhakhisa Attorneys</h3>
              </div>
            </div>
            <p className="text-gray-400 mb-4">
              Professional legal services you can trust. Serving South Africa with dedication and excellence.
            </p>
          </div>

          <div>
            <h4 className="text-white font-semibold text-lg mb-4">Quick Links</h4>
            <ul className="space-y-2">
              {navLinks.map((link) => (
                <li key={link.name}>
                  <button
                    onClick={() => scrollToSection(link.href)}
                    className="text-gray-400 hover:text-amber-500 transition-colors duration-300"
                  >
                    {link.name}
                  </button>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h4 className="text-white font-semibold text-lg mb-4">Contact Info</h4>
            <ul className="space-y-3">
              <li className="flex items-start space-x-2">
                <MapPin className="w-5 h-5 text-amber-500 flex-shrink-0 mt-1" />
                <span className="text-gray-400">
                  Thohoyandou , VS Roodeport and Rustenburg
                </span>
              </li>
              <li className="flex items-center space-x-2">
                <Phone className="w-5 h-5 text-amber-500 flex-shrink-0" />
                <a href="tel:0813224164" className="text-gray-400 hover:text-amber-500 transition-colors">
                 +27813224164
                </a>
              </li>
              <li className="flex items-center space-x-2">
                <Mail className="w-5 h-5 text-amber-500 flex-shrink-0" />
                <a href="mailto:info@matshayaattorneys.co.za" className="text-gray-400 hover:text-amber-500 transition-colors">
                  info@tshikhakhisaattorneys.co.za
                </a>
              </li>
            </ul>
          </div>

          <div>
            <h4 className="text-white font-semibold text-lg mb-4">Office Hours</h4>
            <ul className="space-y-2 text-gray-400">
              <li>Monday - Friday</li>
              <li className="text-amber-500">8:00 AM - 5:00 PM</li>
              <li className="mt-3">Saturday</li>
              <li className="text-amber-500">By Appointment</li>
              <li className="mt-3">Sunday: Closed</li>
            </ul>
          </div>
        </div>

        <div className="border-t border-gray-800 pt-8">
          <div className="text-center text-gray-400">
            <p>&copy; {currentYear} Tshikhakhisa Attorneys. All rights reserved.</p>
            <p className="mt-2 text-sm">Professional Legal Services in South Africa</p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
