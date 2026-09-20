import React, { useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate, useLocation } from "react-router-dom";
import { motion, AnimatePresence } from "framer-motion";

const Checkout = ({ setOrder }) => {
  const cart = useSelector((state) => state.cart);
  const navigate = useNavigate();
  const location = useLocation();
  const { addressInfo } = location.state || {};

  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState({
    fullName: "",
    email: "",
    contact: "+27",
    address: addressInfo?.address || "",
    city: addressInfo?.city || "",
    zip: addressInfo?.zip || "",
    province: addressInfo?.province || "",
    notes: "",
    paymentMethod: "",
    bankName: "",
    accountNumber: "",
    cardNumber: "",
    expiryDate: "",
    cvv: "",
    mobilePayment: "",
  });

  // Validation
  const errors = {
    fullName:
      form.fullName && !/^[A-Za-z\s]{1,50}$/.test(form.fullName)
        ? "Name must be alphabetic and under 50 characters."
        : "",
    email:
      form.email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)
        ? "Enter a valid email address."
        : "",
    contact:
      form.contact && !/^\+27\d{9}$/.test(form.contact)
        ? "Contact must be +27 followed by 9 digits."
        : "",
    cardNumber:
      form.paymentMethod === "card" &&
      form.cardNumber &&
      !/^\d{4}\s\d{4}\s\d{4}\s\d{4}$/.test(form.cardNumber)
        ? "Enter a valid 16-digit card number."
        : "",
    expiryDate:
      form.paymentMethod === "card" &&
      form.expiryDate &&
      !/^(0[1-9]|1[0-2])\/\d{2}$/.test(form.expiryDate)
        ? "Enter expiry in MM/YY format."
        : "",
    cvv:
      form.paymentMethod === "card" &&
      form.cvv &&
      !/^\d{3,4}$/.test(form.cvv)
        ? "CVV must be 3 or 4 digits."
        : "",
  };

  const isPaymentValid =
    (form.paymentMethod === "eft" && form.bankName && form.accountNumber) ||
    (form.paymentMethod === "card" &&
      !errors.cardNumber &&
      !errors.expiryDate &&
      !errors.cvv &&
      form.cardNumber &&
      form.expiryDate &&
      form.cvv) ||
    ((form.paymentMethod === "snapscan" || form.paymentMethod === "zapper") &&
      form.mobilePayment) ||
    form.paymentMethod === "payfast" ||
    form.paymentMethod === "cod";

  const isFormComplete =
    !errors.fullName &&
    !errors.email &&
    !errors.contact &&
    form.fullName &&
    form.email &&
    form.contact &&
    form.address &&
    form.city &&
    form.zip &&
    form.province &&
    form.paymentMethod &&
    isPaymentValid;

  const handleChange = (field, value) => {
    if (field === "contact") {
      const digits = value.replace(/\D/g, "");
      value = "+27" + digits.slice(2, 11);
    }

    if (field === "cardNumber") {
      const digits = value.replace(/\D/g, "").slice(0, 16);
      value = digits.replace(/(\d{4})(?=\d)/g, "$1 ").trim();
    }

    if (field === "expiryDate") {
      let digits = value.replace(/\D/g, "").slice(0, 4);
      value = digits.length >= 3 ? digits.slice(0, 2) + "/" + digits.slice(2) : digits;
    }

    if (field === "cvv") {
      value = value.replace(/\D/g, "").slice(0, 4);
    }

    setForm((prev) => ({ ...prev, [field]: value }));
  };

  const handleSubmit = () => {
    setLoading(true);
    const newOrder = {
      products: cart.products,
      orderNumber: "4718798",
      shippingInformation: `${form.address}, ${form.city}, ${form.zip}, ${form.province}`,
      totalPrice: cart.totalPrice,
    };
    setOrder(newOrder);

    setTimeout(() => {
      setLoading(false);
      navigate("/order-confrimation");
    }, 1200);
  };

  const grandTotal = cart.products.reduce(
    (acc, p) => acc + p.price * p.quantity,
    0
  );

  return (
    <div className="min-h-screen px-4 py-8 md:px-16 lg:px-24">
      <h2 className="text-3xl font-bold mb-8">Checkout</h2>

      {cart.products.length > 0 ? (
        <div className="w-full max-w-2xl mx-auto bg-white border shadow-lg rounded-lg p-8 space-y-8">
          {/* Order Summary */}
          <div className="border-b pb-6">
            <h3 className="text-xl font-semibold mb-4">Order Summary</h3>
            <div className="space-y-4 text-sm text-gray-700">
              {cart.products.map((product) => (
                <div key={product.id} className="grid grid-cols-5 gap-4 items-center border-b pb-2">
                  <img src={product.image} alt={product.name} className="w-14 h-14 object-contain" />
                  <span>{product.name}</span>
                  <span>R{product.price}</span>
                  <span>{product.quantity}</span>
                  <span className="font-medium">R{(product.price * product.quantity).toFixed(2)}</span>
                </div>
              ))}
            </div>
            <div className="flex justify-between font-bold mt-4 text-gray-800">
              <span>Total Price:</span>
              <span>R{grandTotal.toFixed(2)}</span>
            </div>
          </div>

          {/* Delivery Details */}
          <section>
            <h3 className="text-xl font-semibold mb-4">Delivery Details</h3>

            <input
              placeholder="Full Name"
              value={form.fullName}
              onChange={(e) => handleChange("fullName", e.target.value)}
              className={`border p-2 w-full rounded ${errors.fullName ? "border-red-500" : ""}`}
            />
            {errors.fullName && <p className="text-red-500 text-sm">{errors.fullName}</p>}

            <input
              type="email"
              placeholder="Email Address"
              value={form.email}
              onChange={(e) => handleChange("email", e.target.value)}
              className={`border p-2 w-full mt-2 rounded ${errors.email ? "border-red-500" : ""}`}
            />
            {errors.email && <p className="text-red-500 text-sm">{errors.email}</p>}

            <input
              type="tel"
              placeholder="+27XXXXXXXXX"
              value={form.contact}
              onChange={(e) => handleChange("contact", e.target.value)}
              className={`border p-2 w-full mt-2 rounded ${errors.contact ? "border-red-500" : ""}`}
            />
            {errors.contact && <p className="text-red-500 text-sm">{errors.contact}</p>}

                            <input
                    placeholder="Address"
                    value={form.address}
                    readOnly
                    className="border p-2 w-full mt-2 rounded bg-gray-100 text-gray-700"
                  />

                            <input
                  placeholder="City"
                  value={form.city}
                  readOnly
                  className="border p-2 w-full mt-2 rounded bg-gray-100 text-gray-700"
                />
                <input
                  placeholder="Zip Code"
                  value={form.zip}
                  readOnly
                  className="border p-2 w-full mt-2 rounded bg-gray-100 text-gray-700"
                />
                <input
                  placeholder="Province"
                  value={form.province}
                  readOnly
                  className="border p-2 w-full mt-2 rounded bg-gray-100 text-gray-700"
                />


            <textarea
              placeholder="Additional Notes (optional)"
              value={form.notes}
              onChange={(e) => handleChange("notes", e.target.value)}
              className="border p-2 w-full mt-2 rounded"
              rows={2}
            />
          </section>

          {/* Payment Method */}
          <section>
            <label className="block mb-2 font-medium">Payment Method</label>
            <select
              value={form.paymentMethod}
              onChange={(e) => handleChange("paymentMethod", e.target.value)}
              className="border p-2 w-full rounded"
            >
              <option value="">Select a payment method</option>
              <option value="eft">EFT (Electronic Funds Transfer)</option>
              <option value="card">Credit/Debit Card</option>
              <option value="snapscan">SnapScan</option>
              <option value="zapper">Zapper</option>
              <option value="payfast">PayFast</option>
              <option value="cod">Cash on Delivery</option>
            </select>
          </section>

          {/* Dynamic Payment Fields */}
          <AnimatePresence>
            {form.paymentMethod === "card" && (
              <motion.div initial={{ opacity: 0, y: -10 }} animate={{ opacity: 1, y: 0 }} exit={{ opacity: 0 }} className="space-y-2 mt-4">
                <input
                  placeholder="Card Number"
                  value={form.cardNumber}
                  onChange={(e) => handleChange("cardNumber", e.target.value)}
                  className={`border p-2 w-full rounded ${errors.cardNumber ? "border-red-500" : ""}`}
                />
                {errors.cardNumber && <p className="text-red-500 text-sm">{errors.cardNumber}</p>}

                <input
                  placeholder="Expiry Date (MM/YY)"
                  value={form.expiryDate}
                  onChange={(e) => handleChange("expiryDate", e.target.value)}
                  className={`border p-2 w-full rounded ${errors.expiryDate ? "border-red-500" : ""}`}
                />
                {errors.expiryDate && <p className="text-red-500 text-sm">{errors.expiryDate}</p>}

                <input
                  placeholder="CVV"
                  value={form.cvv}
                  onChange={(e) => handleChange("cvv", e.target.value)}
                  className={`border p-2 w-full rounded ${errors.cvv ? "border-red-500" : ""}`}
                />
                {errors.cvv && <p className="text-red-500 text-sm">{errors.cvv}</p>}
              </motion.div>
            )}
          </AnimatePresence>

          {/* Confirm Button */}
          <button
            disabled={!isFormComplete || loading}
            onClick={handleSubmit}
            className={`w-full mt-6 px-4 py-2 rounded text-white transition ${
              isFormComplete && !loading
                ? "bg-green-600 hover:bg-green-700"
                : "bg-gray-400 cursor-not-allowed"
            }`}
          >
            {loading ? "Processing..." : "Confirm & Pay"}
          </button>
        </div>
      ) : (
        <div className="flex flex-col items-center justify-center text-center">
          <p className="text-gray-500 text-lg">Your cart is currently empty.</p>
        </div>
      )}
    </div>
  );
};

export default Checkout;
