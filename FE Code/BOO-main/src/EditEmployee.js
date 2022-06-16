import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";
import employee from "./images/employee.png"
import { Link } from "react-router-dom";
import goBackIcon from './Components/product/img/goBackIcon.png'

import './Components/cssStyles/EditEmployee.css'



function EditEmployee(){
    return(
        <div className="editemp">
            <Header1 />
            <div className="fulpanel">
            <div className="inav">
                <Profile />
                <NavBar />
            </div>
        <div className="paanell">
            <div className="smallpanell">
                <Link to="/employee"><img src={goBackIcon} alt="" className="goBackIconImage4" /></Link>
                <img src={employee} alt="" className="employeeImg"/>
                <a href="#" className="updatePhoto">Update Photo</a>
                <div className="bottomPanel2">
                    <form action="">
                        <div className="threeInputs">
                        <label htmlFor="">Username</label><br />
                        <input type="text" className="username" placeholder=" Mary Jane"/><br />
                        <label htmlFor="">Password</label><br />
                        <input type="password" className="pass" placeholder="********" /><br />
                        <label htmlFor="" >Confirm Password</label><br />
                        <input type="password" className="confirmPass" placeholder="********" /> 
                        <br />
                        </div>
                        <div className="btnn">
                            <Link to="/employee"><button type="submit" className="bluee">Save Changes</button></Link>
                            <Link to="/employee"><button type="submit" className="redd">Remove Employee</button></Link>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
        </div>
    );
}

export default EditEmployee;