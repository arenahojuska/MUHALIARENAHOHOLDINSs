import {
  ArrowRight,
  Sparkles,
  CheckCircle,
  Users,
  Zap,
  Target,
  Star,
  Award,
  TrendingUp,
  Lightbulb,
  Heart
} from 'lucide-react';

function About() {
  const teamMembers = [
    { name: 'Alex Rivera', role: 'Creative Director', image: '👨‍💼' },
    { name: 'Sarah Chen', role: 'Lead Developer', image: '👩‍💻' },
    { name: 'Marcus Johnson', role: 'Marketing Lead', image: '👨‍💼' },
    { name: 'Emma Thompson', role: 'UX/UI Designer', image: '👩‍🎨' },
    { name: 'David Kim', role: 'SEO Specialist', image: '👨‍💼' },
    { name: 'Lisa Anderson', role: 'Project Manager', image: '👩‍💼' }
  ];

  const values = [
    {
      icon: Heart,
      title: 'Customer First',
      description: 'Your satisfaction is our top priority. We listen, understand, and deliver solutions that exceed expectations.'
    },
    {
      icon: Lightbulb,
      title: 'Innovation',
      description: 'We stay ahead of industry trends and embrace new technologies to deliver cutting-edge solutions.'
    },
    {
      icon: Award,
      title: 'Excellence',
      description: 'We are committed to delivering the highest quality work in every project we undertake.'
    },
    {
      icon: Zap,
      title: 'Agility',
      description: 'We adapt quickly to changing market demands and client needs without compromising quality.'
    }
  ];

  const testimonials = [
    {
      name: 'Joel Baxter',
      location: 'Bandung',
      quote: 'Creative Design Solution completely transformed our digital presence. Their team understood our vision and delivered beyond expectations. The results speak for themselves!'
    },
    {
      name: 'Jennifer Hopkins',
      location: 'Capetown',
      quote: 'Working with this agency was a game-changer for our business. They combined strategic thinking with creative excellence to deliver outstanding results.'
    },
    {
      name: 'Aviana Benson',
      location: 'Randburg',
      quote: 'The team at Creative Design Solution brought fresh ideas and professional execution to our project. Highly recommended for any business looking to grow online.'
    }
  ];

  const stats = [
    { number: '18K+', label: 'Projects Done', icon: TrendingUp },
    { number: '7K+', label: 'Happy Customers', icon: Users },
    { number: '4.7', label: 'Customer Rating', icon: Star },
    { number: '10+', label: 'Years Experience', icon: Award }
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
            <span>Our Story</span>
          </div>
          <h1 className="text-6xl md:text-7xl font-bold text-white mb-6 leading-tight">
            About <span className="bg-gradient-to-r from-blue-400 to-cyan-400 bg-clip-text text-transparent">Us</span>
          </h1>
          <p className="text-2xl text-gray-300 max-w-3xl mx-auto leading-relaxed">
            At Creative Design Solution, we are passionate about helping businesses succeed in the digital landscape.
          </p>
        </div>
      </section>

      <section className="py-32 bg-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
            <div>
              <h2 className="text-5xl font-bold text-gray-900 mb-8 leading-tight">
                Your Partner in <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Digital Excellence</span>
              </h2>
              <p className="text-xl text-gray-700 mb-6 leading-relaxed">
                Founded with a vision to bridge the gap between creativity and technology, we specialize in delivering high-quality digital experiences that drive results.
              </p>
              <p className="text-lg text-gray-600 leading-relaxed">
                Our team of dedicated professionals combines expertise in web design, digital marketing, branding, and more to create impactful solutions that enhance your online presence.
              </p>
            </div>
            <div className="relative">
              <div className="absolute inset-0 bg-gradient-to-br from-blue-500 to-cyan-500 rounded-3xl transform -rotate-6 opacity-20"></div>
              <div className="relative bg-gradient-to-br from-blue-50 to-cyan-50 p-12 rounded-3xl border border-blue-100 shadow-2xl">
                <div className="space-y-6">
                  {[
                    'Strategic Thinking',
                    'Creative Excellence',
                    'Measurable Results',
                    'Client Focus'
                  ].map((item, index) => (
                    <div key={index} className="flex items-center gap-4">
                      <div className="w-3 h-3 rounded-full bg-gradient-to-r from-blue-600 to-cyan-600"></div>
                      <span className="text-lg font-semibold text-gray-900">{item}</span>
                    </div>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-b from-slate-50 to-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              Our <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Approach</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-3xl mx-auto leading-relaxed">
              We start by understanding your business, goals, and target audience. With this insight, we craft customized strategies and solutions that align with your vision.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
            {[
              { step: '01', title: 'Discovery', description: 'We learn about your business, goals, and target market.' },
              { step: '02', title: 'Strategy', description: 'We develop a comprehensive strategy tailored to your needs.' },
              { step: '03', title: 'Design', description: 'We create stunning designs that engage and convert.' },
              { step: '04', title: 'Launch', description: 'We deploy and support your solution for maximum impact.' }
            ].map((item, index) => (
              <div key={index} className="relative group">
                <div className="absolute -inset-0.5 bg-gradient-to-r from-blue-600 to-cyan-600 rounded-2xl opacity-0 group-hover:opacity-100 transition-opacity duration-500 blur"></div>
                <div className="relative bg-white p-8 rounded-2xl border border-gray-100 h-full group-hover:shadow-2xl transition-shadow duration-500">
                  <p className="text-6xl font-bold bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent mb-4">
                    {item.step}
                  </p>
                  <h3 className="text-2xl font-bold text-gray-900 mb-3">{item.title}</h3>
                  <p className="text-gray-600">{item.description}</p>
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
              The People Behind the <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Magic</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Meet our talented team of digital experts dedicated to your success
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
            {teamMembers.map((member, index) => (
              <div
                key={index}
                className="group relative bg-gradient-to-br from-gray-50 to-white p-8 rounded-3xl shadow-sm hover:shadow-2xl transition-all duration-500 border border-gray-100 hover:-translate-y-2"
              >
                <div className="text-6xl mb-4">{member.image}</div>
                <h3 className="text-2xl font-bold text-gray-900 mb-2">{member.name}</h3>
                <p className="text-blue-600 font-semibold text-lg mb-4">{member.role}</p>
                <p className="text-gray-600">
                  Bringing expertise and passion to every project we undertake.
                </p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-b from-slate-50 to-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              Our <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Core Values</span>
            </h2>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
            {values.map((value, index) => (
              <div
                key={index}
                className="group relative bg-white p-8 rounded-2xl shadow-sm hover:shadow-xl transition-all duration-500 border border-gray-100 text-center"
              >
                <div className="w-16 h-16 rounded-2xl bg-gradient-to-br from-blue-500 to-cyan-500 p-3 mb-6 mx-auto group-hover:scale-110 group-hover:rotate-6 transition-all duration-500 shadow-lg">
                  <value.icon className="w-full h-full text-white" />
                </div>
                <h3 className="text-2xl font-bold text-gray-900 mb-3">{value.title}</h3>
                <p className="text-gray-600 leading-relaxed">{value.description}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="py-32 bg-white relative">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
            <div>
              <h2 className="text-5xl font-bold text-gray-900 mb-8 leading-tight">
                Vision & <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Mission</span>
              </h2>

              <div className="mb-10">
                <h3 className="text-2xl font-bold text-gray-900 mb-4 flex items-center gap-3">
                  <Target className="w-8 h-8 text-blue-600" />
                  Our Vision
                </h3>
                <p className="text-lg text-gray-700 leading-relaxed">
                  To be the leading creative agency known for transforming businesses through exceptional design and strategic marketing.
                </p>
              </div>

              <div>
                <h3 className="text-2xl font-bold text-gray-900 mb-4 flex items-center gap-3">
                  <Lightbulb className="w-8 h-8 text-blue-600" />
                  Our Mission
                </h3>
                <p className="text-lg text-gray-700 leading-relaxed">
                  To empower businesses with innovative digital solutions that drive growth and success.
                </p>
              </div>
            </div>

            <div className="space-y-6">
              {stats.map((stat, index) => (
                <div
                  key={index}
                  className="group relative bg-gradient-to-br from-gray-50 to-white p-8 rounded-2xl shadow-sm hover:shadow-xl transition-all duration-500 border border-gray-100 hover:scale-105"
                >
                  <div className="flex items-center gap-6">
                    <div className="w-16 h-16 rounded-xl bg-gradient-to-br from-blue-500 to-cyan-500 p-3 flex-shrink-0 group-hover:scale-110 transition-transform duration-300 shadow-lg">
                      <stat.icon className="w-full h-full text-white" />
                    </div>
                    <div>
                      <p className="text-4xl font-bold bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">
                        {stat.number}
                      </p>
                      <p className="text-gray-600 font-semibold">{stat.label}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </section>

      <section className="py-32 bg-gradient-to-b from-slate-50 to-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-20">
            <h2 className="text-5xl font-bold text-gray-900 mb-6">
              What They Say About <span className="bg-gradient-to-r from-blue-600 to-cyan-600 bg-clip-text text-transparent">Us</span>
            </h2>
            <p className="text-xl text-gray-600 max-w-2xl mx-auto">
              Don't just take our word for it — hear from our satisfied clients
            </p>
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
                    <p className="text-gray-500 text-sm">{testimonial.location}</p>
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
            Discover how Creative Design Solution can transform your online experience and help your business thrive in the digital world.
          </p>
          <button className="group inline-flex items-center gap-3 bg-gradient-to-r from-blue-600 to-cyan-600 text-white px-10 py-5 rounded-xl text-lg font-semibold hover:from-blue-700 hover:to-cyan-700 transition-all duration-300 shadow-2xl hover:shadow-blue-500/50 hover:scale-105">
            Start Your Journey
            <ArrowRight className="w-5 h-5 group-hover:translate-x-1 transition-transform duration-300" />
          </button>
        </div>
      </section>
    </main>
  );
}

export default About;
