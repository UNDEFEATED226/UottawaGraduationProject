import './Textarea.css';
import PropTypes from "prop-types";
import { useEffect, useState } from 'react';

const Textarea = ({name, labelText, text, placeholderText, disabled, errorMessage, rows, cols, onChange}) => {

    const [fieldText, setFieldText] = useState('');

    useEffect(() => {
        setFieldText(text);
    }, [text]);

    const handleChange = e => {
        setFieldText(e.target.value);
        onChange(name, e.target.value);
    }

    return (
        <div className='Textarea'>
            <label htmlFor={name}>{labelText}</label>
            <textarea
                id={name}
                name={name}
                value={fieldText ?? ""}
                placeholder={placeholderText}
                disabled={disabled}
                rows={rows}
                cols={cols}
                onChange={handleChange}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
        </div>
    );
}

Textarea.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    text: PropTypes.string,
    placeholderText: PropTypes.string,
    disabled: PropTypes.bool,
    errorMessage: PropTypes.string,
    rows: PropTypes.number,
    cols: PropTypes.number
}

export default Textarea;
