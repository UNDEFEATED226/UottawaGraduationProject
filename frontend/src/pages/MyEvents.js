import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyEvents = () => {

    const { t } = useTranslation();
    const [events, setEvents] = useState([]);

    async function fetchEvents() {
        const response = await fetch(`/api/main_events/find_all`);
        const body = await response.json();
        setEvents(body);
    }

    useEffect(() => {
        fetchEvents();
    }, [])

    return ( 
        <div className="MyEvents">
            <h2>{t('page_titles.my_events')}</h2>
            <List items={events} columns={['nameEn', 'nameFr', 'startDate', 'endDate']} fixedUrl='edit_event'/>
        </div>
    );
}
 
export default MyEvents;