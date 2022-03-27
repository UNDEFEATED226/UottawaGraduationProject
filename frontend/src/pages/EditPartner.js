import './FormPage.css';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditPartner = ({userId}) => {
    const { t, i18n } = useTranslation();
    const { partnerId } = useParams();

    const [partner, setPartner] = useState({});
    const [types, setTypes] = useState([]);
    const [scopes, setScopes] = useState([]);
    const [memInvolved, setMemInvolved] = useState([]);

    const handleFieldChange = (id, value) => {
        setPartner({ ...partner, [id]: value });
    }

    useEffect(() => {
        async function fetchPartner(id) {
            const response = await fetch(`/api/main_partners/find_by_id?id=${id}`);
            const body = await response.json();
            setPartner(body);
        }

        // API FUNCTION IS NOT WORKING YET
        // async function fetchMemInvolved(id) {
        //     const response = await fetch(`/api/relp_partner_member/find_by_partner_id?id=${id}`);
        //     const body = await response.json();
        //     setMemInvolved(body);
        // }
        
        async function fetchScopes() {
            const response = await fetch(`/api/types_partnership_scope/find_all`);
            const body = await response.json();
            setScopes(body);
        }
    
        async function fetchTypes() {
            const response = await fetch(`/api/types_partnership_type/find_all`);
            const body = await response.json();
            setTypes(body);
        }

        fetchScopes();
        fetchTypes();
        fetchPartner(partnerId);
    }, [])
    
    return ( 
        <div className="EditPartner FormPage">
            <h2>{t('page_titles.edit_partner')}</h2>
            <form>
                <div className='fields'>
                    <Textbox name='name' 
                        labelText={t('partner.name')} 
                        text={partner.name} 
                        required={true}
                        onChange={handleFieldChange}/>
                    <Dropdown
                        name='type'
                        labelText={t('partner.type')}
                        selectedChoice={partner.type}
                        choices={types.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr}))}
                        onChange={handleFieldChange}/>
                    <Dropdown
                        name='scope'
                        labelText={t('partner.scope')}
                        selectedChoice={partner.scope}
                        choices={scopes.map(e => ({id: e.id, name: i18n.resolvedLanguage === "en" ? e.scopeEn : e.scopeFr}))}
                        onChange={handleFieldChange}/>
                    {/* MULTI DROPDOWN FOR: MEMBERS INVOLVED */}
                    {/* <DropdownSelectList
                        name='membersInvolved'
                        labelText={t('partner.members_involved')}
                        choices={}/> */}
                    <Textarea name='notes' 
                        labelText={t('partner.notes')} 
                        text={partner.notes} 
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
 
export default EditPartner;