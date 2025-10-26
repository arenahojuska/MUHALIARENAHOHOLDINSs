import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  products: [],
  totalQuantity: 0,
  totalPrice: 0,
};

const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addToCart(state, action) {
      const newItem = action.payload;
      const existingItem = state.products.find(item => item.id === newItem.id);

      if (existingItem) {
        existingItem.quantity++;
        existingItem.totalPrice += newItem.price;
      } else {
        state.products.push({
          id: newItem.id,
          name: newItem.name,
          price: newItem.price,
          quantity: 1,
          totalPrice: newItem.price,
          image: newItem.image,
        });
      }

      state.totalQuantity++;
      state.totalPrice += newItem.price;
    },

    removeFromCart(state, action) {
      const itemId = action.payload.id;
      const itemToRemove = state.products.find(item => item.id === itemId);

      if (itemToRemove) {
        state.totalQuantity -= itemToRemove.quantity;
        state.totalPrice -= itemToRemove.totalPrice;
        state.products = state.products.filter(item => item.id !== itemId);
      }
    },

    increaseQuantity(state, action) {
      const itemId = action.payload.id;
      const item = state.products.find(item => item.id === itemId);

      if (item) {
        item.quantity++;
        item.totalPrice += item.price;
        state.totalQuantity++;
        state.totalPrice += item.price;
      }
    },

    decreaseQuantity(state, action) {
      const itemId = action.payload.id;
      const item = state.products.find(item => item.id === itemId);

      if (item && item.quantity > 1) {
        item.quantity--;
        item.totalPrice -= item.price;
        state.totalQuantity--;
        state.totalPrice -= item.price;
      } else if (item && item.quantity === 1) {
        state.totalQuantity--;
        state.totalPrice -= item.price;
        state.products = state.products.filter(i => i.id !== itemId);
      }
    },

    clearCart(state) {
      state.products = [];
      state.totalQuantity = 0;
      state.totalPrice = 0;
    },
  },
});

export const {
  addToCart,
  removeFromCart,
  increaseQuantity,
  decreaseQuantity,
  clearCart, 
} = cartSlice.actions;

export default cartSlice.reducer;
