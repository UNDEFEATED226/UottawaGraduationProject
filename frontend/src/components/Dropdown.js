import './Dropdown.css'
import PropTypes from "prop-types";
import { useState } from 'react';

const Dropdown = ({name, labelText, required, disabled, selectedChoice, choices}) => {

    const [selected, setSelected] = useState(selectedChoice);

    return ( 
        <div className="Dropdown">
            <label htmlFor={name}>{labelText}</label>
            <select id={name} name={name} required={required} disabled={disabled} value={selected} onChange={e => setSelected(e.target.value)}>
                <option value=''>None</option>
                {choices.map(({id, name}) => (
                    <option key={id} value={id}>{name}</option>
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