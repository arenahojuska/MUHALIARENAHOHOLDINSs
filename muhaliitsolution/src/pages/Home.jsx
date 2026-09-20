import {
  Monitor,
  Server,
  TrendingUp,
  Palette,
  ShoppingCart,
  Search,
  Share2,
  Globe,
  FileText,
  Target,
  Users,
  Headphones,
  Star,
  CheckCircle,
  ArrowRight,
  Sparkles,
  Layers,
  Database,
  Smartphone,
  Bolt
} from 'lucide-react';
import { Link } from "react-router-dom";
import { Hero } from '../components/Hero';
import { useEffect, useState } from "react";
function Home() {
  const coreServices = [
    {
      icon: Monitor,
      title: 'Website Design',
      description: 'Custom, responsive websites tailored to your brand and business goals.',
      color: 'from-primary to-primary/80'
    },
    {
      icon: Server,
      title: 'Web Hosting',
      description: 'Reliable, secure, and fast hosting solutions for your website.',
      color: 'from-accent to-accent/80'
    },
    {
      icon: TrendingUp,
      title: 'Manual Testing',
      description: 'Thorough test plans and validation to ensure software quality.',
      color: 'from-primary/80 to-accent/80'
    },
    {
      icon: Bolt,
      title: 'Automation Testing',
      description: 'Streamlined test scripts to accelerate regression testing.',
      color: 'from-accent/80 to-primary/80'
    },
    {
      icon: Smartphone,
      title: 'Mobile Testing',
      description: 'Seamless performance across diverse devices and OS versions.',
      color: 'from-primary to-accent'
    },
    {
      icon: Globe,
      title: 'Web Testing',
      description: 'Comprehensive validation of UI, functionality, and responsiveness.',
      color: 'from-accent to-primary'
    },
    {
      icon: Layers,
      title: 'SAP Testing',
      description: 'Specialized testing of SAP modules and workflows.',
      color: 'from-primary/70 to-accent/70'
    },
    {
      icon: Database,
      title: 'Database Testing',
      description: 'Verify data integrity and query performance.',
      color: 'from-accent/70 to-primary/70'
    }
  ];

  const allServices = [
    {
      icon: Monitor,
      title: 'Website Design',
      description: 'Professional, modern websites built with latest technologies.',
      gradient: 'from-primary to-primary/80'
    },
    {
      icon: ShoppingCart,
      title: 'E-commerce Solutions',
      description: 'Powerful online stores that drive sales and conversions.',
      gradient: 'from-accent to-accent/80'
    },
    {
      icon: Search,
      title: 'SEO',
      description: 'Improve rankings and drive organic traffic to your website.',
      gradient: 'from-primary/80 to-accent/80'
    },
    {
      icon: Target,
      title: 'Google Ads',
      description: 'Targeted campaigns that deliver measurable ROI.',
      gradient: 'from-accent/80 to-primary/80'
    },
    {
      icon: Share2,
      title: 'Social Media',
      description: 'Engage audiences and build your brand across platforms.',
      gradient: 'from-primary to-accent'
    },
    {
      icon: Server,
      title: 'Hosting',
      description: 'Fast, secure hosting with 99.9% uptime guarantee.',
      gradient: 'from-accent to-primary'
    },
    {
      icon: Globe,
      title: 'Domain',
      description: 'Register your perfect domain and establish identity.',
      gradient: 'from-primary/70 to-accent/70'
    },
    {
      icon: Palette,
      title: 'Graphic Design',
      description: 'Creative designs that capture attention and communicate.',
      gradient: 'from-accent/70 to-primary/70'
    },
    {
      icon: FileText,
      title: 'Content Marketing',
      description: 'Compelling content that attracts and converts.',
      gradient: 'from-primary/60 to-accent/60'
    }
  ];
const stats = [
  { number: '500+', label: 'Projects Done', icon: CheckCircle },
  { number: '300+', label: 'Happy Customers', icon: Users },
  { number: '10+', label: 'Years Experience', icon: Star },
  { number: '4.9', label: 'Customer Rating', icon: TrendingUp }
];
 const StatCard = ({ stat }) => {
  const [count, setCount] = useState(0);

  useEffect(() => {
    const numeric = parseFloat(stat.number);
    let start = 0;
    const duration = 1500; 
    const increment = numeric / (duration / 16);

    const animate = () => {
      start += increment;
      if (start < numeric) {
        setCount(start);
        requestAnimationFrame(animate);
      } else {
        setCount(numeric);
      }
    };

    animate();
  }, [stat.number]);

  const display = stat.number.includes("+")
    ? `${Math.floor(count)}+`
    : count.toFixed(stat.number.includes(".") ? 1 : 0);

  return (
    <div className="bg-background/50 backdrop-blur-lg p-8 rounded-2xl border border-border hover:bg-background/80 transition-all duration-300 hover:scale-105 group">
      <stat.icon className="w-8 h-8 text-primary mb-4 group-hover:scale-110 transition-transform duration-300" />
      <p className="text-4xl md:text-5xl font-bold text-primary mb-2">
        {display}
      </p>
      <p className="text-muted-foreground font-semibold">{stat.label}</p>
    </div>
  );
};

  const [count, setCount] = useState(0);


  const whyChooseUs = [
    {
      icon: CheckCircle,
      title: 'Affordable Pricing',
      description: 'Quality services that fit your budget. Great design accessible to all businesses.',
      gradient: 'from-primary to-primary/80'
    },
    {
      icon: Users,
      title: 'Experienced Team',
      description: 'Years of expertise staying current with industry trends and best practices.',
      gradient: 'from-accent to-accent/80'
    },
    {
      icon: Headphones,
      title: 'Ongoing Support',
      description: 'Continuous support and maintenance for peace of mind after launch.',
      gradient: 'from-primary/80 to-accent/80'
    }
  ];

  const testimonials = [
    {
      name: 'John Van Der Valt',
      location: 'Randburg',
      review: 'Transformed our online presence completely. Professional, responsive, and delivered beyond expectations. Highly recommended!'
    },
    {
      name: 'Nkosimande Lerato',
      location: 'Cape Town',
      review: 'A game-changer for our business. The e-commerce site increased our sales by 200%. Exceptional service!'
    },
    {
      name: 'Emily Rodriguez',
      location: 'Bryston',
      review: 'From design to launch, everything was seamless. They understood our vision perfectly. Thank you!'
    }
  ];

  return (
    <>
      <Hero />
      <main className="overflow-hidden">
        {/* Core Services */}
        <section className="py-24 md:py-32 bg-gradient-to-b from-background via-muted/30 to-background relative">
          <div className="absolute inset-0 bg-grid-pattern opacity-20"></div>
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative">
            <div className="text-center mb-16 space-y-4 animate-in">
              <div className="inline-flex items-center gap-2 px-4 py-2 bg-primary/10 rounded-full text-primary font-medium text-sm">
                <Sparkles className="w-4 h-4" />
                <span>Premium Digital Solutions</span>
              </div>
              <h2 className="text-4xl md:text-5xl font-bold text-foreground">
                Our Core <span className="bg-gradient-primary bg-clip-text text-transparent">Services</span>
              </h2>
              <p className="text-lg md:text-xl text-muted-foreground max-w-2xl mx-auto">
                Comprehensive digital solutions designed to elevate your brand and drive results
              </p>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              {coreServices.map((service, index) => (
                <div
                  key={index}
                  className="group relative bg-card p-8 rounded-2xl shadow-sm hover:shadow-lg transition-all duration-500 border border-border hover:-translate-y-2"
                >
                  <div className={`absolute inset-0 bg-gradient-to-br ${service.color} opacity-0 group-hover:opacity-5 rounded-2xl transition-opacity duration-500`}></div>
                  <div className={`w-14 h-14 rounded-xl bg-gradient-to-br ${service.color} p-3 mb-6 transform group-hover:scale-110 group-hover:rotate-3 transition-all duration-500 shadow-md`}>
                    <service.icon className="w-full h-full text-primary-foreground" />
                  </div>
                  <h3 className="text-xl font-bold mb-3 text-card-foreground">{service.title}</h3>
                  <p className="text-muted-foreground leading-relaxed">{service.description}</p>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* About Section */}
        <section className="py-24 md:py-32 bg-gradient-to-br from-card via-card/50 to-card text-card-foreground relative overflow-hidden">
          <div className="absolute inset-0 opacity-10 bg-grid-pattern"></div>
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative">
            <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
              <div className="space-y-6 animate-in">
                <div className="inline-flex items-center gap-2 px-4 py-2 bg-primary/10 backdrop-blur-sm rounded-full text-primary font-medium text-sm">
                  <CheckCircle className="w-4 h-4" />
                  <span>Trusted by 300+ Businesses</span>
                </div>
                <h2 className="text-4xl md:text-5xl font-bold leading-tight">
                  About MUHALI IT Solutions <span className="bg-gradient-primary bg-clip-text text-transparent"></span>
                </h2>
                <div className="space-y-4 text-lg text-muted-foreground">
                  <p className="leading-relaxed">
                    With over 10 years of experience, we've been helping businesses establish and grow their online presence.
                  </p>
                  <p className="leading-relaxed">
                    Our mission is to provide affordable, high-quality services that empower businesses to succeed digitally.
                  </p>
                  <p className="leading-relaxed">
                    We believe in transparency, creativity, and delivering results that exceed expectations.
                  </p>
                </div>
              </div>

              <div className="grid grid-cols-2 gap-6">
  {stats.map((stat, index) => (
    <StatCard key={index} stat={stat} />
  ))}
</div>
            </div>
          </div>
        </section>

        {/* All Services Grid */}
        <section className="py-24 md:py-32 bg-background relative">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="text-center mb-16 space-y-4 animate-in">
              <h2 className="text-4xl md:text-5xl font-bold text-foreground">
                Complete Digital Ecosystem <span className="bg-gradient-primary bg-clip-text text-transparent"></span>
              </h2>
              <p className="text-lg md:text-xl text-muted-foreground max-w-2xl mx-auto">
                Everything you need to establish, grow, and scale your digital presence
              </p>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
              {allServices.map((service, index) => (
                <div
                  key={index}
                  className="group relative bg-gradient-to-br from-card to-card/50 p-8 rounded-2xl hover:shadow-xl transition-all duration-500 border border-border"
                >
                  <div className={`w-12 h-12 rounded-xl bg-gradient-to-br ${service.gradient} p-2.5 mb-6 group-hover:scale-110 transition-transform duration-300 shadow-md`}>
                    <service.icon className="w-full h-full text-primary-foreground" />
                  </div>
                  <h3 className="text-xl font-bold mb-3 text-card-foreground">{service.title}</h3>
                  <p className="text-muted-foreground mb-6 leading-relaxed">{service.description}</p>
                  
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* E-commerce CTA */}
        <section className="py-24 md:py-32 bg-gradient-primary text-primary-foreground relative overflow-hidden">
          <div className="absolute inset-0 bg-grid-pattern opacity-10"></div>
          <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative space-y-8">
            <div className="inline-flex items-center justify-center w-24 h-24 rounded-3xl bg-background/10 backdrop-blur-sm mb-4 mx-auto">
              <ShoppingCart className="w-12 h-12" />
            </div>
            <h2 className="text-4xl md:text-6xl font-bold leading-tight">
              Sell Your Products 24/7 with High-Powered eCommerce!
            </h2>
            <p className="text-xl md:text-2xl max-w-3xl mx-auto leading-relaxed opacity-90">
              We build flawless eCommerce websites to help businesses generate online sales 24/7.
              From product catalogs to secure payment processing, we handle everything.
            </p>
            <Link
  to="/Services"
  className="group inline-flex items-center gap-3 px-8 py-4 bg-background text-foreground rounded-xl text-lg font-semibold hover:bg-background/90 transition-all duration-300 shadow-2xl hover:shadow-3xl hover:scale-105 border border-border/20"
>
  Discover More
  <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
</Link>

          </div>
        </section>

        {/* Why Choose Us */}
        <section className="py-24 md:py-32 bg-gradient-to-b from-muted/30 to-background">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="text-center mb-16 space-y-4 animate-in">
              <h2 className="text-4xl md:text-5xl font-bold text-foreground">
                Why Choose Us <span className="bg-gradient-primary bg-clip-text text-transparent">Us</span>
              </h2>
              <p className="text-lg md:text-xl text-muted-foreground max-w-2xl mx-auto">
                We combine expertise, affordability, and dedication to deliver exceptional results
              </p>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
              {whyChooseUs.map((item, index) => (
                <div key={index} className="text-center group">
                  <div className={`relative inline-flex items-center justify-center w-24 h-24 rounded-3xl bg-gradient-to-br ${item.gradient} mb-8 mx-auto shadow-lg group-hover:scale-110 group-hover:rotate-3 transition-all duration-500`}>
                    <item.icon className="w-12 h-12 text-primary-foreground relative z-10" />
                  </div>
                  <h3 className="text-2xl font-bold mb-4 text-foreground">{item.title}</h3>
                  <p className="text-muted-foreground text-lg leading-relaxed">{item.description}</p>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* Testimonials */}
        <section className="py-24 md:py-32 bg-background">
          <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <div className="text-center mb-16 space-y-4 animate-in">
              <h2 className="text-4xl md:text-5xl font-bold text-foreground">
                What Our Clients Say  <span className="bg-gradient-primary bg-clip-text text-transparent"></span>
              </h2>
              <p className="text-lg md:text-xl text-muted-foreground max-w-2xl mx-auto">
                Don't just take our word for it — hear from businesses we've helped succeed
              </p>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
              {testimonials.map((testimonial, index) => (
                <div
                  key={index}
                  className="group relative bg-gradient-to-br from-card to-card/50 p-10 rounded-3xl shadow-sm hover:shadow-xl transition-all duration-500 border border-border hover:-translate-y-2"
                >
                  <div className="flex gap-1 mb-6">
                    {[...Array(5)].map((_, i) => (
                      <Star key={i} className="w-5 h-5 text-accent fill-current" />
                    ))}
                  </div>
                  <p className="text-muted-foreground mb-8 text-lg leading-relaxed italic">
                    "{testimonial.review}"
                  </p>
                  <div className="flex items-center gap-4">
                    <div className="w-12 h-12 rounded-full bg-gradient-primary flex items-center justify-center text-primary-foreground font-bold text-lg">
                      {testimonial.name.charAt(0)}
                    </div>
                    <div>
                      <p className="font-bold text-foreground">{testimonial.name}</p>
                      <p className="text-muted-foreground text-sm">{testimonial.location}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* Contact CTA */}
        <section className="py-24 md:py-32 bg-gradient-to-br from-card via-card/50 to-card text-card-foreground relative overflow-hidden">
          <div className="absolute inset-0 bg-grid-pattern opacity-10"></div>
          <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative space-y-8">
            <div className="inline-flex items-center gap-2 px-4 py-2 bg-primary/10 backdrop-blur-sm rounded-full text-primary font-medium text-sm">
              <Sparkles className="w-4 h-4" />
              <span>Let's Build Something Amazing</span>
            </div>
            <h2 className="text-4xl md:text-6xl font-bold leading-tight">
              Ready to Get Started? <span className="bg-gradient-primary bg-clip-text text-transparent"></span>
            </h2>
            <p className="text-xl md:text-2xl text-muted-foreground leading-relaxed">
              Let's discuss your project and bring your vision to life. Contact us today for a free consultation.
            </p>
           <Link
  to="/Contact"
  className="group inline-flex items-center gap-3 px-10 py-5 bg-gradient-primary text-primary-foreground rounded-xl text-lg font-semibold hover:opacity-90 transition-all duration-300 shadow-2xl hover:shadow-elegant hover:scale-105"
>
  Get Your Free Quote
  <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
</Link>

          </div>
        </section>
      </main>
    </>
  );
}

export default Home;
