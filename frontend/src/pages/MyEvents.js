import './TablePage.css';
import List from 'components/List';

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllEvents } from 'api/events';

const MyEvents = () => {

    const { t } = useTranslation();
    const [events, setEvents] = useState([]);
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const data = await getAllEvents();
        if (data != null) {
            setEvents(data);
            setReadyRender(true);
        }
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <div className="MyEvents TablePage">
            <h2>{t('page_titles.my_events')}</h2>
            {!readyRender && <span>Loading...</span>}
            { readyRender && <List
                items={events}
                columns={['nameEn', 'nameFr', 'startDate', 'endDate']}
                columnTitles={{
                    'nameEn': t('event.column_name_en'),
                    'nameFr': t('event.column_name_fr'),
                    'startDate' : t('event.column_start_date'),
                    'endDate': t('event.column_end_date'),
                }}
                addButtonText={t('button.add_event')}
                fixedUrl='edit_event'
            />}
        </div>
    );
}

export default MyEvents;
