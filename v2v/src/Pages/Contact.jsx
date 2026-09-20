import { Phone, Mail } from 'lucide-react';

export default function Contact() {
  return (
    <section id="contact" className="py-16 px-4 bg-gray-50">
      <div className="container mx-auto max-w-4xl">
        <h2 className="text-4xl font-bold text-center text-gray-900 mb-12">Contact Us</h2>

        <div className="grid md:grid-cols-2 gap-8">
          <div className="bg-white p-8 rounded-xl shadow-md">
            <h3 className="text-2xl font-bold text-gray-900 mb-6">Get In Touch</h3>

            <div className="space-y-4">
              <div className="flex items-center">
                <Phone className="w-6 h-6 text-emerald-600 mr-3" />
                <div>
                  <p className="text-sm text-gray-600">WhatsApp / Call</p>
                  <a href="tel:+27729792961" className="text-lg font-semibold text-gray-900 hover:text-emerald-600">
                    +27 72 979 2961
                  </a>
                </div>
              </div>

              <div className="flex items-center">
                <Mail className="w-6 h-6 text-emerald-600 mr-3" />
                <div>
                  <p className="text-sm text-gray-600">Email</p>
                  <a href="mailto:arenahojuska@gmail.com" className="text-lg font-semibold text-gray-900 hover:text-emerald-600">
                    arenahojuska@gmail.com
                  </a>
                </div>
              </div>

              <div className="pt-4">
                <a
                  href="https://wa.me/27729792961"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="block w-full px-6 py-3 bg-green-600 text-white text-center rounded-lg font-semibold hover:bg-green-700 transition"
                >
                  Chat on WhatsApp
                </a>
              </div>
            </div>
          </div>

          <div className="bg-white p-8 rounded-xl shadow-md">
            <h3 className="text-2xl font-bold text-gray-900 mb-6">Send a Message</h3>
            <form onSubmit={(e) => { e.preventDefault(); alert('Thank you for your message! We will get back to you soon.'); e.currentTarget.reset(); }} className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Name</label>
                <input type="text" required className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500"/>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
                <input type="email" required className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500"/>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Message</label>
                <textarea required rows={4} className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500"/>
              </div>
              <button type="submit" className="w-full px-6 py-3 bg-emerald-600 text-white rounded-lg font-semibold hover:bg-emerald-700 transition">
                Send Message
              </button>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
}
