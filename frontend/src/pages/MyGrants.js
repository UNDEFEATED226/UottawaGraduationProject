import './TablePage.css';
import List from "components/List";

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllGrants } from 'api/grants';

const MyGrants = () => {

    const { t } = useTranslation();
    const [grants, setGrants] = useState([]);
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const data = await getAllGrants();
        if (data != null) {
            setGrants(data);
            setReadyRender(true);
        }
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <div className="MyGrants TablePage">
            <h2>{t('page_titles.my_grants')}</h2>
            {!readyRender && <span>Loading...</span>}
            { readyRender && <List
                items={grants}
                columns={['title', 'submissionDate']}
                columnTitles={{
                    'title': t('grant.column_title'),
                    'submissionDate' : t('grant.column_submission_date'),
                }}
                addButtonText={t('button.add_grant')}
                fixedUrl='edit_grant'
            />}
        </div>
    );
}

export default MyGrants;
