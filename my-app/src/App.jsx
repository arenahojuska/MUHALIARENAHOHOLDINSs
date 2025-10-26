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
import { useState } from "react";

function Layout({ children }) {
  const location = useLocation();
  const hideLayout = location.pathname === "/" || location.pathname === "/register";
  return (
    <>
      {!hideLayout && <Navbar />}
      {children}
      {!hideLayout && <Footer />}
    </>
  );
}

// Protected route wrapper
const ProtectedRoute = ({ children }) => {
  const user =
    JSON.parse(localStorage.getItem("user")) ||
    JSON.parse(sessionStorage.getItem("user"));

  return user ? children : <Navigate to="/" replace />;
};

function App() {
  const [order, setOrder] = useState(null);
  const [addressInfo, setAddressInfo] = useState({
    address: "",
    city: "",
    zip: "",
    province: "",
  });

  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          {/* Public Pages */}
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />

          {/* Protected Pages */}
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
            path="/Contact"
            element={
              <ProtectedRoute>
                <Contact />
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
