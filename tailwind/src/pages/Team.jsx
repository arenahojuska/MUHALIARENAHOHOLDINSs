import { User } from 'lucide-react';
import React, { useState } from 'react';
const Team = () => {
  const team = [
    {
      name: 'N Tshikhakhisa',
      title: 'Senior Attorney & Founder',
      bio: 'With extensive experience in commercial and civil law, N Tshikhakhisa leads our firm with a commitment to excellence and client satisfaction.',
    },
    {
      name: 'Legal Team Member',
      title: 'Associate Attorney',
      bio: 'Specializing in family law and estate administration, bringing compassionate and effective legal representation to clients.',
    },
    {
      name: 'Legal Team Member',
      title: 'Litigation Specialist',
      bio: 'Expert in civil and criminal litigation with a proven track record of successful court representations and dispute resolutions.',
    },
  ];

  return (
    <section id="team" className="py-20 px-4 bg-white">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-16">
          <h2 className="text-4xl md:text-5xl font-bold text-slate-900 mb-4">
            Meet Our Team
          </h2>
          <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
          <p className="text-xl text-gray-700 max-w-3xl mx-auto">
            Experienced legal professionals dedicated to protecting your rights and interests
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-10">
          {team.map((member, index) => (
            <div
              key={index}
              className="bg-gradient-to-br from-slate-900 to-slate-800 rounded-lg overflow-hidden shadow-xl hover:shadow-2xl transition-shadow duration-300"
            >
              <div className="bg-amber-500 h-48 flex items-center justify-center">
                <User className="w-24 h-24 text-slate-900" />
              </div>
              <div className="p-6">
                <h3 className="text-2xl font-bold text-white mb-2">{member.name}</h3>
                <p className="text-amber-500 font-semibold mb-4">{member.title}</p>
                <p className="text-gray-300 leading-relaxed">{member.bio}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Team;
