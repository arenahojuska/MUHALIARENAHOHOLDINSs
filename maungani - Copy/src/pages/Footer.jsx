import { Phone, MapPin, Mail, Facebook, Instagram, Twitter } from 'lucide-react';

export default function Footer() {
  return (
    <footer className="bg-[#E8D4BA] py-16">
      <div className="container mx-auto px-4">
        <div className="grid md:grid-cols-3 gap-12 mb-12">
          <div>
            <h3 className="text-4xl font-serif italic text-[#8B6F47] mb-4">
              Maungani Lodge
            </h3>
            <div className="w-16 h-1 bg-[#C4A574] rounded-full mb-4"></div>
            <p className="text-[#6B5744] leading-relaxed">
              Your home away from home in Thohoyandou. Affordable comfort and warm hospitality await you.
            </p>
          </div>

          <div>
            <h4 className="text-2xl font-semibold text-[#6B5744] mb-6">Quick Links</h4>
            <ul className="space-y-3">
              {['About Us', 'Features', 'Pricing', 'Contact', 'Location'].map((link) => (
                <li key={link}>
                  <a
                    href={`#${link.toLowerCase().replace(' ', '-')}`}
                    className="text-[#8B6F47] hover:text-[#6B5744] transition-colors text-lg"
                  >
                    {link}
                  </a>
                </li>
              ))}
            </ul>
          </div>

          <div>
            <h4 className="text-2xl font-semibold text-[#6B5744] mb-6">Contact Info</h4>
            <div className="space-y-4">
              <div className="flex items-start">
                <Phone className="w-5 h-5 text-[#8B6F47] mr-3 mt-1 flex-shrink-0" />
                <a href="tel:0715803599" className="text-[#8B6F47] hover:text-[#6B5744] transition-colors">
                  071 580 3599
                </a>
              </div>
              <div className="flex items-start">
                <MapPin className="w-5 h-5 text-[#8B6F47] mr-3 mt-1 flex-shrink-0" />
                <p className="text-[#6B5744]">
                  115 Maungani, Thohoyandou<br />Limpopo, 0900
                </p>
              </div>
              <div className="flex items-start">
                <Mail className="w-5 h-5 text-[#8B6F47] mr-3 mt-1 flex-shrink-0" />
                <a href="mailto:info@maunganilodge.co.za" className="text-[#8B6F47] hover:text-[#6B5744] transition-colors">
                  info@maunganilodge.co.za
                </a>
              </div>
            </div>
          </div>
        </div>

        <div className="border-t-2 border-[#C4A574] pt-8">
          <div className="flex flex-col md:flex-row justify-between items-center gap-6">
            <p className="text-[#6B5744] text-center md:text-left">
              &copy; 2024 Maungani Lodge. All rights reserved.
            </p>

            <div className="flex gap-4">
              {[
                { Icon: Facebook, href: '#' },
                { Icon: Instagram, href: '#' },
                { Icon: Twitter, href: '#' }
              ].map(({ Icon, href }, index) => (
                <a
                  key={index}
                  href={href}
                  className="w-12 h-12 bg-[#8B6F47] hover:bg-[#6B5744] rounded-full flex items-center justify-center transition-all duration-300 transform hover:scale-110 shadow-md"
                  aria-label="Social media link"
                >
                  <Icon className="w-6 h-6 text-white" />
                </a>
              ))}
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
}
