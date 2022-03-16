import Checkbox from 'components/Checkbox';
import Date from 'components/Date';
import Dropdown from 'components/Dropdown';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditProduct = ({userId}) => {

    const { t } = useTranslation();
    const { productId } = useParams();

    const [product, setProduct] = useState({});
    const [relMembers, setRelMembers] = useState([]); // Member authors
    const [relPartners, setRelPartners] = useState([]); // Product's partners
    const [relStakeholder, setRelStakeholders] = useState([]); // Product's target stakeholders
    const [relTopics, setRelTopics] = useState([]); // Chosen Topics
    
    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [partners, setPartners] = useState([]); // Dropdown result - all partners
    const [productType, setProductType] = useState([]); // Dropdown result - all product types
    const [targetStakeholder, setTargetStakeholder] = useState([]); // Dropdown result - all target stakeholders
    const [topic, setTopic] = useState([]); // Dropdown result - all topics
    
    const handleFieldChange = (id, value) => {
        setProduct({ ...product, [id]: value });
    }

    // Get data about specific product
    async function fetchProduct(id) {
        const response = await fetch(`/api/main_products/find_by_id?id=${id}`);
        const body = await response.json();
        setProduct(body);
    }

    // Get members authors of this product
    async function fetchRelMembers(id) { // NOT WORKING - NEED FIX
        const response = await fetch(`/api/relp_product_member/find_by_product_id?id=${id}`);
        const body = await response.json();
        setRelMembers(body);
    }

    // FOR TESTING
    useEffect(() => {
        console.log(relMembers.length)
    }, [relMembers])

    // FOR TESTING
    useEffect(() => {
        console.log(members.length)
    }, [members])

    // ADD RELATIONSHIP QUERIES TO FETCH DATA - NEED TOPICS, PRODUCT PARTNERS, PRODUCT STAKEHOLDERS
    
    // Get all members for dropdown
    async function fetchMembers() {
        const response = await fetch('/api/main_members/find_all');
        const body = await response.json();
        setMembers(body);
    }

    // Get all partners for dropdown
    async function fetchPartners() {
        const response = await fetch('/api/main_partners/find_all');
        const body = await response.json();
        setPartners(body);
    }

    // Get all product types for dropdown
    async function fetchProductType() {
        const response = await fetch('/api/types_product/find_all');
        const body = await response.json();
        setProductType(body);
    }

    // Get all stakeholders for dropdown
    async function fetchTargetStakeholder() {
        const response = await fetch('/api/types_target_stakeholder/find_all');
        const body = await response.json();
        setTargetStakeholder(body);
    }

    // Get all topics for dropdown
    async function fetchTopic() {
        const response = await fetch('/api/types_topic/find_all');
        const body = await response.json();
        setTopic(body);
    }

    useEffect(() => {
        fetchProduct(productId);
        fetchRelMembers(productId);

        fetchMembers();
        fetchPartners();
        fetchProductType();
        fetchTargetStakeholder();
        fetchTopic();
    }, [])

    // Example: {"id":2,"title":"DASH requirements and database","date":"2021-05-01","onGoing":0,"peerReviewed":0,"doi":null,"authorsAll":"Shadi Fasihi Lahroudi","notes":null,"type":41}

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

                    <Textarea name='allAuthors' 
                        labelText={t('edit_product.all_authors')} 
                        text={product.authorsAll} 
                        rows={10} cols={30}
                        onChange={handleFieldChange}/>

                    {/* NEED DROPDOWNS: TYPE */}
                    {/* NEED MULTI-SELECT DROPDOWNS: TOPICS, MEMBER AUTHORS, TARGET STAKEHOLDER, PARTNERS */}

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