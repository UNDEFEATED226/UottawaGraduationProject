import './TablePage.css';
import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const Partners = () => {

    const { i18n, t } = useTranslation();

    const [dataCollected, setDataCollected] = useState(false);
    const [readyRender, setReadyRender] = useState(false);

    const [partners, setPartners] = useState({});
    const [types, setTypes] = useState([]);
    const [scopes, setScopes] = useState([]);
    const objType = new Object();
    const objScope = new Object();

    // Object of column titles to display
    // Keys are titles from database, values are the titles we want to display
    const columnTitles = {
        'name': t('partner.column_name'),
        'typeEn' : t('partner.column_type'),
        'typeFr' : t('partner.column_type'),
        'scopeEn' : t('partner.column_scope'),
        'scopeFr' : t('partner.column_scope'),
    }

    useEffect(() => {
        async function fetchPartners() {
            const response = await fetch(`/api/main_partners/find_all`);
            const body = await response.json();
            setPartners(body);
        }
    
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
        fetchPartners();
        fetchScopes();
        fetchTypes();
    }, [])

    useEffect(() => {
        setDataCollected((partners.length != 0) && (scopes.length != 0) && (types.length != 0))
    }, [partners, scopes, types])


    useEffect(() => {
        const addTypesAndScopes = () => {
            return partners.map(partner => {
                partner['scopeEn'] = objScope[partner.scope].en;
                partner['scopeFr'] = objScope[partner.scope].fr;
                partner['typeEn'] = objType[partner.type].en;
                partner['typeFr'] = objType[partner.type].fr;
            });            
        }

        if (dataCollected == true){
            scopes.forEach(scope => (
                objScope[scope.id] = {'en': scope.scopeEn, 'fr': scope.scopeFr}
            ));

            types.forEach(type => (
                objType[type.id] = {'en': type.typeEn, 'fr': type.typeFr}
            ));
            addTypesAndScopes();
            setReadyRender(!(partners == null || partners.length === 0));
        }
    }, [dataCollected])


    // [{"id":8,"name":"Alliance to End Homelessness Ottawa","type":4,"scope":1,"notes":null},{"id":9,"name":"Some Partner","type":1,"scope":4,"notes":null}]
    return ( 
        <div className="Partners TablePage">
            <h2>{t('page_titles.partners')}</h2>
            {!readyRender && <span>Loading...</span>}
            {readyRender && <List items={partners} columns={['name', i18n.resolvedLanguage === "en" ? 'typeEn' : 'typeFr', i18n.resolvedLanguage === "en" ? 'scopeEn' : 'scopeFr']} columnTitles={columnTitles} fixedUrl='edit_partner'/>
            }
        </div>
    );
}
 
export default Partners;