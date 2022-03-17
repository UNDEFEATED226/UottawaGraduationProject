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

const EditEvent = ({userId}) => {

    const { t } = useTranslation();
    const { eventId } = useParams();

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

    useEffect(() => {
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
            <form>
                <div className='fields'>
                    <Textbox name='nameEn' 
                        labelText={t('edit_event.name_en')} 
                        text={event.nameEn} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Textbox name='nameFr' 
                        labelText={t('edit_event.name_fr')} 
                        text={event.nameFr} 
                        required={true}
                        onChange={handleFieldChange}/>
                    
                    <Date name='startDate' labelText={t('edit_event.start_date')} textValue={event.startDate} onChange={handleFieldChange} />
                    <Date name='endDate' labelText={t('edit_event.end_date')} textValue={event.endDate} onChange={handleFieldChange} />

                    {/* NEED DROPDOWNS: TYPE */}
                    {/* NEED MULTI-SELECT DROPDOWNS: TOPICS, PRODUCTS RESULTED, PARTNERS INVOLVED, GRANTS RESULTED, PAST EVENTS, FUTURE EVENTS, MEMBERS INVOLVED */}

                    <Textarea name='notes' 
                        labelText={t('edit_event.notes')} 
                        text={event.notes} 
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
 
export default EditEvent;