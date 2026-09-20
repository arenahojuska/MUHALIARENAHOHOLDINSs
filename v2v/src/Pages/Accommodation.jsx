import { Home } from 'lucide-react';
import AccommodationCard from '../components/AccommodationCard';
import accommodations from './accommodationDetails';

export default function Accommodation() {
  return (
    <section id="accommodation" className="py-16 px-4 bg-white">
      <div className="container mx-auto max-w-6xl">
        {/* Header */}
        <div className="text-center mb-12">
          <div className="flex items-center justify-center mb-4">
            <Home className="w-12 h-12 text-emerald-600" />
          </div>
          <h2 className="text-4xl font-bold text-gray-900 mb-4">
            Student Accommodation
          </h2>
          <p className="text-lg text-gray-600 max-w-2xl mx-auto">
            We assist students with finding quality accommodation near their universities in Vanderbijlpark.
          </p>
        </div>

        {/* Accommodation Cards */}
        <div className="grid md:grid-cols-2 gap-6 mb-8">
          {accommodations.map((accommodation, index) => (
            <AccommodationCard key={index} {...accommodation} />
          ))}
        </div>

        {/* Footer Note */}
        <div className="bg-blue-50 border border-blue-200 p-6 rounded-lg text-center">
          <p className="text-gray-700 mb-4">
            <strong>Note:</strong> Accommodation listings are subject to availability.
            Contact us on WhatsApp for current options.
          </p>
          <a
            href="https://wa.me/27729792961?text=Hi! I'm interested in student accommodation in Vanderbijlpark."
            target="_blank"
            rel="noopener noreferrer"
            className="inline-block px-6 py-3 bg-green-600 text-white rounded-lg font-semibold hover:bg-green-700 transition"
          >
            Enquire About Accommodation /List your accomodation with us 
          </a>
        </div>
      </div>
    </section>
  );
}
