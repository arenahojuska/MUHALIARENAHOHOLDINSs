import { Briefcase, Scale, FileText, Users, Car, DollarSign, Heart, MessageSquare, Gavel } from 'lucide-react';

const Services = () => {
  const services = [
    {
      icon: <Briefcase className="w-10 h-10" />,
      title: 'Commercial Law',
      description: 'Expert guidance on business contracts, corporate legal advice, and regulatory compliance to protect your business interests.',
    },
    {
      icon: <Scale className="w-10 h-10" />,
      title: 'Civil & Criminal Litigation',
      description: 'Professional representation in court proceedings and effective dispute resolution for both civil and criminal matters.',
    },
    {
      icon: <FileText className="w-10 h-10" />,
      title: 'Administration of Deceased Estate',
      description: 'Comprehensive estate planning, wills drafting, and executorship services to ensure your legacy is protected.',
    },
    {
      icon: <Users className="w-10 h-10" />,
      title: 'Labour Law',
      description: 'Protecting employee rights and ensuring employer compliance in workplace matters and dispute resolution.',
    },
    {
      icon: <Car className="w-10 h-10" />,
      title: 'Road Accident Fund (RAF)',
      description: 'Expert assistance with RAF claims, injury compensation, and legal support for accident victims.',
    },
    {
      icon: <DollarSign className="w-10 h-10" />,
      title: 'Debt Review & Debt Collection',
      description: 'Professional financial legal services for individuals and businesses managing debt matters.',
    },
    {
      icon: <Heart className="w-10 h-10" />,
      title: 'Family Law',
      description: 'Compassionate legal support for divorce, child custody, maintenance, adoption, and other family matters.',
    },
    {
      icon: <MessageSquare className="w-10 h-10" />,
      title: 'Legal Consultations & Advisory',
      description: 'Professional legal advice and strategic guidance tailored to your specific needs and circumstances.',
    },
    {
      icon: <Gavel className="w-10 h-10" />,
      title: 'Mediation & Arbitration',
      description: 'Alternative dispute resolution services to help parties reach fair agreements outside of court.',
    },
  ];

  return (
    <section id="services" className="py-20 px-4 bg-gray-50">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-slate-900 mb-4">
            Our Legal Services
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
          <p className="text-xl text-gray-700 max-w-3xl mx-auto">
            Comprehensive legal solutions tailored to meet your individual and business needs
          </p>
        </div>

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
