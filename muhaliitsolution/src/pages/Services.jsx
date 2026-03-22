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
  Facebook,
  Instagram,
  Youtube,
  MessageCircle
} from 'lucide-react';

function Services() {
  const services = [
    {
      icon: Globe,
      title: 'Website Design',
      description: 'Craft visually appealing and user-friendly websites tailored to your brand.'
    },
    {
      icon: Server,
      title: 'Web Hosting',
      description: 'Secure and reliable hosting solutions to keep websites running smoothly.'
    },
    {
      icon: TrendingUp,
      title: 'Digital Marketing',
      description: 'Comprehensive strategies to boost online presence and engagement.'
    },
    {
      icon: Palette,
      title: 'Graphic Design',
      description: 'Eye-catching graphics to enhance your brand identity and visual presence.'
    },
    {
      icon: ShoppingCart,
      title: 'E-commerce Solutions',
      description: 'Build secure and scalable online stores that drive conversions.'
    },
    {
      icon: Search,
      title: 'SEO Optimization',
      description: 'Improve search engine rankings and attract organic visitors.'
    },
    {
      icon: Zap,
      title: 'Google Ads Marketing',
      description: 'Targeted campaigns to increase ROI and reach your audience.'
    },
    {
      icon: Share2,
      title: 'Social Media Marketing',
      description: 'Engage followers and build brand awareness across platforms.'
    },
    {
      icon: Globe,
      title: 'Domain Registration',
      description: 'Register professional domains for your business.'
    },
    {
      icon: FileText,
      title: 'Content Marketing',
      description: 'Create valuable content that builds authority and trust.'
    }
  ];

  const topServices = [
    {
      title: 'Website Design',
      description: 'We create stunning, user-centric websites that convert visitors into customers.',
      benefits: ['Responsive Design', 'SEO Optimized', 'Fast Loading'],
      icon: Globe
    },
    {
      title: 'E-commerce Solutions',
      description: 'Build powerful online stores with secure payment integration and inventory management.',
      benefits: ['Secure Payments', 'Scalable Platform', 'Easy Management'],
      icon: ShoppingCart
    },
    {
      title: 'SEO Optimization',
      description: 'Dominate search results and drive organic traffic to your website.',
      benefits: ['Keyword Research', 'Technical SEO', 'Link Building'],
      icon: Search
    },
    {
      title: 'Digital Marketing',
      description: 'Strategic campaigns designed to reach your target audience and boost conversions.',
      benefits: ['Multi-Channel', 'Data-Driven', 'ROI Focused'],
      icon: TrendingUp
    }
  ];

  const testimonials = [
    {
      name: 'Joel Baxter',
      location: 'Bandung',
      role: 'Founder',
      quote: 'Creative Design Solution delivered beyond our expectations. Their strategic approach transformed our business.'
    },
    {
      name: 'Jennifer Hopkins',
      location: 'Capetown',
      role: 'CEO',
      quote: 'Professional, efficient, and results-driven. Best decision we made for our digital presence.'
    },
    {
      name: 'Aviana Benson',
      location: 'Randburg',
      role: 'Marketing Director',
      quote: "The team's expertise in digital marketing helped us increase our revenue by 300%."
    }
  ];

  return (
    <main className="overflow-hidden">
      <section className="relative min-h-screen flex items-center justify-center bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 overflow-hidden pt-20">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_30%_50%,rgba(59,130,246,0.15),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_70%_50%,rgba(6,182,212,0.15),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[linear-gradient(to_right,#8080800a_1px,transparent_1px),linear-gradient(to_bottom,#8080800a_1px,transparent_1px)] bg-[size:32px_32px]"></div>

        <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative z-10">
          <div className="inline-flex items-center gap-2 px-4 py-2 bg-blue-500/10 backdrop-blur-sm rounded-full text-blue-300 font-medium text-sm mb-8 animate-fade-in">
            <Sparkles className="w-4 h-4" />
            <span>What We Offer</span>
          </div>
          <h1 className="text-6xl md:text-7xl font-bold text-white mb-6 leading-tight">
            Our <span className="bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Services</span>
          </h1>
          <p className="text-2xl text-gray-300 max-w-3xl mx-auto leading-relaxed mb-8">
            Discover how our expert team can transform your online presence
          </p>
          <p className="text-lg text-gray-400 max-w-4xl mx-auto leading-relaxed mb-12">
            Let us craft your digital presence and drive growth through innovative and effective strategies tailored to your needs. We pride ourselves on delivering high-quality, results-driven services that enhance your online presence and drive business forward.
          </p>
          <button className="group inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-cyan-600 text-white px-10 py-4 rounded-xl text-lg font-semibold hover:from-blue-700 hover:to-cyan-700 transition-all duration-300 shadow-2xl hover:shadow-blue-500/50 hover:scale-105">
            Learn More
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
          </button>
        </div>
      </section>

      <section className="py-32 bg-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <div className="inline-flex items-center gap-2 px-4 py-2 bg-blue-100 rounded-full text-blue-600 font-medium text-sm mb-6">
              <Award className="w-4 h-4" />
              <span>10+ Years of Experience</span>
            </div>
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              Our Core <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Services</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Comprehensive digital solutions to help your business thrive online
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-6">
            {services.map((service, index) => (
              <div
                key={index}
                className="group relative bg-gradient-to-br from-gray-50 to-white p-8 rounded-2xl shadow-sm hover:shadow-2xl transition-all duration-500 border border-gray-100 hover:-translate-y-2"
              >
                <div className="w-14 h-14 rounded-xl bg-gradient-to-br from-blue-500 to-cyan-500 p-3 mb-6 group-hover:scale-110 group-hover:rotate-6 transition-all duration-500 shadow-lg">
                  <service.icon className="w-full h-full text-white" />
                </div>
                <h3 className="text-xl font-bold text-gray-900 mb-3">{service.title}</h3>
                <p className="text-gray-600 leading-relaxed text-sm">{service.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-b from-slate-50 to-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              Featured <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Services</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Explore our top-tier services that drive measurable results
            </p>
          </div>

          <div className="space-y-16">
            {topServices.map((service, index) => (
              <div
                key={index}
                className={`grid grid-cols-1 lg:grid-cols-2 gap-12 items-center ${index % 2 === 1 ? 'lg:grid-cols-2 lg:[&>:first-child]:order-2' : ''}`}
              >
                <div>
                  <div className="w-16 h-16 rounded-2xl bg-gradient-to-br from-blue-500 to-cyan-500 p-4 mb-6 shadow-lg">
                    <service.icon className="w-full h-full text-white" />
                  </div>
                  <h3 className="text-4xl font-bold text-gray-900 mb-6">{service.title}</h3>
                  <p className="text-lg text-gray-700 leading-relaxed mb-8">{service.description}</p>

                  <div className="space-y-4 mb-8">
                    {service.benefits.map((benefit, i) => (
                      <div key={i} className="flex items-center gap-3">
                        <CheckCircle className="w-6 h-6 text-blue-600 flex-shrink-0" />
                        <span className="text-gray-700 font-semibold">{benefit}</span>
                      </div>
                    ))}
                  </div>

                  <button className="inline-flex items-center gap-2 text-blue-600 font-bold text-lg hover:text-blue-700 transition-colors duration-300 group">
                    Learn More
                    <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
                  </button>
                </div>

                <div className="relative">
                  <div className="absolute -inset-4 bg-gradient-to-br from-blue-500/20 to-cyan-500/20 rounded-3xl blur-2xl"></div>
                  <div className="relative bg-gradient-to-br from-blue-50 to-cyan-50 p-12 rounded-3xl border border-blue-200 shadow-2xl">
                    <div className="grid grid-cols-2 gap-6">
                      {[1, 2, 3, 4].map((item) => (
                        <div key={item} className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                          <div className="w-12 h-12 rounded-lg bg-gradient-to-br from-blue-500 to-cyan-500 mb-3"></div>
                          <p className="text-sm font-semibold text-gray-900">Feature {item}</p>
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

      <section className="py-32 bg-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              What Our <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Clients Say</span>
            </h2>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {testimonials.map((testimonial, index) => (
              <div
                key={index}
                className="group relative bg-gradient-to-br from-gray-50 to-white p-10 rounded-3xl shadow-sm hover:shadow-2xl transition-all duration-500 border border-gray-100 hover:-translate-y-2"
              >
                <div className="flex gap-1 mb-6">
                  {[...Array(5)].map((_, i) => (
                    <Star key={i} className="w-5 h-5 text-yellow-400 fill-current" />
                  ))}
                </div>
                <p className="text-gray-700 mb-8 text-lg leading-relaxed italic">
                  "{testimonial.quote}"
                </p>
                <div className="flex items-center gap-4 pt-6 border-t border-gray-100">
                  <div className="w-12 h-12 rounded-full bg-gradient-to-br from-blue-500 to-cyan-500 flex items-center justify-center text-white font-bold text-lg">
                    {testimonial.name.charAt(0)}
                  </div>
                  <div>
                    <p className="font-bold text-gray-900">{testimonial.name}</p>
                    <p className="text-gray-500 text-sm">{testimonial.role}, {testimonial.location}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white relative overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_50%_50%,rgba(59,130,246,0.1),transparent_50%)]"></div>

        <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative">
          <h2 className="text-5xl md:text-6xl font-bold mb-8 leading-tight">
            Ready to Elevate Your <span className="bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Digital Presence?</span>
          </h2>
          <p className="text-xl md:text-2xl mb-12 text-gray-300 leading-relaxed max-w-3xl mx-auto">
            At Creative Design Solution, we offer a wide range of services tailored to help your business thrive online. Whether you need a stunning website, effective marketing strategies, or a strong brand identity, we've got you covered.
          </p>
          <button className="group inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-cyan-600 text-white px-10 py-5 rounded-xl text-lg font-semibold hover:from-blue-700 hover:to-cyan-700 transition-all duration-300 shadow-2xl hover:shadow-blue-500/50 hover:scale-105">
            Discover More
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
          </button>
        </div>
      </section>

      <section className="py-20 bg-white border-t border-gray-200 relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-12 mb-20">
            <div>
              <h3 className="text-lg font-bold text-gray-900 mb-6">Services</h3>
              <ul className="space-y-3">
                {['Web Hosting', 'Website Design', 'SEO Optimization', 'Digital Marketing', 'E-commerce Solutions'].map((item, index) => (
                  <li key={index}>
                    <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">{item}</a>
                  </li>
                ))}
              </ul>
            </div>

            <div>
              <h3 className="text-lg font-bold text-gray-900 mb-6">Company</h3>
              <ul className="space-y-3">
                {['About Us', 'Services', 'Marketing', 'Contact Us', 'Websites'].map((item, index) => (
                  <li key={index}>
                    <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">{item}</a>
                  </li>
                ))}
              </ul>
            </div>

            <div>
              <h3 className="text-lg font-bold text-gray-900 mb-6">Newsletter</h3>
              <p className="text-gray-600 mb-4">Get the latest updates and offers</p>
              <div className="flex gap-2">
                <input
                  type="email"
                  placeholder="Enter your email"
                  className="flex-1 px-4 py-3 rounded-lg bg-gray-100 border border-gray-200 text-gray-900 placeholder-gray-500 focus:outline-none focus:border-blue-500 focus:bg-white transition-all duration-300"
                />
                <button className="px-4 py-3 bg-gradient-to-r from-blue-600 to-cyan-600 text-white font-semibold rounded-lg hover:from-blue-700 hover:to-cyan-700 transition-all duration-300">
                  Join
                </button>
              </div>
            </div>

            <div>
              <h3 className="text-lg font-bold text-gray-900 mb-6">Follow Us</h3>
              <div className="flex gap-4 mb-6">
                {[
                  { icon: Facebook, label: 'Facebook' },
                  { icon: Instagram, label: 'Instagram' },
                  { icon: Youtube, label: 'YouTube' },
                  { icon: MessageCircle, label: 'WhatsApp' }
                ].map((social, index) => (
                  <a
                    key={index}
                    href="#"
                    className="w-10 h-10 rounded-lg bg-gray-100 hover:bg-blue-600 text-gray-600 hover:text-white flex items-center justify-center transition-all duration-300"
                    title={social.label}
                  >
                    <social.icon className="w-5 h-5" />
                  </a>
                ))}
              </div>
            </div>
          </div>

          <div className="border-t border-gray-200 pt-8">
            <div className="flex flex-col md:flex-row items-center justify-between gap-6">
              <p className="text-gray-600">
                &copy; 2025 Creative Design Solution. All rights reserved.
              </p>
              <div className="flex gap-6 text-sm">
                <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">Terms of Use</a>
                <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">Privacy Policy</a>
                <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">Cookie Policy</a>
                <a href="#" className="text-gray-600 hover:text-blue-600 transition-colors duration-300">WhatsApp</a>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

export default Services;
