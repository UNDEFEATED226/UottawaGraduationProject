import './Textbox.css'
import PropTypes from "prop-types";

const Textbox = ({name, labelText, text, placeholderText, required, disabled, onChange}) => {

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
                value={text ?? ""} 
                placeholder={placeholderText} 
                required={required} 
                disabled={disabled}
                onChange={handleChange}
            />
        </div>
    );
}

Textbox.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    text: PropTypes.string,
    placeholderText: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool
}
 
export default Textbox;
