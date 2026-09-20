import Navbar from './pages/Navbar';
import Hero from './pages/Hero';
import About from './pages/About';
import Services from './pages/services';
import Team from './pages/team';
import Testimonials from './pages/testimonials';
import Contact from './pages/Contact';
import Footer from './pages/Footer';
function App() {
  return (
    <div className="min-h-screen">
         <Navbar />
      <Hero />
      <About />
      <Services />
      <Team />
      <Testimonials />
      <Contact />
      <Footer />
     
    </div>
  );
}

export default App;
