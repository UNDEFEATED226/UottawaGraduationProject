import './FormPage.css';
import Date from 'components/Date';
import { validateDate, isNotAfter } from 'components/Date';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditEvent = ({userId}) => {

    const { t, i18n } = useTranslation();
    const { eventId } = useParams();

    const [errors, setErrors] = useState({}); // Holds errors

    const [event, setEvent] = useState({});
    const [relPastEvents, setRelPastEvents] = useState([]); // Past events
    const [relFutEvents, setRelFutEvents] = useState([]); // Future events
    const [relGrants, setRelGrants] = useState([]); // Grants resulted
    const [relMembers, setRelMembers] = useState([]); // Members involved
    const [relProducts, setRelProducts] = useState([]); // Products resulted
    const [relPartners, setRelPartners] = useState([]); // Partners involved
    const [relTopics, setRelTopics] = useState([]); // Chosen Topics

    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [myEvents, setMyEvents] = useState([]); // Dropdown result - user's events
    const [myGrants, setMyGrants] = useState([]); // Dropdown result - user's grants
    const [myProducts, setMyProducts] = useState([]); // Dropdown result - user's products
    const [partners, setPartners] = useState([]); // Dropdown result - all partners
    const [types, setTypes] = useState([]); // Dropdown result - all event types
    const [topics, setTopics] = useState([]); // Dropdown result - all topics
    
    const handleFieldChange = (id, value) => {
        setEvent({ ...event, [id]: value });
    }

    async function postEvent() {
        const response = await fetch('/api/main_events/update', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(event)
        });
        if (!response.ok) {
            console.error("Event POST request responded with failure.");
        }
    }

    const handleSubmit = (submitEvent) => {
        submitEvent.preventDefault();
        if (handleValidation() && Object.keys(event).length !== 0) {
            postEvent();
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!event.nameEn || event.nameEn.length === 0 || !event.nameFr || event.nameFr.length === 0) {
            newErrors.nameEn = `Both English Name and French Name cannot be empty. One must be filled.`;
            newErrors.nameFr = `Both English Name and French Name cannot be empty. One must be filled.`;
        }

        const validateStartDate = validateDate(event.startDate);
        if (!validateStartDate[0]) {
            newErrors.startDate = `Start date has an invalid ${validateStartDate[1]}`;
        }

        const validateEndDate = validateDate(event.endDate);
        if (!validateEndDate[0]) {
            newErrors.endDate = `End date has an invalid ${validateEndDate[1]}`;
        } else if (validateStartDate[0] && validateEndDate[0]) {
            if (!isNotAfter(event.startDate, event.endDate)) {
                newErrors.endDate = `End date cannot be before Start date.`;
            }
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    useEffect(() => {
        // Get data about specific event
        async function fetchEvent(id) {
            const response = await fetch(`/api/main_events/find_by_id?id=${id}`);
            const body = await response.json();
            setEvent(body);
        }

        // ADD RELATIONSHIP QUERIES TO FETCH DATA

        // Get all members for dropdown
        async function fetchMembers() {
            const response = await fetch('/api/main_members/find_all');
            const body = await response.json();
            setMembers(body);
        }

        // Get user's events for dropdown
        async function fetchMyEvents() {
            const response = await fetch(`/api/main_events/find_all`);
            const body = await response.json();
            setMyEvents(body);
        }

        // Get user's grants for dropdown
        async function fetchMyGrants() {
            const response = await fetch(`/api/main_grants/find_all`);
            const body = await response.json();
            setMyGrants(body);
        }

        // Get user's products for dropdown
        async function fetchMyProducts() {
            const response = await fetch(`/api/main_products/find_all`);
            const body = await response.json();
            setMyProducts(body);
        }

        // Get all partners for dropdown
        async function fetchPartners() {
            const response = await fetch(`/api/main_partners/find_all`);
            const body = await response.json();
            setPartners(body);
        }

        // Get topics for dropdown
        async function fetchTopics() {
            const response = await fetch('/api/types_topic/find_all');
            const body = await response.json();
            setTopics(body);
        }

        // Get event types for dropdown
        async function fetchTypes() {
            const response = await fetch('/api/types_event/find_all');
            const body = await response.json();
            setTypes(body);
        }

        fetchEvent(eventId);

        fetchMembers();
        fetchMyEvents();
        fetchMyGrants();
        fetchMyProducts();
        fetchPartners();
        fetchTypes();
        fetchTopics();
    }, [])

    return ( 
        <div className="EditEvent FormPage">
            <h2>{t('page_titles.edit_event')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox name='nameEn' 
                        labelText={t('event.name_en')} 
                        text={event.nameEn}
                        errorMessage={errors.nameEn}
                        onChange={handleFieldChange}/>

                    <Textbox name='nameFr' 
                        labelText={t('event.name_fr')} 
                        text={event.nameFr} 
                        errorMessage={errors.nameFr}
                        onChange={handleFieldChange}/>
                    
                    <Date name='startDate'
                        labelText={t('event.start_date')}
                        textValue={event.startDate}
                        errorMessage={errors.startDate}
                        onChange={handleFieldChange}/>

                    <Date name='endDate'
                        labelText={t('event.end_date')}
                        textValue={event.endDate}
                        errorMessage={errors.endDate}
                        onChange={handleFieldChange}/>

                    <Dropdown
                        name='type'
                        labelText={t('event.type')}
                        selectedChoice={event.type}
                        choices={types.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr}))}
                        hideNoneOption={true}
                        onChange={handleFieldChange}/>

                    {/* NEED MULTI-SELECT DROPDOWNS: TOPICS, PRODUCTS RESULTED, PARTNERS INVOLVED, GRANTS RESULTED, PAST EVENTS, FUTURE EVENTS, MEMBERS INVOLVED */}

                    <Textarea name='notes' 
                        labelText={t('event.notes')} 
                        text={event.notes} 
                        rows={10} cols={30}
                        onChange={handleFieldChange}/>

                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit'/>
                    <Button text={t('button.cancel')} type={2} />
                </div>
            </form>
        </div>
    );
}
 
export default EditEvent;