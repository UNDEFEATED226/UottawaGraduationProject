import Checkbox from 'components/Checkbox';
import Date from 'components/Date';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditGrant = ({userId}) => {

    const { t } = useTranslation();
    const { grantId } = useParams();

    const [grant, setGrant] = useState({});
    const [relMemberInvestigators, setRelMemberInvestigators] = useState([]); // Investigators (members)
    const [relMembers, setRelMembers] = useState([]); // Involved Members
    const [relTopics, setRelTopics] = useState([]); // Chosen Topics

    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [sources, setSources] = useState([]); // Dropdown result - all sources
    const [statuses, setStatuses] = useState([]); // Dropdown result - all statuses
    const [topics, setTopics] = useState([]); // Dropdown result - all topics    

    const handleFieldChange = (id, value) => {
        setGrant({ ...grant, [id]: value });
    }

    // Get data on specific grant
    async function fetchGrant(id) {
        const response = await fetch(`/api/main_grants/find_by_id?id=${id}`);
        const body = await response.json();
        setGrant(body);
    }

    // Get involved members of this grant
    async function fetchRelMembers(id) { // NOT WORKING - NEED FIX
        const response = await fetch(`/api/relp_grant_member/find_by_grant_id?id=${id}`);
        const body = await response.json();
        setRelMembers(body);
    }

    // FOR TESTING
    useEffect(() => {
        console.log(relMembers.length)
    }, [relMembers])

    // ADD RELATIONSHIP QUERIES TO FETCH DATA - NEED TOPICS, MEMBER INVESTIGATORS

    // Get all members for dropdown
    async function fetchMembers() {
        const response = await fetch('/api/main_members/find_all');
        const body = await response.json();
        setMembers(body);
    }

    // Get all sources for dropdown
    async function fetchSources() {
        const response = await fetch('/api/types_grant_source/find_all');
        const body = await response.json();
        setSources(body);
    }

    // Get all statuses for dropdown
    async function fetchStatuses() {
        const response = await fetch('/api/types_grant_status/find_all');
        const body = await response.json();
        setStatuses(body);
    }

    // Get all topics for dropdown
    async function fetchTopics() {
        const response = await fetch('/api/types_topic/find_all');
        const body = await response.json();
        setTopics(body);
    }

    useEffect(() => {
        fetchGrant(grantId);
        fetchRelMembers(grantId);

        fetchMembers();
        fetchSources();
        fetchStatuses();
        fetchTopics();
    }, [])

    // Example: {"id":1,"title":"Making IT Count: DASH","amount":100000.0000,"isThroughLRI":0,"status":4,"submissionDate":"2021-01-20","receivedDate":null,"finishedDate":null,"source":7,"investigatorsAll":"Katrine Sauvé-Schenk\r\nDaniel Amyot\r\nLysanne Lessard\r\nJohn Sylvestre","notes":"Housing, submitted to CMHC/SCHL. Resubmitted to SSHRC."}

    return ( 
        <div className="EditGrant">
            <h2>{t('page_titles.edit_grant')}</h2>
            <form>
                <div className='fields'>
                    <Textbox name='title' 
                        labelText={t('edit_grant.title')} 
                        text={grant.title} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Textbox name='amount' 
                        labelText={t('edit_grant.amount')} 
                        text={grant.amount} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Checkbox name='throughLri' labelText={t('edit_grant.through_lri')} checkedNum={grant.isThroughLRI} />

                    <Date name='submissionDate' labelText={t('edit_grant.submission_date')} textValue={grant.submissionDate} onChange={handleFieldChange} />
                    <Date name='obtainedDate' labelText={t('edit_grant.obtained_date')} textValue={grant.receivedDate} onChange={handleFieldChange} />
                    <Date name='completedDate' labelText={t('edit_grant.completed_date')} textValue={grant.finishedDate} onChange={handleFieldChange} />

                    <Textarea name='investigatorsAll' 
                        labelText={t('edit_grant.investigators_all')} 
                        text={grant.investigatorsAll} 
                        rows={10} cols={30}
                        onChange={handleFieldChange}/>

                    {/* NEED DROPDOWNS: SOURCE, STATUS */}
                    {/* NEED MULTI-SELECT DROPDOWNS: TOPICS, INVESTIGATORS (MEMBERS), MEMBERS INVOLVED */}

                    <Textarea name='notes' 
                        labelText={t('edit_grant.notes')} 
                        text={grant.notes} 
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
 
export default EditGrant;