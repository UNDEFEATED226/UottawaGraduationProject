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
                    <Textbox name='firstName' labelText={t('basic_information.name_first')} placeholderText={info.firstName} required={true}/>
                    <Textbox name='lastName' labelText={t('basic_information.name_last')} placeholderText={info.lastName} required={true}/>
                    <Textbox name='address' labelText={t('basic_information.address')} placeholderText={info.address}/>
                    <Textbox name='city' labelText={t('basic_information.city')} placeholderText={info.city} required={true}/>
                    <Textbox name='provinceState' labelText={t('basic_information.province_state')} placeholderText={info.province} required={true}/>
                    <Textbox name='country' labelText={t('basic_information.country')} placeholderText={info.country} required={true}/>
                    <Textbox name='postalCode' labelText={t('basic_information.postal_code')} placeholderText={info.postalCode}/>
                    <Textbox name='email' labelText={t('basic_information.email')} placeholderText={info.email} required={true}/>
                    <Textbox name='mobilePhone' labelText={t('basic_information.phone_mobile')} placeholderText={info.mobilePhone}/>
                    <Textbox name='businessPhone' labelText={t('basic_information.phone_business')} placeholderText={info.businessPhone}/>
                    <Dropdown name='faculty' labelText={t('basic_information.faculty')} selectedChoice={String(info.faculty)} choices={
                        faculties.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr}))
                    }/>
                    <Textarea name='howHelp' labelText={t('basic_information.how_help')} placeholderText={info.howCanWeHelp} rows={10} cols={30}/>
                    <Textarea name='keywordsEn' labelText={t('basic_information.keyword_en')} placeholderText={info.keywordsEN} rows={10} cols={30}/>
                    <Textarea name='keywordsFr' labelText={t('basic_information.keyword_fr')} placeholderText={info.keywordsFR} rows={10} cols={30}/>
                    <Textarea name='problemsEn' labelText={t('basic_information.problem_en')} placeholderText={info.problemsEN} rows={10} cols={30}/>
                    <Textarea name='problemsFr' labelText={t('basic_information.problem_fr')} placeholderText={info.problemsFR} rows={10} cols={30}/>
                    <Textarea name='notes' labelText={t('basic_information.notes')} placeholderText={info.notes} rows={10} cols={30}/>
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
