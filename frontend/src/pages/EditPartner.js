import './FormPage.css';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { useNavigate, useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    getPartner,
    updatePartner,
    updatePartnerMembers,
    getPartnerMembers,
    addPartner
} from 'api/partners';

import { getPartnerScopes, getPartnerTypes } from 'api/types';
import { getMemberNames } from 'api/members';

const EditPartner = ({isNew}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { partnerId } = useParams();
    const navigate = useNavigate();

    // Partner data
    const [partner, setPartner] = useState({});

    // Member involved with partner relation
    const [relMembers, setRelMembers] = useState([]);
    const [newRelMembers, setNewRelMembers] = useState([]);

    // Populating dropdown options
    const [members, setMembers] = useState([])
    const [scopes, setScopes] = useState([]);
    const [types, setTypes] = useState([]);

    // See handleValidation function
    const [errors, setErrors] = useState({});

    const fetchData = useCallback(async () => {
        const results = await Promise.all([
            getPartner(partnerId),
            getPartnerMembers(partnerId),
        ]);
        if (!results.includes(null)) {
            setPartner(results[0]);
            setRelMembers(results[1]);
            setNewRelMembers(results[1]);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [partnerId, pushNotification, t])

    const fetchTypes = useCallback(async () => {
        const results = await Promise.all([
            getMemberNames(),
            getPartnerScopes(),
            getPartnerTypes(),
        ]);
        if (!results.includes(null)) {
            setMembers(results[0]);
            setScopes(results[1]);
            setTypes(results[2]);
        }
        else pushNotification('negative', t('error.unable_fetch_types'));
    }, [pushNotification, t])

    useEffect(() => {
        if (!isNew) fetchData();
        fetchTypes();
    }, [fetchData, fetchTypes, isNew])

    const handleFieldChange = (key, value) => {
        setPartner(curr => ({ ...curr, [key]: value }));
    }

    const handleRelMembersChange = (_, newMembers) => {
        setNewRelMembers(newMembers);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            let id = partnerId;
            if (isNew) {
                id = await addPartner(partner);
                if (id == null) {
                    pushNotification('negative', t('error.unable_submit'));
                    return;
                }
            }
            const results = await Promise.all([
                isNew ? true : updatePartner(partner),
                updatePartnerMembers(id, relMembers, newRelMembers),
            ]);
            if (!results.includes(false)) {
                if (isNew) {
                    pushNotification('positive', t('feedback.submit_success'));
                    navigate(`/edit_partner/${id}`);
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

        if (!partner.name) {
            newErrors.name = t('error.empty_partner_name');
        }
        if (!partner.scope) { 
            newErrors.scope = t('error.empty_partner_scope');
        }
        if (!partner.type) {
            newErrors.type = t('error.empty_partner_type');
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            if (isNew) {
                navigate('/partners');
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
        <div className="EditPartner FormPage">
            <h2>{isNew ? t('page_titles.new_partner') : t('page_titles.edit_partner')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox name='name' 
                        labelText={t('partner.name')} 
                        text={partner.name} 
                        errorMessage={errors.name}
                        onChange={handleFieldChange}/>
                    <Dropdown
                        name='type'
                        labelText={t('partner.type')}
                        selectedChoice={partner.type}
                        choices={types.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr}))}
                        hideNoneOption
                        errorMessage={errors.type}
                        onChange={handleFieldChange}/>
                    <Dropdown
                        name='scope'
                        labelText={t('partner.scope')}
                        selectedChoice={partner.scope}
                        choices={scopes.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.scopeEn : e.scopeFr}))}
                        hideNoneOption
                        errorMessage={errors.scope}
                        onChange={handleFieldChange}/>
                    <DropdownSelectList
                        name='membersInvolved'
                        labelText={t('partner.members_involved')}
                        dropdownLabel={t('dropdown.add_member_involved')}
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMembers}
                        onChange={handleRelMembersChange} />
                    <Textarea name='notes' 
                        labelText={t('partner.notes')} 
                        text={partner.notes} 
                        rows={10} cols={30}
                        onChange={handleFieldChange}/>
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit' />
                    <Button text={t('button.cancel')} type={2} clickHandler={handleCancel} />
                </div>
            </form>
        </div>
    );
}
 
export default EditPartner;
