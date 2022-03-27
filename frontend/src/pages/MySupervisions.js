import './TablePage.css';
import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MySupervisions = () => {

    const { t, i18n } = useTranslation();

    const [dataCollected, setDataCollected] = useState(false);
    const [readyRender, setReadyRender] = useState(false);

    const [supervisions, setSupervisions] = useState({});
    const [members, setMembers] = useState([]);
    const [levels, setLevels] = useState([]);
    const objMembers = new Object();
    const objLevels = new Object();

    // Object of column titles to display
    // Keys are titles from database, values are the titles we want to display
    const columnTitles = {
        'firstName': t('supervision.column_name_first'),
        'lastName' : t('supervision.column_name_last'),
        'startDate' : t('supervision.column_start_date'),
        'levelEn' : t('supervision.column_level'),
        'levelFr' : t('supervision.column_level'),
    }

    useEffect(() => {
        async function fetchSupervisions() {
            const response = await fetch(`/api/main_supervision/find_all`);
            const body = await response.json();
            setSupervisions(body);
        }
    
        // Get all members for dropdown
        async function fetchMembers() {
            const response = await fetch('/api/main_members/find_all');
            const body = await response.json();
            setMembers(body);
        }

        async function fetchLevels() {
            const response = await fetch('/api/types_trainee_level/find_all');
            const body = await response.json();
            setLevels(body);
        }

        fetchSupervisions();
        fetchMembers();
        fetchLevels();
    }, [])

    useEffect(() => {
        setDataCollected((supervisions.length != 0) && (members.length != 0) && (levels.length != 0));
    }, [supervisions, members, levels])

    useEffect(() => {
        const addTraineeNames = () => {
            return supervisions.map(supervision => {
                if (supervision.trainee !== null) {
                    supervision['firstName'] = objMembers[supervision.trainee].firstName;
                    supervision['lastName'] = objMembers[supervision.trainee].lastName;
                }

                if (supervision.level !== null) {
                    supervision['levelEn'] = objLevels[supervision.level].en;
                    supervision['levelFr'] = objLevels[supervision.level].fr;
                } else {
                    supervision['levelEn'] = '';
                    supervision['levelFr'] = '';
                }
                
            });
        }

        if (dataCollected == true){
            members.forEach(member => (
                objMembers[member.id] = {'firstName': member.firstName, 'lastName': member.lastName}
            ));
            levels.forEach(level => (
                objLevels[level.id] = {'en': level.levelEn, 'fr': level.levelFr}
            ));
            addTraineeNames();
            setReadyRender(!(supervisions == null || supervisions.length === 0));
        }
    }, [dataCollected])

    return (
        <div className="MySupervisons TablePage">
            <h2>{t('page_titles.my_supervisions')}</h2>
            {!readyRender && <span>Loading...</span>}
            {readyRender && <List items={supervisions} columns={['firstName', 'lastName', 'startDate', i18n.resolvedLanguage === "en" ? 'levelEn' : 'levelFr']} columnTitles={columnTitles} fixedUrl='edit_supervision'/>
            }
        </div>
    );
}
 
export default MySupervisions;