import './Checkbox.css'

const Checkbox = (prop) => {
    return (
        <div className='Checkbox'>
            <input type="checkbox" name={prop.name} id={prop.name} disabled={prop.disabled} />
            <label htmlFor={prop.name}>{prop.name}</label>
        </div>
    );
}
 
export default Checkbox;