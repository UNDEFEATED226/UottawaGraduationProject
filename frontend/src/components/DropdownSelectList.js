import "./DropdownSelectList.css";
import Dropdown from "components/Dropdown";
import { useCallback, useEffect, useState } from "react";
import PropTypes from "prop-types";

const DropdownSelectList = ({name, labelText, dropdownLabel, choices, selectedChoices, errorMessage, onChange}) => {

    const [selectedList, setSelectedList] = useState([]);

    const sortListByName = useCallback(list => {
        return [...list].sort((a, b) => {
            let nameA = choices.find(e => e.id === a)?.name;
            let nameB = choices.find(e => e.id === b)?.name;
            return nameA > nameB ? 1 : -1;
        })
    }, [choices]);

    useEffect(() => {
        setSelectedList(sortListByName(selectedChoices ?? []));
    }, [sortListByName, selectedChoices]);

    const handleDropdownSelect = (_, id) => {
        if (selectedList.includes(id)) return;
        let newList = sortListByName([ ...selectedList, id ]);
        setSelectedList(newList);
        onChange(name, newList);
    }

    const removeItem = id => {
        let filteredList = selectedList.filter(e => e !== id);
        setSelectedList(filteredList);
        onChange(name, filteredList);
    }

    return (
        <div className="DropdownSelectList">
            <label htmlFor={name}>{labelText}</label>
            <div className="selectedItems">
                {selectedList.map(id => (
                    <div className="selectedItem" key={id}>
                        {choices.find(e => e.id === id)?.name ?? "Loading..."}
                        <button onClick={() => removeItem(id)}>Remove</button>
                    </div>
                ))}
            </div>
            <Dropdown
                name={name}
                labelText={labelText}
                hideLabel
                noneOptionText={dropdownLabel}
                hideNoneOption
                choices={choices.filter(e => !selectedList.includes(e.id))}
                onChange={handleDropdownSelect}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
        </div>
    );
}

DropdownSelectList.propTypes = {
    name: PropTypes.string,
    labelText: PropTypes.string,
    dropdownLabel: PropTypes.string,
    choices: PropTypes.array,
    selectedChoices: PropTypes.array,
    errorMessage: PropTypes.string,
    onChange: PropTypes.func,
}

DropdownSelectList.defaultProps = {
    name: '',
    labelText: '',
    dropdownLabel: null,
    choices: [],
    selectedChoices: [],
    errorMessage: null,
    onChange: null,
}

export default DropdownSelectList;
