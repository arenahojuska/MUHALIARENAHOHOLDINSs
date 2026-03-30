import { Award, Target, Users } from 'lucide-react';

const About = () => {
  const values = [
    {
      icon: <Award className="w-8 h-8 text-amber-500" />,
      title: 'Excellence',
      description: 'Committed to delivering the highest quality legal services',
    },
    {
      icon: <Target className="w-8 h-8 text-amber-500" />,
      title: 'Integrity',
      description: 'Upholding ethical standards and professional conduct',
    },
    {
      icon: <Users className="w-8 h-8 text-amber-500" />,
      title: 'Client-Focused',
      description: 'Your legal needs and success are our top priority',
    },
  ];

  return (
    <section id="about" className="py-20 px-4 bg-white">
      <div className="max-w-7xl mx-auto">
        {/* Heading */}
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-slate-900 mb-4">
            About Our Firm
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto"></div>
        </div>

        {/* Two-column layout */}
        <div className="grid md:grid-cols-2 gap-12 items-center mb-16">
          {/* Left column */}
          <div>
            <h3 className="text-3xl font-bold text-slate-900 mb-6">
              Trusted Legal Partners 
            </h3>
            <p className="text-gray-700 text-lg mb-4 leading-relaxed text-justify">
              PM Mahlathi Attorneys Inc is a premier law firm dedicated to delivering exceptional legal services with professionalism and integrity. Our experienced team specializes in a comprehensive range of legal matters, from civil and criminal litigation to corporate and family law.
            </p>
            <p className="text-gray-700 text-lg mb-4 leading-relaxed text-justify">
              We prioritize client satisfaction, offering tailored solutions and expert guidance in every legal matter. Our commitment to excellence ensures that your legal needs are met efficiently and ethically.
            </p>
          </div>

          {/* Right column - Core Values */}
          <div className="bg-slate-900 p-8 rounded-lg shadow-xl">
            <h4 className="text-2xl font-bold text-amber-500 mb-6">Our Core Values</h4>
            <div className="space-y-6">
              {values.map((value, index) => (
                <div key={index} className="flex items-start space-x-4">
                  <div className="flex-shrink-0 mt-1">{value.icon}</div>
                  <div>
                    <h5 className="text-xl font-semibold text-white mb-2">{value.title}</h5>
                    <p className="text-gray-300">{value.description}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Stats section */}
        <div className="grid md:grid-cols-3 gap-6 text-center">
          <div className="bg-white rounded-lg p-6 shadow-sm hover:shadow-md transition">
            <div className="text-3xl font-bold text-amber-500 mb-2">15+</div>
            <p className="text-gray-600">Areas of Practice</p>
          </div>
          <div className="bg-white rounded-lg p-6 shadow-sm hover:shadow-md transition">
            <div className="text-3xl font-bold text-amber-500 mb-2">Expert</div>
            <p className="text-gray-600">Legal Representation</p>
          </div>
          <div className="bg-white rounded-lg p-6 shadow-sm hover:shadow-md transition">
            <div className="text-3xl font-bold text-amber-500 mb-2">24/7</div>
            <p className="text-gray-600">Available Support</p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default About;