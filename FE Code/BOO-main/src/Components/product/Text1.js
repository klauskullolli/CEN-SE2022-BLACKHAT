import React, { useState } from 'react'
import '../cssStyles/App.css'

function Text1({name, isActive}){ 
    return (
        <div className={`cofe1 ${isActive ? `active` : ``}`}>
            <h4>{name}</h4>
        </div>
    );
};


export default Text1