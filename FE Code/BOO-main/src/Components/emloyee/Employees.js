import React from 'react'
import { Link } from 'react-router-dom';


function Employee(props) {
    const {name, img, color} = props;
    return (
        <div className='empl' style={{background: color}}>
            <div className='nameimage'>
                <h4>{name}</h4>
                <Link to="/editEmployee" className="addEmployee"><img src={img} alt="" className='employeesPhotos' /></Link>
                </div>
        </div> 
    );
};

export default Employee