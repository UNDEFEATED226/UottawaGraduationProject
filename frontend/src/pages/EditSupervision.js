import './FormPage.css';
import Date, { isNotAfter, validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { useNavigate, useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    addSupervision,
    getSupervisionAndRelations,
    updateSupervision,
    updateSupervisionCoSupervisors,
    updateSupervisionPrincipalSupervisors,
    updateSupervisionThesisAdvisoryCommittees
} from 'api/supervisions';

import { getMemberNames } from 'api/members';
import { getFaculties, getTraineeLevels } from 'api/types';

const EditSupervision = ({isNew}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { supervisionId } = useParams();
    const navigate = useNavigate();

    const [supervision, setSupervision] = useState({});

    const [relPrincipalSupervisors, setRelPrincipalSupervisors] = useState([]);
    const [newRelPrincipalSupervisors, setNewRelPrincipalSupervisors] = useState([]);

    const [relCoSupervisors, setRelCoSupervisors] = useState([]);
    const [newRelCoSupervisors, setNewRelCoSupervisors] = useState([]);

    const [relThesisAdvisory, setRelThesisAdvisory] = useState([]);
    const [newRelThesisAdvisory, setNewRelThesisAdvisory] = useState([]);

    const [members, setMembers] = useState([]);
    const [levels, setLevels] = useState([]);
    const [faculties, setFaculties] = useState([]);

    const [errors, setErrors] = useState({});

    const fetchData = useCallback(async () => {
        const results = await getSupervisionAndRelations(supervisionId);
        if (results != null) {
            setSupervision(results.supervision);
            setRelPrincipalSupervisors(results.supervisionPrincipalSupervisors);
            setNewRelPrincipalSupervisors(results.supervisionPrincipalSupervisors);
            setRelCoSupervisors(results.supervisionCoSupervisors);
            setNewRelCoSupervisors(results.supervisionCoSupervisors);
            setRelThesisAdvisory(results.supervisionThesisAdvisoryCommittees);
            setNewRelThesisAdvisory(results.supervisionThesisAdvisoryCommittees);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [supervisionId, pushNotification, t])

    const fetchTypes = useCallback(async () => {
        const results = await Promise.all([
            getMemberNames(),
            getTraineeLevels(),
            getFaculties(),
        ]);
        if (!results.includes(null)) {
            setMembers(results[0]);
            setLevels(results[1]);
            setFaculties(results[2]);
        }
        else pushNotification('negative', t('error.unable_fetch_types'));
    }, [pushNotification, t])

    useEffect(() => {
        if (!isNew) fetchData();
        fetchTypes();
    }, [fetchData, fetchTypes, isNew])

    const handleSupervisionChange = (key, value) => {
        setSupervision(curr => ({ ...curr, [key]: value }));
    }

    const handleRelPrincipalSupervisorsChange = (_, newRels) => {
        setNewRelPrincipalSupervisors(newRels);
    }

    const handleRelCoSupervisorsChange = (_, newRels) => {
        setNewRelCoSupervisors(newRels);
    }

    const handleRelThesisAdvisoryChange = (_, newRels) => {
        setNewRelThesisAdvisory(newRels);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            let id = supervisionId;
            if (isNew) {
                id = await addSupervision(supervision);
                if (id == null) {
                    pushNotification('negative', t('error.unable_submit'));
                    return;
                }
            }
            const results = await Promise.all([
                isNew ? true : updateSupervision(supervision),
                updateSupervisionPrincipalSupervisors(id, relPrincipalSupervisors, newRelPrincipalSupervisors),
                updateSupervisionCoSupervisors(id, relCoSupervisors, newRelCoSupervisors),
                updateSupervisionThesisAdvisoryCommittees(id, relThesisAdvisory, newRelThesisAdvisory),
            ]);
            if (!results.includes(false)) {
                if (isNew) {
                    pushNotification('positive', t('feedback.submit_success'));
                    navigate(`/edit_supervision/${id}`);
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

        if (supervision.trainee == null) {
            if (!supervision.firstName || !supervision.lastName) {
                newErrors.trainee = t('error.empty_trainee');
            }
        }

        let [startDateIsValid, startDateError] = validateDate(supervision.startDate);

        if (!startDateIsValid) {
            if (startDateError === 'empty')
                newErrors.startDate = t('error.empty_date');
            if (startDateError === 'format')
                newErrors.startDate = t('error.invalid_date');
            if (startDateError === 'month')
                newErrors.startDate = t('error.invalid_month');
            if (startDateError === 'day')
                newErrors.startDate = t('error.invalid_day');
        }

        if (supervision.endDate) {

            let [endDateIsValid, endDateError] = validateDate(supervision.endDate);

            if (!endDateIsValid) {
                if (endDateError === 'empty')
                    newErrors.endDate = t('error.empty_date');
                if (endDateError === 'format')
                    newErrors.endDate = t('error.invalid_date');
                if (endDateError === 'month')
                    newErrors.endDate = t('error.invalid_month');
                if (endDateError === 'day')
                    newErrors.endDate = t('error.invalid_day');
            }

            if (startDateIsValid && endDateIsValid) {
                if (!isNotAfter(supervision.startDate, supervision.endDate)) {
                    newErrors.endDate = t('error.invalid_date_order');
                }
            }
        }

        if (supervision.level == null) {
            newErrors.level = t('error.empty_level');
        }

        if (!newRelPrincipalSupervisors || newRelPrincipalSupervisors.length === 0) {
            newErrors.principalSupervisors = t('error.empty_principal_supervisors');
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            if (isNew) {
                navigate('/my_supervisions');
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
        <div className="EditSupervision FormPage">
            <h2>{isNew ? t('page_titles.new_supervision') : t('page_titles.edit_supervision')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Dropdown
                        name='trainee'
                        labelText={t('supervision.trainee')}
                        selectedChoice={supervision.trainee}
                        choices={members.map(e => ({
                            id: e.id,
                            name: `${e.firstName} ${e.lastName}`
                        }))}
                        errorMessage={errors.trainee}
                        onChange={handleSupervisionChange}
                    />
                    <Dropdown
                        name='level'
                        labelText={t('supervision.level')}
                        selectedChoice={supervision.level}
                        choices={levels.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.levelEn : e.levelFr
                        }))}
                        errorMessage={errors.level}
                        onChange={handleSupervisionChange}
                    />
                    <Textbox
                        name='firstName'
                        labelText={t('supervision.name_first')}
                        text={supervision.firstName}
                        disabled={supervision.trainee != null}
                        onChange={handleSupervisionChange}
                    />
                    <Textbox
                        name='lastName'
                        labelText={t('supervision.name_last')}
                        text={supervision.lastName}
                        disabled={supervision.trainee != null}
                        onChange={handleSupervisionChange}
                    />
                    <Date
                        name='startDate'
                        labelText={t('supervision.start_date')}
                        textValue={supervision.startDate}
                        errorMessage={errors.startDate}
                        onChange={handleSupervisionChange}
                    />
                    <Date
                        name='endDate'
                        labelText={t('supervision.end_date')}
                        textValue={supervision.endDate}
                        errorMessage={errors.endDate}
                        onChange={handleSupervisionChange}
                    />
                    <Dropdown
                        name='faculty'
                        labelText={t('supervision.faculty')}
                        selectedChoice={supervision.faculty}
                        choices={faculties.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        onChange={handleSupervisionChange}
                    />
                    <DropdownSelectList
                        name='principalSupervisors'
                        labelText={t('supervision.supervisor_principal')}
                        dropdownLabel={t('dropdown.add_principal_supervisor')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: `${e.firstName} ${e.lastName}`
                        }))}
                        selectedChoices={newRelPrincipalSupervisors}
                        errorMessage={errors.principalSupervisors}
                        onChange={handleRelPrincipalSupervisorsChange}
                    />
                    <DropdownSelectList
                        name='coSupervisors'
                        labelText={t('supervision.supervisor_co')}
                        dropdownLabel={t('dropdown.add_co_supervisor')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: `${e.firstName} ${e.lastName}`
                        }))}
                        selectedChoices={newRelCoSupervisors}
                        onChange={handleRelCoSupervisorsChange}
                    />
                    <DropdownSelectList
                        name='thesisAdvisoryCommittee'
                        labelText={t('supervision.thesis_advisory_committee')}
                        dropdownLabel={t('dropdown.add_thesis_advisory_committee')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: `${e.firstName} ${e.lastName}`
                        }))}
                        selectedChoices={newRelThesisAdvisory}
                        onChange={handleRelThesisAdvisoryChange}
                    />
                    <Textarea
                        name='notes'
                        labelText={t('supervision.notes')}
                        text={supervision.notes}
                        rows={10} cols={30}
                        onChange={handleSupervisionChange}
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

export default EditSupervision;
