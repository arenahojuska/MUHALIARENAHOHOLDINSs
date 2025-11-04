import React, { useState } from "react";
import { useParams } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addToCart } from "../redux/cartSlice";
import { ShoppingCart, Check } from "lucide-react";

/*
  ✅ ProductDetails Page:
  - Fetches product ID from URL params
  - Displays product details (image, name, price, description)
  - Allows user to select color and size
  - Adds product to Redux cart with selected options
*/
const ProductDetails = () => {
  const { id } = useParams(); // Get product ID from URL
  const dispatch = useDispatch();

  // Get the product list from Redux
  const products = useSelector((state) => state.product.products);
  const product = products.find((p) => p.id === parseInt(id));

  // Local state for user selections
  const [selectedColor, setSelectedColor] = useState("Red"); // default color
  const [selectedSize, setSelectedSize] = useState("M"); // default size
  const [added, setAdded] = useState(false); // success notification

  if (!product) {
    return <p className="text-center mt-10 text-gray-500">Product not found.</p>;
  }

  // ✅ Handle Add to Cart click
  const handleAddToCart = () => {
    dispatch(
      addToCart({
        ...product,
        selectedColor,
        selectedSize,
      })
    );
    setAdded(true);
    setTimeout(() => setAdded(false), 2000); // hide notification after 2s
  };

  return (
    <div className="max-w-5xl mx-auto p-6 md:p-12 grid md:grid-cols-2 gap-8">
      {/* 🖼️ Product Image */}
      <div className="flex items-center justify-center bg-gray-50 rounded-2xl p-6">
        <img
          src={product.image}
          alt={product.name}
          className="w-full h-96 object-contain"
        />
      </div>

      {/* 🧾 Product Info */}
      <div className="flex flex-col justify-between">
        <div>
          <h2 className="text-3xl font-bold text-gray-900 mb-4">{product.name}</h2>
          <p className="text-red-600 text-2xl font-black mb-4">R{product.price}</p>
          <p className="text-gray-700 mb-6">
            High-quality {product.name} with premium materials, perfect for everyday use or special occasions.
          </p>

          {/* 🎨 Color Selection */}
          <div className="mb-4">
            <p className="font-semibold mb-2">Select Color:</p>
            <div className="flex gap-3">
              {["Red", "Blue", "Green", "Black"].map((color) => (
                <button
                  key={color}
                  onClick={() => setSelectedColor(color)}
                  className={`w-8 h-8 rounded-full border-2 ${
                    selectedColor === color ? "border-gray-900" : "border-gray-300"
                  }`}
                  style={{ backgroundColor: color.toLowerCase() }}
                ></button>
              ))}
            </div>
          </div>

          {/* 📏 Size Selection */}
          <div className="mb-6">
            <p className="font-semibold mb-2">Select Size:</p>
            <div className="flex gap-3">
              {["S", "M", "L", "XL"].map((size) => (
                <button
                  key={size}
                  onClick={() => setSelectedSize(size)}
                  className={`px-4 py-2 rounded-xl border-2 font-medium ${
                    selectedSize === size
                      ? "border-red-600 text-red-600"
                      : "border-gray-300 text-gray-700"
                  } transition-colors`}
                >
                  {size}
                </button>
              ))}
            </div>
          </div>

          {/* 🛒 Add to Cart Button */}
          <button
            onClick={handleAddToCart}
            className="bg-gradient-to-r from-red-600 to-red-700 text-white font-bold py-3 rounded-xl shadow-md hover:shadow-xl transition-all flex items-center justify-center gap-2 w-full"
          >
            <ShoppingCart className="w-5 h-5" />
            Add to Cart
          </button>
        </div>

        {/* ✅ Success Notification */}
        {added && (
          <div className="fixed top-5 left-1/2 -translate-x-1/2 bg-green-500 text-white px-6 py-3 rounded-full shadow-xl flex items-center gap-2 animate-fade-in z-50">
            <Check className="w-5 h-5" />
            <span>Added to cart!</span>
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductDetails;
