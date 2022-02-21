import './Header.css';
import Logo from '../assets/life-logo-placeholder.jpg';
import Button from './Button';

// add FR/EN button
// learn to pass name through props
const Header = ({ username }) => {
    return (
        <header className='header'>
            <div className='left'>
                <h1 className='title'>Member Account Information</h1>
                <h2 className='userName'>Profile:{username}</h2>
            </div>
            <div className='right'>
                <div className='btn'>
                    <Button text="Language" />
                </div>
                <div className='btn'>
                    <Button text="Sign Out" />
                </div>
            </div>
            <div className='logo'>
                <img src={Logo} alt="LIFE Research Institute"/>
            </div>
        </header>
    );
}

export default Header;