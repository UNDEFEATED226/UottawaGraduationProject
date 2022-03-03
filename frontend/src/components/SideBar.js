import './SideBar.css'
import { useState } from 'react';
import { Link } from 'react-router-dom';

const SideBar = ({ defaultSelect }) => {

    const [collapsed, setCollapsed] = useState(false);
    const [selected, setSelected] = useState(defaultSelect);

    return (
        <div className={"SideBar" + (collapsed? " collapsed" : "")}>
            <button id='collapse-btn' onClick={() => setCollapsed(!collapsed)}>
                {collapsed? ">" : "< Collapse"}
            </button>
            <Link to='/' onClick={() => setSelected('BasicInfo')}>
                <div className={selected === "BasicInfo"? "selected" : ""}>
                    Basic Information
                </div>
            </Link>
            <Link to='/my_products' onClick={() => setSelected('MyProducts')}>
                <div className={selected === "MyProducts"? "selected" : ""}>
                    My Products
                </div>
            </Link>
            <Link to='/my_grants' onClick={() => setSelected('MyGrants')}>
                <div className={selected === "MyGrants"? "selected" : ""}>
                    My Grants
                </div>
            </Link>
            <Link to='/my_events' onClick={() => setSelected('MyEvents')}>
                <div className={selected === "MyEvents"? "selected" : ""}>
                    My Events
                </div>
            </Link>
            <Link to='/my_supervisions' onClick={() => setSelected('MySupervisions')}>
                <div className={selected === "MySupervisions"? "selected" : ""}>
                    My Supervisions
                </div>
            </Link>
            <Link to='/partners' onClick={() => setSelected('Partners')}>
                <div className={selected === "Partners"? "selected" : ""}>
                    Partners
                </div>
            </Link>
            <div></div>
        </div>
    );
}

export default SideBar;
