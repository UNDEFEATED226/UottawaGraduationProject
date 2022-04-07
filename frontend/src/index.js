import './index.css';
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './i18n';
import App from 'App';
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
import Login from 'pages/Login';
import Home from 'pages/Home';

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route element={<App />}>
                    <Route path="/basic_info" element={<BasicInformation />} />
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
                </Route>
                <Route path="/login" element={<Login />} />
            </Routes>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
);
