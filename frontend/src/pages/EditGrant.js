import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date, { validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import {
    getGrantAndRelations,
    updateGrant,
    updateGrantMemberInvestigators,
    updateGrantMembers,
    updateGrantTopics
} from 'api/grants';

import { getMemberNames } from 'api/members';
import { getGrantSources, getGrantStatuses, getTopics } from 'api/types';

import { useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const enCurrency = new Intl.NumberFormat('en-CA', { style: 'currency', currency: 'CAD' });
const frCurrency = new Intl.NumberFormat('fr-CA', { style: 'currency', currency: 'CAD' });

const EditGrant = () => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { grantId } = useParams();

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

    const fetchEverything = useCallback(async () => {
        const results = await Promise.all([
            getGrantAndRelations(grantId),
            getMemberNames(),
            getGrantSources(),
            getGrantStatuses(),
            getTopics(),
        ]);
        if (!results.includes(null)) {
            setGrant(results[0].grant);
            setRelMembers(results[0].grantMembers);
            setNewRelMembers(results[0].grantMembers);
            setRelMemberInvestigators(results[0].grantMemberInvestigators);
            setNewRelMemberInvestigators(results[0].grantMemberInvestigators);
            setRelTopics(results[0].grantTopics);
            setNewRelTopics(results[0].grantTopics);
            setMembers(results[1]);
            setSources(results[2]);
            setStatuses(results[3]);
            setTopics(results[4]);
        }
        else pushNotification('negative', 'Failed to get your data!');
    }, [grantId, pushNotification])

    useEffect(() => {
        fetchEverything();
    }, [fetchEverything])

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

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', 'Submitting...');
            const results = await Promise.all([
                updateGrant(grant),
                updateGrantMembers(grantId, relMembers, newRelMembers),
                updateGrantMemberInvestigators(grantId, relMemberInvestigators, newRelMemberInvestigators),
                updateGrantTopics(grantId, relTopics, newRelTopics),
            ]);
            if (!results.includes(false)) {
                await fetchEverything();
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

        if (grant.isThroughLRI == null) {
            newErrors.isThroughLRI = 'Checkbox cannot be undefined.';
        }

        let [dateIsValid, dateError] = validateDate(grant.submissionDate);

        if (!dateIsValid) {
            if (dateError === 'empty')
                newErrors.submissionDate = 'Date cannot be empty.';
            if (dateError === 'format')
                newErrors.submissionDate = 'Please use the format YYYY-MM-DD.';
            if (dateError === 'month')
                newErrors.submissionDate = 'Month is invalid.';
            if (dateError === 'day')
                newErrors.submissionDate = 'Day is invalid.';
        }

        if (grant.status == null) {
            newErrors.status = 'Status cannot be none.';
        }

        if (grant.source == null) {
            newErrors.source = 'Source cannot be none.';
        }

        if (!grant.investigatorsAll) {
            newErrors.investigatorsAll = 'Investigators cannot be empty.';
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.membersInvolved = 'Members involved cannot be empty.';
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm('Are you sure? Any unsaved changes will be lost.')) {
            window.scrollTo(0, 0);
            await fetchEverything();
            setErrors({});
            pushNotification('info', 'Changes reverted.');
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
                        required
                        errorMessage={errors.title}
                        onChange={handleGrantChange}
                    />
                    <Checkbox
                        name='isThroughLRI'
                        labelText={t('grant.through_lri')}
                        checkedNum={grant.isThroughLRI}
                        required
                        errorMessage={errors.isThroughLRI}
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
                        required
                        errorMessage={errors.submissionDate}
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
                        required
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
                        required
                        errorMessage={errors.status}
                        onChange={handleGrantChange}
                    />
                    <Textarea
                        name='investigatorsAll'
                        labelText={t('grant.investigators_all')}
                        text={grant.investigatorsAll}
                        rows={10} cols={30}
                        required
                        errorMessage={errors.investigatorsAll}
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
                        errorMessage={errors.membersInvolved}
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
