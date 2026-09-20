import { Check } from 'lucide-react';

export default function Pricing() {
  return (
    <section id="pricing" className="py-24 bg-white relative overflow-hidden">
      <div className="absolute inset-0 opacity-5">
        <div className="absolute top-1/4 left-1/4 w-96 h-96 bg-[#C4A574] rounded-full blur-3xl"></div>
        <div className="absolute bottom-1/4 right-1/4 w-96 h-96 bg-[#8B6F47] rounded-full blur-3xl"></div>
      </div>

      <div className="container mx-auto px-4 relative z-10">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Affordable Pricing</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full"></div>
        </div>

        <div className="max-w-2xl mx-auto">
          <div className="bg-gradient-to-br from-[#F5E6D3] to-[#E8D4BA] rounded-3xl p-12 shadow-2xl border-4 border-[#C4A574]">
            <div className="text-center mb-8">
              <p className="text-2xl text-[#6B5744] mb-2">Starting from</p>
              <div className="flex items-baseline justify-center">
                <span className="text-7xl font-bold text-[#8B6F47]">R100</span>
                <span className="text-3xl text-[#6B5744] ml-3">/ day</span>
              </div>
            </div>

            <div className="space-y-4 mb-8">
              {[
                'Clean and comfortable rooms',
                'All amenities included',
                'No hidden fees',
                'Flexible booking options',
                'Perfect for all guests'
              ].map((item, index) => (
                <div key={index} className="flex items-center">
                  <div className="w-8 h-8 bg-[#8B6F47] rounded-full flex items-center justify-center mr-4 flex-shrink-0">
                    <Check className="w-5 h-5 text-white" />
                  </div>
                  <p className="text-lg text-[#6B5744]">{item}</p>
                </div>
              ))}
            </div>

            <p className="text-center text-xl text-[#6B5744] mb-8 italic">
              "Affordable, clean, and comfortable rooms for all guests."
            </p>

            <button className="w-full bg-[#8B6F47] hover:bg-[#6B5744] text-white px-8 py-5 rounded-full text-xl font-semibold shadow-lg hover:shadow-xl transition-all duration-300 transform hover:scale-105">
              Check Availability
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}
