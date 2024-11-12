import './App.css';
import {Route, Routes, BrowserRouter } from "react-router-dom";
import HomePage from "./project/component/pages/HomePage"
import LoginPage from './project/component/pages/LoginPage';
import JoinPage from './project/component/pages/JoinPage';
import TrainInfoPage from './project/component/pages/TrainInfoPage'
import TrainCodePage from './project/component/pages/TrainCodePage'

function App() {
  return (
      <Routes>
        <Route path="/" element={<LoginPage/>}></Route>
        <Route path="/home" element={<HomePage/>}></Route>
        <Route path="/join" element={<JoinPage/>}></Route>
        <Route path="/trainInfo" element={<TrainInfoPage/>}></Route>
        <Route path="/trainCode" element={<TrainCodePage/>}></Route>
      </Routes>
  );
}

export default App;
