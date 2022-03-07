import './BasicInformation.css'
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const BasicInfomation = () => {

    const [faculties, setFaculties] = useState([]);
    const { t } = useTranslation();
    
    async function fetchFaculties() {
        const response = await fetch('/types_faculty/find_all');
        const body = await response.json();
        setFaculties(body);
    }

    useEffect(() => {
        fetchFaculties();
    }, [])

    return (
        <div className="BasicInformation">
            <h2>{t('page_titles.basic_information')}</h2>
            <form>
                <div className='fields'>
                    <Textbox name='firstName' labelText={t('basic_information.name_first')} required={true}/>
                    <Textbox name='lastName' labelText={t('basic_information.name_last')} required={true}/>
                    <Textbox name='address' labelText={t('basic_information.address')}/>
                    <Textbox name='city' labelText={t('basic_information.city')} required={true}/>
                    <Textbox name='provinceState' labelText={t('basic_information.province_state')} required={true}/>
                    <Textbox name='country' labelText={t('basic_information.country')} required={true}/>
                    <Textbox name='postalCode' labelText={t('basic_information.postal_code')}/>
                    <Textbox name='email' labelText={t('basic_information.email')} required={true}/>
                    <Textbox name='mobilePhone' labelText={t('basic_information.phone_mobile')}/>
                    <Textbox name='businessPhone' labelText={t('basic_information.phone_business')}/>
                    <Dropdown name='faculty' labelText={t('basic_information.faculty')} selectedChoice='' choices={
                        faculties.map(e => ({id: e.id, name: e.nameEn + ' / ' + e.nameFr}))
                    }/>
                    <Textarea name='howHelp' labelText={t('basic_information.how_help')} rows={10} cols={30}/>
                    <Textarea name='keywordsEn' labelText={t('basic_information.keyword_en')} rows={10} cols={30}/>
                    <Textarea name='keywordsFr' labelText={t('basic_information.keyword_fr')} rows={10} cols={30}/>
                    <Textarea name='problemsEn' labelText={t('basic_information.problem_en')} rows={10} cols={30}/>
                    <Textarea name='problemsFr' labelText={t('basic_information.problem_fr')} rows={10} cols={30}/>
                    <Textarea name='notes' labelText={t('basic_information.notes')} rows={10} cols={30}/>
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
