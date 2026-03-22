import React from "react";

function Product(props) {
  return (
    <div className="product-card">
      <h2>{props.product.name}</h2>
      <p>
        {Number(props.product.price).toLocaleString("en-US", {
          style: "currency",
          currency: "USD"
        })}{" "}
        – {props.product.description}
      </p>
    </div>
  );
}

export default Product;
