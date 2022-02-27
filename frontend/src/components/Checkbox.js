import './Checkbox.css'

const Checkbox = ({name, disabled}) => {
    return (
        <div className='Checkbox'>
            <input type="checkbox" name={name} id={name} disabled={disabled} />
            <label htmlFor={name}>{name}</label>
        </div>
    );
}
 
export default Checkbox;