import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date, { validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { useNavigate, useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    addGrant,
    getGrantAndRelations,
    updateGrant,
    updateGrantMemberInvestigators,
    updateGrantMembers,
    updateGrantTopics
} from 'api/grants';

import { getMemberNames } from 'api/members';
import { getGrantSources, getGrantStatuses, getTopics } from 'api/types';

const enCurrency = new Intl.NumberFormat('en-CA', { style: 'currency', currency: 'CAD' });
const frCurrency = new Intl.NumberFormat('fr-CA', { style: 'currency', currency: 'CAD' });

const EditGrant = ({isNew}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { grantId } = useParams();
    const navigate = useNavigate();

    // Main_Grants data
    const [grant, setGrant] = useState({});

    // Involved Members relation
    const [relMembers, setRelMembers] = useState([]);
    const [newRelMembers, setNewRelMembers] = useState([]);

    // Investigators members relation
    const [relMemberInvestigators, setRelMemberInvestigators] = useState([]);
    const [newRelMemberInvestigators, setNewRelMemberInvestigators] = useState([]);

    // Topics relation
    const [relTopics, setRelTopics] = useState([]);
    const [newRelTopics, setNewRelTopics] = useState([]);

    // For populating dropdown options
    const [members, setMembers] = useState([]);
    const [sources, setSources] = useState([]);
    const [statuses, setStatuses] = useState([]);
    const [topics, setTopics] = useState([]);

    // See handleValidation function
    const [errors, setErrors] = useState({});

    const fetchData = useCallback(async () => {
        const results = await getGrantAndRelations(grantId);
        if (results != null) {
            setGrant(results.grant);
            setRelMembers(results.grantMembers);
            setNewRelMembers(results.grantMembers);
            setRelMemberInvestigators(results.grantMemberInvestigators);
            setNewRelMemberInvestigators(results.grantMemberInvestigators);
            setRelTopics(results.grantTopics);
            setNewRelTopics(results.grantTopics);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [grantId, pushNotification, t])

    const fetchTypes = useCallback(async () => {
        const results = await Promise.all([
            getMemberNames(),
            getGrantSources(),
            getGrantStatuses(),
            getTopics(),
        ]);
        if (!results.includes(null)) {
            setMembers(results[0]);
            setSources(results[1]);
            setStatuses(results[2]);
            setTopics(results[3]);
        }
        else pushNotification('negative', t('error.unable_fetch_types'));
    }, [pushNotification, t])

    useEffect(() => {
        if (!isNew) fetchData();
        fetchTypes();
    }, [fetchData, fetchTypes, isNew])

    const handleGrantChange = (key, value) => {
        setGrant(curr => ({ ...curr, [key]: value }));
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

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            let id = grantId;
            if (isNew) {
                id = await addGrant(grant);
                if (id == null) {
                    pushNotification('negative', t('error.unable_submit'));
                    return;
                }
            }
            const results = await Promise.all([
                isNew ? true : updateGrant(grant),
                updateGrantMembers(id, relMembers, newRelMembers),
                updateGrantMemberInvestigators(id, relMemberInvestigators, newRelMemberInvestigators),
                updateGrantTopics(id, relTopics, newRelTopics),
            ]);
            if (!results.includes(false)) {
                if (isNew) {
                    pushNotification('positive', t('feedback.submit_success'));
                    navigate(`/edit_grant/${id}`);
                }
                else {
                    await fetchData();
                    pushNotification('positive', t('feedback.submit_success'));
                }
            }
            else {
                pushNotification('negative', t('error.unable_submit'));
            }
        }
        else {
            pushNotification('negative', t('error.invalid_submit'));
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!grant.title) {
            newErrors.title = t('error.empty_title');
        }

        let [submissionDateIsValid, submissionDateError] = validateDate(grant.submissionDate);

        if (!submissionDateIsValid) {
            if (submissionDateError === 'empty')
                newErrors.submissionDate = t('error.empty_date');
            if (submissionDateError === 'format')
                newErrors.submissionDate = t('error.invalid_date');
            if (submissionDateError === 'month')
                newErrors.submissionDate = t('error.invalid_month');
            if (submissionDateError === 'day')
                newErrors.submissionDate = t('error.invalid_day');
        }

        let [receivedDateIsValid, receivedDateError] = validateDate(grant.receivedDate);

        if (!receivedDateIsValid) {
            if (receivedDateError === 'format')
                newErrors.receivedDate = t('error.invalid_date');
            if (receivedDateError === 'month')
                newErrors.receivedDate = t('error.invalid_month');
            if (receivedDateError === 'day')
                newErrors.receivedDate = t('error.invalid_day');
        }

        let [finishedDateIsValid, finishedDateError] = validateDate(grant.finishedDate);

        if (!finishedDateIsValid) {
            if (finishedDateError === 'format')
                newErrors.finishedDate = t('error.invalid_date');
            if (finishedDateError === 'month')
                newErrors.finishedDate = t('error.invalid_month');
            if (finishedDateError === 'day')
                newErrors.finishedDate = t('error.invalid_day');
        }

        if (grant.status == null) {
            newErrors.status = t('error.empty_status');
        }

        if (grant.source == null) {
            newErrors.source = t('error.empty_source');
        }

        if (!grant.investigatorsAll) {
            newErrors.investigatorsAll = t('error.empty_investigators');
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.membersInvolved = t('error.empty_members_involved');
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            if (isNew) {
                navigate('/my_grants');
            }
            else {
                window.scrollTo(0, 0);
                await fetchData();
                setErrors({});
                pushNotification('info', t('feedback.changes_reverted'));
            }
        }
    }

    return (
        <div className="EditGrant FormPage">
            <h2>{isNew ? t('page_titles.new_grant') : t('page_titles.edit_grant')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox
                        name='title'
                        labelText={t('grant.title')}
                        text={grant.title}
                        errorMessage={errors.title}
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
                        text={grant.amount}
                        isNumber
                        formatter={
                            i18n.resolvedLanguage === 'en' ?
                            enCurrency.format : frCurrency.format
                        }
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='submissionDate'
                        labelText={t('grant.submission_date')}
                        textValue={grant.submissionDate}
                        errorMessage={errors.submissionDate}
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='receivedDate'
                        labelText={t('grant.obtained_date')}
                        textValue={grant.receivedDate}
                        errorMessage={errors.receivedDate}
                        onChange={handleGrantChange}
                    />
                    <Date
                        name='finishedDate'
                        labelText={t('grant.completed_date')}
                        textValue={grant.finishedDate}
                        errorMessage={errors.finishedDate}
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
                        hideNoneOption
                        errorMessage={errors.source}
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
                        hideNoneOption
                        errorMessage={errors.status}
                        onChange={handleGrantChange}
                    />
                    <Textarea
                        name='investigatorsAll'
                        labelText={t('grant.investigators_all')}
                        text={grant.investigatorsAll}
                        rows={10} cols={30}
                        errorMessage={errors.investigatorsAll}
                        onChange={handleGrantChange}
                    />
                    <DropdownSelectList
                        name='memberInvestigators'
                        labelText={t('grant.investigators_members')}
                        noneOptionText={t('dropdown.add_member_investigator')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMemberInvestigators}
                        onChange={handleRelMemberInvestigatorsChange}
                    />
                    <DropdownSelectList
                        name='membersInvolved'
                        labelText={t('grant.member_involved')}
                        noneOptionText={t('dropdown.add_member_involved')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMembers}
                        errorMessage={errors.membersInvolved}
                        onChange={handleRelMembersChange}
                    />
                    <DropdownSelectList
                        name='topics'
                        labelText={t('grant.topics')}
                        noneOptionText={t('dropdown.add_topic')}
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
