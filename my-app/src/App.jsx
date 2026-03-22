// Import required modules and components
import { BrowserRouter, Routes, Route, useLocation, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Shop from "./pages/Shop";
import Cart from "./pages/Cart";
import Checkout from "./pages/Checkout";
import Order from "./pages/Order";
import Login from "./components/Login";
import Register from "./components/Register";
import Contact from "./pages/Contact";
import ProductDetails from "./pages/ProductDetails"; // ✅ New product details page
import { useState } from "react";

/* 
  ✅ Layout Component:
  - Displays Navbar and Footer on all pages EXCEPT login and register.
  - This allows login/register to have a clean, standalone layout.
*/
function Layout({ children }) {
  const location = useLocation();

  // Hide Navbar and Footer on login and register pages
  const hideLayout = location.pathname === "/" || location.pathname === "/register";

  return (
    <>
      {!hideLayout && <Navbar />} {/* Show Navbar if not on login/register */}
      {children} {/* Render current page content */}
      {!hideLayout && <Footer />} {/* Show Footer if not on login/register */}
    </>
  );
}

/*
  ✅ ProtectedRoute Component:
  - Ensures only logged-in users can access certain routes.
  - If not logged in, user is redirected to the login page ("/").
*/
const ProtectedRoute = ({ children }) => {
  const user =
    JSON.parse(localStorage.getItem("user")) ||
    JSON.parse(sessionStorage.getItem("user"));

  return user ? children : <Navigate to="/" replace />;
};

/*
  ✅ App Component:
  - Main application wrapper containing all routes.
  - Uses BrowserRouter for navigation and route management.
*/
function App() {
  // State for handling order and checkout address info
  const [order, setOrder] = useState(null);
  const [addressInfo, setAddressInfo] = useState({
    address: "",
    city: "",
    zip: "",
    province: "",
  });

  return (
    <BrowserRouter>
      {/* Layout wraps all routes and applies consistent UI */}
      <Layout>
        <Routes>
          {/* 🟢 PUBLIC ROUTES */}
          <Route path="/" element={<Login />} /> {/* Login page */}
          <Route path="/register" element={<Register />} /> {/* Register page */}

          {/* 🔒 PROTECTED ROUTES (Only accessible when logged in) */}
          <Route
            path="/home"
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            }
          />

          <Route
            path="/shop"
            element={
              <ProtectedRoute>
                <Shop />
              </ProtectedRoute>
            }
          />

          <Route
            path="/cart"
            element={
              <ProtectedRoute>
                <Cart />
              </ProtectedRoute>
            }
          />

          <Route
            path="/contact"
            element={
              <ProtectedRoute>
                <Contact />
              </ProtectedRoute>
            }
          />

          {/* ✅ New Product Details Route */}
          {/* Opens when user clicks on a product card */}
          <Route
            path="/product/:id"
            element={
              <ProtectedRoute>
                <ProductDetails />
              </ProtectedRoute>
            }
          />

          <Route
            path="/checkout"
            element={
              <ProtectedRoute>
                <Checkout setOrder={setOrder} addressInfo={addressInfo} />
              </ProtectedRoute>
            }
          />

          <Route
            path="/order-confrimation"
            element={
              <ProtectedRoute>
                <Order order={order} />
              </ProtectedRoute>
            }
          />
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}

export default App;
