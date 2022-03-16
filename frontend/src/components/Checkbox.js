import './Checkbox.css'
import PropTypes from "prop-types";

const Checkbox = ({name, labelText, checkedNum, disabled}) => {

    const getChecked = () => {
        // Json responses return 0 or 1 for false or true respectively
        return checkedNum === 1;
    };

    return (
        <div className='Checkbox'>
            <input type="checkbox" name={name} id={name} defaultChecked={getChecked()} disabled={disabled} />
            <label htmlFor={name}>{labelText}</label>
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
