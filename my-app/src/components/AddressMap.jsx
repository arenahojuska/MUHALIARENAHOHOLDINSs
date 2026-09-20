import React, { useState, useEffect } from 'react';
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import axios from 'axios';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

const midrandCoords = [-25.9992, 28.1266];

const RecenterMap = ({ coords }) => {
  const map = useMap();
  useEffect(() => {
    if (coords) map.setView(coords, 15);
  }, [coords, map]);
  return null;
};

const AddressMap = ({ onClose, onSave }) => {
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [selectedCoords, setSelectedCoords] = useState(midrandCoords);

  const handleSearch = async (text) => {
    setQuery(text);
    if (text.length < 3) return setSuggestions([]);

    const res = await axios.get('https://nominatim.openstreetmap.org/search', {
      params: {
        q: text,
        format: 'json',
        addressdetails: 1,
        limit: 5,
        countrycodes: 'za',
      },
    });

    setSuggestions(res.data);
  };

  const handleSelect = (place) => {
    const coords = [parseFloat(place.lat), parseFloat(place.lon)];
    setSelectedCoords(coords);
    setQuery(place.display_name);
    setSuggestions([]);
  };

  return (
    <div className="space-y-4">
      <input
        type="text"
        value={query}
        onChange={(e) => handleSearch(e.target.value)}
        placeholder="Enter delivery address"
        className="border p-2 w-full"
      />

      {suggestions.length > 0 && (
        <ul className="border rounded bg-white shadow max-h-48 overflow-y-auto">
          {suggestions.map((place, idx) => (
            <li
              key={idx}
              onClick={() => handleSelect(place)}
              className="p-2 hover:bg-gray-100 cursor-pointer"
            >
              {place.display_name}
            </li>
          ))}
        </ul>
      )}

      <MapContainer center={midrandCoords} zoom={13} scrollWheelZoom={false} className="h-96 w-full rounded shadow">
        <TileLayer
          attribution='&copy; OpenStreetMap contributors'
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <RecenterMap coords={selectedCoords} />
        <Marker
          position={selectedCoords}
          icon={L.icon({
            iconUrl: 'https://unpkg.com/leaflet@1.9.4/dist/images/marker-icon.png',
            iconSize: [25, 41],
            iconAnchor: [12, 41],
          })}
        >
          <Popup>{query || 'Midrand'}</Popup>
        </Marker>
      </MapContainer>

      <div className="flex justify-end space-x-4 pt-2">
        <button onClick={onClose} className="px-4 py-2 border rounded">Cancel</button>
        <button onClick={() => onSave(query)} className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition">
          Save Address
        </button>
      </div>
    </div>
  );
};

export default AddressMap;
