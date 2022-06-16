import React, { useState } from 'react'
import '../cssStyles/App.css'
import { useHistory } from "react-router-dom";

function Text({name, isActive}){ 
    return (
        <div className={`cofe ${isActive ? `active` : ``}`}>
            <h4>{name}</h4>
        </div>
    );
};


export default Text