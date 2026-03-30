import { useState } from 'react';
import { X } from 'lucide-react';

const galleryImages = [
  {
    id: 1,
    title: 'Modern Room',
    image: 'https://images.pexels.com/photos/271643/pexels-photo-271643.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Rooms'
  },
  {
    id: 2,
    title: 'Comfortable Bedroom',
    image: 'https://images.pexels.com/photos/3050505/pexels-photo-3050505.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Rooms'
  },
  {
    id: 3,
    title: 'Family Accommodation',
    image: 'https://images.pexels.com/photos/1350789/pexels-photo-1350789.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Rooms'
  },
  {
    id: 4,
    title: 'Reception Area',
    image: 'https://images.pexels.com/photos/1438761/pexels-photo-1438761.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Facilities'
  },
  {
    id: 5,
    title: 'Lobby & Lounge',
    image: 'https://images.pexels.com/photos/1350789/pexels-photo-1350789.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Facilities'
  },
  {
    id: 6,
    title: 'Exterior View',
    image: 'https://images.pexels.com/photos/271643/pexels-photo-271643.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Facilities'
  },
  {
    id: 7,
    title: 'Bathroom',
    image: 'https://images.pexels.com/photos/3050505/pexels-photo-3050505.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Amenities'
  },
  {
    id: 8,
    title: 'Entertainment Area',
    image: 'https://images.pexels.com/photos/1438761/pexels-photo-1438761.jpeg?auto=compress&cs=tinysrgb&w=600',
    category: 'Amenities'
  }
];

export default function Gallery() {
    const [selectedImage, setSelectedImage] = useState(null);
  const [filter, setFilter] = useState('All');

  const categories = ['All', ...new Set(galleryImages.map(img => img.category))];
  const filteredImages = filter === 'All' ? galleryImages : galleryImages.filter(img => img.category === filter);

  return (
    <section id="gallery" className="py-24 bg-white">
      <div className="container mx-auto px-4">
        <div className="text-center mb-16">
          <h2 className="text-5xl font-serif italic text-[#8B6F47] mb-4">Gallery</h2>
          <div className="w-24 h-1 bg-[#C4A574] mx-auto rounded-full mb-8"></div>
          <p className="text-lg text-gray-700 max-w-2xl mx-auto">
            Explore our comfortable rooms and facilities designed for your perfect stay
          </p>
        </div>

        <div className="flex flex-wrap justify-center gap-4 mb-12">
          {categories.map((category) => (
            <button
              key={category}
              onClick={() => setFilter(category)}
              className={`px-8 py-3 rounded-full font-semibold transition-all duration-300 ${
                filter === category
                  ? 'bg-[#8B6F47] text-white shadow-lg transform scale-105'
                  : 'bg-[#F5E6D3] text-[#8B6F47] hover:bg-[#E8D4BA]'
              }`}
            >
              {category}
            </button>
          ))}
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 max-w-6xl mx-auto">
          {filteredImages.map((image) => (
            <div
              key={image.id}
              onClick={() => setSelectedImage(image.id)}
              className="group relative overflow-hidden rounded-2xl shadow-lg cursor-pointer h-64"
            >
              <img
                src={image.image}
                alt={image.title}
                className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-300"
              />
              <div className="absolute inset-0 bg-gradient-to-t from-black/80 via-black/40 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300 flex items-end p-6">
                <div>
                  <p className="text-white text-sm font-medium">{image.category}</p>
                  <h3 className="text-white text-xl font-semibold">{image.title}</h3>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>

      {selectedImage && (
        <div
          className="fixed inset-0 bg-black/90 z-50 flex items-center justify-center p-4 backdrop-blur-sm"
          onClick={() => setSelectedImage(null)}
        >
          <div className="relative max-w-4xl w-full" onClick={(e) => e.stopPropagation()}>
            <button
              onClick={() => setSelectedImage(null)}
              className="absolute -top-12 right-0 text-white hover:text-[#C4A574] transition-colors"
            >
              <X className="w-8 h-8" />
            </button>
            <img
              src={galleryImages.find(img => img.id === selectedImage)?.image}
              alt="Full view"
              className="w-full rounded-2xl"
            />
            <p className="text-center text-white mt-4 text-lg font-semibold">
              {galleryImages.find(img => img.id === selectedImage)?.title}
            </p>
          </div>
        </div>
      )}
    </section>
  );
}
