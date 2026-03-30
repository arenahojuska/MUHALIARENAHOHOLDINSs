import { Menu } from "lucide-react";
import { useState } from "react";
import { Link } from "react-router-dom";

import CardNav from "./navbar-design";
import logo from '../assets/logo.png';

export const Header = () => {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

 

  const cardNavItems = [
 {
  label: "Home",
  path: "/",
  bgColor: "#0D0716",
  textColor: "#fff",
} , 
 {
  label: "About Us",
  path: "/About",
  bgColor: "#0D0716",
  textColor: "#fff",
} , 

 {
  label: "Services",
  path: "/Services",
  bgColor: "#0D0716",
  textColor: "#fff",
} , 

 {
  label: "Promotion",
  path: "/Promotion",
  bgColor: "#0D0716",
  textColor: "#fff",
} ,
    {
  label: "Contact Us",
  path: "/Contact",
  bgColor: "#0D0716",
  textColor: "#fff",
} ,
  
  ];

  return (
    <header className="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
      <div className="container mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex h-16 items-center justify-between">
          {/* Logo */}
          <div className="flex-shrink-0">
            <h1 className="text-2xl font-bold bg-gradient-to-r from-primary to-accent bg-clip-text text-transparent">
              CreativeHub
            </h1>
          </div>
          <div className="absolute top-0 left-0 p-4">
  <Link to="/">
    <img src={logo} alt="Company Logo" className="h-12 w-auto object-contain" />
  </Link>
</div>

          {/* CardNav integration (desktop only) */}
          <div className="hidden lg:flex lg:flex-1 justify-center">
            <CardNav
              logo={logo}
              logoAlt="Company Logo"
              items={cardNavItems}
              baseColor="#fff"
              menuColor="#000"
              buttonBgColor="#111"
              buttonTextColor="#fff"
              ease="power3.out"
            />
          </div>
          {/* CardNav integration (mobile only) */}
<div className="lg:hidden flex flex-col items-center">
  <CardNav
    logo={logo}
    logoAlt="Company Logo"
    items={cardNavItems}
    baseColor="#fff"
    menuColor="#000"
    buttonBgColor="#111"
    buttonTextColor="#fff"
    ease="power3.out"
  />
</div>

         

          {/* Mobile menu button */}
          <button
            className="lg:hidden p-2"
            onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
          >
            <Menu className="h-6 w-6" />
          </button>
        </div>

        {/* Mobile Navigation */}
        {mobileMenuOpen && (
          <div className="lg:hidden py-4 space-y-2">
            {cardNavItems.map((item) => (
              <Link
                key={item.name}
                to={item.path}
                className="block px-3 py-2 text-sm font-medium text-foreground/80 hover:text-foreground hover:bg-muted rounded-md"
                onClick={() => setMobileMenuOpen(false)}
              >
                {item.name}
              </Link>
            ))}

          </div>
        )}
      </div>
    </header>
  );
};
