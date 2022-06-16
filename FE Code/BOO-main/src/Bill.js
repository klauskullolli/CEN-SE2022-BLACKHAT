import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";

import './Components/cssStyles/Bill.css'


function Bill(){
    return(
        <div className="bill">
            <Header1 />
            <div className="fullpanelBill">
            <div className="profnavBill">
                <Profile />
                <NavBar />
            </div>
            <div className="panelBill">
            
        </div>
        </div>
        </div>
    );
}

export default Bill;