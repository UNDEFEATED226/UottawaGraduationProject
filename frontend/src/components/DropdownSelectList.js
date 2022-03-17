import "./DropdownSelectList.css"
import Dropdown from "components/Dropdown";
import { useCallback, useEffect, useState } from "react";

const DropdownSelectList = ({name, labelText, noneOptionText, choices, selectedChoices}) => {

    const [selectedList, setSelectedList] = useState([]);

    const sortListByName = useCallback(list => {
        return [...list].sort((a, b) => {
            let nameA = choices.find(e => e.id === a).name;
            let nameB = choices.find(e => e.id === b).name;
            return nameA > nameB ? 1 : -1;
        })
    }, [choices]);

    useEffect(() => {
        setSelectedList(sortListByName(selectedChoices));
    }, [sortListByName, selectedChoices]);
    
    const handleDropdownSelect = (_, id) => {
        if (selectedList.includes(id)) return;
        setSelectedList(sortListByName([ ...selectedList, id ]));
    }

    const removeItem = id => {
        let filteredList = selectedList.filter(e => e !== id);
        setSelectedList(filteredList);
    }

    return (
        <div className="DropdownSelectList">
            <label htmlFor={name}>{labelText}</label>
            <div className="selectBox">
                <div className="selectedItems">
                    {selectedList.map(id => (
                        <div className="selectedItem" key={id}>
                            {choices.find(e => e.id === id).name}
                            <button onClick={() => removeItem(id)}>Remove</button>
                        </div>
                    ))}
                </div>
                <Dropdown 
                    name={name} 
                    labelText={labelText} 
                    noneOptionText={noneOptionText}
                    hideLabel 
                    selected=''
                    choices={choices.filter(e => !selectedList.includes(e.id))}
                    onChange={handleDropdownSelect}/>
            </div>
        </div>
    );
}
 
export default DropdownSelectList;
