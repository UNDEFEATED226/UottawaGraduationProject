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
                        logo_alt: 'LIFE Research Institute',
                        logo_alt_uo: 'University of Ottawa'
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
                    error: {
                        empty_city: 'City cannot be empty.',
                        empty_country: 'Country cannot be empty.',
                        empty_email: 'Email cannot be empty.',
                        empty_name_first: 'First name cannot be empty.',
                        empty_name_last: 'Last name cannot be empty.',
                        empty_province: 'Province cannot be empty.',
                        invalid_format_email: 'Email format is invalid.',
                        invalid_submit: 'There are errors in the form.',  
                        unable_fetch_member_names: 'Unable to fetch members\' names',
                        unable_fetch_partners: 'Unable to fetch partners',
                        unable_fetch_type_event: 'Unable to fetch event types',
                        unable_fetch_type_faculty: 'Fetching faculties list failed!',
                        unable_fetch_type_grant_source: 'Fetching grant source list failed!',
                        unable_fetch_type_partner: 'Fetching partner type list failed!',
                        unable_fetch_type_partner_scope: 'Fetching partner scope type list failed!',
                        unable_fetch_type_product: 'Fetching product type list failed!',
                        unable_fetch_type_stakeholders: 'Fetching target stakeholder list failed!',
                        unable_fetch_type_topic: 'Fetching topic list failed!',
                        unable_fetch_type_trainee_level: 'Fetching trainee level list failed!',
                        unable_fetch_user_event: 'Fetching your events failed!',
                        unable_fetch_user_info: 'Fetching your info failed!',
                        unable_post_user_info: 'Submitting your info failed!',
                        unable_fetch_user_product: 'Fetching your products failed!',
                        unable_fetch_user_supervision: 'Fetching your supervisions failed!',               
                    },
                    feedback: {
                        submitting: 'Submitting...',
                        submit_success: 'Submitted successfully!',
                    },
                    prompt: {
                        cancel_unsaved: 'Are you sure? Any unsaved changes will be lost.',
                    },
                    page_titles: {
                        home_page: 'Welcome',
                        basic_information: 'Basic Information',
                        my_products: 'My Products',
                        edit_product: 'Edit Product',
                        my_grants: 'My Grants',
                        edit_grant: 'Edit Grant',
                        my_events: 'My Events',
                        edit_event: 'Edit Event',
                        my_supervisions: 'My Supervisions',
                        edit_supervision: 'Edit Supervision',
                        partners: 'Partners',
                        edit_partner: 'Edit Partner'
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
                    product: {
                        column_title: 'Product Title',
                        column_date: 'Date',
                        title: 'Title:',
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
                    },
                    grant: {
                        column_title: 'Grant Title',
                        column_submission_date: 'Submission Date',
                        title: 'Title:',
                        amount: 'Amount:',
                        through_lri: 'Through LRI:',
                        status: 'Status:',
                        submission_date: 'Submission date:',
                        obtained_date: 'Obtained date:',
                        completed_date: 'Completed date:',
                        topics: 'Topics:',
                        notes: 'Notes:',
                        source: 'Source:',
                        investigators_all: 'Investigators (all):',
                        investigators_members: 'Investigators (members):',
                        member_involved: 'Members involved:'
                    },
                    event: {
                        column_name_en: 'English Name',
                        column_name_fr: 'French Name',
                        column_start_date: 'Start Date',
                        column_end_date: 'End Date',
                        name_en: 'Name (EN):',
                        name_fr: 'Name (FR):',
                        start_date: 'Start date:',
                        end_date: 'End date:',
                        type: 'Type of Event:',
                        topics: 'Topics:',
                        products_resulted: 'Product resulted:',
                        partners_involved: 'Partners involved:',
                        grants_resulted: 'Grants resulted:',
                        past_events: 'Past events:',
                        future_events: 'Future events:',
                        members_involved: 'Members involved:',
                        notes: 'Notes:'

                    },
                    partner: {
                        column_name: 'Name',
                        column_type: 'Type',
                        column_scope: 'Scope',
                        name: 'Name:',
                        type: 'Type:',
                        scope: 'Scope:',
                        members_involved: 'Members involved:',
                        notes: 'Notes:'
                    },
                    supervision: {
                        column_name_first: 'Trainee First Name',
                        column_name_last: 'Trainee Last Name',
                        column_start_date: 'Start Date',
                        column_level: 'Level',
                        trainee: 'Trainee:',
                        name_first: 'First name:',
                        name_last: 'Last name:',
                        start_date: 'Start date:',
                        end_date: 'End date:',
                        level: 'Level:',
                        faculty: 'Faculty:',
                        supervisor_principal: 'Principal Supervisor:',
                        supervisor_co: 'Co-Supervisor:',
                        thesis_advisory_committee: 'Thesis Advisory Committee',
                        notes: 'Notes'
                    }
                }
            },
            fr: {
                translation: {
                    // place french words
                    header: {
                        title: 'Informations sur le compte des membres',
                        subtitle: 'Profil',
                        logo_alt: 'Institut de recherche LIFE',
                        logo_alt_uo: 'Université d\'Ottawa'
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
                    error: {
                        and: 'et',
                        cannot_be_empty: 'ne peut être vide.',
                        is_invalid: 'n\'est pas valide.',
                        one_mandatory: 'Un champ doit être rempli.',
                    },
                    page_titles: {
                        home_page: 'Bienvenue',
                        basic_information: 'Informations de base',
                        my_products: 'Mes produits',
                        edit_product: 'Editer le produit',
                        my_grants: 'Mes subventions',
                        edit_grant: 'Editer la subvention',
                        my_events: 'Mes événements',
                        edit_event: 'Editer l\'événement',
                        my_supervision: 'Mes supervisions',
                        edit_supervision: 'Supervision de l\'édition',
                        partners: 'Partenaires',
                        edit_partner: 'Partenaire d\'édition'
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
                    product: {
                        column_title: 'Titre du produit',
                        column_date: 'Date',
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
                    },
                    grant: {
                        column_title: 'Titre de la subvention',
                        column_submission_date: 'Date de soumission',
                        title: 'Titre :',
                        amount: 'Montant :',
                        through_lri: 'Par le biais de IRL :',
                        status: 'Statut :',
                        submission_date: 'Date de soumission :',
                        obtained_date: 'Date d\'obtention :',
                        completed_date: 'Date d\'achèvement :',
                        topics: 'Sujets :',
                        notes: 'Notes :',
                        source: 'Source :',
                        investigators_all: 'Investigateurs (tous) :',
                        investigators_members: 'Investigateurs (membres) :',
                        member_involved: 'Membres impliqués :'
                    },
                    event: {
                        column_name_en: 'Nom anglais',
                        column_name_fr: 'Nom français',
                        column_start_date: 'Date de début',
                        column_end_date: 'Date de fin',
                        name_en: 'Nom (EN):',
                        name_fr: 'Nom (FR):',
                        start_date: 'Date de début :',
                        end_date: 'Date de fin :',
                        type: 'Type d\'événement :',
                        topics: 'Sujets :',
                        products_resulted: 'Résultat du produit :',
                        partners_involved: 'Partenaires impliqués :',
                        grants_resulted: 'Des subventions ont été accordées :',
                        past_events: 'Événements passés :',
                        future_events: 'Événements futurs :',
                        members_involved: 'Membres impliqués :',
                        notes: 'Notes :'
                    },
                    partner: {
                        column_name: 'Nom',
                        column_type: 'Type',
                        column_scope: 'Portée',
                        name: 'Nom :',
                        type: 'Type :',
                        scope: 'Portée :',
                        members_involved: 'Membres impliqués :',
                        notes: 'Notes :'
                    },
                    supervision: {
                        column_name_first: 'Prénom du stagiaire',
                        column_name_last: 'Nom de famille du stagiaire',
                        column_start_date: 'Date de début',
                        column_level: 'Niveau',
                        trainee: 'Stagiaire :',
                        name_first: 'Prénom :',
                        name_last: 'Nom de famille :',
                        start_date: 'Date de début :',
                        end_date: 'Date de fin :',
                        level: 'Niveau :',
                        faculty: 'La faculté :',
                        supervisor_principal: 'Superviseur principal :',
                        supervisor_co: 'Co-superviseur :',
                        thesis_advisory_committee: 'Comité consultatif de thèse :',
                        notes: 'Notes :'
                    }
                }
            }
        }
    })