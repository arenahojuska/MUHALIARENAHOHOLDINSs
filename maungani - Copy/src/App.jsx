import Navbar from './pages/Navbar';
import Hero from './pages/Hero';
import About from './pages/About';
import Rooms from './pages/Rooms';
import Gallery from './pages/Gallery';
import Features from './pages/Features';
import Pricing from './pages/Pricing';
import Contact from './pages/Contact';
import Location from './pages/Location';
import Footer from './pages/Footer';
function App() {
  return (
    <div className="min-h-screen">
      <Navbar />
      <Hero />
      <About />
       <Gallery />
     <Rooms />
      <Features />
      <Pricing />
      <Contact />
      <Location />
      <Footer />
     
    </div>
  );
}

export default App;
