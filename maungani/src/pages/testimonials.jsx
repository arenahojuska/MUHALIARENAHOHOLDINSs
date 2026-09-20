import { MapPin, Navigation } from 'lucide-react';

export default function Location() {
  return (
    <section id="location" className="py-24 bg-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Find Us</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full"></div>
        </div>

        <div className="max-w-5xl mx-auto">
          <div className="bg-gradient-to-br from-[#F5E6D3] to-[#E8D4BA] rounded-3xl p-8 md:p-12 shadow-2xl mb-8">
            <div className="flex flex-col md:flex-row items-start md:items-center justify-between gap-6">
              <div className="flex-1">
                <div className="flex items-start mb-4">
                  <MapPin className="w-8 h-8 text-[#8B6F47] mr-4 flex-shrink-0 mt-1" />
                  <div>
                    <h3 className="text-2xl font-semibold text-[#6B5744] mb-2">Our Address</h3>
                    <p className="text-xl text-[#8B6F47] leading-relaxed">
                      115 Maungani<br />
                      Thohoyandou, Limpopo<br />
                      0900
                    </p>
                  </div>
                </div>
              </div>

              <button className="flex items-center bg-[#8B6F47] hover:bg-[#6B5744] text-white px-8 py-4 rounded-full text-lg font-semibold shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
                <Navigation className="w-5 h-5 mr-2" />
                Get Directions
              </button>
            </div>
          </div>

          <div className="rounded-3xl overflow-hidden shadow-2xl h-96">
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3645.8!2d30.48!3d-22.95!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zMjLCsDU3JzAwLjAiUyAzMMKwMjgnNDguMCJF!5e0!3m2!1sen!2sza!4v1234567890"
              width="100%"
              height="100%"
              style={{ border: 0 }}
              allowFullScreen
              loading="lazy"
              referrerPolicy="no-referrer-when-downgrade"
              title="Maungani Lodge Location"
            ></iframe>
          </div>
        </div>
      </div>
    </section>
  );
}
