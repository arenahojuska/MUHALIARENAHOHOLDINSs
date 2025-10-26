import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  products: [],
  filteredProducts: [],
};

const productSlice = createSlice({
  name: 'products',
  initialState,
  reducers: {
    setProducts(state, action) {
      state.products = action.payload;
      state.filteredProducts = action.payload;
    },
    filterProducts(state, action) {
      const term = action.payload.toLowerCase();
      state.filteredProducts = state.products.filter(product =>
        product.name.toLowerCase().includes(term)
      );
    },
    sortProducts(state, action) {
      const { type } = action.payload;
      if (type === 'name-asc') {
        state.filteredProducts.sort((a, b) => a.name.localeCompare(b.name));
      } else if (type === 'name-desc') {
        state.filteredProducts.sort((a, b) => b.name.localeCompare(a.name));
      } else if (type === 'price-asc') {
        state.filteredProducts.sort((a, b) => a.price - b.price);
      } else if (type === 'price-desc') {
        state.filteredProducts.sort((a, b) => b.price - a.price);
      }
    },
  },
});

export const { setProducts, filterProducts, sortProducts } = productSlice.actions;
export default productSlice.reducer;
