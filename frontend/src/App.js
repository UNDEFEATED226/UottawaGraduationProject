import './App.css'
import { Component } from 'react';
import { Routes, Route } from 'react-router-dom';
import Header from 'components/Header';
import SideBar from 'components/SideBar';
import BasicInformation from 'pages/BasicInformation';
import MyProducts from 'pages/MyProducts';
import EditProduct from 'pages/EditProduct';
import MyGrants from 'pages/MyGrants';
import EditGrant from 'pages/EditGrant';
import MyEvents from 'pages/MyEvents';
import EditEvent from 'pages/EditEvent';
import Partners from 'pages/Partners';
import EditPartner from 'pages/EditPartner';
import MySupervisions from 'pages/MySupervisions';
import EditSupervision from 'pages/EditSupervision';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Header username="Joe"/>
        <SideBar defaultSelect="BasicInfo"/>
        <Routes>
          <Route path="/" element={<BasicInformation />} />
          <Route path="/my_products" element={<MyProducts />} />
          <Route path="/edit_product/:productId" element={<EditProduct />} />
          <Route path="/my_grants" element={<MyGrants />} />
          <Route path="/edit_grant/:grantId" element={<EditGrant />} />
          <Route path="/my_events" element={<MyEvents />} />
          <Route path="/edit_event/:eventId" element={<EditEvent />} />
          <Route path="/my_supervisions" element={<MySupervisions />} />
          <Route path="/edit_supervision/:supervisionId" element={<EditSupervision />} />
          <Route path="/partners" element={<Partners />} />
          <Route path="/edit_partner/:partnerId" element={<EditPartner />} />
        </Routes>
      </div>
    );
  }
}

export default App;
