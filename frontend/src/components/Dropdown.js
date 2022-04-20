import './Dropdown.css'
import PropTypes from "prop-types";
import { useTranslation } from 'react-i18next';
import { useCallback, useEffect, useState } from 'react';

const Dropdown = ({name, labelText, hideLabel, noneOptionText, hideNoneOption,
    required, disabled, selectedChoice, choices, errorMessage, onChange}) => {

    const { t } = useTranslation();

    const [showOptionsList, setShowOptionsList] = useState(false);
    const [searchBoxText, setSearchBoxText] = useState('');
    const [filteredChoices, setFilteredChoices] = useState([]);

    const getNameFromID = useCallback(id => {
        let foundName = choices.find(choice => choice.id === id)?.name;
        return foundName ?? noneOptionText ?? t("dropdown.none");
    }, [choices, noneOptionText, t])

    // Set search box text upon load
    useEffect(() => {
        setSearchBoxText(getNameFromID(selectedChoice));
    }, [getNameFromID, selectedChoice]);

    // Updates filtered choices on search
    useEffect(() => {
        let filtered = choices.filter(choice => {
            if (!choice.name) return false;
            return choice.name.toLowerCase().includes(searchBoxText.toLowerCase());
        });
        filtered.sort((a, b) => a.name > b.name ? 1 : -1);
        setFilteredChoices(filtered);
    }, [choices, searchBoxText]);

    const handleSearch = e => {
        setSearchBoxText(e.target.value);
    }

    const handleInputFocus = _ => {
        setShowOptionsList(true);
        setSearchBoxText('');
    }

    const handleBlur = e => {
        if (!e.currentTarget.contains(e.relatedTarget)) {
            setShowOptionsList(false);
            setSearchBoxText(getNameFromID(selectedChoice));
        }
    }

    const handleSelect = e => {
        setShowOptionsList(false);
        let id = Number(e.target.getAttribute('data-id'));
        if (id === -1) id = null;
        setSearchBoxText(getNameFromID(id));
        onChange(name, id);
    }

    return (
        <div className="Dropdown" onBlur={handleBlur}>
            <label htmlFor={name} className={hideLabel ? 'visuallyhidden' : ''}>{labelText}</label>
            <div className="dropdownArrow">&#9660;</div>
            <input type="text"
                name={name}
                id={name}
                value={searchBoxText ?? ''}
                onChange={handleSearch}
                onFocus={handleInputFocus}
                required={required}
                disabled={disabled}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
            {showOptionsList && <div className="optionsList">
                {hideNoneOption || <div tabIndex={0} data-id='-1' onClick={handleSelect}>{noneOptionText ?? t("dropdown.none")}</div>}
                {filteredChoices.map(({id, name}, idx) => (
                    <div key={id} tabIndex={idx + 1} data-id={id} onClick={handleSelect}>{name}</div>
                ))}
            </div>}
        </div>
    );
}

Dropdown.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    hideLabel: PropTypes.bool,
    noneOptionText: PropTypes.string,
    hideNoneOption: PropTypes.bool,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    selectedChoice: PropTypes.number,
    choices: PropTypes.array,
    errorMessage: PropTypes.string,
    onChange: PropTypes.func,
}

Dropdown.defaultProps = {
    name: '',
    labelText: '',
    hideLabel: false,
    noneOptionText: null,
    hideNoneOption: false,
    required: false,
    disabled: false,
    selectedChoice: null,
    choices: [],
    errorMessage: null,
    onChange: null,
}

export default Dropdown;
