import { Star, Users, Wifi, Wind, Tv, Refrigerator, Check } from 'lucide-react';

const rooms = [
  {
    id: 1,
    name: 'Standard Room',
    price: 'From R100/night',
    image: 'https://images.pexels.com/photos/271643/pexels-photo-271643.jpeg?auto=compress&cs=tinysrgb&w=600',
    guests: 2,
    rating: 4.5,
    features: ['Free WiFi', 'Air Conditioning', 'Private Bathroom', 'Comfortable Bed'],
    description: 'Cozy and comfortable room perfect for budget-conscious travelers. Ideal for single occupants or couples.'
  },
  {
    id: 2,
    name: 'Deluxe Room',
    price: 'From R150/night',
    image: 'https://images.pexels.com/photos/3050505/pexels-photo-3050505.jpeg?auto=compress&cs=tinysrgb&w=600',
    guests: 2,
    rating: 4.8,
    features: ['Free WiFi', 'Air Conditioning', 'DSTV', 'In-Room Fridge', 'Work Desk'],
    description: 'Enhanced comfort with premium amenities. Perfect for business travelers and those seeking extra space.'
  },
  {
    id: 3,
    name: 'Family Room',
    price: 'From R250/night',
    image: 'https://images.pexels.com/photos/1350789/pexels-photo-1350789.jpeg?auto=compress&cs=tinysrgb&w=600',
    guests: 4,
    rating: 5.0,
    features: ['Free WiFi', 'Air Conditioning', 'DSTV', 'Mini Kitchen', 'Fridge', 'Multiple Beds'],
    description: 'Spacious room designed for families and groups. Includes kitchenette for added convenience.'
  }
];

export default function Rooms() {
  return (
    <section id="rooms" className="py-24 bg-gradient-to-b from-white to-[#F5E6D3]">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Our Rooms</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full mb-6"></div>
          <p className="text-lg text-gray-700 max-w-2xl mx-auto">
            Discover comfortable, well-appointed rooms tailored to meet your needs and budget
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 max-w-6xl mx-auto">
          {rooms.map((room) => (
            <div key={room.id} className="bg-white rounded-3xl overflow-hidden shadow-lg hover:shadow-2xl transition-all duration-300 transform hover:-translate-y-4 group">
              <div className="relative overflow-hidden h-64">
                <img
                  src={room.image}
                  alt={room.name}
                  className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
                />
                <div className="absolute top-4 right-4 bg-[#8B6F47] text-white px-4 py-2 rounded-full flex items-center space-x-1">
                  <Star className="w-4 h-4 fill-white" />
                  <span className="font-semibold">{room.rating}</span>
                </div>
              </div>

              <div className="p-8">
                <div className="mb-4">
                  <h3 className="text-3xl font-semibold text-[#6B5744] mb-2">{room.name}</h3>
                  <p className="text-xl font-bold text-[#C4A574] mb-3">{room.price}</p>
                  <p className="text-gray-700 leading-relaxed text-sm mb-4">{room.description}</p>
                </div>

                <div className="flex items-center space-x-2 mb-6 pb-6 border-b-2 border-[#E8D4BA]">
                  <Users className="w-5 h-5 text-[#8B6F47]" />
                  <span className="text-[#6B5744] font-medium">Up to {room.guests} {room.guests === 1 ? 'guest' : 'guests'}</span>
                </div>

                <div className="space-y-3 mb-6">
                  {room.features.map((feature, index) => (
                    <div key={index} className="flex items-center space-x-2">
                      <div className="w-5 h-5 bg-[#C4A574] rounded-full flex items-center justify-center flex-shrink-0">
                        <Check className="w-3 h-3 text-white" />
                      </div>
                      <span className="text-gray-700 text-sm">{feature}</span>
                    </div>
                  ))}
                </div>

                <button className="w-full bg-gradient-to-r from-[#8B6F47] to-[#C4A574] hover:from-[#6B5744] hover:to-[#A68556] text-white py-3 rounded-full font-semibold transition-all duration-300 transform hover:scale-105">
                  Book This Room
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
