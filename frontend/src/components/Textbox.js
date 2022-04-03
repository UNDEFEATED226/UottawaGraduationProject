import './Textbox.css'
import PropTypes from "prop-types";

const Textbox = ({name, labelText, text, placeholderText, errorMessage, required, disabled, onChange}) => {

    const handleChange = e => {
        onChange(name, e.target.value);
    }

    return (
        <div className="Textbox">
            <label htmlFor={name}>{labelText}</label>
            <input
                type="text"
                id={name}
                name={name}
                defaultValue={text ?? ""}
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
    onChange: PropTypes.func
}

export default Textbox;
