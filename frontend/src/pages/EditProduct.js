import './BasicInformation.css'
import Checkbox from 'components/Checkbox';
import Date from 'components/Date';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useHistory, useParams } from 'react-router-dom'
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditProduct = ({userId}) => {

    const { t } = useTranslation();
    const { productId } = useParams();

    const [product, setProduct] = useState({});
    const [members, setMember] = useState([]);
    const [partners, setPartner] = useState([]);
    const [productType, setProductType] = useState([]);
    //const [targetStakeholder, setTargetStakeholder] = useState([]);
    const [topic, setTopic] = useState([]);

    const handleFieldChange = (id, value) => {
        setProduct({ ...product, [id]: value });
    }

    async function fetchProduct(id) {
        const response = await fetch(`/api/main_products/find_by_id?id=${id}`);
        const body = await response.json();
        setProduct(body);
    }

    useEffect(() => {
        console.log("EDITPRODUCT component : " + product.date);
    }, [product]);
    
    async function fetchMember() {
        const response = await fetch('/api/main_members/find_all');
        const body = await response.json();
        setMember(body);
    }

    async function fetchPartner() {
        const response = await fetch('/api/main_partners/find_all');
        const body = await response.json();
        setPartner(body);
    }

    async function fetchProductType() {
        const response = await fetch('/api/types_product/find_all');
        const body = await response.json();
        setProductType(body);
    }

    // async function fetchTargetStakeholder() {
    //     const response = await fetch('/api/types_targetstakeholder/find_all');
    //     const body = await response.json();
    //     setTargetStakeholder(body);
    // }

    async function fetchTopic() {
        const response = await fetch('/api/types_topic/find_all');
        const body = await response.json();
        setTopic(body);
    }

    useEffect(() => {
        fetchProduct(productId);
        console.log("PRODUCTID : " + productId);
        fetchMember();
        fetchPartner();
        fetchProductType();
        //fetchTargetStakeholder();
        fetchTopic();
    }, [])

    // {"id":2,"title":"DASH requirements and database","date":"2021-05-01","onGoing":0,"peerReviewed":0,"doi":null,"authorsAll":"Shadi Fasihi Lahroudi","notes":null,"type":41}

    return ( 
        <div className="EditProduct">
            <h2>{t('page_titles.edit_product')}</h2>
            <form>
                <div className='fields'>
                    <Textbox name='title' 
                        labelText={t('edit_product.title')} 
                        text={product.title} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Date name='date' labelText={t('edit_product.date')} textValue={product.date} onChange={handleFieldChange} />
                    <Checkbox name='ongoing' labelText={t('edit_product.ongoing')} checkedNum={product.onGoing} />
                    <Checkbox name='peerReview' labelText={t('edit_product.peer_review')} checkedNum={product.peerReviewed} />
                    

                    <Textbox name='doi' 
                        labelText={t('edit_product.doi')} 
                        placeholderText={product.doi} 
                        required={true}
                        onChange={handleFieldChange}/>

                    <Textarea name='notes' 
                        labelText={t('edit_product.notes')} 
                        text={product.notes} 
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
 
export default EditProduct;

            //         <Dropdown name='type' labelText={t('edit_product.type')} selectedChoice={String(product.type)} choices={
            //             productType.map(e => ({NOT IMPLEMENTED}))
            //         }/>