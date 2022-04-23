import './Button.css';
import PropTypes from "prop-types";

const Button = ({text, type, htmlButtonType, disabled, clickHandler}) => {

    const prepClassName = [
        "Button",
        type === 1 ? "positive" : "",
        type === 2 ? "negative" : ""
    ];

    const className = prepClassName.join(" ").trim();

    const handleClick = () => {
        if (clickHandler) clickHandler();
    }

    return (
        <button
            className={className}
            type={htmlButtonType ?? 'button'}
            disabled={disabled}
            onClick={handleClick}
        >
            {text}
        </button>
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
