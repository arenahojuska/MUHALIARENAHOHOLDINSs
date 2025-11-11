import React from "react";
import {
  Check,
  Globe,
  Smartphone,
  MessageCircle,
  Shield,
  Zap,
  Search,
  TrendingUp,
  Facebook,
  Instagram,
  Youtube,
  CheckCircle2,
  Package,
  Clock,
  HeadphonesIcon,
} from "lucide-react";

// --- Simple replacements for Button, Card, CardContent, and Badge ---
const Button = ({ children, className = "", onClick, size = "md", variant = "solid" }) => {
  const base =
    "inline-flex items-center justify-center font-medium rounded-lg transition-all focus:outline-none";
  const sizes = {
    sm: "px-4 py-2 text-sm",
    md: "px-6 py-3 text-base",
    lg: "px-8 py-4 text-lg",
  };
  const variants = {
    solid: "bg-blue-600 text-white hover:bg-blue-700",
    outline:
      "border-2 border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white transition-colors",
  };
  return (
    <button onClick={onClick} className={`${base} ${sizes[size]} ${variants[variant]} ${className}`}>
      {children}
    </button>
  );
};

const Card = ({ children, className = "" }) => (
  <div className={`rounded-2xl border bg-white shadow-sm ${className}`}>{children}</div>
);

const CardContent = ({ children, className = "" }) => (
  <div className={`p-4 ${className}`}>{children}</div>
);

const Badge = ({ children, className = "", variant = "solid" }) => {
  const variants = {
    solid: "bg-blue-600 text-white",
    outline: "border border-blue-600 text-blue-600",
  };
  return (
    <span
      className={`inline-block text-sm font-semibold px-3 py-1 rounded-full ${variants[variant]} ${className}`}
    >
      {children}
    </span>
  );
};

