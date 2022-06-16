import React from "react";
import epokaLogo from "./product/img/epokaLogo .png"
import bereqetLogo from "./product/img/bereqetLogo.png"

// import './cssStyles/App.css'


function Header1() {
    return (
            <div className="logos">
                <img src={epokaLogo} alt="" className="epoka"/>
                <img src={bereqetLogo} alt="" className="bereqet"/>
            </div>
    );
}

export default Header1;
