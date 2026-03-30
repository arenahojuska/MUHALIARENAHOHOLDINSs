import { MapPin, Calendar, DollarSign, CheckCircle } from 'lucide-react';
import sprinter from '../images/sprinter.jpeg';
import bus from '../images/bus.jpeg';
import taxi from '../images/taxi.jpeg';
export default function Transport({ handleBookNow }) {
  return (
    <section id="transport" className="py-16 px-4 bg-white">
      <div className="container mx-auto max-w-6xl">
        <h2 className="text-4xl font-bold text-center text-gray-900 mb-12">Transport Information</h2>

        <div className="grid md:grid-cols-3 gap-6 mb-12">
         <img
  src={sprinter}
  alt="Bus"
  className="rounded-2xl shadow-xl h-80 w-full object-cover"
/>
       <img
  src={bus}
  alt="Bus"
  className="rounded-2xl shadow-xl h-80 w-full object-cover"
/>
       <img
  src={taxi}
  alt="Bus"
  className="rounded-2xl shadow-xl h-80 w-full object-cover"
/>

        </div>

        <div className="grid md:grid-cols-2 gap-8">
          <div className="bg-gradient-to-br from-emerald-50 to-teal-50 p-8 rounded-xl">
            <div className="flex items-center mb-4">
              <MapPin className="w-6 h-6 text-emerald-600 mr-2" />
              <h3 className="text-2xl font-bold text-gray-900">Route</h3>
            </div>
            <p className="text-lg text-gray-700 mb-4">Venda → Vaal</p>
            <h4 className="font-semibold text-gray-900 mb-2">Pickup Points:</h4>
            <ul className="space-y-2 text-gray-700">
              {['Thohoyandou Thavhani Mall 06:30am', 'Zwikwengani', 'Tshakhuma', 'Elim', 'Nzhelele', 'Louis Trichardt', 'Polokwane', 'Naboom'].map((point) => (
                <li key={point} className="flex items-center">
                  <CheckCircle className="w-4 h-4 mr-2 text-emerald-600" /> {point}
                </li>
              ))}
            </ul>
          </div>

          <div className="space-y-6">
            <div className="bg-gradient-to-br from-blue-50 to-cyan-50 p-8 rounded-xl">
              <div className="flex items-center mb-4">
                <Calendar className="w-6 h-6 text-blue-600 mr-2" />
                <h3 className="text-2xl font-bold text-gray-900">Travel Dates</h3>
              </div>
              <ul className="space-y-2 text-lg text-gray-700">
                {['28 January 2026 17:00pm','29 January 2026 06:00','3 February 2026 17:00pm','6 February 2026','15 February 2026'].map((date) => (
                  <li key={date} className="flex items-center">
                    <CheckCircle className="w-5 h-5 mr-2 text-blue-600" /> {date}
                  </li>
                ))}
              </ul>
            </div>

            <div className="bg-gradient-to-br from-amber-50 to-orange-50 p-8 rounded-xl">
              <div className="flex items-center mb-4">
                <DollarSign className="w-6 h-6 text-amber-600 mr-2" />
                <h3 className="text-2xl font-bold text-gray-900">Pricing</h3>
              </div>
              <div className="space-y-3">
                <p className="text-3xl font-bold text-gray-900">R450 <span className="text-lg font-normal text-gray-600">per student</span></p>
                <p className="text-lg text-gray-700">Required Deposit: <span className="font-bold text-amber-700">R100</span></p>
              </div>
            </div>
          </div>
        </div>

  

      </div>
    </section>
  );
}
