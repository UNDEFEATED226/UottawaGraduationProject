import './FormPage.css';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    getPartner,
    updatePartner,
    updatePartnerMembers,
    getPartnerMembers
} from 'api/partners';

import { getPartnerScopes, getPartnerTypes } from 'api/types';
import { getMemberNames } from 'api/members';

const EditPartner = () => {
    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { partnerId } = useParams();

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

    const handleFieldChange = (key, value) => {
        setPartner(curr => ({ ...curr, [key]: value }));
    }

    const handleRelMembersChange = (_, newMembers) => {
        setNewRelMembers(newMembers);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            const results = await Promise.all([
                updatePartner(partner),
                updatePartnerMembers(partnerId, relMembers, newRelMembers)
            ]);
            if (!results.includes(false)) {
                await fetchEverything();
                pushNotification('positive', t('feedback.submit_success'));
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
            window.scrollTo(0, 0);
            await fetchEverything();
            setErrors({});
            pushNotification('info', t('feedback.changes_reverted'));
        }
    }

    const fetchEverything = useCallback(async () => {
        const results = await Promise.all([
            getPartnerMembers(partnerId),
            getPartner(partnerId),
            getMemberNames(),
            getPartnerScopes(),
            getPartnerTypes(),
        ]);
        if (!results.includes(null)) {
            setRelMembers(results[0]);
            setNewRelMembers(results[0]);
            setPartner(results[1]);
            setMembers(results[2]);
            setScopes(results[3]);
            setTypes(results[4])
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [partnerId, pushNotification, t])

    useEffect(() => {
        fetchEverything()
    }, [fetchEverything])
    
    return ( 
        <div className="EditPartner FormPage">
            <h2>{t('page_titles.edit_partner')}</h2>
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
