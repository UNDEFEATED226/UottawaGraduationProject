import './Button.css';
import PropTypes from "prop-types";

const Button = ({text, type, htmlButtonType, disabled, clickHandler}) => {
    /* source of inspiration: https://github.com/ahfarmer/calculator */

    const prepClassName = [
        "Button",
        type === 1 ? "positive" : "",
        type === 2 ? "negative" : ""
    ];

    const className = prepClassName.join(" ").trim();

    const handleClick = () => {
        if (clickHandler) clickHandler();
    }

    return ( // Look into when to disable, like tied to the submit state
        <button 
            className={className} 
            type={htmlButtonType ?? 'button'} 
            disabled={disabled} 
            onClick={handleClick}
        >{text}</button>
    );
}

Button.propTypes = {
    text: PropTypes.string.isRequired,
    type: PropTypes.number,
    htmlButtonType: PropTypes.string,
    disabled: PropTypes.bool,
    clickHandler: PropTypes.func
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
