import React from 'react'
import CloseIcon from '@mui/icons-material/Close';
import App from '../../App'
import { useState } from 'react';
import { Menu } from '../product/Cafeteria'


function CategoryPopUp(props) {

    return (props.trigger) ? (
        <div className='categoryPopup'>
            <button className='popupButton' onClick={() => props.setTrigger(false)}><CloseIcon /></button>
            <div className='writeCategoryName'>
                <form >
                <label htmlFor="" className='categoryNameText'>Category name:</label><br />
                <input type="text" name="" id="" className='inputCategory'  /><br />
                <button className='confirmPopup' onClick={() => props.setTrigger(false)}><p className='confirmPopupText'>Confirm</p></button>
                </form>
            </div>
            {props.children}
            </div>
    ) : "";
}

export default CategoryPopUp
