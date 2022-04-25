import './Date.css';
import PropTypes from "prop-types";
import { useEffect, useState } from "react";

/**
* Validates a date in ISO format.
* @param {string} value - Date string.
* @return {[boolean, string]} Array of a boolean and a string.
* Boolean is true if valid, string indicates what error was found
* ('empty', 'format', 'month', or 'day').
*/
export const validateDate = (value) => {
    if (!value) return [false, 'empty'];

    let dateRE = new RegExp(/^\d{4}-\d{2}-\d{2}$/);
    if (!dateRE.test(value)) return [false, 'format'];

    const splitDate = value.split('-');
    const year = parseInt(splitDate[0]);
    const month = parseInt(splitDate[1]);
    const day = parseInt(splitDate[2]);

    if (year < 1) return [false, 'year'];

    if (month < 1 || month > 12) return [false, 'month'];

    let maxDay;
    if (month === 2) {
        maxDay = (year % 4 === 0) ? 29 : 28;
    }
    else if ([4, 6, 9, 11].includes(month)) {
        maxDay = 30;
    }
    else if ([1, 3, 5, 7, 8, 10, 12].includes(month)) {
        maxDay = 31;
    }

    if (day < 1 || day > maxDay) return [false, 'day'];

    return [true, null]
};

/**
* Checks to see if the first date string is before or equal the second date string.
* Returns false if the first date is after the second date, or if the dates are invalid.
* @param {string} date1 - First ISO date string.
* @param {string} date2 - Second ISO date string.
* @return {boolean} Boolean is true if date1 is before or equal to date2.
*/
export const isNotAfter = (date1, date2) => {
    if (validateDate(date1)[0] && validateDate(date2)[0]) {
        const splitDate1 = date1.split('-');
        const val1 = parseInt(splitDate1[0] + splitDate1[1] + splitDate1[2]);
        const splitDate2 = date2.split('-');
        const val2 = parseInt(splitDate2[0] + splitDate2[1] + splitDate2[2]);
        return val1 <= val2;
    }
    return false;
};

const Date = ({name, labelText, textValue, disabled, errorMessage, onChange}) => {

    const [inputValue, setInputValue] = useState('');

    useEffect(() => {
        setInputValue(textValue);
    }, [textValue])

    const handleInput = (e) => {
        let formattedDate = formatDate(e.target.value);
        setInputValue(formattedDate);
        onChange(name, formattedDate);
    }

    const formatDate = (value) => {
        if (!value) return value;

        let date = value.replace(/[^\d]/g, '');

        if (date.length < 5) return date;
        if (date.length < 7) return `${date.slice(0,4)}-${date.slice(4)}`;
        return `${date.slice(0,4)}-${date.slice(4,6)}-${date.slice(6,8)}`;
    };

    return (
        <div className="Date">
            <label htmlFor={name}>{labelText}</label>
            <input
                type="text"
                id={name}
                name={name}
                value={inputValue ?? ''}
                disabled={disabled}
                onChange={handleInput}
            />
            {errorMessage && <span className='errorMsg'>{errorMessage}</span>}
        </div>
    );
}

Date.propTypes = {
    name: PropTypes.string.isRequired,
    labelText: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    textValue: PropTypes.string,
    disabled: PropTypes.bool,
    errorMessage: PropTypes.string    
}

export default Date;
