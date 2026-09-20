import { Quote } from 'lucide-react';
import React, { useState } from 'react';
const Testimonials = () => {
  const testimonials = [
    {
      name: 'Sarah Mohlala',
      role: 'Business Owner',
      text: 'Tshikhakhisa Attorneys provided exceptional legal guidance for my business. Their professionalism and expertise gave me the confidence I needed.',
    },
    {
      name: 'John Malope',
      role: 'Private Client',
      text: 'I was facing a difficult eviction matter, and the team handled my case with compassion and skill. I am grateful for their support.',
    },
    {
      name: 'Grace Nkuna',
      role: 'Estate Administrator',
      text: 'The estate administration service was thorough and efficient. They made a complex process simple and stress-free for our family.',
    },
    {
      name: 'David Mashaba',
      role: 'Corporate Client',
      text: 'Their commercial law expertise has been invaluable to our company. They consistently provide sound legal advice that protects our interests.',
    },
  ];

  return (
    <section className="py-20 px-4 bg-slate-900">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-white mb-4">
            Client Testimonials
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
          <p className="text-xl text-gray-300 max-w-3xl mx-auto">
            Hear what our clients say about our legal services and commitment to excellence
          </p>
        </div>

        <div className="grid md:grid-cols-2 gap-8">
          {testimonials.map((testimonial, index) => (
            <div
              key={index}
              className="bg-slate-800 p-8 rounded-lg shadow-xl border-l-4 border-amber-500"
            >
              <Quote className="w-10 h-10 text-amber-500 mb-4" />
              <p className="text-gray-300 text-lg mb-6 leading-relaxed italic">
                "{testimonial.text}"
              </p>
              <div className="border-t border-gray-700 pt-4">
                <p className="text-white font-semibold text-lg">{testimonial.name}</p>
                <p className="text-amber-500">{testimonial.role}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Testimonials;
