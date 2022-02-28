import './BasicInformation.css'
import Checkbox from 'components/Checkbox';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

const BasicInfomation = () => {
    return (
        <div className="BasicInformation">
            <h2>Basic Information</h2>
            <form>
                <div className='fields'>
                    <Textbox name='firstName' labelText='First Name:' required={true}/>
                    <Textbox name='lastName' labelText='Last Name:' required={true}/>
                    <Textbox name='address' labelText='Address:'/>
                    <Textbox name='city' labelText='City:' required={true}/>
                    <Textbox name='provinceState' labelText='Province/State:' required={true}/>
                    <Textbox name='country' labelText='Country:' required={true}/>
                    <Textbox name='postalCode' labelText='Postal Code:'/>
                    <Textbox name='email' labelText='Email:' required={true}/>
                    <Textbox name='mobilePhone' labelText='Mobile Phone Number:'/>
                    <Textbox name='businessPhone' labelText='Business Phone Number:'/>
                    <Dropdown name='faculty' labelText='Faculty:' selectedChoice='' choices={[
                        {ID: 1, name: 'Engineering'},
                        {ID: 2, name: 'Arts'}
                    ]}/>
                    <Textarea name='howHelp' labelText='How can we help?' row='10' cols='30'/>
                    <Textarea name='notes' labelText='Notes' rows='10' cols='30'/>
                    <Textarea name='keywordsEn' labelText='Keywords (EN):' rows='10' cols='30'/>
                    <Textarea name='keywordsFr' labelText='Keywords (FR):' rows='10' cols='30'/>
                    <Textarea name='problemsEn' labelText='Problems (EN):' rows='10' cols='30'/>
                    <Textarea name='problemsFr' labelText='Problems (FR):' rows='10' cols='30'/>
                </div>
                <div className='buttons'>
                    <Button text='Submit' type={1} />
                    <Button text='Cancel' type={2} />
                </div>
            </form>
        </div>
    );
}
 
export default BasicInfomation;
