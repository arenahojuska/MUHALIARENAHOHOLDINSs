import React, { useState } from "react";

const Contact = () => {
  const [formData, setFormData] = useState({
    name: "",
    company: "",
    phone: "",
    email: "",
    service: "",
    message: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    alert("Message Sent! Thank you for contacting us. We'll get back to you soon.");
    setFormData({
      name: "",
      company: "",
      phone: "",
      email: "",
      service: "",
      message: "",
    });
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100">
      {/* Hero Section */}
      <section className="container mx-auto px-4 py-16 text-center">
        <h1 className="text-4xl md:text-5xl font-bold mb-6">Contact Us</h1>
        <p className="text-lg text-gray-600 max-w-3xl mx-auto mb-8">
          We're here to help you bring your creative vision to life. Whether you have
          questions, need support, or want to discuss a project, our team is ready to assist.
        </p>
        <h2 className="text-2xl font-semibold text-blue-600 mb-2">Send us a message</h2>
        <p className="text-gray-500">
          Reach out to us via the form below, and we'll get back to you as soon as possible.
        </p>
      </section>

      {/* Contact Form Section */}
      <section className="container mx-auto px-4 py-12 max-w-2xl">
        <div className="bg-white rounded-lg shadow-lg p-8">
          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label htmlFor="name" className="block text-gray-700 font-medium mb-2">Name</label>
              <input
                id="name"
                name="name"
                value={formData.name}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Your full name"
                required
              />
            </div>

            <div>
              <label htmlFor="company" className="block text-gray-700 font-medium mb-2">Company</label>
              <input
                id="company"
                name="company"
                value={formData.company}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Your company name"
              />
            </div>

            <div>
              <label htmlFor="phone" className="block text-gray-700 font-medium mb-2">Phone</label>
              <input
                id="phone"
                name="phone"
                type="tel"
                value={formData.phone}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="+27 XX XXX XXXX"
                required
              />
            </div>

            <div>
              <label htmlFor="email" className="block text-gray-700 font-medium mb-2">Email</label>
              <input
                id="email"
                name="email"
                type="email"
                value={formData.email}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="your.email@example.com"
                required
              />
            </div>

            <div>
              <label htmlFor="service" className="block text-gray-700 font-medium mb-2">Services Required</label>
              <select
                id="service"
                name="service"
                value={formData.service}
                onChange={handleChange}
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              >
                <option value="">Select a service</option>
                <option value="website">Website Design & Development</option>
                <option value="marketing">Digital Marketing</option>
                <option value="seo">SEO</option>
                <option value="graphic">Graphic Design</option>
                <option value="ecommerce">E-commerce Solutions</option>
                <option value="other">Other</option>
              </select>
            </div>

            <div>
              <label htmlFor="message" className="block text-gray-700 font-medium mb-2">Message</label>
              <textarea
                id="message"
                name="message"
                value={formData.message}
                onChange={handleChange}
                rows="6"
                className="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Tell us about your project or inquiry..."
                required
              ></textarea>
            </div>

            <button
              type="submit"
              className="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition"
            >
              Get in Touch
            </button>
          </form>
        </div>
      </section>

      {/* Contact Info Section */}
      <section className="container mx-auto px-4 py-16">
        <div className="text-center mb-12">
          <h2 className="text-3xl md:text-4xl font-bold mb-4">Get in Touch</h2>
          <p className="text-lg text-gray-600 max-w-3xl mx-auto">
            Don't hesitate to contact us for more information. Use our contact details below to get in touch directly. Let’s start a conversation and make something amazing together!
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8 max-w-5xl mx-auto text-center">
          <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition">
            <h3 className="text-xl font-semibold mb-3">Head Office</h3>
            <p className="text-gray-600">
              111 Agulhas Rd, Bloubosrand,<br />Randburg, 2188
            </p>
          </div>

          <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition">
            <h3 className="text-xl font-semibold mb-3">Email Us</h3>
            <p className="text-gray-600">
              <a href="mailto:info@creativedesignsolution.co.za" className="text-blue-600 hover:underline">
                info@creativedesignsolution.co.za
              </a><br />
              <a href="mailto:admin@creativedesignsolution.co.za" className="text-blue-600 hover:underline">
                admin@creativedesignsolution.co.za
              </a>
            </p>
          </div>

          <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition">
            <h3 className="text-xl font-semibold mb-3">Call Us</h3>
            <p className="text-gray-600">
              <a href="tel:+27729792961" className="text-blue-600 hover:underline">
                Phone: +27 68 515 3028
              </a><br />
              <a href="https://wa.me/27729792961" className="text-blue-600 hover:underline">
                WhatsApp: +27 74 407 8500
              </a>
            </p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Contact;
