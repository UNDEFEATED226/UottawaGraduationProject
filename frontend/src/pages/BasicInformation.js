import './FormPage.css';
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

    const [errors, setErrors] = useState({}); // Holds errors

    const handleFieldChange = (id, value) => {
        setInfo({ ...info, [id]: value });
    }

    const handleSubmit = (event) => {
        if (event) {
            event.preventDefault();
        }
        
        handleValidation();

        if(Object.keys(errors).length === 0 && Object.keys(info).length !==0 ){
            // DO SOMETHING ON SUBMIT
        }
    }

    const handleValidation = () => {

        let newErrors = {};

        if (!info.firstName || info.firstName.length === 0) {
            newErrors.firstName = 'First name cannot be empty.';
        } else {
            newErrors.firstName = undefined;
        }
        
        if (!info.lastName || info.lastName.length === 0) {
            newErrors.lastName = 'Last name cannot be empty.';
        } else {
            newErrors.lastName = undefined;
        }
        
        if (!info.city || info.city.length === 0) { 
            newErrors.city = 'City cannot be empty.';
        } else {
            newErrors.city = undefined;
        }
            
        if (!info.province || info.province.length === 0) {
            newErrors.province = 'Province cannot be empty.';
        } else {
            newErrors.province = undefined;
        }
        
        if (!info.country || info.country.length === 0) {
            newErrors.country = 'Country cannot be empty.';
        } else {
            newErrors.country = undefined;
        }
            
        if (!info.email || info.email.length === 0) {
            newErrors.email = 'Email cannot be empty.';
        } else if (!new RegExp( /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(info.email)) {
            newErrors.email = 'Email format is invalid.';
        } else {
            newErrors.email = undefined;
        }

        setErrors(newErrors);
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
        <div className="BasicInformation FormPage">
            <h2>{t('page_titles.basic_information')}</h2>
            <form onSubmit={ (e) => handleSubmit(e)}>
                <div className='fields'>
                    <div>
                        <Textbox
                            name='firstName'
                            labelText={t('basic_information.name_first')}
                            text={info.firstName}
                            // required={true}
                            onChange={handleFieldChange} />
                        <span style={{ color: "red" }}>{errors.firstName}</span>
                    </div>
                    
                    <div>
                        <Textbox
                            name='lastName'
                            labelText={t('basic_information.name_last')}
                            text={info.lastName}
                            //required={true}
                            onChange={handleFieldChange} />
                        <span style={{ color: "red" }}>{errors.lastName}</span>
                    </div>
                    
                    <Textbox
                        name='address'
                        labelText={t('basic_information.address')}
                        text={info.address}
                        onChange={handleFieldChange} />

                    <div>
                        <Textbox
                            name='city'
                            labelText={t('basic_information.city')}
                            text={info.city}
                            //required={true}
                            onChange={handleFieldChange} />
                        <span style={{ color: "red" }}>{errors.city}</span>
                    </div>

                    <div>
                        <Textbox
                            name='province'
                            labelText={t('basic_information.province_state')}
                            text={info.province}
                            //required={true}
                            onChange={handleFieldChange}/>
                        <span style={{ color: "red" }}>{errors.province}</span>
                    </div>
                    
                    <div>
                        <Textbox
                            name='country'
                            labelText={t('basic_information.country')}
                            text={info.country}
                            //required={true}
                            onChange={handleFieldChange}/>
                        <span style={{ color: "red" }}>{errors.country}</span>
                    </div>
                    
                    <Textbox
                        name='postalCode'
                        labelText={t('basic_information.postal_code')}
                        text={info.postalCode}
                        onChange={handleFieldChange}/>

                    <div>
                        <Textbox
                            name='email'
                            labelText={t('basic_information.email')}
                            text={info.email}
                            //required={true}
                            onChange={handleFieldChange}/>
                        <span style={{ color: "red" }}>{errors.email}</span>
                    </div>
                    
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
                        selectedChoice={info.faculty}
                        choices={faculties.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr}))}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='howCanWeHelp'
                        labelText={t('basic_information.how_help')}
                        text={info.howCanWeHelp}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='keywordsEN'
                        labelText={t('basic_information.keyword_en')}
                        text={info.keywordsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='keywordsFR'
                        labelText={t('basic_information.keyword_fr')}
                        text={info.keywordsFR}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='problemsEN'
                        labelText={t('basic_information.problem_en')}
                        text={info.problemsEN}
                        rows={10}
                        cols={30}
                        onChange={handleFieldChange}/>
                    <Textarea
                        name='problemsFR'
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
