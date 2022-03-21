import './TablePage.css';
import List from "components/List";
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyGrants = () => {
    
    const { t } = useTranslation();
    const [grants, setGrants] = useState([]);

    // Object of column titles to display
    // Keys are titles from database, values are the titles we want to display
    const columnTitles = {
        'title': t('grant.column_title'),
        'submissionDate' : t('grant.column_submission_date'),
    }

    async function fetchGrants() {
        const response = await fetch(`/api/main_grants/find_all`);
        const body = await response.json();
        setGrants(body);
    }

    useEffect(() => {
        fetchGrants();
    }, [])

    return ( 
        <div className="MyGrants TablePage">
            <h2>{t('page_titles.my_grants')}</h2>
            <List items={grants} columns={['title', 'submissionDate']} columnTitles={columnTitles} fixedUrl='edit_grant'/>
        </div>
    );
}
 
export default MyGrants;