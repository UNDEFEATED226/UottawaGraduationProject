import './TablePage.css';
import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyProducts = () => {

    const { t } = useTranslation();
    const [products, setProducts] = useState([]);

    // Object of column titles to display
    // Keys are titles from database, values are the titles we want to display
    const columnTitles = {
        'title': t('product.column_title'),
        'date' : t('product.column_date'),
    }

    async function fetchProducts() {
        const response = await fetch(`/api/main_products/find_all`);
        const body = await response.json();
        setProducts(body);
    }

    useEffect(() => {
        fetchProducts();
    }, [])

    return (
        <div className="MyProducts TablePage">
            <h2>{t('page_titles.my_products')}</h2>
            <List items={products} columns={['title', 'date']} columnTitles={columnTitles} fixedUrl='edit_product'/>
        </div>
    );
}
 
export default MyProducts;
