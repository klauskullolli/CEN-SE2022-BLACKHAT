import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";

import './Components/cssStyles/BillDetailedView.css'


function BillDetailedView(){
    return(
        <div className="billDetailedView">
            <Header1 />
            <div className="fullpanelBillDetailedView">
            <div className="profnavfullpanelBillDetailedView">
                <Profile />
                <NavBar />
            </div>
            <div className="panelBillDetailedView">
            
        </div>
        </div>
        </div>
    );
}

export default BillDetailedView;