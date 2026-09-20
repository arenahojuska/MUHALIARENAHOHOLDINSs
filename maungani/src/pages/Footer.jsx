import { Scale, Facebook, Linkedin, Mail, Phone } from 'lucide-react';

export default function Footer() {
  return (
    <footer className="bg-gray-900 text-white py-12">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid md:grid-cols-4 gap-8 mb-8">
          <div>
            <div className="flex items-center space-x-2 mb-4">
              <div className="w-8 h-8 rounded-lg bg-gradient-to-br from-blue-400 to-blue-600 flex items-center justify-center">
                <Scale size={20} className="text-white" />
              </div>
              <span className="font-bold">PM Mahlathi</span>
            </div>
            <p className="text-gray-400 text-sm leading-relaxed">
              Professional legal representation with integrity and excellence in all areas of law.
            </p>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-4 text-blue-400">Quick Links</h3>
            <ul className="space-y-2 text-sm">
              <li><a href="#home" className="text-gray-400 hover:text-blue-400 transition">Home</a></li>
              <li><a href="#about" className="text-gray-400 hover:text-blue-400 transition">About</a></li>
              <li><a href="#services" className="text-gray-400 hover:text-blue-400 transition">Services</a></li>
              <li><a href="#contact" className="text-gray-400 hover:text-blue-400 transition">Contact</a></li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-4 text-blue-400">Services</h3>
            <ul className="space-y-2 text-sm">
              <li><a href="#services" className="text-gray-400 hover:text-blue-400 transition">Civil Litigation</a></li>
              <li><a href="#services" className="text-gray-400 hover:text-blue-400 transition">Criminal Law</a></li>
              <li><a href="#services" className="text-gray-400 hover:text-blue-400 transition">Corporate Law</a></li>
              <li><a href="#services" className="text-gray-400 hover:text-blue-400 transition">Family Law</a></li>
            </ul>
          </div>

          <div>
            <h3 className="font-bold text-lg mb-4 text-blue-400">Contact</h3>
            <ul className="space-y-3 text-sm">
              <li className="flex items-center space-x-2">
                <Phone size={16} className="text-blue-400" />
                <span className="text-gray-400">0668535465</span>
              </li>
              <li className="flex items-center space-x-2">
                <Mail size={16} className="text-blue-400" />
                <span className="text-gray-400 break-all">mahlathipim@gmail.com</span>
              </li>
            </ul>
          </div>
        </div>

        <div className="border-t border-gray-800 pt-8">
          <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-6">
            <p className="text-gray-400 text-sm">
              &copy; {new Date().getFullYear()} PM Mahlathi Attorneys Inc. All rights reserved.
            </p>
            <div className="flex space-x-6">
              <a href="#" className="text-gray-400 hover:text-blue-400 transition">
                <Facebook size={20} />
              </a>
              <a href="#" className="text-gray-400 hover:text-blue-400 transition">
                <Linkedin size={20} />
              </a>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
}
