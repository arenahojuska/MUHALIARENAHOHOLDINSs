import { Phone, Mail, MapPin, Send } from 'lucide-react';

export default function Contact() {
  return (
    <section id="contact" className="py-16 lg:py-24 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-12">
          <span className="inline-block text-sm font-bold text-blue-600 bg-blue-50 px-4 py-1 rounded-full mb-4">GET IN TOUCH</span>
          <h2 className="text-4xl sm:text-5xl font-bold text-gray-900 mb-6">Contact Our Firm</h2>
          <div className="w-16 h-1 bg-gradient-to-r from-blue-600 to-blue-400 mx-auto mb-8"></div>
          <p className="text-lg text-gray-700 max-w-2xl mx-auto">
            Reach out to schedule a consultation or inquire about our services.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 max-w-4xl mx-auto mb-12">
          <div className="bg-gradient-to-br from-blue-50 to-blue-100 rounded-lg p-8 text-center hover:shadow-lg transition">
            <div className="w-12 h-12 rounded-full bg-blue-600 flex items-center justify-center mx-auto mb-4">
              <Phone size={24} className="text-white" />
            </div>
            <h3 className="font-bold text-lg text-gray-900 mb-3">Phone</h3>
            <p className="text-gray-700 font-medium">0668535465</p>
            <p className="text-gray-700 font-medium">0868160462</p>
          </div>

          <div className="bg-gradient-to-br from-blue-50 to-blue-100 rounded-lg p-8 text-center hover:shadow-lg transition">
            <div className="w-12 h-12 rounded-full bg-blue-600 flex items-center justify-center mx-auto mb-4">
              <Mail size={24} className="text-white" />
            </div>
            <h3 className="font-bold text-lg text-gray-900 mb-3">Email</h3>
            <p className="text-gray-700 font-medium break-words">mahlathipim@gmail.com</p>
          </div>

          <div className="bg-gradient-to-br from-blue-50 to-blue-100 rounded-lg p-8 text-center hover:shadow-lg transition">
            <div className="w-12 h-12 rounded-full bg-blue-600 flex items-center justify-center mx-auto mb-4">
              <MapPin size={24} className="text-white" />
            </div>
            <h3 className="font-bold text-lg text-gray-900 mb-3">Location</h3>
            <p className="text-gray-700 font-medium">South Africa</p>
          </div>
        </div>

        <div className="max-w-2xl mx-auto bg-gray-50 rounded-lg p-8 md:p-12">
          <form className="space-y-6">
            <div className="grid md:grid-cols-2 gap-6">
              <input type="text" placeholder="Your Name" className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-600" />
              <input type="email" placeholder="Your Email" className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-600" />
            </div>
            <input type="text" placeholder="Subject" className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-600" />
            <textarea placeholder="Your Message" rows={5} className="w-full px-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-600 resize-none"></textarea>
            <button type="submit" className="w-full inline-flex items-center justify-center space-x-2 px-8 py-3 bg-gradient-to-r from-blue-600 to-blue-800 text-white font-semibold rounded-lg hover:shadow-lg transition">
              <span>Send Message</span>
              <Send size={20} />
            </button>
          </form>
        </div>
      </div>
    </section>
  );
}
