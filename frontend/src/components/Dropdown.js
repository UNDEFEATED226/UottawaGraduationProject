import './Dropdown.css'
import PropTypes from "prop-types";

const Dropdown = ({name, labelText, required, disabled, selectedChoice, choices}) => {
    return ( 
        <div className="Dropdown">
            <label for={name}>{labelText}</label>
            <select id={name} name={name} required={required} disabled={disabled}>
                <option value="" selected>{selectedChoice}</option>
                {choices.map(choice => (
                    <option value={choice.ID}>{choice.name}</option>
                ))}
            </select>
        </div>
    );
}

Dropdown.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    selectedChoice: PropTypes.string.isRequired,
    choices: PropTypes.array
}
 
export default Dropdown;