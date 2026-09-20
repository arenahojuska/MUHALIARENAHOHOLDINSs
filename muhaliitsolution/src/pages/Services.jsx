import {
  ArrowRight,
  Sparkles,
  Globe,
  Server,
  TrendingUp,
  Palette,
  ShoppingCart,
  Search,
  Zap,
  Share2,
  FileText,
  Award,
  CheckCircle,
  Star,
  Clock,
  Users,
  Target
} from 'lucide-react';
import { Link } from "react-router-dom";

function Services() {
  const services = [
    {
      icon: Globe,
      title: 'Website Design',
      description: 'Craft visually appealing and user-friendly websites tailored to your brand.',
      features: ['Responsive Layout', 'Modern UI/UX', 'Brand Integration'],
      deliveryTime: '2-4 weeks'
    },
    {
      icon: Server,
      title: 'Web Hosting',
      description: 'Secure and reliable hosting solutions to keep websites running smoothly.',
      features: ['99.9% Uptime', '24/7 Support', 'SSL Included'],
      deliveryTime: 'Instant'
    },
    {
      icon: TrendingUp,
      title: 'Digital Marketing',
      description: 'Comprehensive strategies to boost online presence and engagement.',
      features: ['Multi-Channel', 'Analytics', 'ROI Tracking'],
      deliveryTime: 'Ongoing'
    },
    {
      icon: Palette,
      title: 'Graphic Design',
      description: 'Eye-catching graphics to enhance your brand identity and visual presence.',
      features: ['Logo Design', 'Brand Assets', 'Print Materials'],
      deliveryTime: '1-2 weeks'
    },
    {
      icon: ShoppingCart,
      title: 'E-commerce Solutions',
      description: 'Build secure and scalable online stores that drive conversions.',
      features: ['Payment Gateway', 'Inventory System', 'Order Management'],
      deliveryTime: '4-6 weeks'
    },
    {
      icon: Search,
      title: 'SEO Optimization',
      description: 'Improve search engine rankings and attract organic visitors.',
      features: ['Keyword Research', 'On-Page SEO', 'Link Building'],
      deliveryTime: '3-6 months'
    },
    {
      icon: Zap,
      title: 'Google Ads Marketing',
      description: 'Targeted campaigns to increase ROI and reach your audience.',
      features: ['Campaign Setup', 'Ad Optimization', 'Performance Reports'],
      deliveryTime: '1 week'
    },
    {
      icon: Share2,
      title: 'Social Media Marketing',
      description: 'Engage followers and build brand awareness across platforms.',
      features: ['Content Strategy', 'Community Management', 'Growth Tactics'],
      deliveryTime: 'Ongoing'
    },
    {
      icon: Globe,
      title: 'Domain Registration',
      description: 'Register professional domains for your business.',
      features: ['Domain Search', 'Privacy Protection', 'DNS Management'],
      deliveryTime: 'Instant'
    },
    {
      icon: FileText,
      title: 'Content Marketing',
      description: 'Create valuable content that builds authority and trust.',
      features: ['Blog Writing', 'Video Scripts', 'Email Campaigns'],
      deliveryTime: '2-3 weeks'
    }
  ];

  const topServices = [
    {
      title: 'Website Design',
      description: 'We create stunning, user-centric websites that convert visitors into customers with modern design principles and cutting-edge technology.',
      benefits: ['Responsive Design', 'SEO Optimized', 'Fast Loading', 'Mobile First'],
      icon: Globe,
      stats: { clients: '500+', satisfaction: '98%', projects: '750+' }
    },
    {
      title: 'E-commerce Solutions',
      description: 'Build powerful online stores with secure payment integration, inventory management, and seamless customer experience.',
      benefits: ['Secure Payments', 'Scalable Platform', 'Easy Management', 'Multi-Currency'],
      icon: ShoppingCart,
      stats: { clients: '200+', satisfaction: '96%', revenue: '$5M+' }
    },
    {
      title: 'SEO Optimization',
      description: 'Dominate search results and drive organic traffic to your website with proven strategies and data-driven insights.',
      benefits: ['Keyword Research', 'Technical SEO', 'Link Building', 'Content Strategy'],
      icon: Search,
      stats: { rankings: 'Top 3', traffic: '+250%', keywords: '1000+' }
    },
    {
      title: 'Digital Marketing',
      description: 'Strategic campaigns designed to reach your target audience and boost conversions across all digital channels.',
      benefits: ['Multi-Channel', 'Data-Driven', 'ROI Focused', 'A/B Testing'],
      icon: TrendingUp,
      stats: { campaigns: '300+', roi: '400%', reach: '10M+' }
    }
  ];

  const testimonials = [
    {
      name: 'Joel Baxter',
      location: 'Bandung',
      role: 'Founder',
      company: 'Tech Innovations',
      quote: 'Muhali Software Solutions delivered beyond our expectations. Their strategic approach transformed our business.',
      rating: 5,
      project: 'Full Website Redesign'
    },
    {
      name: 'Jennifer Hopkins',
      location: 'Capetown',
      role: 'CEO',
      company: 'Global Ventures',
      quote: 'Professional, efficient, and results-driven. Best decision we made for our digital presence.',
      rating: 5,
      project: 'E-commerce Platform'
    },
    {
      name: 'Aviana Benson',
      location: 'Randburg',
      role: 'Marketing Director',
      company: 'Growth Co',
      quote: "The team's expertise in digital marketing helped us increase our revenue by 300%.",
      rating: 5,
      project: 'Digital Marketing Campaign'
    }
  ];

  return (
    <main className="overflow-hidden">
      {/* Hero Section */}
      <section className="relative min-h-screen flex items-center justify-center bg-gradient-to-br from-background via-secondary/20 to-background overflow-hidden pt-20">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_30%_50%,hsl(var(--primary)/0.15),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_70%_50%,hsl(var(--accent)/0.15),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[linear-gradient(to_right,hsl(var(--border))_1px,transparent_1px),linear-gradient(to_bottom,hsl(var(--border))_1px,transparent_1px)] bg-[size:32px_32px] opacity-20"></div>

        <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative z-10">
          <div className="inline-flex items-center gap-2 px-6 py-3 bg-primary/10 backdrop-blur-sm rounded-full text-primary font-semibold text-sm mb-8 animate-fade-in border border-primary/20">
            <Sparkles className="w-4 h-4" />
            <span>What We Offer</span>
          </div>
          <h1 className="text-6xl md:text-7xl font-bold text-foreground mb-6 leading-tight">
            Our <span className="bg-gradient-to-r from-primary to-accent bg-clip-text ">Services</span>
          </h1>
          <p className="text-2xl text-muted-foreground max-w-3xl mx-auto leading-relaxed mb-8">
            Discover how our expert team can transform your online presence
          </p>
          <p className="text-lg text-muted-foreground/80 max-w-4xl mx-auto leading-relaxed mb-12">
            Let us craft your digital presence and drive growth through innovative and effective strategies tailored to your needs. We pride ourselves on delivering high-quality, results-driven services that enhance your online presence and drive business forward.
          </p>
        </div>
      </section>

      {/* Core Services Grid */}
      <section className="py-32 bg-background relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <div className="inline-flex items-center gap-2 px-6 py-3 bg-primary/10 rounded-full text-primary font-semibold text-sm mb-6 border border-primary/20">
              <Award className="w-4 h-4" />
              <span>10+ Years of Experience</span>
            </div>
            <h2 className="text-5xl font-bold text-foreground mb-6">
              Our Core <span className="bg-gradient-to-r from-primary to-accent bg-clip-text ">Services</span>
            </h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Comprehensive digital solutions to help your business thrive online
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-8">
            {services.map((service, index) => (
              <div
                key={index}
                className="group relative bg-card p-8 rounded-3xl shadow-lg hover:shadow-elegant transition-all duration-500 border border-border hover:border-primary/30 hover:-translate-y-3 overflow-hidden"
              >
                {/* Background gradient on hover */}
                <div className="absolute inset-0 bg-gradient-to-br from-primary/5 to-accent/5 opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
                
                <div className="relative z-10">
                  <div className="w-16 h-16 rounded-2xl bg-gradient-to-br from-primary to-accent p-4 mb-6 group-hover:scale-110 group-hover:rotate-6 transition-all duration-500 shadow-lg">
                    <service.icon className="w-full h-full text-primary-foreground" />
                  </div>
                  
                  <h3 className="text-xl font-bold text-foreground mb-3 group-hover:text-primary transition-colors duration-300">
                    {service.title}
                  </h3>
                  
                  <p className="text-muted-foreground leading-relaxed text-sm mb-6">
                    {service.description}
                  </p>

                  {/* Features List */}
                  <div className="space-y-2 mb-6">
                    {service.features.map((feature, i) => (
                      <div key={i} className="flex items-center gap-2">
                        <CheckCircle className="w-4 h-4 text-primary flex-shrink-0" />
                        <span className="text-xs text-muted-foreground font-medium">{feature}</span>
                      </div>
                    ))}
                  </div>

                  {/* Delivery Time */}
                  <div className="flex items-center gap-2 pt-4 border-t border-border">
                    <Clock className="w-4 h-4 text-muted-foreground" />
                    <span className="text-xs text-muted-foreground font-semibold">{service.deliveryTime}</span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Featured Services */}
      <section className="py-32 bg-secondary/30 relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-foreground mb-6">
              Featured <span className="bg-gradient-to-r from-primary to-accent bg-clip-text ">Services</span>
            </h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Explore our top-tier services that drive measurable results
            </p>
          </div>

          <div className="space-y-24">
            {topServices.map((service, index) => (
              <div
                key={index}
                className={`grid grid-cols-1 lg:grid-cols-2 gap-16 items-center ${
                  index % 2 === 1 ? 'lg:[&>:first-child]:order-2' : ''
                }`}
              >
                <div className="space-y-8">
                  <div className="w-20 h-20 rounded-3xl bg-gradient-to-br from-primary to-accent p-5 shadow-elegant">
                    <service.icon className="w-full h-full text-primary-foreground" />
                  </div>
                  
                  <h3 className="text-4xl md:text-5xl font-bold text-foreground leading-tight">
                    {service.title}
                  </h3>
                  
                  <p className="text-lg text-muted-foreground leading-relaxed">
                    {service.description}
                  </p>

                  {/* Benefits Grid */}
                  <div className="grid grid-cols-2 gap-4">
                    {service.benefits.map((benefit, i) => (
                      <div key={i} className="flex items-start gap-3 bg-card p-4 rounded-xl border border-border shadow-sm hover:shadow-md transition-shadow duration-300">
                        <CheckCircle className="w-5 h-5 text-primary flex-shrink-0 mt-0.5" />
                        <span className="text-sm text-foreground font-semibold">{benefit}</span>
                      </div>
                    ))}
                  </div>

                  {/* Stats */}
                  <div className="flex gap-8 pt-6">
                    {Object.entries(service.stats).map(([key, value]) => (
                      <div key={key} className="text-center">
                        <div className="text-2xl font-bold text-primary mb-1">{value}</div>
                        <div className="text-xs text-muted-foreground uppercase tracking-wide">{key}</div>
                      </div>
                    ))}
                  </div>
                </div>

                {/* Visual Card */}
                <div className="relative">
                  <div className="absolute -inset-4 bg-gradient-to-br from-primary/20 to-accent/20 rounded-3xl blur-3xl opacity-60"></div>
                  <div className="relative bg-gradient-to-br from-card to-secondary border-2 border-border p-12 rounded-3xl shadow-elegant overflow-hidden">
                    <div className="absolute top-0 right-0 w-64 h-64 bg-gradient-to-br from-primary/10 to-transparent rounded-full -mr-32 -mt-32"></div>
                    <div className="absolute bottom-0 left-0 w-64 h-64 bg-gradient-to-tr from-accent/10 to-transparent rounded-full -ml-32 -mb-32"></div>
                    
                    <div className="relative grid grid-cols-2 gap-6">
                      {[
                        { icon: Target, label: 'Strategy', value: '100%' },
                        { icon: Users, label: 'Support', value: '24/7' },
                        { icon: TrendingUp, label: 'Growth', value: '+300%' },
                        { icon: Award, label: 'Quality', value: 'Premium' }
                      ].map((item, i) => (
                        <div key={i} className="bg-background/80 backdrop-blur-sm p-6 rounded-2xl border border-border shadow-sm hover:shadow-md hover:scale-105 transition-all duration-300 group">
                          <div className="w-12 h-12 rounded-xl bg-gradient-to-br from-primary to-accent p-2.5 mb-4 group-hover:rotate-12 transition-transform duration-300">
                            <item.icon className="w-full h-full text-primary-foreground" />
                          </div>
                          <div className="text-2xl font-bold text-foreground mb-1">{item.value}</div>
                          <p className="text-sm font-medium text-muted-foreground">{item.label}</p>
                        </div>
                      ))}
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Testimonials */}
      <section className="py-32 bg-background relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-foreground mb-6">
              What Our <span className="bg-gradient-to-r from-primary to-accent bg-clip-text ">Clients Say</span>
            </h2>
            <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
              Real feedback from real clients who trust our services
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {testimonials.map((testimonial, index) => (
              <div
                key={index}
                className="group relative bg-card p-10 rounded-3xl shadow-lg hover:shadow-elegant transition-all duration-500 border-2 border-border hover:border-primary/30 hover:-translate-y-3 overflow-hidden"
              >
                <div className="absolute inset-0 bg-gradient-to-br from-primary/5 to-accent/5 opacity-0 group-hover:opacity-100 transition-opacity duration-500"></div>
                
                <div className="relative z-10">
                  {/* Stars */}
                  <div className="flex gap-1 mb-6">
                    {[...Array(testimonial.rating)].map((_, i) => (
                      <Star key={i} className="w-5 h-5 text-yellow-500 fill-yellow-500" />
                    ))}
                  </div>
                  
                  {/* Quote */}
                  <p className="text-muted-foreground mb-8 text-lg leading-relaxed italic">
                    "{testimonial.quote}"
                  </p>
                  
                  {/* Project Badge */}
                  <div className="inline-flex items-center gap-2 px-4 py-2 bg-primary/10 rounded-full mb-6">
                    <FileText className="w-4 h-4 text-primary" />
                    <span className="text-xs text-primary font-semibold">{testimonial.project}</span>
                  </div>
                  
                  {/* Author Info */}
                  <div className="flex items-center gap-4 pt-6 border-t border-border">
                    <div className="w-14 h-14 rounded-full bg-gradient-to-br from-primary to-accent flex items-center justify-center text-primary-foreground font-bold text-xl shadow-lg">
                      {testimonial.name.charAt(0)}
                    </div>
                    <div>
                      <p className="font-bold text-foreground text-lg">{testimonial.name}</p>
                      <p className="text-muted-foreground text-sm">{testimonial.role}</p>
                      <p className="text-muted-foreground/70 text-xs">{testimonial.company} • {testimonial.location}</p>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="py-32 bg-gradient-to-br from-background via-secondary/20 to-background relative overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_50%_50%,hsl(var(--primary)/0.1),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[linear-gradient(to_right,hsl(var(--border))_1px,transparent_1px),linear-gradient(to_bottom,hsl(var(--border))_1px,transparent_1px)] bg-[size:32px_32px] opacity-20"></div>

        <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative z-10">
          <h2 className="text-5xl md:text-6xl font-bold mb-8 leading-tight text-foreground">
            Ready to Elevate Your <span className="bg-gradient-to-r from-primary to-accent bg-clip-text ">Digital Presence?</span>
          </h2>
          <p className="text-xl md:text-2xl mb-12 text-muted-foreground leading-relaxed max-w-3xl mx-auto">
            At Muhali Software Solutions, we offer a wide range of services tailored to help your business thrive online. Whether you need a stunning website, effective marketing strategies, or a strong brand identity, we've got you covered.
          </p>
          <Link to="/contact">
            <button className="group inline-flex items-center gap-3 bg-gradient-to-r from-primary to-accent text-primary-foreground px-12 py-6 rounded-2xl text-lg font-bold hover:shadow-elegant hover:scale-105 transition-all duration-300 border-2 border-transparent hover:border-primary/20">
              Discover More
              <ArrowRight className="w-6 h-6 group-hover:translate-x-2 transition-transform duration-300" />
            </button>
          </Link>
        </div>
      </section>
    </main>
  );
}

export default Services;
