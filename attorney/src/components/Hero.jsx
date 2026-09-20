import { Phone } from 'lucide-react';

const Hero = () => {
  const handleContactClick = () => {
    const element = document.querySelector('#contact');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const handleCallClick = () => {
    console.log('Calling: 076 223 9306');
    window.location.href = 'tel:0762239306';
  };

  return (
    <section id="home" className="relative bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 text-white pt-32 pb-20 px-4">
      <div className="max-w-7xl mx-auto">
        <div className="text-center">
          <h1 className="text-5xl md:text-6xl lg:text-7xl font-bold mb-6 leading-tight">
            Professional Legal Services
            <span className="block text-amber-500">You Can Trust</span>
          </h1>
          <p className="text-xl md:text-2xl text-gray-300 mb-10 max-w-3xl mx-auto">
            Expert Attorneys for All Your Legal Needs in Polokwane
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center items-center">
            <button
              onClick={handleContactClick}
              className="bg-amber-500 hover:bg-amber-600 text-slate-900 font-semibold px-8 py-4 rounded-lg transition-all duration-300 transform hover:scale-105 shadow-lg"
            >
              Get Legal Consultation
            </button>
            <button
              onClick={handleCallClick}
              className="bg-transparent border-2 border-amber-500 hover:bg-amber-500 hover:text-slate-900 text-amber-500 font-semibold px-8 py-4 rounded-lg transition-all duration-300 flex items-center gap-2"
            >
              <Phone className="w-5 h-5" />
              076 223 9306
            </button>
          </div>
        </div>
      </div>
      <div className="absolute bottom-0 left-0 right-0 h-16 bg-gradient-to-t from-white to-transparent"></div>
    </section>
  );
};

export default Hero;
