import './FormPage.css';
import Date, { validateDate, isNotAfter } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { useNavigate, useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    addEvent,
    getAllEvents,
    getEventAndRelations,
    updateEvent,
    updateEventGrants,
    updateEventMembers,
    updateEventPartners,
    updateEventProducts,
    updateEventTopics,
    updateFutureEvents,
    updatePastEvents
} from 'api/events';

import { getMemberNames } from 'api/members';
import { getAllGrants } from 'api/grants';
import { getAllProducts } from 'api/products';
import { getAllPartners } from 'api/partners';
import { getEventTypes, getTopics } from 'api/types';

const EditEvent = ({isNew}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { eventId } = useParams();
    const navigate = useNavigate();

    const [event, setEvent] = useState({});

    const [relPastEvents, setRelPastEvents] = useState([]);
    const [newRelPastEvents, setNewRelPastEvents] = useState([]);

    const [relFutureEvents, setRelFutureEvents] = useState([]);
    const [newRelFutureEvents, setNewRelFutureEvents] = useState([]);

    const [relGrants, setRelGrants] = useState([]);
    const [newRelGrants, setNewRelGrants] = useState([]);

    const [relMembers, setRelMembers] = useState([]);
    const [newRelMembers, setNewRelMembers] = useState([]);

    const [relProducts, setRelProducts] = useState([]);
    const [newRelProducts, setNewRelProducts] = useState([]);

    const [relPartners, setRelPartners] = useState([]);
    const [newRelPartners, setNewRelPartners] = useState([]);

    const [relTopics, setRelTopics] = useState([]);
    const [newRelTopics, setNewRelTopics] = useState([]);

    const [members, setMembers] = useState([]);
    const [events, setEvents] = useState([]);
    const [grants, setGrants] = useState([]);
    const [products, setProducts] = useState([]);
    const [partners, setPartners] = useState([]);
    const [eventTypes, setEventTypes] = useState([]);
    const [topics, setTopics] = useState([]);

    const [errors, setErrors] = useState({});

    const fetchData = useCallback(async () => {
        const results = await getEventAndRelations(eventId);
        if (results != null) {
            setEvent(results.event);
            setRelPastEvents(results.pastEvents);
            setNewRelPastEvents(results.pastEvents);
            setRelFutureEvents(results.futureEvents);
            setNewRelFutureEvents(results.futureEvents);
            setRelGrants(results.eventGrants);
            setNewRelGrants(results.eventGrants);
            setRelMembers(results.eventMembers);
            setNewRelMembers(results.eventMembers);
            setRelProducts(results.eventProducts);
            setNewRelProducts(results.eventProducts);
            setRelPartners(results.eventPartners);
            setNewRelPartners(results.eventPartners);
            setRelTopics(results.eventTopics);
            setNewRelTopics(results.eventTopics);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [eventId, pushNotification, t])

    const fetchTypes = useCallback(async () => {
        const results = await Promise.all([
            getMemberNames(),
            getAllEvents(),
            getAllGrants(),
            getAllProducts(),
            getAllPartners(),
            getEventTypes(),
            getTopics(),
        ]);
        if (!results.includes(null)) {
            setMembers(results[0]);
            setEvents(results[1]);
            setGrants(results[2]);
            setProducts(results[3]);
            setPartners(results[4]);
            setEventTypes(results[5]);
            setTopics(results[6]);
        }
        else pushNotification('negative', t('error.unable_fetch_types'));
    }, [pushNotification, t])

    useEffect(() => {
        if (!isNew) fetchData();
        fetchTypes();
    }, [fetchData, fetchTypes, isNew])

    const handleEventChange = (key, value) => {
        setEvent(curr => ({ ...curr, [key]: value }));
    }

    const handleRelPastEventsChange = (_, newRels) => {
        setNewRelPastEvents(newRels);
    }

    const handleRelFutureEventsChange = (_, newRels) => {
        setNewRelFutureEvents(newRels);
    }

    const handleRelGrantsChange = (_, newRels) => {
        setNewRelGrants(newRels);
    }

    const handleRelMembersChange = (_, newRels) => {
        setNewRelMembers(newRels);
    }

    const handleRelProductsChange = (_, newRels) => {
        setNewRelProducts(newRels);
    }

    const handleRelPartnersChange = (_, newRels) => {
        setNewRelPartners(newRels);
    }

    const handleRelTopicsChange = (_, newRels) => {
        setNewRelTopics(newRels);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            let id = eventId;
            if (isNew) {
                id = await addEvent(event);
                if (id == null) {
                    pushNotification('negative', t('error.unable_submit'));
                    return;
                }
            }
            const results = await Promise.all([
                isNew ? true : updateEvent(event),
                updatePastEvents(id, relPastEvents, newRelPastEvents),
                updateFutureEvents(id, relFutureEvents, newRelFutureEvents),
                updateEventGrants(id, relGrants, newRelGrants),
                updateEventMembers(id, relMembers, newRelMembers),
                updateEventProducts(id, relProducts, newRelProducts),
                updateEventPartners(id, relPartners, newRelPartners),
                updateEventTopics(id, relTopics, newRelTopics),
            ]);
            if (!results.includes(false)) {
                if (isNew) {
                    pushNotification('positive', t('feedback.submit_success'));
                    navigate(`/edit_event/${id}`);
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

        if (!event.nameEn && !event.nameFr) {
            newErrors.name = t('error.empty_both_names');
        }

        let [startDateIsValid, startDateError] = validateDate(event.startDate);

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

        let [endDateIsValid, endDateError] = validateDate(event.endDate);

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
            if (!isNotAfter(event.startDate, event.endDate)) {
                newErrors.endDate = t('error.invalid_date_order');
            }
        }

        if (event.type == null) {
            newErrors.type = t('error.empty_type');
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.membersInvolved = t('error.empty_members_involved');
        }

        if (newRelPastEvents.includes(Number(eventId))) {
            newErrors.pastEvents = t('error.invalid_event');
        }

        if (newRelFutureEvents.includes(Number(eventId))) {
            newErrors.futureEvents = t('error.invalid_event');
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            if (isNew) {
                navigate('/my_events');
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
        <div className="EditEvent FormPage">
            <h2>{isNew ? t('page_titles.new_event') : t('page_titles.edit_event')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox
                        name='nameEn'
                        labelText={t('event.name_en')}
                        text={event.nameEn}
                        errorMessage={errors.name}
                        onChange={handleEventChange}
                    />
                    <Textbox
                        name='nameFr'
                        labelText={t('event.name_fr')}
                        text={event.nameFr}
                        errorMessage={errors.name}
                        onChange={handleEventChange}
                    />
                    <Date
                        name='startDate'
                        labelText={t('event.start_date')}
                        textValue={event.startDate}
                        errorMessage={errors.startDate}
                        onChange={handleEventChange}
                    />
                    <Date
                        name='endDate'
                        labelText={t('event.end_date')}
                        textValue={event.endDate}
                        errorMessage={errors.endDate}
                        onChange={handleEventChange}
                    />
                    <Dropdown
                        name='type'
                        labelText={t('event.type')}
                        selectedChoice={event.type}
                        choices={eventTypes.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr
                        }))}
                        hideNoneOption
                        errorMessage={errors.type}
                        onChange={handleEventChange}
                    />
                    <DropdownSelectList
                        name='membersInvolved'
                        labelText={t('event.members_involved')}
                        dropdownLabel={t('dropdown.add_member_involved')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMembers}
                        errorMessage={errors.membersInvolved}
                        onChange={handleRelMembersChange}
                    />
                    <DropdownSelectList
                        name='pastEvents'
                        labelText={t('event.past_events')}
                        dropdownLabel={t('dropdown.add_past_event')}
                        choices={events.map(e => ({
                            id: e.id,
                            name: e.nameEn ?
                                ( e.nameFr ?
                                    e.nameEn + ' / ' + e.nameFr :
                                    e.nameEn
                                ) :
                                e.nameFr
                        })).filter(e => e.id !== Number(eventId))}
                        selectedChoices={newRelPastEvents}
                        errorMessage={errors.pastEvents}
                        onChange={handleRelPastEventsChange}
                    />
                    <DropdownSelectList
                        name='futureEvents'
                        labelText={t('event.future_events')}
                        dropdownLabel={t('dropdown.add_future_event')}
                        choices={events.map(e => ({
                            id: e.id,
                            name: e.nameEn ?
                                ( e.nameFr ?
                                    e.nameEn + ' / ' + e.nameFr :
                                    e.nameEn
                                ) :
                                e.nameFr
                        })).filter(e => e.id !== Number(eventId))}
                        selectedChoices={newRelFutureEvents}
                        errorMessage={errors.futureEvents}
                        onChange={handleRelFutureEventsChange}
                    />
                    <DropdownSelectList
                        name='grants'
                        labelText={t('event.grants_resulted')}
                        dropdownLabel={t('dropdown.add_grant')}
                        choices={grants.map(e => ({
                            id: e.id,
                            name: e.title
                        }))}
                        selectedChoices={newRelGrants}
                        onChange={handleRelGrantsChange}
                    />
                    <DropdownSelectList
                        name='products'
                        labelText={t('event.products_resulted')}
                        dropdownLabel={t('dropdown.add_product')}
                        choices={products.map(e => ({
                            id: e.id,
                            name: e.title
                        }))}
                        selectedChoices={newRelProducts}
                        onChange={handleRelProductsChange}
                    />
                    <DropdownSelectList
                        name='partners'
                        labelText={t('event.partners_involved')}
                        dropdownLabel={t('dropdown.add_partner')}
                        choices={partners.map(e => ({
                            id: e.id,
                            name: e.name
                        }))}
                        selectedChoices={newRelPartners}
                        onChange={handleRelPartnersChange}
                    />
                    <DropdownSelectList
                        name='topics'
                        labelText={t('event.topics')}
                        dropdownLabel={t('dropdown.add_topic')}
                        choices={topics.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        selectedChoices={newRelTopics}
                        onChange={handleRelTopicsChange}
                    />
                    <Textarea name='notes'
                        labelText={t('event.notes')}
                        text={event.notes}
                        rows={10} cols={30}
                        onChange={handleEventChange}
                    />
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit'/>
                    <Button text={t('button.cancel')} type={2} clickHandler={handleCancel} />
                </div>
            </form>
        </div>
    );
}

export default EditEvent;
