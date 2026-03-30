import { Sparkles } from 'lucide-react';

export default function About() {
  return (
    <section id="about" className="py-24 bg-white relative overflow-hidden">
      <div className="absolute top-0 right-0 w-64 h-64 bg-[#F5E6D3] rounded-full -mr-32 -mt-32 opacity-50"></div>
      <div className="absolute bottom-0 left-0 w-64 h-64 bg-[#E8D4BA] rounded-full -ml-32 -mb-32 opacity-50"></div>

      <div className="container mx-auto px-4 relative z-10">
        <div className="max-w-4xl mx-auto text-center">
          <div className="inline-flex items-center justify-center mb-6">
            <Sparkles className="w-8 h-8 text-[#C4A574]" />
            <h2 className="text-5xl font-serif italic text-[#8B6F47] mx-4">About Us</h2>
            <Sparkles className="w-8 h-8 text-[#C4A574]" />
          </div>

          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full mb-8"></div>

          <p className="text-xl text-[#6B5744] leading-relaxed mb-6">
            Welcome to Maungani Lodge, your home away from home in the heart of Thohoyandou, Limpopo.
          </p>

          <p className="text-lg text-gray-700 leading-relaxed mb-6">
            We pride ourselves on providing comfortable, affordable, and secure accommodation for all our guests. Whether you're visiting for business or leisure, our well-appointed rooms offer everything you need for a pleasant stay.
          </p>

          <p className="text-lg text-gray-700 leading-relaxed">
            Experience warm hospitality, modern amenities, and exceptional value at Maungani Lodge. We look forward to welcoming you.
          </p>
        </div>
      </div>
    </section>
  );
}
