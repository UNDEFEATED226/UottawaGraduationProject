import './Header.css';
import Logo from '../assets/life-logo-placeholder.jpg';
import Button from './Button';
import LanguageButton from './LanguageButton';
import { useTranslation } from 'react-i18next';

// add FR/EN button
// learn to pass name through props
const Header = ({ username }) => {

    const { t } = useTranslation();

    return (
        <header className='Header'>
            <div className='container'>
                <div className='titles'>
                    <h1 className='title'>{t('header.title')}</h1>
                    <h2 className='userName'>{t('header.subtitle')}: {username}</h2>
                </div>
                <div className='buttons'>
                    <LanguageButton />
                    <Button text={t('button.sign_out')} />
                </div>
                <div className='logo'>
                    <img src={Logo} alt={t('header.logo_alt')}/>
                </div>
            </div>
            <div className='separator'></div>
        </header>
    );
}

export default Header;