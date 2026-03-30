import { Bus } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

export default function Hero({ handleTransport, handleBookNow }) {
  const navigate = useNavigate();

  const handleAccommodation = () => {
    navigate('/accommodation'); // navigate to accommodation page
  };

  return (
    <section className="relative pt-24 pb-16 px-4 bg-gradient-to-br from-orange-800 via-yellow-500 to-orange-400 overflow-hidden">
      <div className="absolute -top-32 -left-32 w-96 h-96 bg-yellow-200 rounded-full opacity-20 mix-blend-multiply"></div>
      <div className="absolute -bottom-32 -right-32 w-96 h-96 bg-orange-900 rounded-full opacity-20 mix-blend-multiply"></div>

      <div className="container mx-auto max-w-6xl text-center relative z-10">
        <div className="flex items-center justify-center mb-6">
          <Bus className="w-16 h-16 text-yellow-400" />
        </div>
        <h1 className="text-5xl md:text-6xl font-bold text-white mb-4 drop-shadow-lg">
          V2V
        </h1>
        <p className="text-2xl md:text-3xl text-yellow-200 font-semibold mb-4 drop-shadow-sm">
          Reliable Student Transport & Accommodation
        </p>
        <p className="text-2xl md:text-3xl text-yellow-200 font-semibold mb-6 drop-shadow-sm">
          Vha Khou tuwa afho ?
        </p>
        <p className="text-lg md:text-xl text-cream-100 max-w-3xl mx-auto mb-8 drop-shadow-sm">
          V2V provides safe, affordable transport for students traveling from Venda to Vaal and assists with student accommodation.
        </p>
        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          {/* Scroll to Transport section */}
          <button
            onClick={handleBookNow}
            className="px-8 py-4 bg-orange-700 text-white rounded-lg font-semibold text-lg hover:bg-orange-800 transition shadow-lg"
          >
            Book Now
          </button>

          {/* Navigate to accommodation page */}
          <button
            onClick={handleAccommodation}
            className="px-8 py-4 bg-yellow-400 text-orange-900 border-2 border-yellow-400 rounded-lg font-semibold text-lg hover:bg-yellow-300 transition"
          >
            View Accommodation
          </button>
        </div>
      </div>
    </section>
  );
}
