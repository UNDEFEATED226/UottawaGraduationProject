import './App.css'
import { Component } from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import SideBar from './components/SideBar';
import BasicInformation from 'BasicInformation';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header username="Joe"/>
        <SideBar selected="BasicInfo"/>
        <Routes>
          <Route path="/" element={<BasicInformation />} />
        </Routes>
      </div>
    );
  }
}

export default App;
