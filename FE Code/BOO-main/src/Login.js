import React from "react";
import Header1 from "./Components/Header1";
import login from "./images/login.png"
import Pages from "./Pages";
import { useHistory } from 'react-router-dom';



import './Components/cssStyles/Login.css'
import Product from "./Components/product/Product";


const color='white';

function Login(){
    const history = useHistory();

    return(
        <div className="login">
            <Header1 />
            <div className="smalpanel">
                <img src={login} alt="" className="loginImg"/>
                <h2 className="loginText">Login</h2>
                <div className="lowPanel" method="GET">
                    <form onSubmit={() => history.push('/product')}>
                        <label htmlFor="" style={{color: color}} className="usernameText">Username</label><br />
                        <input type="text" className="usernameInput" placeholder=" Mary Jane"/><br />
                        <label htmlFor="" style={{color: color}} className="passwordText">Password</label><br />
                        <input type="password" className="passwordInput" placeholder="********" /><br />
                        <button type="submit" className="signin" >Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;