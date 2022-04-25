import './TablePage.css';
import List from 'components/List';

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllProducts } from 'api/products';
import { getProductTypes } from 'api/types'

const MyProducts = () => {

    const { t, i18n } = useTranslation();
    const [products, setProducts] = useState([]);
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const results = await Promise.all([
            getAllProducts(),
            getProductTypes()
        ]);
        if(!results.includes(null)) {
            let productList = results[0];
            let types = results[1];
            setProducts(productList.map(p => {
                if (p.type != null) {
                    let type = types.find(t => t.id === p.type);
                    p.typeEn = type.typeEn;
                    p.typeFr = type.typeFr;
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
        <div className="MyProducts TablePage">
            <h2>{t('page_titles.my_products')}</h2>
            {!readyRender && <span>Loading...</span>}
            { readyRender && <List
                items={products}
                columns={['title', i18n.resolvedLanguage === "en" ? 'typeEn' : 'typeFr','date']}
                columnTitles={{
                    'title': t('product.column_title'),
                    'typeEn' : t('product.column_type'),
                    'typeFr' : t('product.column_type'),
                    'date' : t('product.column_date'),
                }}
                addButtonText={t('button.add_product')}
                fixedUrl='edit_product'
            />}
        </div>
    );
}

export default MyProducts;
