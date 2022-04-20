import './Textbox.css';
import PropTypes from "prop-types";
import { useEffect, useState } from 'react';

const Textbox = ({name, labelText, text, isPassword, isNumber, formatter,
    placeholderText, errorMessage, required, disabled, onChange}) => {

    const [displayText, setDisplayText] = useState('');
    const [focused, setFocused] = useState(false);

    useEffect(() => {
        if (!focused) {
            if (formatter) setDisplayText(formatter(text));
            else setDisplayText(text);
        }
    }, [text, formatter, focused]);

    const handleChange = e => {
        let value = e.target.value;
        if (isNumber) {
            if (value !== '' && !Number(value)) return;
            setDisplayText(value);
            onChange(name, Number(value));
        }
        else {
            setDisplayText(value);
            onChange(name, value);
        }
    }

    const handleFocus = () => {
        setFocused(true);
        setDisplayText(text);
    }

    const handleBlur = () => {
        setFocused(false);
    }

    return (
        <div className="Textbox">
            <label htmlFor={name}>{labelText}</label>
            <input
                type={isPassword ? "password" : "text"}
                id={name}
                name={name}
                value={displayText ?? ""}
                placeholder={placeholderText}
                required={required}
                disabled={disabled}
                onChange={handleChange}
                onFocus={handleFocus}
                onBlur={handleBlur}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
        </div>
    );
}

Textbox.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    text: PropTypes.oneOfType([
        PropTypes.string,
        PropTypes.number
    ]),
    isPassword: PropTypes.bool,
    isNumber: PropTypes.bool,
    formatter: PropTypes.func,
    placeholderText: PropTypes.string,
    errorMessage: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    onChange: PropTypes.func,
}

Textbox.defaultProps = {
    name: '',
    labelText: '',
    text: '',
    isPassword: false,
    isNumber: false,
    formatter: null,
    placeholderText: null,
    errorMessage: null,
    required: false,
    disabled: false,
    onChange: null,
}

export default Textbox;
