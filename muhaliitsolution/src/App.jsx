import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { Header } from './components/Header';
import Footer from './components/Footer';

import About from './pages/About';
import Home from './pages/Home';
import Services from './pages/Services';
import Promotion from './pages/Promotion';
import Contact from './pages/Contact';
function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/services" element={<Services />} />
        <Route path="/promotion" element={<Promotion />} />
        <Route path="/contact" element={<Contact />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
