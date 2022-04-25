import './TablePage.css';
import List from 'components/List';

import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import { getAllProducts } from 'api/products';

const MyProducts = () => {

    const { t } = useTranslation();
    const [products, setProducts] = useState([]);
    const [readyRender, setReadyRender] = useState(false);

    const fetchData = async () => {
        const data = await getAllProducts();
        if (data != null) {
            setProducts(data);
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
                columns={['title', 'date']}
                columnTitles={{
                    'title': t('product.column_title'),
                    'date' : t('product.column_date'),
                }}
                addButtonText={t('button.add_product')}
                fixedUrl='edit_product'
            />}
        </div>
    );
}

export default MyProducts;
