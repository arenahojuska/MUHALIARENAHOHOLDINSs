import { Wifi, Wind, Tv, Refrigerator, Shield, Home } from 'lucide-react';

const features = [
  {
    icon: Wifi,
    title: 'Free WiFi',
    description: 'Stay connected with complimentary high-speed internet access throughout your stay.'
  },
  {
    icon: Wind,
    title: 'Air-Conditioned Rooms',
    description: 'Enjoy optimal comfort with individually controlled air conditioning in every room.'
  },
  {
    icon: Tv,
    title: 'DSTV',
    description: 'Entertainment at your fingertips with full DSTV access in all rooms.'
  },
  {
    icon: Refrigerator,
    title: 'In-Room Fridge',
    description: 'Keep your refreshments cool with a personal fridge in every room.'
  },
  {
    icon: Shield,
    title: 'Secure Environment',
    description: 'Rest easy with 24/7 security ensuring your safety and peace of mind.'
  },
  {
    icon: Home,
    title: 'Comfortable Stay',
    description: 'Experience the perfect blend of comfort and affordability in our well-maintained rooms.'
  }
];

export default function Features() {
  return (
    <section className="py-24 bg-gradient-to-b from-[#F5E6D3] to-[#E8D4BA]">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Our Features</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full"></div>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8 max-w-6xl mx-auto">
          {features.map((feature, index) => {
            const Icon = feature.icon;
            return (
              <div
                key={index}
                className="bg-white rounded-3xl p-8 shadow-lg hover:shadow-2xl transition-all duration-300 transform hover:-translate-y-2"
              >
                <div className="w-20 h-20 bg-gradient-to-br from-[#C4A574] to-[#8B6F47] rounded-full flex items-center justify-center mb-6">
                  <Icon className="w-10 h-10 text-white" />
                </div>
                <h3 className="text-2xl font-semibold text-[#6B5744] mb-3">{feature.title}</h3>
                <p className="text-gray-700 leading-relaxed">{feature.description}</p>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
}
