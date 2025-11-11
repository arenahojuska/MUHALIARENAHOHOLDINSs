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
  Sparkles
} from 'lucide-react';
import { Hero } from '../components/Hero';
function Home() {
  
  return (
      <>
      <Hero />
    <main className="overflow-hidden">
      <section id="services" className="py-32 bg-gradient-to-b from-slate-50 via-white to-slate-50 relative">
        <div className="absolute inset-0 bg-[linear-gradient(to_right,#8080800a_1px,transparent_1px),linear-gradient(to_bottom,#8080800a_1px,transparent_1px)] bg-[size:24px_24px]"></div>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative">
          <div className="text-center mb-20">
            <div className="inline-flex items-center gap-2 px-4 py-2 bg-blue-50 rounded-full text-blue-600 font-medium text-sm mb-6">
              <Sparkles className="w-4 h-4" />
              <span>Premium Digital Solutions</span>
            </div>
            <h2 className="text-5xl md:text-6xl font-bold text-gray-900 mb-6 tracking-tight">
              Our Core <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Services</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Comprehensive digital solutions designed to elevate your brand and drive measurable results
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            {[
              {
                icon: Monitor,
                title: 'Website Design',
                description: 'Custom, responsive websites tailored to your brand and business goals.',
                color: 'from-blue-500 to-cyan-500'
              },
              {
                icon: Server,
                title: 'Web Hosting',
                description: 'Reliable, secure, and fast hosting solutions for your website.',
                color: 'from-violet-500 to-blue-500'
              },
              {
                icon: TrendingUp,
                title: 'Digital Marketing',
                description: 'Comprehensive marketing strategies to boost your online presence.',
                color: 'from-cyan-500 to-teal-500'
              },
              {
                icon: Palette,
                title: 'Graphic Design',
                description: 'Eye-catching logos, branding, and visual content for your business.',
                color: 'from-teal-500 to-emerald-500'
              }
            ].map((service, index) => (
              <div
                key={index}
                className="group relative bg-white p-8 rounded-2xl shadow-sm hover:shadow-2xl transition-all duration-500 border border-gray-100 hover:border-transparent hover:-translate-y-2"
              >
                <div className={`absolute inset-0 bg-gradient-to-br ${service.color} opacity-0 group-hover:opacity-5 rounded-2xl transition-opacity duration-500`}></div>
                <div className={`w-14 h-14 rounded-xl bg-gradient-to-br ${service.color} p-3 mb-6 transform group-hover:scale-110 group-hover:rotate-3 transition-all duration-500 shadow-lg`}>
                  <service.icon className="w-full h-full text-white" />
                </div>
                <h3 className="text-xl font-bold mb-3 text-gray-900">{service.title}</h3>
                <p className="text-gray-600 leading-relaxed">{service.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section id="about" className="py-32 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white relative overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_30%_50%,rgba(59,130,246,0.1),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_70%_50%,rgba(6,182,212,0.1),transparent_50%)]"></div>

        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
            <div>
              <div className="inline-flex items-center gap-2 px-4 py-2 bg-white/10 backdrop-blur-sm rounded-full text-blue-300 font-medium text-sm mb-6">
                <CheckCircle className="w-4 h-4" />
                <span>Trusted by 300+ Businesses</span>
              </div>
              <h2 className="text-5xl font-bold mb-6 leading-tight">
                About Creative Design <span className="bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Solution</span>
              </h2>
              <div className="space-y-4 text-lg text-gray-300">
                <p className="leading-relaxed">
                  With over 10 years of experience in the digital industry, Creative Design Solution
                  has been helping businesses of all sizes establish and grow their online presence.
                </p>
                <p className="leading-relaxed">
                  Our mission is to provide affordable, high-quality web design and digital marketing
                  services that empower businesses to succeed in the digital landscape.
                </p>
                <p className="leading-relaxed">
                  We believe in transparency, creativity, and delivering results that exceed expectations.
                  Our team of experienced professionals is dedicated to your success.
                </p>
              </div>
            </div>

            <div className="grid grid-cols-2 gap-6">
              {[
                { number: '500+', label: 'Projects Done', icon: CheckCircle },
                { number: '300+', label: 'Happy Customers', icon: Users },
                { number: '10+', label: 'Years Experience', icon: Star },
                { number: '4.9', label: 'Customer Rating', icon: TrendingUp }
              ].map((stat, index) => (
                <div
                  key={index}
                  className="bg-white/5 backdrop-blur-lg p-8 rounded-2xl border border-white/10 hover:bg-white/10 transition-all duration-300 hover:scale-105 group"
                >
                  <stat.icon className="w-8 h-8 text-blue-400 mb-4 group-hover:scale-110 transition-transform duration-300" />
                  <p className="text-5xl font-bold bg-gradient-to-br from-blue-400 to-cyan-400 bg-clip-text text-transparent mb-2">
                    {stat.number}
                  </p>
                  <p className="text-gray-300 font-semibold">{stat.label}</p>
                </div>
              ))}
            </div>
          </div>
        </div>
      </section>

      <section className="py-32 bg-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6 tracking-tight">
              Complete Digital <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Ecosystem</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Everything you need to establish, grow, and scale your digital presence
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {[
              {
                icon: Monitor,
                title: 'Website Design',
                description: 'Professional, modern websites built with the latest technologies and best practices.',
                gradient: 'from-blue-500 to-cyan-500'
              },
              {
                icon: ShoppingCart,
                title: 'E-commerce Solutions',
                description: 'Powerful online stores that drive sales and provide seamless shopping experiences.',
                gradient: 'from-cyan-500 to-teal-500'
              },
              {
                icon: Search,
                title: 'SEO',
                description: 'Improve your search engine rankings and drive organic traffic to your website.',
                gradient: 'from-teal-500 to-emerald-500'
              },
              {
                icon: Target,
                title: 'Google Ads',
                description: 'Targeted advertising campaigns that deliver measurable results and ROI.',
                gradient: 'from-emerald-500 to-green-500'
              },
              {
                icon: Share2,
                title: 'Social Media',
                description: 'Engage your audience and build your brand across all social platforms.',
                gradient: 'from-violet-500 to-blue-500'
              },
              {
                icon: Server,
                title: 'Hosting',
                description: 'Fast, secure, and reliable web hosting with 99.9% uptime guarantee.',
                gradient: 'from-blue-500 to-indigo-500'
              },
              {
                icon: Globe,
                title: 'Domain',
                description: 'Register your perfect domain name and establish your online identity.',
                gradient: 'from-indigo-500 to-violet-500'
              },
              {
                icon: Palette,
                title: 'Graphic Design',
                description: 'Creative designs that capture attention and communicate your brand message.',
                gradient: 'from-pink-500 to-rose-500'
              },
              {
                icon: FileText,
                title: 'Content Marketing',
                description: 'Compelling content that attracts, engages, and converts your target audience.',
                gradient: 'from-rose-500 to-orange-500'
              }
            ].map((service, index) => (
              <div
                key={index}
                className="group relative bg-gradient-to-br from-gray-50 to-white p-8 rounded-2xl hover:shadow-2xl transition-all duration-500 border border-gray-100"
              >
                <div className={`w-12 h-12 rounded-xl bg-gradient-to-br ${service.gradient} p-2.5 mb-6 group-hover:scale-110 transition-transform duration-300 shadow-lg`}>
                  <service.icon className="w-full h-full text-white" />
                </div>
                <h3 className="text-xl font-bold mb-3 text-gray-900">{service.title}</h3>
                <p className="text-gray-600 mb-6 leading-relaxed">{service.description}</p>
                <button className="inline-flex items-center gap-2 text-blue-600 font-semibold hover:gap-3 transition-all duration-300 group/btn">
                  Learn More
                  <ArrowRight className="w-4 h-4 group-hover/btn:translate-x-1 transition-transform duration-300" />
                </button>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-br from-blue-600 via-cyan-600 to-teal-600 text-white relative overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_30%_50%,rgba(255,255,255,0.1),transparent_50%)]"></div>
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_70%_50%,rgba(255,255,255,0.1),transparent_50%)]"></div>

        <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative">
          <div className="inline-flex items-center justify-center w-24 h-24 rounded-3xl bg-white/20 backdrop-blur-sm mb-8 mx-auto">
            <ShoppingCart className="w-12 h-12" />
          </div>
          <h2 className="text-5xl md:text-6xl font-bold mb-8 leading-tight">
            Sell Your Products 24/7 with High-Powered eCommerce!
          </h2>
          <p className="text-xl md:text-2xl mb-12 max-w-3xl mx-auto leading-relaxed text-blue-50">
            We build flawless eCommerce websites to help businesses generate online sales 24/7.
            From product catalogs to secure payment processing, we handle everything.
          </p>
          <button className="group inline-flex items-center gap-3 bg-white text-blue-600 px-10 py-5 rounded-xl text-lg font-semibold hover:bg-gray-50 transition-all duration-300 shadow-2xl hover:shadow-3xl hover:scale-105">
            Discover More
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
          </button>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-b from-slate-50 to-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6 tracking-tight">
              Why Choose <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Us</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              We combine expertise, affordability, and dedication to deliver exceptional results
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-12">
            {[
              {
                icon: CheckCircle,
                title: 'Affordable Pricing',
                description: 'Quality services that fit your budget. We believe great design should be accessible to all businesses.',
                gradient: 'from-blue-500 to-cyan-500'
              },
              {
                icon: Users,
                title: 'Experienced Team',
                description: 'Our team of experts brings years of experience and stays current with industry trends.',
                gradient: 'from-cyan-500 to-teal-500'
              },
              {
                icon: Headphones,
                title: 'Ongoing Support',
                description: 'We\'re here for you even after launch. Get continuous support and maintenance for peace of mind.',
                gradient: 'from-teal-500 to-emerald-500'
              }
            ].map((item, index) => (
              <div key={index} className="text-center group">
                <div className={`relative inline-flex items-center justify-center w-24 h-24 rounded-3xl bg-gradient-to-br ${item.gradient} mb-8 mx-auto shadow-xl group-hover:scale-110 group-hover:rotate-3 transition-all duration-500`}>
                  <div className="absolute inset-0 rounded-3xl bg-white opacity-0 group-hover:opacity-20 transition-opacity duration-300"></div>
                  <item.icon className="w-12 h-12 text-white relative z-10" />
                </div>
                <h3 className="text-2xl font-bold mb-4 text-gray-900">{item.title}</h3>
                <p className="text-gray-600 text-lg leading-relaxed">{item.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6 tracking-tight">
              What Our <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Clients Say</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Don't just take our word for it — hear from businesses we've helped succeed
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            {[
              {
                name: 'Sarah Johnson',
                location: 'New York, NY',
                review: 'Creative Design Solution transformed our online presence completely. Their team was professional, responsive, and delivered beyond our expectations. Highly recommended!'
              },
              {
                name: 'Michael Chen',
                location: 'Los Angeles, CA',
                review: 'Working with Creative Design Solution was a game-changer for our business. The e-commerce site they built has increased our sales by 200%. Exceptional service!'
              },
              {
                name: 'Emily Rodriguez',
                location: 'Chicago, IL',
                review: 'From design to launch, everything was seamless. The team understood our vision perfectly and created a website that truly represents our brand. Thank you!'
              }
            ].map((testimonial, index) => (
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
                  "{testimonial.review}"
                </p>
                <div className="flex items-center gap-4">
                  <div className="w-12 h-12 rounded-full bg-gradient-to-br from-blue-500 to-cyan-500 flex items-center justify-center text-white font-bold text-lg">
                    {testimonial.name.charAt(0)}
                  </div>
                  <div>
                    <p className="font-bold text-gray-900">{testimonial.name}</p>
                    <p className="text-gray-500 text-sm">{testimonial.location}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section id="contact" className="py-32 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white relative overflow-hidden">
        <div className="absolute inset-0 bg-[radial-gradient(circle_at_50%_50%,rgba(59,130,246,0.1),transparent_50%)]"></div>

        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center relative">
          <div className="inline-flex items-center gap-2 px-4 py-2 bg-blue-500/10 backdrop-blur-sm rounded-full text-blue-300 font-medium text-sm mb-8">
            <Sparkles className="w-4 h-4" />
            <span>Let's Build Something Amazing</span>
          </div>
          <h2 className="text-5xl md:text-6xl font-bold mb-8 leading-tight">
            Ready to Get <span className="bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Started?</span>
          </h2>
          <p className="text-xl md:text-2xl mb-12 text-gray-300 leading-relaxed">
            Let's discuss your project and bring your vision to life. Contact us today for a free consultation.
          </p>
          <button className="group inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-cyan-600 text-white px-10 py-5 rounded-xl text-lg font-semibold hover:from-blue-700 hover:to-cyan-700 transition-all duration-300 shadow-2xl hover:shadow-blue-500/50 hover:scale-105">
            Get Your Free Quote
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
          </button>
        </div>
      </section>
    </main>
    </>
  );
}

export default Home;
