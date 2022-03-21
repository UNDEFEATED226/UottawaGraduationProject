import './TablePage.css';
import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyEvents = () => {

    const { t } = useTranslation();
    const [events, setEvents] = useState([]);

    // Object of column titles to display
    // Keys are titles from database, values are the titles we want to display
    const columnTitles = {
        'nameEn': t('event.column_name_en'),
        'nameFr': t('event.column_name_fr'),
        'startDate' : t('event.column_start_date'),
        'endDate': t('event.column_end_date'),
    }

    async function fetchEvents() {
        const response = await fetch(`/api/main_events/find_all`);
        const body = await response.json();
        setEvents(body);
    }

    useEffect(() => {
        fetchEvents();
    }, [])

    return ( 
        <div className="MyEvents TablePage">
            <h2>{t('page_titles.my_events')}</h2>
            <List items={events} columns={['nameEn', 'nameFr', 'startDate', 'endDate']} columnTitles={columnTitles} fixedUrl='edit_event'/>
        </div>
    );
}
 
export default MyEvents;