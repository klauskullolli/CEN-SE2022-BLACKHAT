import React from "react";
import Header1 from "./Components/Header1";
import editcred from "./images/editcred.png"
import { useHistory } from "react-router-dom";

import './Components/cssStyles/EditCredentials.css'


const color='white';

function EditCredentials(){
    const history = useHistory();
    return(
        <div className="editcredentials">
            <Header1 />
            <div className="smalllpanel">
                <div className="topPanel">
                <div className="editcredImg1">
                    <img src={editcred} alt="" className="editcredImg"/>
                </div>
                <a href="#" className="editcredText">Edit Profile</a>
                </div>
                <div className="lowwPanel">
                    <form action="">
                        <label htmlFor="" style={{color: color}} className="usnText">Username</label><br />
                        <input type="text" className="usname" placeholder=" Mary Jane"/><br />
                        <label htmlFor="" style={{color: color}} className="passwText">Password</label><br />
                        <input type="password" className="pasw" placeholder="********" /><br />
                        <label htmlFor="" style={{color: color}} className="rewriteText">Rewrite Password</label><br />
                        <input type="password" className="passww" placeholder="********" /><br />
                        <button type="submit" className="savebtn">Save</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default EditCredentials;