import React from 'react';
import Employee from '../Employee';
import {BrowserRouter, Route, Link} from 'react-router-dom'
import Product from '../App'
import Bill from '../Bill'
import Inventory from '../Inventory';

function NavBar(){
    return(
        <div class="navigationBar">
                <nav className="pnav">
                    <li className="nav">
                    <Link to="/product"> Products </Link>
                    </li>
                    <li className="nav">
                    <Link to="/employee"> Employee </Link>
                    </li>
                    <li className="nav" >
                    <Link to="/bills"> Bills </Link>
                    </li>
                    <li className="nav">
                    <Link to="/inventory"> Inventory </Link>
                    </li>
            </nav>
            </div>
    );
}

export default NavBar;