import React from "react";
import Header1 from "./Components/Header1";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";
import employee from "./images/employee.png"
import { Link } from "react-router-dom";
import goBackIcon from './Components/product/img/goBackIcon.png'


import './Components/cssStyles/AddEmployee.css'


function EditEmployee(){
    return(
        <div className="editemp1">
            <Header1 />
            <div className="fulpanel1">
            <div className="inav1">
                <Profile />
                <NavBar />
            </div>
        <div className="paanell1">
            <div className="smallpanell1">
                <Link to="/employee"><img src={goBackIcon} alt="" className="goBackIconImage3" /></Link>
                <div className="employeeImg1">
                    <div className="innerImageEmpl"></div>
                </div>
                <a href="#" className="updatePhoto1">Add Photo</a>
                <div className="bottomPanel21">
                    <form action="">
                        <div className="threeInputs1">
                        <label htmlFor="">Username</label><br />
                        <input type="text" className="username1"/><br />
                        <label htmlFor="">Password</label><br />
                        <input type="password" className="pass1"/><br />
                        <label htmlFor="" >Confirm Password</label><br />
                        <input type="password" className="confirmPass1" /> 
                        <br />
                        </div>
                        <div className="btnn">
                            <Link to="/employee"><button type="submit" className="bluee1">Save Changes</button></Link>
                            <Link to="/employee"><button type="submit" className="redd1">Remove Employee</button></Link>
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