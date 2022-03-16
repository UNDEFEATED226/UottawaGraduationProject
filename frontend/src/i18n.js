import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

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
                translation: {
                    // place english words
                    header: {
                        title: 'Member Account Information',
                        subtitle: 'Profile',
                        logo_alt: 'LIFE Research Institute'
                    },
                    button: {
                        edit: 'Edit',
                        submit: 'Submit',
                        save: 'Save',
                        cancel: 'Cancel',
                        language: 'Language',
                        sign_out: 'Sign Out',
                        collapse: 'Collapse'
                    },
                    dropdown: {
                        none: 'None'
                    },
                    page_titles: {
                        basic_information: 'Basic Information',
                        my_products: 'My Products',
                        edit_product: 'Edit Product',
                        my_grants: 'My Grants',
                        my_events: 'My Events',
                        my_supervision: 'My Supervisions',
                        partners: 'Partners'
                    },
                    basic_information: {
                        name_first: 'First Name:',
                        name_last: 'Last Name:',
                        address: 'Address:',
                        city: 'City:',
                        province_state: 'Province/State:',
                        country: 'Country:',
                        postal_code: 'Postal Code:',
                        email: 'Email:',
                        phone_mobile: 'Mobile Phone Number:',
                        phone_business: 'Business Phone Number:',
                        faculty: 'Faculty:',
                        how_help: 'How can the institute help?',
                        keyword_en: 'Keywords (EN):',
                        keyword_fr: 'Keywords (FR):',
                        problem_en: 'Problems (EN):',
                        problem_fr: 'Problems (FR):',
                        notes: 'Notes:'
                    },
                    edit_product: {
                        title: 'Title',
                        date: 'Date:',
                        type: 'Type:',
                        ongoing: 'On going',
                        peer_review: 'Peer reviewed',
                        doi: 'DOI:',
                        all_authors: 'All Authors:',
                        member_authors: 'Member Authors',
                        partners: 'Partners:',
                        topics: 'Topics:',
                        target_stakeholders: 'Target Stakeholders:',
                        notes: 'Notes:'
                    }
                }
            },
            fr: {
                translation: {
                    // place french words
                    header: {
                        title: 'Informations sur le compte des membres',
                        subtitle: 'Profil',
                        logo_alt: 'Institut de recherche LIFE'
                    },
                    button: {
                        edit: 'Éditer',
                        submit: 'Soumettre',
                        save: 'Sauvegarder',
                        cancel: 'Annuler',
                        language: 'Langue',
                        sign_out: 'Se déconnecter',
                        collapse: 'Effondrement'
                    },
                    dropdown: {
                        none: 'Aucune'
                    },
                    page_titles: {
                        basic_information: 'Informations de base',
                        my_products: 'Mes produits',
                        edit_product: 'Editer le produit',
                        my_grants: 'Mes subventions',
                        my_events: 'Mes événements',
                        my_supervision: 'Mes supervisions',
                        partners: 'Partenaires'
                    },
                    basic_information: {
                        name_first: 'Prénom :',
                        name_last: 'Nom de famille :',
                        address: 'Adresse :',
                        city: 'Ville :',
                        province_state: 'Province/État :',
                        country: 'Pays :',
                        postal_code: 'Code postal :',
                        email: 'Courriel :',
                        phone_mobile: 'Numéro de téléphone mobile :',
                        phone_business: 'Numéro de téléphone professionnel :',
                        faculty: 'Faculté :',
                        how_help: 'Comment l\'institut peut-il vous aider ?',
                        keyword_en: 'Mots clés (EN) :',
                        keyword_fr: 'Mots clés (FR) :',
                        problem_en: 'Problèmes (EN) :',
                        problem_fr: 'Problèmes (FR) :',
                        notes: 'Notes'
                    },
                    edit_product: {
                        title: 'Titre :',
                        date: 'Date :',
                        type: 'Type :',
                        ongoing: 'En cours',
                        peer_review: 'Révision par les pairs',
                        doi: 'Identificateur d\'objet numérique :',
                        all_authors: 'Tous les auteurs :',
                        member_authors: 'Auteurs membres :',
                        partners: 'Partenaires :',
                        topics: 'Sujets :',
                        target_stakeholders: 'Parties prenantes cibles :',
                        notes: 'Notes :'
                    }
                }
            }
        }
    })