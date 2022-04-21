import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import enTranslations from 'locales/en.json';
import frTranslations from 'locales/fr.json';

// tutorial: https://dev.to/adrai/how-to-properly-internationalize-a-react-application-using-i18next-3hdb
// documentation for packages: https://www.i18next.com/overview/getting-started
i18n
    .use(LanguageDetector) // detect user language
    .use(initReactI18next) // pass the i18n instance to react-i18next
    .init({ // init i18next
        debug: true,
        fallbackLng: 'en',
        resources: {
            en: {
                translation: enTranslations
            },
            fr: {
                translation: frTranslations
            }
        }
    })