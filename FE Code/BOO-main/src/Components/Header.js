import React from "react";
import {SearchRounded} from "@mui/icons-material"
import {Logout} from "@mui/icons-material"
import {useHistory} from 'react-router-dom'
import Employee from "../Employee";
import { useState } from "react";
import EmployeeList from "./emloyee/EmployeeList";


function Header() {
    const history = useHistory();
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
        }
        else{
            setFilteredResults(EmployeeList);
        }
    }

    return (
        <header className="headerr">
            <div className="inputBox">
                <SearchRounded />
                <div><input type="text" value={searchInput} placeholder="               Search" className="search" onChange={(e) => searchItems(e.target.value)}/></div>
            </div>
            <button className="logoutbtn" onClick={() => history.push('/login')}><Logout /></button>
        </header>
    );
}

export default Header;
