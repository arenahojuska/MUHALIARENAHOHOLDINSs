import { Briefcase, Scale, FileText, Users, Car, DollarSign, Heart, MessageSquare, Gavel } from 'lucide-react';

const Services = () => {
  const services = [
    { icon: <Scale className="w-10 h-10" />, title: 'Civil Litigation', description: 'Professional representation in civil court matters and dispute resolution.' },
    { icon: <Gavel className="w-10 h-10" />, title: 'Criminal Law Matters', description: 'Expert legal assistance for criminal cases and defense strategies.' },
    { icon: <Briefcase className="w-10 h-10" />, title: 'Military Law Matters', description: 'Legal guidance and representation for military personnel.' },
    { icon: <FileText className="w-10 h-10" />, title: 'Bail Application', description: 'Efficient processing and representation for bail applications.' },
    { icon: <Briefcase className="w-10 h-10" />, title: 'Commercial Litigation', description: 'Resolving business disputes and corporate legal conflicts.' },
    { icon: <FileText className="w-10 h-10" />, title: 'Contract Drafting', description: 'Drafting and reviewing contracts to protect your legal interests.' },
    { icon: <Users className="w-10 h-10" />, title: 'Labour Law Matters', description: 'Guidance on employment disputes, compliance, and worker rights.' },
    { icon: <Car className="w-10 h-10" />, title: 'RAF Claim', description: 'Assistance with Road Accident Fund claims and compensation.' },
    { icon: <Heart className="w-10 h-10" />, title: 'Medical Negligence', description: 'Representation in cases of medical malpractice and negligence.' },
    { icon: <FileText className="w-10 h-10" />, title: 'Deceased Estate', description: 'Comprehensive estate planning and administration services.' },
    { icon: <DollarSign className="w-10 h-10" />, title: 'Debt Collection', description: 'Legal support for managing and recovering debts.' },
    { icon: <Heart className="w-10 h-10" />, title: 'Divorce', description: 'Compassionate legal guidance through divorce proceedings.' },
    { icon: <Gavel className="w-10 h-10" />, title: 'Unlawful Arrests', description: 'Protection and representation for unlawful arrest cases.' },
    { icon: <FileText className="w-10 h-10" />, title: 'Trusts', description: 'Assistance with creating and managing legal trusts.' },
    { icon: <Briefcase className="w-10 h-10" />, title: 'Immigration Matters', description: 'Guidance and representation in immigration cases.' },
  ];

  return (
    <section id="services" className="py-20 px-4 bg-gray-50">
      <div className="max-w-7xl mx-auto">
        {/* Heading */}
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-slate-900 mb-4">
            Our Legal Services
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
          <p className="text-xl text-gray-700 max-w-3xl mx-auto">
            Comprehensive legal solutions tailored to meet your individual and business needs
          </p>
        </div>

        {/* Services Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {services.map((service, index) => (
            <div
              key={index}
              className="bg-white p-8 rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 border-t-4 border-amber-500"
            >
              <div className="text-amber-500 mb-4">{service.icon}</div>
              <h3 className="text-xl font-bold text-slate-900 mb-3">{service.title}</h3>
              <p className="text-gray-700 leading-relaxed">{service.description}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Services;