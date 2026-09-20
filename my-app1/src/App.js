import React from "react";
import Header from "./header";
import Footer from "./footer";

import ProductsData from "./schoolProducts";
import Product from "./products";

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      isLoggedIn: true
    };
  }

  render() {
    let wordDisplay;
    if (this.state.isLoggedIn === true) {
      wordDisplay = "in";
    } else {
      wordDisplay = "out";
    }

    return (
      <div>
        <Header />

        <div>
          <h1>You are currently logged {wordDisplay}</h1>
        </div>

        <Footer />
      </div>
    );
  }
}

export default App;
