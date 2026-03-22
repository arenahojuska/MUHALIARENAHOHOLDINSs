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
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-slate-900 mb-4">
            About Our Firm
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto"></div>
        </div>

        <div className="grid md:grid-cols-2 gap-12 items-center mb-16">
          <div>
            <h3 className="text-3xl font-bold text-slate-900 mb-6">
              Trusted Legal Partners in Polokwane
            </h3>
            <p className="text-gray-700 text-lg mb-4 leading-relaxed">
              Matshaya M. T Attorneys is a reputable law firm dedicated to providing expert legal services to individuals and businesses across Polokwane and beyond. With years of experience and a commitment to excellence, we have built a strong reputation for delivering results.
            </p>
            <p className="text-gray-700 text-lg mb-4 leading-relaxed">
              Our mission is to ensure justice, fairness, and professional guidance in all legal matters. We understand that legal issues can be complex and stressful, which is why we approach every case with dedication, expertise, and personalized attention.
            </p>
            <p className="text-gray-700 text-lg leading-relaxed">
              Whether you need assistance with commercial law, litigation, estate administration, or family matters, our team is here to protect your rights and interests with unwavering commitment.
            </p>
          </div>

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
      </div>
    </section>
  );
};

export default About;
