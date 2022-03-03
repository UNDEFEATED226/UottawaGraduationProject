import './Textarea.css';
import PropTypes from "prop-types";

const Textarea = ({name, labelText, placeholderText, required, disabled, rows, cols}) => {
    
    return (
        <div className='Textarea'>
            <label htmlFor={name}>{labelText}</label>
            <textarea 
                   id={name} name={name} 
                   placeholder={placeholderText} 
                   required={required} 
                   disabled={disabled}
                   rows={rows}
                   cols={cols}
            />
        </div>
    );
}

Textarea.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    placeholderText: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    row: PropTypes.number,
    cols: PropTypes.number,
}
 
export default Textarea;