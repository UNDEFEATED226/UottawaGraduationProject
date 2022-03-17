import './TablePage.css';
import List from 'components/List';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const MyProducts = () => {

    const { t } = useTranslation();
    const [products, setProducts] = useState([]);

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
            <List items={products} columns={['title', 'date']} fixedUrl='edit_product'/>
        </div>
    );
}
 
export default MyProducts;
