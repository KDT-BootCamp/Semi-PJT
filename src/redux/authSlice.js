import { createSlice } from '@reduxjs/toolkit';


const initialState = { isLoggedIn: false, id: '', name: '', password: '' };

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
      logout: (state) => {
        console.log('logout slice');
        state.isLoggedIn = false;
        state.id = '';
        state.name = '';
        state.password = '';
      },
      loggedIn: (state, action) => {
        console.log('login slice');
        state.isLoggedIn = true;
        state.id = action.payload[0];
        state.name = action.payload[1];
        state.password = action.payload[2];
      },
    },
  });
  
export const { logout, loggedIn } = authSlice.actions;
const { reducer } = authSlice;
export default reducer;