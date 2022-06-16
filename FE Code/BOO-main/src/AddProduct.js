import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";
import burger from "./images/burger.png"
import { Link } from "react-router-dom";
import goBackIcon from './Components/product/img/goBackIcon.png'
import { useState } from "react";
import { Cafeteria } from "./Components/product/Cafeteria";

import './Components/cssStyles/AddProduct.css'


function AddProduct(){

    return(
        <div className="editprd1">
            <Header1 />
            <div className="fupanel1">
            <div className="profilenav1">
                <Profile />
                <NavBar />
            </div>
        <div className="ppanell1">
            <div className="smallpanel1">
                <Link to="/product"><img src={goBackIcon} alt="" className="goBackIconImage3" /></Link>
                <div className="blankImage"></div>
                <a href="#" className="updatePhotoLink1">Add Photo</a>
                <div className="bottomPanell1">
                    <form action="">
                        <div className="name11">
                        <label htmlFor="" className="name21">Name</label><br />
                        <input type="text" className="name111"/><br />
                        </div>
                        <div className="amount11">
                        <label htmlFor="" className="amount21">Amount</label><br />
                        <input type="number" className="amount111" /><br />
                        </div>
                        <div className="description11">
                        <label htmlFor="" className="description21">Description</label><br />
                        <input type="textarea" className="description111" /><br /><br />
                        </div>
                        <div className="price11">
                        <label htmlFor="" className="price21" >Price</label><br />
                        <input type="number" className="price111" /> L 
                        <br />
                        </div>
                        <div className="buttonn">
                            <Link to="/product"><button type="submit" className="blue1">Save Changes</button></Link>
                            <Link to="/product"><button type="submit" className="red1">Remove Product</button></Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
        </div>
    );
}

export default AddProduct;