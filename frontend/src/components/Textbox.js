import './Textbox.css'
import PropTypes from "prop-types";

const Textbox = ({name, labelText, text, placeholderText, required, disabled}) => {
    
    return (
        <div className="Textbox">
            <label htmlFor={name}>{labelText}</label>
            <input type="text" id={name} name={name} value={text} placeholder={placeholderText} required={required} disabled={disabled}/>
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
