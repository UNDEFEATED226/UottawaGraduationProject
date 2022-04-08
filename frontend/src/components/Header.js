import './Header.css';
import lifeLogo from 'assets/life-logo-g.svg';
import uoLogo from 'assets/university-of-ottawa-logo.png';
import Button from 'components/Button';
import LanguageButton from 'components/LanguageButton';
import { useTranslation } from 'react-i18next';
import { useNavigate } from 'react-router-dom';

// add FR/EN button
// learn to pass name through props
const Header = ({ username }) => {

    const { t } = useTranslation();
    const navigate = useNavigate();

    async function logout() {
        const response = await fetch('/api/logout');
        if (response.ok) {
            navigate('/');
        }
        else {
            console.error('Failed to logout.');
        }
    }

    return (
        <header className='Header'>
            <div className='container'>
                <div className='titles'>
                    <h1 className='title'>{t('header.title')}</h1>
                    <h2 className='userName'>{t('header.subtitle')}: {username}</h2>
                </div>
                <div className='buttons'>
                    <LanguageButton />
                    <Button
                        text={t('button.sign_out')}
                        clickHandler={logout}
                    />
                </div>
                <div className='logo'>
                    <img src={lifeLogo} alt={t('header.logo_alt')}/>
                    <img src={uoLogo} alt={t('header.logo_alt')}/>
                </div>
            </div>
            <div className='separator'></div>
        </header>
    );
}

export default Header;