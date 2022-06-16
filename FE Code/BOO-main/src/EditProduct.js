import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";
import burger from "./images/burger.png"
import { Link } from "react-router-dom";
import goBackIcon from './Components/product/img/goBackIcon.png'
import { useState } from "react";
import './Components/cssStyles/EditProduct.css'
import { Cafeteria } from "./Components/product/Cafeteria";


function EditProduct(){
    const [list, updateList] = useState(Cafeteria);

    const handleRemoveItem = (e) => {
        const name = e.target.getAttribute("name")
            updateList(list.filter(item => item.name !== name));
            console.log(e);
    }

    return(
        <div className="editprd">
            <Header1 />
            <div className="fupanel">
            <div className="profilenav">
                <Profile />
                <NavBar />
            </div>
        <div className="ppanell">
            <div className="smallpanel">
                <Link to="/product"><img src={goBackIcon} alt="" className="goBackIconImage" /></Link>
                <img src={burger} alt="" className="burgerImg"/>
                <a href="#" className="updatePhotoLink">Update Photo</a>
                <div className="bottomPanell">
                    <form action="">
                        <div className="name1">
                        <label htmlFor="" className="name2">Name</label><br />
                        <input type="text" className="name"/><br />
                        </div>
                        <div className="amount1">
                        <label htmlFor="" className="amount2">Amount</label><br />
                        <input type="number" className="amount" /><br />
                        </div>
                        <div className="description1">
                        <label htmlFor="" className="description2">Description</label><br />
                        <input type="textarea" className="description" /><br /><br />
                        </div>
                        <div className="price1">
                        <label htmlFor="" className="price2" >Price</label><br />
                        <input type="number" className="price" /> L 
                        <br />
                        </div>
                        <div className="buttonn">
                            <Link to="/product"><button type="submit" className="blue">Save Changes</button></Link>
                            <Link to="/product"><button type="submit" className="red" onClick={handleRemoveItem}>Remove Product</button></Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
        </div>
    );
}

export default EditProduct;