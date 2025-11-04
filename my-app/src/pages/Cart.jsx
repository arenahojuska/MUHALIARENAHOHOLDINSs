import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import EmptyCart from '../assets/images/cart.png';
import { FaTrashAlt } from 'react-icons/fa';
import Modal from '../components/modal';

import {
  increaseQuantity,
  decreaseQuantity,
  removeFromCart,
  clearCart, // <-- added here
} from '../redux/cartSlice';

const saProvinces = [
  "Eastern Cape",
  "Free State",
  "Gauteng",
  "KwaZulu-Natal",
  "Limpopo",
  "Mpumalanga",
  "Northern Cape",
  "North West",
  "Western Cape",
];

const Cart = () => {
  const cart = useSelector((state) => state.cart);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [isModelOpen, setIsModelOpen] = useState(false);
  const [addressInfo, setAddressInfo] = useState({
    address: '',
    city: '',
    zip: '',
    province: ''
  });

  const fullAddress =
    addressInfo.address &&
    addressInfo.city &&
    addressInfo.zip &&
    addressInfo.province
      ? `${addressInfo.address}, ${addressInfo.city}, ${addressInfo.zip}, ${addressInfo.province}`
      : '';

  return (
    <div className="min-h-screen px-4 py-8 md:px-16 lg:px-24">
      {cart.products.length > 0 ? (
        <div>
          <h2 className="text-2xl font-bold mb-6">Your Shopping Cart</h2>

          {/* Header Row */}
          <div className="grid grid-cols-5 gap-4 font-semibold border-b pb-2 mb-4 text-sm md:text-base">
            <p className="col-span-2">PRODUCT</p>
            <p>PRICE</p>
            <p>QUANTITY</p>
            <p>REMOVE</p>
          </div>

          {/* Cart Items */}
          {cart.products.map((product) => (
            <div
              key={product.id}
              className="grid grid-cols-5 gap-4 items-center border-b py-4 text-sm md:text-base"
            >
              <div className="col-span-2 flex items-center space-x-4">
                <img
                  src={product.image}
                  alt=""
                  className="w-16 h-16 object-contain"
                />
                <div>
                  <h3 className="font-medium">{product.name}</h3>
                  <p className="text-gray-500">
                    Subtotal: R{product.totalPrice.toFixed(2)}
                  </p>
                </div>
              </div>
              <p>R{product.price}</p>
              <div className="flex items-center space-x-2">
                <button
                  className="px-2 border"
                  onClick={() =>
                    dispatch(decreaseQuantity({ id: product.id }))
                  }
                >
                  -
                </button>
                <p>{product.quantity}</p>
                <button
                  className="px-2 border"
                  onClick={() =>
                    dispatch(increaseQuantity({ id: product.id }))
                  }
                >
                  +
                </button>
              </div>
              <button
                className="text-red-600 hover:text-red-800"
                onClick={() => dispatch(removeFromCart({ id: product.id }))}
              >
                <FaTrashAlt />
              </button>
            </div>
          ))}

          {/* Cart Summary */}
          <div className="flex justify-end mt-8">
            <div className="w-full max-w-sm border p-4 rounded">
              <h3 className="text-xl font-bold mb-4">Cart Summary</h3>
              <div className="space-y-2">
                <button
                  onClick={() => setIsModelOpen(true)}
                  className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition w-full"
                >
                  Enter Address
                </button>

                {fullAddress && (
                  <div className="text-sm text-gray-700">
                    <p>
                      <strong>Shipping to:</strong>
                    </p>
                    <p>{fullAddress}</p>
                  </div>
                )}

                <div className="flex justify-between pt-2">
                  <span>Total Items:</span>
                  <span>{cart.totalQuantity}</span>
                </div>
                <div className="flex justify-between">
                  <span>Total Price:</span>
                  <span>R{cart.totalPrice.toFixed(2)}</span>
                </div>

                <div className="mt-4">
                  <button
                    disabled={!fullAddress}
                    onClick={() => navigate('/checkout', { state: { addressInfo } })}
                    className={`px-4 py-2 w-full rounded transition ${
                      fullAddress
                        ? 'bg-green-600 text-white hover:bg-green-700'
                        : 'bg-gray-400 text-white cursor-not-allowed'
                    }`}
                  >
                    Proceed to Checkout
                  </button>

                  {/* Clear Cart Button */}
                  <div className="mt-2">
                    <button
                      onClick={() => dispatch(clearCart())}
                      className="px-4 py-2 w-full rounded bg-red-600 text-white hover:bg-red-700 transition"
                    >
                      Clear Cart
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Modal */}
          <Modal isModelOpen={isModelOpen} setIsModelOpen={setIsModelOpen}>
            <h3 className="text-lg font-semibold mb-4">Enter Delivery Address</h3>

            <div className="space-y-3">
              <input
                type="text"
                placeholder="Address"
                value={addressInfo.address}
                onChange={(e) =>
                  setAddressInfo((prev) => ({ ...prev, address: e.target.value }))
                }
                className={`border p-2 w-full rounded ${
                  addressInfo.address && addressInfo.address.length < 3
                    ? "border-red-500"
                    : ""
                }`}
              />
              {addressInfo.address && addressInfo.address.length < 3 && (
                <p className="text-red-500 text-sm">Address must be at least 3 characters.</p>
              )}

              <input
                type="text"
                placeholder="City"
                value={addressInfo.city}
                onChange={(e) =>
                  setAddressInfo((prev) => ({ ...prev, city: e.target.value }))
                }
                className={`border p-2 w-full rounded ${
                  addressInfo.city && !/^[A-Za-z\s]{2,}$/.test(addressInfo.city)
                    ? "border-red-500"
                    : ""
                }`}
              />
              {addressInfo.city && !/^[A-Za-z\s]{2,}$/.test(addressInfo.city) && (
                <p className="text-red-500 text-sm">City must be at least 2 letters.</p>
              )}

              <input
                type="text"
                placeholder="Zip Code"
                value={addressInfo.zip}
                onChange={(e) =>
                  setAddressInfo((prev) => ({ ...prev, zip: e.target.value }))
                }
                className={`border p-2 w-full rounded ${
                  addressInfo.zip && !/^\d{4,6}$/.test(addressInfo.zip)
                    ? "border-red-500"
                    : ""
                }`}
              />
              {addressInfo.zip && !/^\d{4,6}$/.test(addressInfo.zip) && (
                <p className="text-red-500 text-sm">Zip code must be 4-6 digits.</p>
              )}

              <select
                value={addressInfo.province}
                onChange={(e) =>
                  setAddressInfo((prev) => ({ ...prev, province: e.target.value }))
                }
                className={`border p-2 w-full rounded ${
                  addressInfo.province === "" ? "border-red-500" : ""
                }`}
              >
                <option value="">Select Province</option>
                {saProvinces.map((prov) => (
                  <option key={prov} value={prov}>
                    {prov}
                  </option>
                ))}
              </select>
              {addressInfo.province === "" && (
                <p className="text-red-500 text-sm">Please select a province.</p>
              )}
            </div>

            <div className="flex justify-end space-x-2 mt-4">
              <button
                onClick={() => setIsModelOpen(false)}
                className="px-4 py-2 border"
              >
                Cancel
              </button>
              <button
                onClick={() => setIsModelOpen(false)}
                disabled={
                  !addressInfo.address ||
                  addressInfo.address.length < 3 ||
                  !addressInfo.city ||
                  !/^[A-Za-z\s]{2,}$/.test(addressInfo.city) ||
                  !addressInfo.zip ||
                  !/^\d{4,6}$/.test(addressInfo.zip) ||
                  !addressInfo.province
                }
                className={`px-4 py-2 rounded text-white transition ${
                  !addressInfo.address ||
                  addressInfo.address.length < 3 ||
                  !addressInfo.city ||
                  !/^[A-Za-z\s]{2,}$/.test(addressInfo.city) ||
                  !addressInfo.zip ||
                  !/^\d{4,6}$/.test(addressInfo.zip) ||
                  !addressInfo.province
                    ? "bg-gray-400 cursor-not-allowed"
                    : "bg-green-600 hover:bg-green-700"
                }`}
              >
                Save Address
              </button>
            </div>
          </Modal>
        </div>
      ) : (
        <div className="flex flex-col items-center justify-center text-center">
          <img
            src={EmptyCart}
            alt="Empty Cart"
            className="h-96 object-contain"
          />
          <p className="mt-4 text-gray-500 text-lg">
            Your cart is currently empty.
          </p>
          <Link to="/shop">
            <button className="mt-4 bg-red-600 text-white px-6 py-2 rounded hover:bg-red-700 transition">
              Continue Shopping
            </button>
          </Link>
        </div>
      )}
    </div>
  );
};

export default Cart;
