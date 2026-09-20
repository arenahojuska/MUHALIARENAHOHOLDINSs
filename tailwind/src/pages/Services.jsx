import React, { useState } from 'react';
import { Briefcase, Scale, FileText, Users, Car, DollarSign, Heart, MessageSquare, Gavel, ShieldCheck, PenTool, ClipboardList, Key, Home, FileSignature } from 'lucide-react';

const Services = () => {
  const services = [
    {
      icon: <Scale className="w-10 h-10" />,
      title: 'Civil and Criminal Litigation',
      description: 'Experienced representation in both civil and criminal matters, ensuring justice and protection of your rights in court.',
    },
    {
      icon: <Car className="w-10 h-10" />,
      title: 'RAF Claims',
      description: 'Comprehensive legal support for Road Accident Fund claims, helping you secure rightful compensation for injuries and damages.',
    },
    {
      icon: <Heart className="w-10 h-10" />,
      title: 'Divorce',
      description: 'Professional and compassionate assistance in divorce proceedings, maintenance, and child custody matters.',
    },
    {
      icon: <DollarSign className="w-10 h-10" />,
      title: 'Debt Collection',
      description: 'Efficient recovery of outstanding debts through legal processes that prioritize fairness and compliance.',
    },
    {
      icon: <Briefcase className="w-10 h-10" />,
      title: 'Intellectual Property Registration',
      description: 'Safeguard your creative and business assets through trademark, copyright, and patent registrations.',
    },
    {
      icon: <FileText className="w-10 h-10" />,
      title: 'Letters of Authority',
      description: 'Assisting families in obtaining letters of authority for estate administration and legal recognition of executors.',
    },
    {
      icon: <ClipboardList className="w-10 h-10" />,
      title: 'Liquor License',
      description: 'Guidance and representation in obtaining and renewing liquor licenses in compliance with statutory requirements.',
    },
    {
      icon: <ShieldCheck className="w-10 h-10" />,
      title: 'Wrongful Arrests',
      description: 'Protecting your rights and seeking compensation for unlawful arrests or detentions.',
    },
    {
      icon: <Home className="w-10 h-10" />,
      title: 'Evictions',
      description: 'Legal expertise in handling evictions while ensuring compliance with housing and tenancy laws.',
    },
    {
      icon: <FileSignature className="w-10 h-10" />,
      title: 'Drafting of Contracts',
      description: 'Preparation of clear, enforceable contracts that protect your business and personal interests.',
    },
    {
      icon: <Gavel className="w-10 h-10" />,
      title: 'Appeals and Review of Judgements',
      description: 'Professional assistance in filing appeals or seeking review of unfair or erroneous court judgments.',
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
