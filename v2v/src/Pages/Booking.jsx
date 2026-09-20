import { useState, forwardRef } from 'react';
import { CheckCircle } from 'lucide-react';
import BookingForm from '../components/BookingForm';

const Booking = forwardRef((props, ref) => {
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [bookingDetails, setBookingDetails] = useState(null);

  return (
    <section ref={ref} id="booking" className="py-16 px-4 bg-gray-50">
      <div className="container mx-auto max-w-4xl">
        <h2 className="text-4xl font-bold text-center text-gray-900 mb-4">
          Book Your Transport
        </h2>
        <p className="text-center text-gray-600 mb-12">
          Fill in the form below to reserve your seat. You'll receive a PDF receipt immediately.
        </p>

        {showConfirmation ? (
          <div className="bg-white p-8 rounded-xl shadow-lg">
            <div className="text-center">
              <CheckCircle className="w-16 h-16 text-emerald-600 mx-auto mb-4" />
              <h3 className="text-2xl font-bold text-gray-900 mb-4">Booking Submitted!</h3>
              <p className="text-gray-700 mb-6">Your booking receipt has been generated. Please download it and follow the payment instructions.</p>

              <div className="bg-amber-50 border border-amber-200 p-6 rounded-lg mb-6 text-left">
                <h4 className="font-bold text-gray-900 mb-3">Payment Instructions:</h4>
                <p className="text-gray-700 mb-2">To confirm your booking, please pay a deposit of <span className="font-bold">R100</span> to:</p>
                <div className="bg-white p-4 rounded border border-amber-300 mb-3">
                  <p className="font-semibold">Reference:Your name , Your Whatsapp Number</p>
                  <p className="font-semibold">Bank: Capitec</p>
                  <p className="font-semibold">Account Number: 1730480401</p>
                </div>
                <p className="text-gray-700">
                  Send proof of payment via WhatsApp to <span className="font-bold">+27728009120</span> or email <span className="font-bold">arenahojuska@gmail.com</span>
                </p>
              </div>

              <div className="flex flex-col sm:flex-row gap-4 justify-center">
                <a
                  href={`https://wa.me/27728009120?text=${encodeURIComponent(`Hi! I've just booked transport with V2V. Booking Ref: ${bookingDetails?.reference}. Sending proof of payment now.`)}`}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="px-6 py-3 bg-green-600 text-white rounded-lg font-semibold hover:bg-green-700 transition"
                >
                  Send Payment on WhatsApp
                </a>
                <button
                  onClick={() => {
                    setShowConfirmation(false);
                    setBookingDetails(null);
                  }}
                  className="px-6 py-3 bg-gray-200 text-gray-800 rounded-lg font-semibold hover:bg-gray-300 transition"
                >
                  Make Another Booking
                </button>
              </div>
            </div>
          </div>
        ) : (
          <BookingForm
            onBookingComplete={(details) => {
              setBookingDetails(details);
              setShowConfirmation(true);
            }}
          />
        )}
      </div>
    </section>
  );
});

export default Booking;
