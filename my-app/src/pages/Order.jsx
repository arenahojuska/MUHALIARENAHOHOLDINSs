import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { clearCart } from '../redux/cartSlice';

const Order = ({ order }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    // Clear cart when the page loads
    dispatch(clearCart());
  }, [dispatch]);

  if (!order) return null;

  return (
    <div className="min-h-screen bg-gradient-to-r from-green-50 to-green-100 flex items-center justify-center p-6">
      <div className="w-full max-w-4xl bg-white shadow-xl rounded-xl overflow-hidden">
        {/* Header */}
        <div className="bg-green-600 text-white text-center py-8">
          <h2 className="text-3xl font-bold mb-2">🎉 Thank You for Your Order!</h2>
          <p className="text-lg">Your order has been placed successfully. A confirmation email is on its way!</p>
        </div>

        <div className="p-6 space-y-6">
          {/* Order Summary */}
          <section>
            <h3 className="text-xl font-semibold text-gray-800 mb-2">🧾 Order Summary</h3>
            <p className="text-gray-700">
              <span className="font-medium">Order Number:</span> {order.orderNumber}
            </p>
          </section>

          {/* Shipping Information */}
          <section>
            <h3 className="text-xl font-semibold text-gray-800 mb-2">🚚 Shipping Information</h3>
            <p className="text-gray-700">{order.shippingInformation}</p>
          </section>

          {/* Products */}
          <section>
            <h3 className="text-xl font-semibold text-gray-800 mb-4">🛍️ Products</h3>
            <div className="space-y-3">
              {order.products.map((product) => (
                <div
                  key={product.id}
                  className="flex justify-between items-center bg-gray-50 p-4 rounded-lg shadow-sm hover:shadow-md transition-shadow"
                >
                  <div>
                    <p className="font-medium text-gray-800">{product.name}</p>
                    <p className="text-gray-500 text-sm">Quantity: {product.quantity}</p>
                  </div>
                  <p className="text-green-600 font-semibold">
                    R{(product.price * product.quantity).toFixed(2)}
                  </p>
                </div>
              ))}
            </div>
          </section>

          {/* Total */}
          <div className="border-t pt-4 flex justify-between items-center text-lg font-bold text-gray-800">
            <span>Total Price:</span>
            <span className="text-green-700 text-xl">R{order.totalPrice.toFixed(2)}</span>
          </div>

          {/* Call to Action */}
          <div className="text-center mt-6">
            <Link
              to="/shop"
              className="inline-block px-6 py-3 bg-green-600 text-white font-semibold rounded-lg shadow hover:bg-green-700 transition"
            >
              Continue Shopping
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Order;
