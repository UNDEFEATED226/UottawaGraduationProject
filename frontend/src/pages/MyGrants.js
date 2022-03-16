import List from "components/List";
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyGrants = () => {
    
    const { t } = useTranslation();
    const [grants, setGrants] = useState([]);

    async function fetchGrants() {
        const response = await fetch(`/api/main_grants/find_all`);
        const body = await response.json();
        setGrants(body);
    }

    useEffect(() => {
        fetchGrants();
    }, [])

    return ( 
        <div className="MyGrants">
            <h2>{t('page_titles.my_grants')}</h2>
            <List items={grants} columns={['title', 'submissionDate']} fixedUrl='edit_grant'/>
        </div>
    );
}
 
export default MyGrants;