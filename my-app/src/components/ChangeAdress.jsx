import React from 'react';

const ChangeAddress = () => {
  return (
    <div className="p-4">
      <input type="text" placeholder="Enter new address" className="border p-2 w-full mb-4" />
      <div className="flex space-x-4">
        <button className="px-4 py-2 border">Cancel</button>
        <button className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition">
          Save Address
        </button>
      </div>
    </div>
  );
};

export default ChangeAddress;
