import './SideBar.css'
import { useState } from 'react';

const SideBar = ({ selected }) => {

    const [collapsed, setCollapsed] = useState(false);

    return (
        <div className={"SideBar" + (collapsed? " collapsed" : "")}>
            <button onClick={() => setCollapsed(!collapsed)}>
                {collapsed? ">" : "< Collapse"}
            </button>
            <div className={selected === "BasicInfo"? "selected" : "unselected"}>
                Basic Information
            </div>
            <div className={selected === "AboutMe"? "selected" : "unselected"}>
                About Me
            </div>
            <div className={selected === "MyProducts"? "selected" : "unselected"}>
                My Products
            </div>
            <div className={selected === "MyGrants"? "selected" : "unselected"}>
                My Grants
            </div>
            <div className={selected === "MyEvents"? "selected" : "unselected"}>
                My Events
            </div>
            <div className={selected === "MySupervisions"? "selected" : "unselected"}>
                My Supervisions
            </div>
            <div className={selected === "Partners"? "selected" : "unselected"}>
                Partners
            </div>
            <div></div>
        </div>
    );
}

export default SideBar;
