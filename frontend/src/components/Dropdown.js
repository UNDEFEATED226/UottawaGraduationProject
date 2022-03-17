import './Dropdown.css'
import PropTypes from "prop-types";
import { useTranslation } from 'react-i18next';

const Dropdown = ({name, labelText, hideLabel, noneOptionText, required, disabled, selected, choices, onChange}) => {

    const { t } = useTranslation();

    const handleChange = e => {
        let id = Number(e.target.value);
        if (id === 0) id = null;
        onChange(name, id);
    }

    return ( 
        <div className="Dropdown">
            <label htmlFor={name} className={hideLabel ? 'visuallyhidden' : ''}>{labelText}</label>
            <select id={name} name={name} required={required} disabled={disabled} value={selected} onChange={handleChange}>
                <option value=''>{noneOptionText ?? t("dropdown.none")}</option>
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
    selected: PropTypes.string.isRequired,
    choices: PropTypes.array
}
 
export default Dropdown;