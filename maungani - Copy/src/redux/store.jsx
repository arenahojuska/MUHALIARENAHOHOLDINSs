import { configureStore } from '@reduxjs/toolkit';

// Dummy reducer
const dummyReducer = (state = {}, action) => state;

const store = configureStore({
  reducer: {
    dummy: dummyReducer, 
  },
});

export default store;
