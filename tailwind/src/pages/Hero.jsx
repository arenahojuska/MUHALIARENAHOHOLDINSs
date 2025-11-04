import React from 'react';
import LiquidEther from './LiquidEther';
import { Phone } from 'lucide-react';


const Hero = () => {
  const handleContactClick = () => {
    const element = document.querySelector('#contact');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  const handleCallClick = () => {
    console.log('Calling: +27762239306');
    window.location.href = 'tel:+27762239306';
  };

  return (
    <section
      id="home"
      className="relative text-white pt-32 pb-20 px-4 overflow-hidden"
    >
      {/* Animated Background */}
      <div className="absolute inset-0 -z-10">
        <LiquidEther
          colors={['#5227FF', '#FF9FFC', '#B19EEF']}
          mouseForce={20}
          cursorSize={100}
          isViscous={false}
          viscous={30}
          iterationsViscous={32}
          iterationsPoisson={32}
          resolution={0.5}
          isBounce={false}
          autoDemo={true}
          autoSpeed={0.5}
          autoIntensity={2.2}
          takeoverDuration={0.25}
          autoResumeDelay={3000}
          autoRampDuration={0.6}
        />
      </div>

      {/* Overlay gradient for text clarity */}
      <div className="absolute inset-0 bg-gradient-to-b from-slate-900/80 via-slate-800/60 to-slate-900/90 -z-[5]" />

      <div className="max-w-7xl mx-auto text-center relative z-10">
        <h1 className="text-5xl md:text-6xl lg:text-7xl font-bold mb-6 leading-tight">
          Professional Legal Services
          <span className="block text-amber-500">You Can Trust</span>
        </h1>
        <p className="text-xl md:text-2xl text-gray-300 mb-10 max-w-3xl mx-auto">
          Expert Attorneys for All Your Legal Needs in Thohoyandou , Roodeeport and Rustenburg 
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
            +27813224164   or  +27674967148
          </button>
        </div>
      </div>

      <div className="absolute bottom-0 left-0 right-0 h-16 bg-gradient-to-t from-slate-900 to-transparent" />
    </section>
  );
};

export default Hero;
