import { useState } from 'react';
import { MapPin, DollarSign, Navigation, Eye, X, ChevronLeft, ChevronRight } from 'lucide-react';

const AccommodationCard = ({
  name,
  location,
  price,
  distance,
  image,
  images = [],
  paymentType = '',
  whatsappNumber = '27729792961', // default number
}) => {
  const [showGallery, setShowGallery] = useState(false);
  const [currentIndex, setCurrentIndex] = useState(0);
  const allImages = images.length > 0 ? images : [image];

  const nextImage = () => setCurrentIndex((prev) => (prev + 1) % allImages.length);
  const prevImage = () => setCurrentIndex((prev) => (prev - 1 + allImages.length) % allImages.length);

  return (
    <>
      {/* CARD */}
      <div className="bg-white border border-gray-200 rounded-xl overflow-hidden hover:shadow-xl transition">
        <div
          className="h-48 overflow-hidden bg-gray-200 cursor-pointer relative group"
          onClick={() => { setShowGallery(true); setCurrentIndex(0); }}
        >
          <img
            src={allImages[0]}
            alt={name}
            className="w-full h-full object-cover group-hover:scale-105 transition duration-300"
          />
          <div className="absolute inset-0 bg-black/0 group-hover:bg-black/30 transition flex items-center justify-center">
            <div className="opacity-0 group-hover:opacity-100 transition">
              <Eye className="w-8 h-8 text-white" />
            </div>
          </div>
        </div>

        <div className="p-6">
          <h3 className="text-xl font-bold text-gray-900 mb-3">{name}</h3>

          <div className="space-y-2 mb-4">
            <div className="flex items-center text-gray-600">
              <MapPin className="w-4 h-4 mr-2 text-emerald-600" />
              <span className="text-sm">{location}</span>
            </div>
            <div className="flex items-center text-gray-600">
              <Navigation className="w-4 h-4 mr-2 text-blue-600" />
              <span className="text-sm">{distance}</span>
            </div>
            <div className="flex items-center text-gray-900">
              <DollarSign className="w-4 h-4 mr-2 text-amber-600" />
              <span className="text-lg font-semibold">{price}</span>
            </div>
            {paymentType && (
              <div className="mt-2 inline-block px-3 py-1 text-sm font-medium bg-yellow-100 text-yellow-800 rounded-full">
                {paymentType}
              </div>
            )}
          </div>

          <button
            onClick={() => { setShowGallery(true); setCurrentIndex(0); }}
            className="block w-full px-4 py-2 bg-blue-600 text-white text-center rounded-lg font-semibold hover:bg-blue-700 transition mb-2"
          >
            View Photos ({allImages.length})
          </button>

          <a
            href={`https://wa.me/${whatsappNumber}?text=${encodeURIComponent(
              `Hi! I'm interested in ${name}. Can you provide more details?`
            )}`}
            target="_blank"
            rel="noopener noreferrer"
            className="block w-full px-4 py-2 bg-green-600 text-white text-center rounded-lg font-semibold hover:bg-green-700 transition"
          >
            Enquire on WhatsApp
          </a>
        </div>
      </div>

      {/* GALLERY MODAL */}
      {showGallery && (
        <div className="fixed inset-0 z-50 bg-black/80 flex flex-col items-center justify-center p-4">
          {/* Close button */}
          <button
            onClick={() => setShowGallery(false)}
            className="absolute top-6 right-6 text-white"
          >
            <X className="w-8 h-8" />
          </button>

          {/* Image display */}
          <div className="relative max-w-4xl w-full flex items-center justify-center">
            {allImages.length > 1 && (
              <button
                onClick={prevImage}
                className="absolute left-0 text-white p-2 hover:bg-black/30 rounded-full"
              >
                <ChevronLeft className="w-8 h-8" />
              </button>
            )}

            <img
              src={allImages[currentIndex]}
              alt={`${name} ${currentIndex + 1}`}
              className="w-full max-h-[80vh] object-contain rounded-lg"
            />

            {allImages.length > 1 && (
              <button
                onClick={nextImage}
                className="absolute right-0 text-white p-2 hover:bg-black/30 rounded-full"
              >
                <ChevronRight className="w-8 h-8" />
              </button>
            )}
          </div>

          {allImages.length > 1 && (
            <p className="text-white mt-4">
              {currentIndex + 1} / {allImages.length}
            </p>
          )}
        </div>
      )}
    </>
  );
};

export default AccommodationCard;
