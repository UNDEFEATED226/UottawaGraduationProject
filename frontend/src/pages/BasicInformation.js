import './BasicInformation.css'
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const BasicInfomation = () => {

    const { t, i18n } = useTranslation();

    const [info, setInfo] = useState({});
    const [faculties, setFaculties] = useState([]);

    const handleFieldChange = (id, value) => {
        setInfo({ ...info, [id]: value });
    }

    // FETCHING

    async function fetchInfo(id) {
        const response = await fetch(`/api/main_members/find_by_id?id=${id}`);
        const body = await response.json();
        setInfo(body);
    }

    async function fetchFaculties() {
        const response = await fetch('/api/types_faculty/find_all');
        const body = await response.json();
        setFaculties(body);
    }

    useEffect(() => {
        fetchInfo(2);
        fetchFaculties();
    }, [])

    return (
        <div className="BasicInformation">
            <h2>{t('page_titles.basic_information')}</h2>
            <form>
                <div className='fields'>
                    <Textbox
                        name='firstName'
                        labelText={t('basic_information.name_first')}
                        text={info.firstName}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='lastName'
                        labelText={t('basic_information.name_last')}
                        text={info.lastName}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='address'
                        labelText={t('basic_information.address')}
                        text={info.address}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='city'
                        labelText={t('basic_information.city')}
                        text={info.city}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='provinceState'
                        labelText={t('basic_information.province_state')}
                        text={info.province}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='country'
                        labelText={t('basic_information.country')}
                        text={info.country}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='postalCode'
                        labelText={t('basic_information.postal_code')}
                        text={info.postalCode}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='email'
                        labelText={t('basic_information.email')}
                        text={info.email}
                        required={true}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='mobilePhone'
                        labelText={t('basic_information.phone_mobile')}
                        text={info.mobilePhone}
                        onChange={handleFieldChange}/>
                    <Textbox
                        name='businessPhone'
                        labelText={t('basic_information.phone_business')}
                        text={info.businessPhone}
                        onChange={handleFieldChange}/>
                    <Dropdown
                        name='faculty'
                        labelText={t('basic_information.faculty')}
                        selected={String(info.faculty)}
                        choices={faculties.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr}))}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='howHelp'
                        labelText={t('basic_information.how_help')}
                        text={info.howCanWeHelp}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='keywordsEn'
                        labelText={t('basic_information.keyword_en')}
                        text={info.keywordsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='keywordsFr'
                        labelText={t('basic_information.keyword_fr')}
                        text={info.keywordsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='problemsEn'
                        labelText={t('basic_information.problem_en')}
                        text={info.problemsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='problemsFr'
                        labelText={t('basic_information.problem_fr')}
                        text={info.problemsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='notes'
                        labelText={t('basic_information.notes')}
                        text={info.notes}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} />
                    <Button text={t('button.cancel')} type={2} />
                </div>
            </form>
        </div>
    );
}

export default BasicInfomation;
