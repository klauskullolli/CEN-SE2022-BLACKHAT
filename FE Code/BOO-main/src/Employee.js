import React from "react";
import Profile from "./Components/Profile";
import NavBar from "./Components/NavBar";
import Header1 from './Components/Header1';
import Header from './Components/Header';
import Employees from "./Components/emloyee/Employees";
import EmployeeList from "./Components/emloyee/EmployeeList";
import { Link } from "react-router-dom";
import addIcon from './Components/emloyee/AddIcon.png'
import { useState } from "react";
import {SearchRounded} from "@mui/icons-material"
import {Logout} from "@mui/icons-material"

import './Components/cssStyles/Employee.css'


function Employee(){

    const [searchInput, setSearchInput]=useState('');
    const [filteredResults, setFilteredResults] = useState([]);

    const searchItems = (searchValue) => {
        setSearchInput(searchValue)
        if (searchInput !== '') {
            const filteredData = EmployeeList.filter((item) => {
                return Object.values(item).join('').toLowerCase().includes(searchInput.toLowerCase())
            })
            setFilteredResults(filteredData)
            console.log(filteredData);
            console.log(searchInput.length);
        }
        else{
            setFilteredResults(EmployeeList);
        }
    }

    return(
        <div className="employee">
            <Header1 />
            <div className="fpanel">
            <div className="imgnav">
                <Profile />
                <NavBar />
            </div>
            <div className="panell">

            <header className="headerr">
                <div className="inputBox">
                    <SearchRounded />
                    <div><input type="text" value={searchInput} placeholder="               Search" className="search" onChange={(e) => searchItems(e.target.value)}/></div>
                </div>
                <button className="logoutbtn" ><Logout /></button>
            </header>

            <div className="employeeList">
                {searchInput.length > 0 ? (
                    filteredResults.map(data => ( 
                            <div key={data.id} >
                            <Employees
                                name={data.name}
                                img={data.image}
                                color={data.color}
                            />
                            </div>
                        ))
                ) : (
                EmployeeList.map(data => ( 
                    <div key={data.id}>
                        <Employees
                            name={data.name}
                            img={data.image}
                            color={data.color}
                        />
                    </div>
                    ))
                )}
            <Link to="/addEmployee" className="addEmployee"> <img src={addIcon} alt="" className="addIconImage" /></Link>
            </div>
        </div>
        </div>
        </div>
    );
}

export default Employee;