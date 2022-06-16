import React from "react";
import myProfilePhoto from "./product/img/myProfilePhoto.png";
import EditCredentials from '../EditCredentials';
import { Link , BrowserRouter, Route} from 'react-router-dom';
import {useHistory} from 'react-router-dom'
// import './cssStyles/App.css'


function Profile() {

    return (
            <div className="profile">
                <img src={myProfilePhoto} alt="" className="myProfilePhoto"/>
                <h3>Sindi Shima</h3>
                <Link to="/edit" className="editLink">Edit</Link>
            </div>
    );
}

export default Profile;
