import './Button.css';
import React, { Component } from 'react';
import PropTypes from "prop-types";

/* source of inspiration: https://github.com/ahfarmer/calculator */
 
class Button extends Component {
    //state = {  } 

    static propTypes = {
        text: PropTypes.string,
        positive: PropTypes.bool,
        negative: PropTypes.bool,
        disabled: PropTypes.bool
    }

    render() { 
        const prepClassName = [
            "component-button",
            this.props.positive ? "positive" : "",
            this.props.negative ? "negative" : ""
        ];

        const className = prepClassName.join(" ").trim();

        return (
            <button className = { className } disabled={ this.props.disabled }>{ this.props.text }</button>
        );
    }
}
 
export default Button;

/*
Types of buttons
- Sign out
- Back
- Language
- Save changes
- Cancel changes --> can be disabled
- Add product --> may need icon in future
- Edit --> on product page, may need pencil icon in future
- Yes --> popup
- No --> popup
- Cancel --> popup
- Add partner --> may need icon in future

--> prop.type --> determine colour, disabled?, logo?

Positive:
- Save
- Yes

Negative:
- Cancel
- No
- Sign out --> Header, maybe neutral?

Neutral:
- Back
- Language --> header
- Add product
- Add partner
- Edit
*/