import React from 'react';
import { Mail, Phone, MapPin, Facebook, Twitter, Linkedin, Instagram, ArrowRight } from 'lucide-react';

const Footer = () => {
  return (
    <footer className="bg-gradient-to-b from-gray-900 via-gray-900 to-black text-white relative overflow-hidden">
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_20%_50%,rgba(59,130,246,0.1),transparent_50%)]"></div>
      <div className="absolute inset-0 bg-[radial-gradient(circle_at_80%_50%,rgba(6,182,212,0.1),transparent_50%)]"></div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 relative">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-12 mb-16">
          <div className="lg:col-span-1">
            <div className="inline-flex items-center gap-2 mb-6">
              <div className="w-10 h-10 rounded-lg bg-gradient-to-br from-blue-500 to-cyan-500 flex items-center justify-center font-bold text-white">
                MS
              </div>
              <span className="text-2xl font-bold bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Muhaliit</span>
            </div>
            <p className="text-gray-400 leading-relaxed mb-6">
              Your one-stop shop for premium products. Fast delivery and excellent support guaranteed.
            </p>
            <div className="flex gap-4">
              {[
                { icon: Facebook, label: 'Facebook' },
                { icon: Twitter, label: 'Twitter' },
                { icon: Linkedin, label: 'LinkedIn' },
                { icon: Instagram, label: 'Instagram' }
              ].map((social, index) => (
                <a
                  key={index}
                  href="#"
                  className="w-10 h-10 rounded-lg bg-white/10 hover:bg-gradient-to-br hover:from-blue-500 hover:to-cyan-500 flex items-center justify-center transition-all duration-300 group"
                  title={social.label}
                >
                  <social.icon className="w-5 h-5 group-hover:scale-110 transition-transform duration-300" />
                </a>
              ))}
            </div>
          </div>

          <div>
            <h3 className="text-lg font-bold mb-6 flex items-center gap-2">
              <div className="w-1 h-6 bg-gradient-to-b from-blue-500 to-cyan-500 rounded-full"></div>
              Quick Links
            </h3>
            <ul className="space-y-3">
              {[
                { label: 'Home', href: '/' },
                { label: 'Shop', href: '/shop' },
                { label: 'About', href: '/about' },
                { label: 'Contact', href: '/contact' }
              ].map((link, index) => (
                <li key={index}>
                  <a
                    href={link.href}
                    className="inline-flex items-center gap-2 text-gray-400 hover:text-white transition-all duration-300 group"
                  >
                    <span className="w-1.5 h-1.5 rounded-full bg-blue-500 opacity-0 group-hover:opacity-100 transition-opacity duration-300"></span>
                    {link.label}
                    <ArrowRight className="w-3 h-3 opacity-0 group-hover:opacity-100 group-hover:translate-x-1 transition-all duration-300" />
                  </a>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h3 className="text-lg font-bold mb-6 flex items-center gap-2">
              <div className="w-1 h-6 bg-gradient-to-b from-blue-500 to-cyan-500 rounded-full"></div>
              Support
            </h3>
            <ul className="space-y-3 text-gray-400">
              <li><a href="#" className="hover:text-white transition-colors duration-300">FAQ</a></li>
              <li><a href="#" className="hover:text-white transition-colors duration-300">Shipping Info</a></li>
              <li><a href="#" className="hover:text-white transition-colors duration-300">Returns</a></li>
              <li><a href="#" className="hover:text-white transition-colors duration-300">Track Order</a></li>
            </ul>
          </div>

          <div>
            <h3 className="text-lg font-bold mb-6 flex items-center gap-2">
              <div className="w-1 h-6 bg-gradient-to-b from-blue-500 to-cyan-500 rounded-full"></div>
              Get In Touch
            </h3>
            <div className="space-y-4">
              <a
                href="mailto:support@muhaliitsolutions.com"
                className="flex items-start gap-3 text-gray-400 hover:text-white transition-colors duration-300 group"
              >
                <Mail className="w-5 h-5 mt-0.5 group-hover:scale-110 transition-transform duration-300 text-blue-500" />
                <span className="text-sm">support@muhaliitsolutions.com</span>
              </a>
              <a
                href="tel:+27123456789"
                className="flex items-start gap-3 text-gray-400 hover:text-white transition-colors duration-300 group"
              >
                <Phone className="w-5 h-5 mt-0.5 group-hover:scale-110 transition-transform duration-300 text-blue-500" />
                <span className="text-sm">+27 123 456 789</span>
              </a>
              <div className="flex items-start gap-3 text-gray-400">
                <MapPin className="w-5 h-5 mt-0.5 text-blue-500" />
                <span className="text-sm">South Africa</span>
              </div>
            </div>
          </div>
        </div>

        <div className="border-t border-white/10 pt-8">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mb-8">
            <div>
              <h4 className="text-sm font-semibold mb-4 text-gray-300">Newsletter</h4>
              <p className="text-sm text-gray-400 mb-4">Subscribe for updates and special offers</p>
              <div className="flex gap-2">
                <input
                  type="email"
                  placeholder="Enter your email"
                  className="flex-1 px-4 py-3 rounded-lg bg-white/10 border border-white/20 text-white placeholder-gray-500 focus:outline-none focus:border-blue-500 focus:bg-white/20 transition-all duration-300"
                />
                <button className="px-6 py-3 rounded-lg bg-gradient-to-r from-blue-600 to-cyan-600 text-white font-semibold hover:from-blue-700 hover:to-cyan-700 transition-all duration-300 hover:scale-105">
                  Join
                </button>
              </div>
            </div>

            <div>
              <h4 className="text-sm font-semibold mb-4 text-gray-300">Legal</h4>
              <div className="flex flex-wrap gap-4 text-sm">
                <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">Privacy Policy</a>
                <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">Terms of Service</a>
                <a href="#" className="text-gray-400 hover:text-white transition-colors duration-300">Cookies</a>
              </div>
            </div>
          </div>

          <div className="border-t border-white/10 pt-8 flex flex-col md:flex-row items-center justify-between">
            <p className="text-gray-500 text-sm">
              &copy; 2025 Muhaliit Solutions. All rights reserved.
            </p>
            <p className="text-gray-500 text-sm mt-4 md:mt-0">
              Crafted with passion for excellence
            </p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
