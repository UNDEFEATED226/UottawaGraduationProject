import './Textbox.css';
import PropTypes from "prop-types";
import { useEffect, useState } from 'react';

const Textbox = ({name, labelText, text, placeholderText, errorMessage, required, disabled, onChange}) => {

    const [fieldText, setFieldText] = useState('');

    useEffect(() => {
        setFieldText(text);
    }, [text]);

    const handleChange = e => {
        setFieldText(e.target.value);
        onChange(name, e.target.value);
    }

    return (
        <div className="Textbox">
            <label htmlFor={name}>{labelText}</label>
            <input
                type="text"
                id={name}
                name={name}
                value={fieldText ?? ""}
                placeholder={placeholderText}
                required={required}
                disabled={disabled}
                onChange={handleChange}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
        </div>
    );
}

Textbox.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    text: PropTypes.string,
    placeholderText: PropTypes.string,
    errorMessage: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    onChange: PropTypes.func,
}

export default Textbox;
