import './App.css'
import { Component } from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from 'components/Header';
import SideBar from 'components/SideBar';
import BasicInformation from 'pages/BasicInformation';
import MyProducts from 'pages/MyProducts';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header username="Joe"/>
        <SideBar defaultSelect="BasicInfo"/>
        <Routes>
          <Route path="/" element={<BasicInformation />} />
          <Route path="/my_products" element={<MyProducts />} />
        </Routes>
      </div>
    );
  }
}

export default App;
