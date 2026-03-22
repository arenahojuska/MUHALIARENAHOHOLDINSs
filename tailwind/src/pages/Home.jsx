import React from 'react'
import Navbar from './Navbar'
import Footer from './Footer';
import About from './About';
import Services from './Services';
import Team from './Team';
import Testimonials from './Testimonials';
import Contact from './Contact';
import Hero from './Hero';
export default function Home() {
  return (
    <div>
      <Navbar />
      <Hero />
      <About />
       <Services />
        <Team />
        <Testimonials />
        <Contact />
      < Footer />
    </div>
  )
}
