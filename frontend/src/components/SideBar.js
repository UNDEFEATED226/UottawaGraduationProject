import './SideBar.css'
import React, { Component } from 'react';

class SideBar extends Component {
    render() { 
        return (
            <div className="SideBar">
                <div className={this.props.selected === "BasicInfo"? "selected" : "unselected"}>
                    Basic Information
                </div>
                <div className={this.props.selected === "AboutMe"? "selected" : "unselected"}>
                    About Me
                </div>
                <div className={this.props.selected === "MyProducts"? "selected" : "unselected"}>
                    My Products
                </div>
                <div className={this.props.selected === "MyGrants"? "selected" : "unselected"}>
                    My Grants
                </div>
                <div className={this.props.selected === "MyEvents"? "selected" : "unselected"}>
                    My Events
                </div>
                <div className={this.props.selected === "MySupervisions"? "selected" : "unselected"}>
                    My Supervisions
                </div>
                <div className={this.props.selected === "Partners"? "selected" : "unselected"}>
                    Partners
                </div>
                <div></div>
            </div>
        );
    }
}

export default SideBar;
