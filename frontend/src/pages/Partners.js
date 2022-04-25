import './TablePage.css';
import List from 'components/List';

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllPartners } from 'api/partners';
import { getPartnerScopes, getPartnerTypes } from 'api/types';

const Partners = () => {

    const { t, i18n } = useTranslation();

    const [partners, setPartners] = useState({});
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const results = await Promise.all([
            getAllPartners(),
            getPartnerTypes(),
            getPartnerScopes(),
        ]);
        if (!results.includes(null)) {
            let partnersList = results[0];
            let types = results[1];
            let scopes = results[2];
            setPartners(partnersList.map(p => {
                if (p.type != null) {
                    let type = types.find(t => t.id === p.type);
                    p.typeEn = type.typeEn;
                    p.typeFr = type.typeFr;
                }
                if (p.scope != null) {
                    let scope = scopes.find(s => s.id === p.scope);
                    p.scopeEn = scope.scopeEn;
                    p.scopeFr = scope.scopeFr;
                }
                return p;
            }));
            setReadyRender(true);
        }
    }

    useEffect(() => {
        fetchData();
    }, [])

    return (
        <div className="Partners TablePage">
            <h2>{t('page_titles.partners')}</h2>
            {!readyRender && <span>Loading...</span>}
            { readyRender && <List
                items={partners}
                columns={[
                    'name',
                    i18n.resolvedLanguage === "en" ? 'typeEn' : 'typeFr',
                    i18n.resolvedLanguage === "en" ? 'scopeEn' : 'scopeFr'
                ]}
                columnTitles={{
                    'name': t('partner.column_name'),
                    'typeEn' : t('partner.column_type'),
                    'typeFr' : t('partner.column_type'),
                    'scopeEn' : t('partner.column_scope'),
                    'scopeFr' : t('partner.column_scope'),
                }}
                addButtonText={t('button.add_partner')}
                fixedUrl='edit_partner'
            />}
        </div>
    );
}

export default Partners;
