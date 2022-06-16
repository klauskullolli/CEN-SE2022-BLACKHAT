import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import Employee from './Employee';
import EditProduct from './EditProduct';
import App from './App'
import Bill from "./Bill"
import Login from './Login';
import EditEmployee from "./EditEmployee"
import EditCredentials from "./EditCredentials"
import BillDetailedView from "./BillDetailedView"
import Inventory from "./Inventory"
import Pages from './Pages';

// ReactDom.render(
//     <BrowserRouter>
//         <App />
//     </BrowserRouter>,
//     document.getElementById('root')
// );




const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter >
    {/* <App /> */}
    <Pages></Pages>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
