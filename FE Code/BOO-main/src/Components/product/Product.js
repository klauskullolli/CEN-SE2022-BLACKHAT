import React from 'react'
import { Link } from 'react-router-dom';

import '../cssStyles/App.css'

const colorList = ['#F7C0FF','#FFC2EE','#F9D0FF','#FDB2FF'] ;
var i = 0 ;

function indexColor(){
    if (i === 3) i=-1 ; 
    i++
    return colorList[i] ;
}

function Product(props) {
    const {name, price, img, amount, rect, itemId} = props;
    return (
        <div className='product' id={itemId} style={{background: indexColor()}}>
            <div className='rectangle' style={{background: rect}}>
                <h4>{amount}</h4>
            </div>
            <div className='npi'>
                <div className='namename'>
                    <p>{name}</p>
                    <p>{price}</p>
                </div>
                <Link to="/editProduct" className="addProduct"><img src={img} alt="" className='productsImages'/></Link>
            </div>
        </div> 
    );
};

export default Product