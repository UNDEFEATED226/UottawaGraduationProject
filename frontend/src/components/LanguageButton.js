import { useTranslation } from 'react-i18next';
import Button from './Button';

// Source of inspiration: https://dev.to/adrai/how-to-properly-internationalize-a-react-application-using-i18next-3hdb 
const LanguageButton = () => {
    const { i18n } = useTranslation();

    // application only supports English and French
    const languages = {
        en: { nativeName: 'English' },
        fr: { nativeName: 'Français' }
    };

    const getOtherLanguage = () => {
        var keys = Object.keys(languages)
        var lng = i18n.resolvedLanguage === keys[0]? keys[1] : keys[0]
        return lng
    };
    
    return ( 
        <Button text={languages[getOtherLanguage()].nativeName} clickHandler={() => i18n.changeLanguage(getOtherLanguage())}/>
    );
}
 
export default LanguageButton;