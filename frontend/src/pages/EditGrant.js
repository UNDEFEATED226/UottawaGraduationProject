import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useOutletContext, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const enCurrency = new Intl.NumberFormat('en-CA', { style: 'currency', currency: 'CAD' });
const frCurrency = new Intl.NumberFormat('fr-CA', { style: 'currency', currency: 'CAD' });

const EditGrant = () => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();

    const { grantId } = useParams();

    const [grant, setGrant] = useState({});

    const [relMemberInvestigators, setRelMemberInvestigators] = useState([]); // Investigators (members)
    const [newRelMemberInvestigators, setNewRelMemberInvestigators] = useState([]); // Investigators (members)
    
    const [relMembers, setRelMembers] = useState([]); // Involved Members
    const [newRelMembers, setNewRelMembers] = useState([]); // Involved Members
    
    const [relTopics, setRelTopics] = useState([]); // Chosen Topics
    const [newRelTopics, setNewRelTopics] = useState([]); // Chosen Topics

    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [sources, setSources] = useState([]); // Dropdown result - all sources
    const [statuses, setStatuses] = useState([]); // Dropdown result - all statuses
    const [topics, setTopics] = useState([]); // Dropdown result - all topics

    const [errors, setErrors] = useState({});

    // Get data on specific grant
    async function fetchGrant(id) {
        const response = await fetch(`/api/main_grants/find_by_id?id=${id}`);
        const body = await response.json();
        setGrant(body);
    }

    // Get involved members of this grant
    async function fetchRelMembers(id) { 
        const response = await fetch(`/api/relp_grant_member/find_all_by_grant_id?grantId=${id}`);
        const body = await response.json();
        const memberIds = body.map(e => e.id.memberId);
        setRelMembers(memberIds);
        setNewRelMembers(memberIds);
    }

    // Get investigator members of this grant
    async function fetchRelMemberInvestigators(id) {
        const response = await fetch(`/api/relp_grant_member_investigator/find_all_by_grant_id?grantId=${id}`);
        const body = await response.json();
        const memberIds = body.map(e => e.id.memberId);
        setRelMemberInvestigators(memberIds);
        setNewRelMemberInvestigators(memberIds);
    }

    // Get grant topics
    async function fetchRelTopics(id) {
        const response = await fetch(`/api/relp_grant_topic/find_all_by_grant_id?grantId=${id}`);
        const body = await response.json();
        const themeIds = body.map(e => e.id.themeId);
        setRelTopics(themeIds);
        setNewRelTopics(themeIds);
    }

    // Get all members for dropdown
    async function fetchMembers() {
        const response = await fetch('/api/main_members/get_names');
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
        fetchRelMemberInvestigators(grantId);
        fetchRelTopics(grantId);
        fetchMembers();
        fetchSources();
        fetchStatuses();
        fetchTopics();
    }, [grantId])

    const updateGrant = async () => {
        const response = await fetch('/api/main_grants/update', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(grant)
        });
        return response.ok;
    }

    const deleteRelMember = async (id) => {
        const response = await fetch(`/api/relp_grant_member/delete_by_id?grantId=${grantId}&memberId=${id}`);
        return response.ok;
    }

    const addRelMember = async (id) => {
        let newRel = {id: {grantId: grantId, memberId: id}};
        const response = await fetch('/api/relp_grant_member/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        return response.ok;
    }

    const updateRelMembers = async () => {
        let allSuccessful = true;
        await Promise.all(relMembers.map(async (id) => {
            if (!newRelMembers.includes(id)) {
                allSuccessful &&= await deleteRelMember(id);
            }
        }));
        await Promise.all(newRelMembers.map(async (id) => {
            if (!relMembers.includes(id)) {
                allSuccessful &&= await addRelMember(id);
            }
        }));
        return allSuccessful;
    }

    const deleteRelMemberInvestigator = async (id) => {
        const response = await fetch(`/api/relp_grant_member_investigator/delete_by_id?grantId=${grantId}&memberId=${id}`);
        return response.ok;
    }

    const addRelMemberInvestigator = async (id) => {
        let newRel = {id: {grantId: grantId, memberId: id}};
        const response = await fetch('/api/relp_grant_member_investigator/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        return response.ok;
    }

    const updateRelMemberInvestigators = async () => {
        let allSuccessful = true;
        await Promise.all(relMemberInvestigators.map(async (id) => {
            if (!newRelMemberInvestigators.includes(id)) {
                allSuccessful &&= await deleteRelMemberInvestigator(id);
            }
        }));
        await Promise.all(newRelMemberInvestigators.map(async (id) => {
            if (!relMemberInvestigators.includes(id)) {
                allSuccessful &&= await addRelMemberInvestigator(id);
            }
        }));
        return allSuccessful;
    }

    const deleteRelTopic = async (id) => {
        const response = await fetch(`/api/relp_grant_topic/delete_by_id?grantId=${grantId}&themeId=${id}`);
        return response.ok;
    }

    const addRelTopic = async (id) => {
        let newRel = {id: {grantId: grantId, themeId: id}};
        const response = await fetch('/api/relp_grant_topic/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        return response.ok;
    }

    const updateRelTopics = async () => {
        let allSuccessful = true;
        await Promise.all(relTopics.map(async (id) => {
            if (!newRelTopics.includes(id)) {
                allSuccessful &&= await deleteRelTopic(id);
            }
        }));
        await Promise.all(newRelTopics.map(async (id) => {
            if (!relTopics.includes(id)) {
                allSuccessful &&= await addRelTopic(id);
            }
        }));
        return allSuccessful;
    }

    const handleGrantChange = (id, value) => {
        setGrant({ ...grant, [id]: value });
    }

    const handleRelMembersChange = (_, newMembers) => {
        setNewRelMembers(newMembers);
    }

    const handleRelMemberInvestigatorsChange = (_, newMemberInvestigators) => {
        setNewRelMemberInvestigators(newMemberInvestigators);
    }

    const handleRelTopicsChange = (_, newTopics) => {
        setNewRelTopics(newTopics);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', 'Submitting...');
            let allSuccessful = (
                await updateGrant() &&
                await updateRelMembers() &&
                await updateRelMemberInvestigators() &&
                await updateRelTopics()
            );
            if (allSuccessful) {
                pushNotification('positive', 'Submitted successfully!');
            }
            else {
                pushNotification('negative', 'Submission failed! Please try again later.');
            }
        }
        else {
            pushNotification('negative', 'There are errors in the form.');
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!grant.title) {
            newErrors.title = 'Title cannot be empty.';
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = () => {
        if (window.confirm('Are you sure? Any unsaved changes will be lost.')) {
            window.location.reload();
            window.scrollTo(0, 0);
        }
    }

    return (
        <div className="EditGrant FormPage">
            <h2>{t('page_titles.edit_grant')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox
                        name='title'
                        labelText={t('grant.title')}
                        text={grant.title}
                        required={true}
                        onChange={handleGrantChange}
                    />
                    <Checkbox
                        name='isThroughLRI'
                        labelText={t('grant.through_lri')}
                        checkedNum={grant.isThroughLRI}
                        onChange={handleGrantChange}
                    />
                    <Textbox
                        name='amount'
                        labelText={t('grant.amount')}
                        text={
                            (i18n.resolvedLanguage === 'en')
                            ? enCurrency.format(grant.amount ?? 0)
                            : frCurrency.format(grant.amount ?? 0)
                        }
                        required={true}
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='submissionDate'
                        labelText={t('grant.submission_date')}
                        textValue={grant.submissionDate}
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='receivedDate'
                        labelText={t('grant.obtained_date')}
                        textValue={grant.receivedDate}
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='finishedDate'
                        labelText={t('grant.completed_date')}
                        textValue={grant.finishedDate}
                        onChange={handleGrantChange}
                    />
                    <Dropdown
                        name='source'
                        labelText={t('grant.source')}
                        choices={sources.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr
                        }))}
                        selectedChoice={grant.source}
                        onChange={handleGrantChange}
                    />
                    <Dropdown
                        name='status'
                        labelText={t('grant.status')}
                        choices={statuses.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.statusEn : e.statusFr
                        }))}
                        selectedChoice={grant.status}
                        onChange={handleGrantChange}
                    />
                    <Textarea
                        name='investigatorsAll'
                        labelText={t('grant.investigators_all')}
                        text={grant.investigatorsAll}
                        rows={10} cols={30}
                        onChange={handleGrantChange}
                    />
                    <DropdownSelectList 
                        name='memberInvestigators'
                        labelText='Member Investigators:'
                        noneOptionText='Add member investigator'
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMemberInvestigators}
                        onChange={handleRelMemberInvestigatorsChange}
                    />
                    <DropdownSelectList 
                        name='membersInvolved'
                        labelText='Members Involved:'
                        noneOptionText='Add member'
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMembers}
                        onChange={handleRelMembersChange}
                    />
                    <DropdownSelectList 
                        name='topics'
                        labelText='Topics:'
                        noneOptionText='Add topic'
                        choices={topics.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        selectedChoices={newRelTopics}
                        onChange={handleRelTopicsChange}
                    />
                    <Textarea
                        name='notes'
                        labelText={t('grant.notes')}
                        text={grant.notes}
                        rows={10} cols={30}
                        onChange={handleGrantChange}
                    />
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit' />
                    <Button text={t('button.cancel')} type={2} clickHandler={handleCancel} />
                </div>
            </form>
        </div>
    );
}

export default EditGrant;
