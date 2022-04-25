import './TablePage.css';
import List from 'components/List';

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllSupervisions } from 'api/supervisions';
import { getMemberNames } from 'api/members';
import { getTraineeLevels } from 'api/types';

const MySupervisions = () => {

    const { t, i18n } = useTranslation();

    const [supervisions, setSupervisions] = useState({});
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const results = await Promise.all([
            getAllSupervisions(),
            getMemberNames(),
            getTraineeLevels(),
        ]);
        if (!results.includes(null)) {
            let supervisionsList = results[0];
            let members = results[1];
            let levels = results[2];
            setSupervisions(supervisionsList.map(s => {
                if (s.trainee != null) {
                    let member = members.find(m => m.id === s.trainee);
                    s.firstName = member.firstName;
                    s.lastName = member.lastName;
                }
                if (s.level != null) {
                    let level = levels.find(l => l.id === s.level);
                    s.levelEn = level.levelEn;
                    s.levelFr = level.levelFr;
                }
                return s;
            }));
            setReadyRender(true);
        }
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <div className="MySupervisons TablePage">
            <h2>{t('page_titles.my_supervisions')}</h2>
            {!readyRender && <span>Loading...</span>}
            { readyRender && <List
                items={supervisions}
                columns={[
                    'firstName',
                    'lastName',
                    'startDate',
                    i18n.resolvedLanguage === "en" ? 'levelEn' : 'levelFr'
                ]}
                columnTitles={{
                    'firstName': t('supervision.column_name_first'),
                    'lastName' : t('supervision.column_name_last'),
                    'startDate' : t('supervision.column_start_date'),
                    'levelEn' : t('supervision.column_level'),
                    'levelFr' : t('supervision.column_level'),
                }}
                addButtonText={t('button.add_supervision')}
                fixedUrl='edit_supervision'
            />}
        </div>
    );
}

export default MySupervisions;
