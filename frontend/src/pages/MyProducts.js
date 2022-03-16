import './MyProducts.css';
import List from 'components/List';
import { useEffect, useState } from 'react';

const MyProducts = () => {

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
        <div className="MyProducts">
            <h2>My Products</h2>
            <List items={products} columns={['title', 'date']} fixedUrl='edit_product'/>
        </div>
    );
}
 
export default MyProducts;
