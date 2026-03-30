// FloatingWhatsApp.jsx
import * as Lucide from 'lucide-react';
const { MessageCircle } = Lucide;

const FloatingWhatsApp = () => {
  return (
    <a
      href="https://chat.whatsapp.com/G6jjjAdncfu2AklOTtjj1D"
      target="_blank"
      rel="noopener noreferrer"
      className="fixed bottom-6 right-6 bg-green-600 text-white p-4 rounded-full shadow-lg hover:bg-green-700 transition-all hover:scale-110 z-50"
      aria-label="Chat on WhatsApp"
    >
      <MessageCircle className="w-6 h-6" />
    </a>
  );
};

export default FloatingWhatsApp;
