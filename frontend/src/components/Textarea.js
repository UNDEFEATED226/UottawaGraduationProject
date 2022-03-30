import './Textarea.css';
import PropTypes from "prop-types";

const Textarea = ({name, labelText, text, placeholderText, required, disabled, errorMessage, rows, cols, onChange}) => {

    const handleChange = e => {
        onChange(name, e.target.value);
    }

    return (
        <div className='Textarea'>
            <label htmlFor={name}>{labelText}</label>
            <textarea
                id={name}
                name={name}
                defaultValue={text ?? ""}
                placeholder={placeholderText}
                required={required}
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
    text: PropTypes.string,
    placeholderText: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    row: PropTypes.number,
    cols: PropTypes.number,
}

export default Textarea;