// --- Main Component ---
const Promotion = () => {
  const features = [
    { icon: Globe, text: "Free .co.za Domain (1 Year)" },
    { icon: Check, text: "1 Year Web Hosting Included" },
    { icon: Check, text: "Professional Online Store Design" },
    { icon: Package, text: "Up to 39 Products Uploaded, unlimited after launch" },
    { icon: Smartphone, text: "Mobile-Friendly & User-Friendly Design" },
    { icon: MessageCircle, text: "WhatsApp Chat Integration" },
    { icon: Shield, text: "Secure Contact Form & Checkout (SSL Protected)" },
    { icon: Search, text: "Basic SEO Setup for Google Indexing" },
    { icon: Clock, text: "Fast Delivery: 7–14 Days" },
  ];

  const requirements = [
    "Company Logo",
    "Business Name",
    "Phone number",
    "Office address",
    "Social Media Links",
    "Business owner details",
    "Pictures (if applicable)",
    "Product List (up to 39 for setup)",
    "Preferred Payment Methods",
    "Delivery/Shipping Options",
    "Domain Name (if already registered)",
    "50% Deposit Required",
  ];

  const whyChooseUs = [
    {
      icon: TrendingUp,
      title: "Affordable Pricing",
      description:
        "Get a professional online store for just R2700 - unbeatable value for quality web design.",
    },
    {
      icon: Zap,
      title: "Experienced Team",
      description: "Our skilled designers and developers bring years of expertise to every project.",
    },
    {
      icon: HeadphonesIcon,
      title: "Ongoing Support",
      description: "We're here for you even after launch with dedicated customer support.",
    },
  ];

  const services = [
    {
      icon: Search,
      title: "Google My Business Optimization",
      description: "Boost your local visibility and attract more customers through Google searches.",
    },
    {
      icon: TrendingUp,
      title: "Search Engine Optimization",
      description: "Rank higher in search results and drive organic traffic to your website.",
    },
    {
      icon: Facebook,
      title: "Facebook Ads",
      description: "Targeted advertising campaigns to reach your ideal customers on social media.",
    },
    {
      icon: Search,
      title: "Google Ads",
      description: "Pay-per-click campaigns to get immediate visibility and qualified leads.",
    },
  ];

  const scrollToContact = () => {
    const element = document.getElementById("contact");
    element?.scrollIntoView({ behavior: "smooth" });
  };

  return (
    <div className="min-h-screen bg-gray-50 text-gray-900">
      {/* Hero Section */}
      <section className="text-center py-20 px-4 bg-gradient-to-b from-blue-50 to-white">
        <Badge variant="outline" className="px-4 py-2 font-semibold">
          Limited Time Offer
        </Badge>
        <h1 className="text-5xl font-bold mt-6">
          Affordable Online Store Website{" "}
          <span className="block text-blue-600">Only R2700</span>
        </h1>
        <p className="text-xl mt-4 max-w-2xl mx-auto">
          Fast setup in 7–14 days. Everything you need to start selling online.
        </p>
        <div className="mt-8 flex flex-col sm:flex-row gap-4 justify-center">
          <Button size="lg" onClick={scrollToContact}>
            Get Your Business Online Today!
          </Button>
          <Button size="lg" variant="outline">
            View Package Details
          </Button>
        </div>
      </section>

      {/* Features */}
      <section className="py-20 px-4 max-w-6xl mx-auto">
        <h2 className="text-4xl font-bold text-center mb-10">
          What's Included in Your Package
        </h2>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {features.map(({ icon: Icon, text }, i) => (
            <Card key={i} className="hover:border-blue-600 transition-all">
              <CardContent className="flex items-start gap-4 p-6">
                <div className="flex-shrink-0 w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                  <Icon className="w-6 h-6 text-blue-600" />
                </div>
                <p className="font-medium">{text}</p>
              </CardContent>
            </Card>
          ))}
        </div>
      </section>

      {/* Requirements */}
      <section className="bg-gray-100 py-20 px-4">
        <h2 className="text-4xl font-bold text-center mb-10">Package Requirements</h2>
        <Card className="max-w-6xl mx-auto">
          <CardContent className="grid sm:grid-cols-2 lg:grid-cols-3 gap-4 p-8">
            {requirements.map((req, i) => (
              <div key={i} className="flex items-center gap-3">
                <CheckCircle2 className="text-blue-600" />
                <span>{req}</span>
              </div>
            ))}
          </CardContent>
        </Card>
      </section>

      {/* Why Choose Us */}
      <section className="py-20 px-4 max-w-6xl mx-auto text-center">
        <h2 className="text-4xl font-bold mb-10">
          Bring Your Brand to Life with{" "}
          <span className="text-blue-600">Creative Design Solution</span>
        </h2>
        <div className="grid md:grid-cols-3 gap-8">
          {whyChooseUs.map(({ icon: Icon, title, description }, i) => (
            <Card key={i} className="hover:shadow-lg transition">
              <CardContent className="p-8">
                <div className="w-16 h-16 bg-blue-100 rounded-xl flex items-center justify-center mx-auto mb-4">
                  <Icon className="text-blue-600 w-8 h-8" />
                </div>
                <h3 className="text-xl font-bold mb-2">{title}</h3>
                <p className="text-gray-600">{description}</p>
              </CardContent>
            </Card>
          ))}
        </div>
      </section>

      {/* Featured Services */}
      <section className="bg-gray-100 py-20 px-4 max-w-6xl mx-auto">
        <h2 className="text-4xl font-bold text-center mb-10">Featured Services</h2>
        <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-6">
          {services.map(({ icon: Icon, title, description }, i) => (
            <Card key={i} className="hover:border-blue-600 transition">
              <CardContent className="text-center p-6">
                <div className="w-14 h-14 bg-blue-100 rounded-lg flex items-center justify-center mx-auto mb-4">
                  <Icon className="text-blue-600 w-7 h-7" />
                </div>
                <h3 className="font-bold mb-2">{title}</h3>
                <p className="text-sm text-gray-600">{description}</p>
              </CardContent>
            </Card>
          ))}
        </div>
      </section>

      {/* CTA */}
      <section id="contact" className="py-20 px-4 text-center">
        <Card className="max-w-4xl mx-auto border-blue-600">
          <CardContent className="p-12">
            <h2 className="text-3xl font-bold mb-4">Ready to Launch Your Online Store?</h2>
            <p className="text-gray-600 mb-8">
              Join hundreds of successful businesses that trust Creative Design Solution
            </p>
            <Button size="lg">Get Started Now</Button>
            <p className="text-sm text-gray-500 mt-6">
              50% deposit required to begin • Full support included
            </p>
          </CardContent>
        </Card>
      </section>

      {/* Footer */}
      <footer className="bg-blue-900 text-white py-12 text-center">
        <h3 className="text-2xl font-bold mb-4">Creative Design Solution</h3>
        <p className="max-w-2xl mx-auto text-blue-100">
          A full-service creative agency helping businesses succeed online through innovative web
          design, digital marketing, and strategic brand development.
        </p>
        <div className="flex justify-center gap-6 mt-6">
          <Facebook className="w-6 h-6 cursor-pointer hover:text-blue-400" />
          <Instagram className="w-6 h-6 cursor-pointer hover:text-pink-400" />
          <Youtube className="w-6 h-6 cursor-pointer hover:text-red-500" />
        </div>
        <p className="text-blue-300 mt-6 text-sm">
          © {new Date().getFullYear()} Creative Design Solution. All rights reserved.
        </p>
      </footer>
    </div>
  );
};

export default Promotion;
