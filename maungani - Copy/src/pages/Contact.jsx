import { Phone, Mail, MessageCircle } from 'lucide-react';

export default function Contact() {
  return (
    <section id="contact" className="py-24 bg-gradient-to-b from-[#E8D4BA] to-[#D4B896]">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Get in Touch</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full"></div>
        </div>

        <div className="max-w-4xl mx-auto">
          <div className="bg-white rounded-3xl p-12 shadow-2xl">
            <div className="text-center mb-12">
              <p className="text-2xl text-[#6B5744] mb-8">
                Ready to book your stay or have questions? We're here to help!
              </p>

              <div className="inline-block bg-gradient-to-r from-[#8B6F47] to-[#C4A574] rounded-3xl p-8 shadow-xl">
                <div className="flex items-center justify-center mb-4">
                  <Phone className="w-10 h-10 text-white mr-4" />
                  <a
                    href="tel:0715803599"
                    className="text-4xl md:text-5xl font-bold text-white hover:text-[#F5E6D3] transition-colors"
                  >
                    071 580 3599
                  </a>
                </div>
                <p className="text-white text-lg">Call us anytime</p>
              </div>
            </div>

            <div className="grid md:grid-cols-2 gap-6">
              <button className="flex items-center justify-center bg-[#F5E6D3] hover:bg-[#E8D4BA] text-[#8B6F47] px-8 py-5 rounded-2xl text-lg font-semibold shadow-md hover:shadow-lg transition-all duration-300 transform hover:scale-105">
                <MessageCircle className="w-6 h-6 mr-3" />
                WhatsApp Us
              </button>

              <button className="flex items-center justify-center bg-[#8B6F47] hover:bg-[#6B5744] text-white px-8 py-5 rounded-2xl text-lg font-semibold shadow-md hover:shadow-lg transition-all duration-300 transform hover:scale-105">
                <Mail className="w-6 h-6 mr-3" />
                More Info
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
