import './Checkbox.css'
import PropTypes from "prop-types";
import { useEffect, useState } from 'react';

const Checkbox = ({name, labelText, checkedNum, disabled, errorMessage, onChange}) => {

    const [checked, setChecked] = useState(false);

    useEffect(() => {
        setChecked(checkedNum === 1);
    }, [checkedNum]);

    const handleChange = e => {
        setChecked(e.target.checked);
        onChange(name, e.target.checked? 1 : 0);
    }

    return (
        <div className='Checkbox'>
            <input
                type="checkbox"
                name={name}
                id={name}
                checked={checked}
                disabled={disabled}
                onChange={handleChange}
            />
            <label htmlFor={name}>{labelText}</label>
            {errorMessage && <div className='errorMsg'>{errorMessage}</div>}
        </div>
    );
}

Checkbox.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    checkedNum: PropTypes.number,
    disabled: PropTypes.bool
}

export default Checkbox;
