import PropTypes from "prop-types";
import { useEffect, useState } from "react";

const Date = ({name, labelText, textValue, required, disabled, onChange}) => {

    //https://tomduffytech.com/how-to-format-phone-number-in-react/
    const [inputValue, setInputValue] = useState('');
    const [valid, setValid] = useState(false);
    const [error, setError] = useState(false);

    const regex = /\d{4}-\d{2}-\d{2}/;

    const handleInput = (e) => {
        // format function
        const formattedDate = formatDate(e.target.value);
        setInputValue(formattedDate);

        onChange(name, e.target.value);
    }

    const formatDate = (value) => {
        if (!value) return value;

        const date = value.replace(/[^\d]/g, '');
        const dateLength = date.length;

        if(dateLength < 5) return date;

        if(dateLength < 7) {
            return `${date.slice(0,4)}-${date.slice(4)}`;
        }

        return `${date.slice(0,4)}-${date.slice(4,6)}-${date.slice(6,8)}`
    };

    const validateDate = (value) => {
        if (value.match(regex) && value.split('-').length === 3) {
            const arrDate = value.split('-');
            const year = parseInt(arrDate[0]);
            const month = parseInt(arrDate[1]);
            const day = parseInt(arrDate[2]);
            if (month >= 1 && month <= 12){
                var maxDay;
                if (month === 2){
                    maxDay = (year % 4 === 0) ? 29 : 28;
                }
                else if (month === 4 || month === 6 || month === 9 || month === 11){
                    maxDay = 30;
                }
                else if (month === 1 || month === 3 || month === 5 || month === 7 || month === 8 || month === 10 || month === 12){
                    maxDay = 31;
                }

                if (day >= 1 && day <= maxDay){
                    setValid(true);
                    return;
                } else {
                    setError('day');
                }
            } else {
                setError('month');
            }
        } else {
            setError('format');
        }
        setValid(false);
    };

    useEffect(() => {
        setInputValue(textValue);
    }, [textValue])

    // useEffect(() => {

    // },[error])

    return (  
        <div className="Date">
            <label htmlFor={name}>{labelText}</label>
            <input
                type="text" 
                id={name} 
                name={name} 
                defaultValue={inputValue} 
                pattern={regex} 
                onBlur={(e) => validateDate(e.target.value)}
                required={required} 
                disabled={disabled}
                onChange={(e) => handleInput(e)}
            />
        </div>
    );
}

Date.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    textValue: PropTypes.string,
    required: PropTypes.bool,
    disabled: PropTypes.bool,
    onChange: PropTypes.func
}
 
export default Date;