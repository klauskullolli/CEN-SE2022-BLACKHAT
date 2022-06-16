import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";

import './Components/cssStyles/Inventory.css'


function Inventory(){
    return(
        <div className="inventory">
            <Header1 />
            <div className="fullpanelInventory">
            <div className="profnavInventory">
                <Profile />
                <NavBar />
            </div>
            <div className="panelInventory">
            
        </div>
        </div>
        </div>
    );
}

export default Inventory;