import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date from 'components/Date';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditSupervision = ({userId}) => {

    const { t, i18n } = useTranslation();
    const { supervisionId } = useParams();
    
    const [supervision, setSupervision] = useState({});
    const [relPrincipalSup, setRelPrincipalSup] = useState([]); // Principal Supervisors
    const [relCoSup, setRelCoSup] = useState([]); // Co-supervisors
    const [relThesisAdvisory, setRelThesisAdvisory] = useState([]); // Product's target stakeholders

    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [levels, setLevels] = useState([]); // Dropdown result - all level types
    const [faculties, setFaculties] = useState([]); // Dropdown result - all faculties
    
    const handleFieldChange = (id, value) => {
        setSupervision({ ...supervision, [id]: value });
    }

    // Get data about specific supervision
    async function fetchSupervision(id) {
        const response = await fetch(`/api/main_supervision/find_by_id?id=${id}`);
        const body = await response.json();
        setSupervision(body);
    }

    // ADD RELATIONSHIP QUERIES TO FETCH DATA - NEED PRINCIPAL SUPERVISORS, CO-SUPERVISORS, THESIS ADVISORY COMMITTEE
    
    // Get all faculties for dropdown
    async function fetchFaculties() {
        const response = await fetch('/api/types_faculty/find_all');
        const body = await response.json();
        setFaculties(body);
    }

    // Get all levels for dropdown
    async function fetchLevels() {
        const response = await fetch('/api/types_trainee_level/find_all');
        const body = await response.json();
        setLevels(body);
    }

    // Get all members for dropdown
    async function fetchMembers() {
        const response = await fetch('/api/main_members/find_all');
        const body = await response.json();
        setMembers(body);
    }

    useEffect(() => {
        fetchSupervision(supervisionId);

        fetchFaculties();
        fetchLevels();
        fetchMembers();
    }, [])

    // Example: {"id":23,"trainee":null,"lastName":"Chen","firstName":"Jun","level":3,"faculty":3,"startDate":"2021-05-03","endDate":"2021-08-27","notes":null}

    return ( 
        <div className="EditSupervision FormPage">
            <h2>{t('page_titles.edit_supervision')}</h2>
            <form>
                <div className='fields'>
                    <Dropdown
                        name='trainee'
                        labelText={t('supervision.trainee')}
                        selectedChoice={supervision.trainee}
                        choices={members.map(e => ({id: e.id, name: `${e.firstName} ${e.lastName}`}))}
                        onChange={handleFieldChange}/>

                    <Textbox name='firstName' 
                        labelText={t('supervision.name_first')} 
                        text={supervision.firstName} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Textbox name='lastName' 
                        labelText={t('supervision.name_last')} 
                        text={supervision.lastName} 
                        required={true}
                        onChange={handleFieldChange}/>  

                    <Date name='startDate' labelText={t('supervision.start_date')} textValue={supervision.startDate} onChange={handleFieldChange} />
                    <Date name='endDate' labelText={t('supervision.end_date')} textValue={supervision.startDate} onChange={handleFieldChange} />

                    <Dropdown
                        name='level'
                        labelText={t('supervision.level')}
                        selectedChoice={supervision.level}
                        choices={levels.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.levelEn : e.levelFr}))}
                        onChange={handleFieldChange}/>
                    
                    <Dropdown
                        name='type'
                        labelText={t('supervision.faculty')}
                        selectedChoice={supervision.faculty}
                        choices={faculties.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr}))}
                        onChange={handleFieldChange}/>

                    {/* NEED MULTI-SELECT DROPDOWNS: PRINCIPAL SUPERVISORS, CO-SUPERVISORS, THESIS ADVISORY COMMITTEE */}

                    <Textarea name='notes' 
                        labelText={t('supervision.notes')} 
                        text={supervision.notes} 
                        rows={10} cols={30}
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
 
export default EditSupervision;