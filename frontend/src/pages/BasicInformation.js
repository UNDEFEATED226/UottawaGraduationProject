import './FormPage.css';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useOutletContext } from 'react-router-dom';

const emailRE = new RegExp(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

const BasicInfomation = () => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();

    const [info, setInfo] = useState({});
    const [faculties, setFaculties] = useState([]);
    const [errors, setErrors] = useState({});

    const handleFieldChange = (id, value) => {
        setInfo({ ...info, [id]: value });
    }

    async function updateInfo() {
        const response = await fetch('/api/main_members/update', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(info)
        });
        return response.ok;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', 'Submitting...');
            if (await updateInfo()) {
                pushNotification('positive', 'Submitted successfully!');
            }
            else {
                pushNotification('negative', 'Submission failed due to server issues! Please try again later.');
            }
        }
        else {
            pushNotification('negative', 'There are errors in the form.');
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!info.firstName || info.firstName.length === 0) {
            newErrors.firstName = 'First name cannot be empty.';
        }

        if (!info.lastName || info.lastName.length === 0) {
            newErrors.lastName = 'Last name cannot be empty.';
        }

        if (!info.city || info.city.length === 0) { 
            newErrors.city = 'City cannot be empty.';
        }

        if (!info.province || info.province.length === 0) {
            newErrors.province = 'Province cannot be empty.';
        }

        if (!info.country || info.country.length === 0) {
            newErrors.country = 'Country cannot be empty.';
        }
        
        if (!info.email || info.email.length === 0) {
            newErrors.email = 'Email cannot be empty.';
        }
        else if (!emailRE.test(info.email)) {
            newErrors.email = 'Email format is invalid.';
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    // FETCHING

    async function fetchInfo() {
        const response = await fetch('/api/main_members/find_by_id');
        if (response.ok) {
            const body = await response.json();
            setInfo(body);
        }
        return response.ok;
    }

    async function fetchFaculties() {
        const response = await fetch('/api/types_faculty/find_all');
        if (response.ok) {
            const body = await response.json();
            setFaculties(body);
        }
        return response.ok;
    }

    useEffect(() => {
        let fetchEverything = async () => {
            if (!await fetchInfo()) {
                pushNotification('negative', 'Fetching your info failed due to server issues!');
            }
            if (!await fetchFaculties()) {
                pushNotification('negative', 'Fetching faculties list failed due to server issues!');
            }
        }
        fetchEverything();
    }, [pushNotification])

    const handleCancel = () => {
        if (window.confirm('Are you sure? Any unsaved changes will be lost.')) {
            window.location.reload();
            window.scrollTo(0, 0);
        }
    }

    return (
        <div className="BasicInformation FormPage">
            <h2>{t('page_titles.basic_information')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox
                        name='firstName'
                        labelText={t('basic_information.name_first')}
                        text={info.firstName}
                        errorMessage={errors.firstName}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='lastName'
                        labelText={t('basic_information.name_last')}
                        text={info.lastName}
                        errorMessage={errors.lastName}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='address'
                        labelText={t('basic_information.address')}
                        text={info.address}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='city'
                        labelText={t('basic_information.city')}
                        text={info.city}
                        errorMessage={errors.city}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='province'
                        labelText={t('basic_information.province_state')}
                        text={info.province}
                        errorMessage={errors.province}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='country'
                        labelText={t('basic_information.country')}
                        text={info.country}
                        errorMessage={errors.country}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='postalCode'
                        labelText={t('basic_information.postal_code')}
                        text={info.postalCode}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='email'
                        labelText={t('basic_information.email')}
                        text={info.email}
                        errorMessage={errors.email}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='mobilePhone'
                        labelText={t('basic_information.phone_mobile')}
                        text={info.mobilePhone}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='businessPhone'
                        labelText={t('basic_information.phone_business')}
                        text={info.businessPhone}
                        onChange={handleFieldChange}
                    />
                    <Dropdown
                        name='faculty'
                        labelText={t('basic_information.faculty')}
                        selectedChoice={info.faculty}
                        choices={faculties.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr}))}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='howCanWeHelp'
                        labelText={t('basic_information.how_help')}
                        text={info.howCanWeHelp}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='keywordsEN'
                        labelText={t('basic_information.keyword_en')}
                        text={info.keywordsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='keywordsFR'
                        labelText={t('basic_information.keyword_fr')}
                        text={info.keywordsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='problemsEN'
                        labelText={t('basic_information.problem_en')}
                        text={info.problemsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='problemsFR'
                        labelText={t('basic_information.problem_fr')}
                        text={info.problemsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='notes'
                        labelText={t('basic_information.notes')}
                        text={info.notes}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit' />
                    <Button text={t('button.cancel')} type={2} clickHandler={handleCancel} />
                </div>
            </form>
        </div>
    );
}

export default BasicInfomation;
