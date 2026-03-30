import React, { useRef, useEffect } from 'react';
import { Routes, Route, useNavigate, useLocation } from 'react-router-dom';

import Header from './Pages/Header';
import Hero from './Pages/Hero';
import Transport from './Pages/Transport';
import Booking from './Pages/Booking';
import Accommodation from './Pages/Accommodation';
import Contact from './Pages/Contact';
import FloatingWhatsApp from './components/FloatingWhatsApp';
import Footer from './components/Footer';

function App() {
  const transportRef = useRef(null);
  const bookingRef = useRef(null);
  const heroRef = useRef(null);
  const navigate = useNavigate();
  const { pathname } = useLocation();

  // Scroll to top on route change
  useEffect(() => {
    window.scrollTo({ top: 0, left: 0, behavior: 'auto' });
  }, [pathname]);

  // Dynamic page title
  useEffect(() => {
    if (pathname === '/accommodation') {
      document.title = 'V2V - Accommodation';
    } else if (pathname === '/contact') {
      document.title = 'V2V - Contact';
    } else if (pathname === '/') {
      document.title = 'V2V - Home';
    } else {
      document.title = 'V2V';
    }
  }, [pathname]);

  // Scroll to Transport section
  const handleTransport = () => {
    navigate('/');
    setTimeout(() => {
      transportRef.current?.scrollIntoView({ behavior: 'smooth' });
    }, 50);
  };

  // Scroll to Booking section
  const handleBookNow = () => {
    navigate('/');
    setTimeout(() => {
      bookingRef.current?.scrollIntoView({ behavior: 'smooth' });
    }, 50);
  };

  return (
    <>
      <Header handleTransport={handleTransport} handleBookNow={handleBookNow} />

      <Routes>
        <Route
          path="/"
          element={
            <>
              <div ref={heroRef}>
                <Hero handleTransport={handleTransport} handleBookNow={handleBookNow} />
              </div>
              <div ref={transportRef}><Transport /></div>
              <div ref={bookingRef}><Booking /></div>
            </>
          }
        />
        <Route path="/accommodation" element={<Accommodation />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>

      {/* WhatsApp button */}
      <FloatingWhatsApp />

      <Footer />
    </>
  );
}

export default App;
