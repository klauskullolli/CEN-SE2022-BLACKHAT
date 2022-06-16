import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import addIcon from './Components/emloyee/AddIcon.png'
import Header from "./Components/Header";
import Header1 from "./Components/Header1";
import Product from "./Components/product/Product";
import Profile from "./Components/Profile";
import Text from "./Components/product/Text";
import Text1 from "./Components/product/Text1";
import NavBar from "./Components/NavBar";
import CategoryPopUp from "./Components/product/CategoryPopUp";
import { useHistory } from "react-router-dom";
import plusIcon from './Components/product/img/plusIcon.png'
import {Cafeteria, Menu, Menu1, Cantina} from "./Components/product/Cafeteria";

import './Components/cssStyles/App.css'
import './Components/cssStyles/CategoryPopup.css'

function App(){

    //CAFETERIA
    const [isMainData, setMainData]=useState(
        Cafeteria.filter((el)=>el.itemId==='sweet2')
    );

    useEffect(() => {
        const text = document.querySelector(".productListCafeteria").querySelectorAll(".cofe");
        // console.log(text);
        function setMenuActive() {
            text.forEach((n) => n.classList.remove("active"));
            this.classList.add("active");
        }

        // console.log(isMainData);
        text.forEach((n) => n.addEventListener("click", setMenuActive));
    }, [isMainData]);
        

    const setData = (itemId) =>{
        setMainData(Cafeteria.filter((el)=>el.itemId == itemId));
    }


    //CANTINA
    const [isMainData1, setMainData1]=useState(
        Cantina.filter((el)=>el.itemId==='burger1')
    );

    useEffect(() => {
        const text1 = document.querySelector(".productListCantina").querySelectorAll(".cofe1");
        // console.log(text1);
        function setMenuActive1() {
            text1.forEach((n) => n.classList.remove("active"));
            this.classList.add("active");
        }

        // console.log(isMainData1);
        text1.forEach((n) => n.addEventListener("click", setMenuActive1));
    }, [isMainData1]);
        

    const setData1 = (itemId) =>{
        setMainData1(Cantina.filter((el)=>el.itemId == itemId));
    }




    // const addCategory = (newCategory) => {
    //     setCafeteriaMenuCategories(newCategory);
    //     console.log(setCafeteriaMenuCategories);
    // }

    //POPUP
    const [buttonPopup, setButtonPopup]=useState(false);


    const [name, setInputValue] = useState("");
    // const [id, setIdValue] = useState(7);
    // const [itemId,setItemId]=useState("sf")

    const [CafeteriaMenuCategories, setCafeteriaMenuCategories]=useState(Menu);

    const handleSubmit=(e)=>{
        e.preventDefault();
        const newCategory={name};
        console.log(newCategory);
        if(newCategory){
            setCafeteriaMenuCategories((ls)=>[ ...ls, newCategory]);
            console.log(CafeteriaMenuCategories);
            console.log(newCategory);
        }
    }


    return(
        <div className="App">
            <Header1 />
            <div className="fullPanel">
            <div className="profileAndNav">
                <Profile />
            <NavBar />
            </div>
            <div className="panel">
            <Header />
            <div className="productListCafeteria">
                {name.length > 0 ? (
                    CafeteriaMenuCategories.map(data =>(
                        <div key={data.id} onClick={()=>setData(data.itemId)}>
                        <Text 
                            name={data.name} 
                            isActive={data.id === "1" ? true : false}
                            /> 
                        </div>
                    ))
                ) : (
                    Menu.map((data) => ( 
                        <div key={data.id} onClick={()=>setData(data.itemId)}>
                        <Text
                            name={data.name}
                            isActive={data.id === "1" ? true : false}
                        />
                    </div>
                    ))
                )
                }
                <button className="buttonPopupProduct" onClick={()=> setButtonPopup(true)}><img src={plusIcon} alt="" className="plusIconImage" />
                <p className="addProductText">add category</p>
                </button>
            </div>
            <form onSubmit={handleSubmit}>
                <label htmlFor="" className='categoryNameText'>Category name:</label><br />
                <input type="text" name="" id="" className='inputCategory' value={name} onChange={(event) => setInputValue(event.target.value)} /><br />
                <button className='confirmPopup'><p className='confirmPopupText'>Confirm</p></button>
                </form>
            {}
            <CategoryPopUp trigger={buttonPopup} setTrigger={setButtonPopup}></CategoryPopUp>
            <Link to="/addProduct" className="addProduct"> <img src={addIcon} alt="" className="addIconProductImage" /></Link>
            <div className="productItemListCafeteria">
                {isMainData && isMainData.map((data) => ( 
                        <Product
                            key={data.id}
                            itemId={data.id}
                            name={data.name}
                            price={data.price}
                            img={data.img}
                            amount={data.amount}
                            color={data.color}
                            rect={data.rect}
                        />
                    ))
                }
            </div>
            <div className="productListCantina">
            {Menu1 && Menu1.map((data1) => ( 
                        <div key={data1.id} onClick={()=>setData1(data1.itemId)}>
                        <Text1
                            name={data1.name}
                            isActive={data1.id === "1" ? true : false}
                        />
                    </div>
                    ))
                }
                <button className="buttonPopupProduct1" onClick={()=> setButtonPopup(true)}><img src={plusIcon} alt="" className="plusIconImage1" />
                    <p className="addProductText1">add category</p>
                </button>
            </div> 
            <Link to="/addProduct" className="addProduct"> <img src={addIcon} alt="" className="addIconProductImage" /></Link>
            <div className="productItemListCantina">
                {isMainData1 && isMainData1.map((data1) => ( 
                        <Product
                            key={data1.id}
                            itemId={data1.id}
                            name={data1.name}
                            price={data1.price}
                            img={data1.img}
                            amount={data1.amount}
                            color={data1.color}
                            rect={data1.rect}
                        />
                    ))
                }
            </div>
        </div>
        </div> 
        </div>
    );
}

export default App;