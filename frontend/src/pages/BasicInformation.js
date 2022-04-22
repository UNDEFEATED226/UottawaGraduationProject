import './FormPage.css';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { getMember, updateMember } from 'api/members';
import { getFaculties } from 'api/types';

import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useOutletContext } from 'react-router-dom';

const emailRE = new RegExp(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);

const BasicInfomation = () => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();

    const [member, setMember] = useState({});
    const [faculties, setFaculties] = useState([]);

    const [errors, setErrors] = useState({});

    const fetchEverything = useCallback(async () => {
        const results = await Promise.all([
            getMember(),
            getFaculties(),
        ]);
        if (!results.includes(null)) {
            setMember(results[0]);
            setFaculties(results[1]);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [pushNotification, t])

    useEffect(() => {
        fetchEverything();
    }, [fetchEverything])

    const handleFieldChange = (id, value) => {
        setMember(curr => ({ ...curr, [id]: value }));
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            if (await updateMember(member)) {
                await fetchEverything();
                pushNotification('positive', t('feedback.submit_success'));
            }
            else {
                pushNotification('negative', t('error.unable_submit'));
            }
        }
        else {
            pushNotification('negative', t('error.invalid_submit'));
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!member.firstName) {
            newErrors.firstName = t('error.empty_name_first');
        }

        if (!member.lastName) {
            newErrors.lastName = t('error.empty_name_last');
        }

        if (!member.city) { 
            newErrors.city = t('error.empty_city');
        }

        if (!member.province) {
            newErrors.province = t('error.empty_province');
        }

        if (!member.country) {
            newErrors.country = t('error.empty_country');
        }
        
        if (!member.email) {
            newErrors.email = t('error.empty_email');
        }
        else if (!emailRE.test(member.email)) {
            newErrors.email = t('error.invalid_format_email');
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            window.scrollTo(0, 0);
            await fetchEverything();
            setErrors({});
            pushNotification('info', t('feedback.changes_reverted'));
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
                        text={member.firstName}
                        errorMessage={errors.firstName}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='lastName'
                        labelText={t('basic_information.name_last')}
                        text={member.lastName}
                        errorMessage={errors.lastName}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='address'
                        labelText={t('basic_information.address')}
                        text={member.address}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='city'
                        labelText={t('basic_information.city')}
                        text={member.city}
                        errorMessage={errors.city}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='province'
                        labelText={t('basic_information.province_state')}
                        text={member.province}
                        errorMessage={errors.province}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='country'
                        labelText={t('basic_information.country')}
                        text={member.country}
                        errorMessage={errors.country}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='postalCode'
                        labelText={t('basic_information.postal_code')}
                        text={member.postalCode}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='email'
                        labelText={t('basic_information.email')}
                        text={member.email}
                        errorMessage={errors.email}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='mobilePhone'
                        labelText={t('basic_information.phone_mobile')}
                        text={member.mobilePhone}
                        onChange={handleFieldChange}
                    />
                    <Textbox
                        name='businessPhone'
                        labelText={t('basic_information.phone_business')}
                        text={member.businessPhone}
                        onChange={handleFieldChange}
                    />
                    <Dropdown
                        name='faculty'
                        labelText={t('basic_information.faculty')}
                        selectedChoice={member.faculty}
                        choices={faculties.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='howCanWeHelp'
                        labelText={t('basic_information.how_help')}
                        text={member.howCanWeHelp}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='keywordsEN'
                        labelText={t('basic_information.keyword_en')}
                        text={member.keywordsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='keywordsFR'
                        labelText={t('basic_information.keyword_fr')}
                        text={member.keywordsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='problemsEN'
                        labelText={t('basic_information.problem_en')}
                        text={member.problemsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='problemsFR'
                        labelText={t('basic_information.problem_fr')}
                        text={member.problemsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}
                    />
                    <Textarea
                        name='notes'
                        labelText={t('basic_information.notes')}
                        text={member.notes}
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
