import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date, { validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditProduct = ({userId}) => {

    const { t, i18n } = useTranslation();
    const { productId } = useParams();

    const [product, setProduct] = useState({});

    const [relMembers, setRelMembers] = useState([]); // Member authors
    const [newRelMembers, setNewRelMembers] = useState([]);

    const [relPartners, setRelPartners] = useState([]); // Product's partners
    const [newRelPartners, setNewRelPartners] = useState([]);

    const [relStakeholder, setRelStakeholders] = useState([]); // Product's target stakeholders
    const [newRelStakeholder, setNewRelStakeholder] = useState([]);

    const [relTopics, setRelTopics] = useState([]); // Chosen Topics
    const [newRelTopics, setNewRelTopics] = useState([]);

    const [members, setMembers] = useState([]); // Dropdown result - all members
    const [partners, setPartners] = useState([]); // Dropdown result - all partners
    const [productType, setProductType] = useState([]); // Dropdown result - all product types
    const [targetStakeholder, setTargetStakeholder] = useState([]); // Dropdown result - all target stakeholders
    const [topic, setTopic] = useState([]); // Dropdown result - all topics

    const [errors, setErrors] = useState({}); // Holds errors

    const handleProductChange = (id, value) => {
        setProduct({ ...product, [id]: value });
    }

    const handleRelMembersChange = (_, newMembers) => {
        setNewRelMembers(newMembers);
    }

    const handleRelPartnersChange = (_, newPartners) => {
        setNewRelPartners(newPartners);
    }
    
    const handleRelStakeholdersChange = (_, newStakeholders) => {
        setNewRelStakeholder(newStakeholders);
    }
    
    const handleRelTopicsChange = (_, newTopics) => {
        setNewRelTopics(newTopics);
    }

    useEffect(() => {
        console.log(product)
    }, [product]);

    // Get data about specific product
    async function fetchProduct(id) {
        const response = await fetch(`/api/main_products/find_by_id?id=${id}`);
        const body = await response.json();
        setProduct(body);
    }

    // Get members authors of this product
    async function fetchRelMembers(id) {
        const response = await fetch(`/api/relp_product_member/find_all_by_product_id?productId=${id}`);
        const body = await response.json();
        const memberIds = body.map(e => e.id.memberId);
        setRelMembers(memberIds);
        setNewRelMembers(memberIds);
    }

    // Get partners of product
    async function fetchRelPartners(id) {
        const response = await fetch(`/api/relp_product_partner/find_all_by_product_id?productId=${id}`);
        const body = await response.json();
        const partnerIds = body.map(e => e.id.partnerId);
        setRelPartners(partnerIds);
        setNewRelPartners(partnerIds);
    }

    // Get target stakeholders of product
    async function fetchRelStakeholders(id) {
        const response = await fetch(`/api/relp_product_target_stakeholder/find_all_by_product_id?productId=${id}`);
        const body = await response.json();
        const stakeholderIds = body.map(e => e.id.targetStakeholderId);
        setRelStakeholders(stakeholderIds);
        setNewRelStakeholder(stakeholderIds);
    }
    
    // Get topics of product
    async function fetchRelTopics(id) {
        const response = await fetch(`/api/relp_product_topic/find_all_by_product_id?productId=${id}`);
        const body = await response.json();
        const topicIds = body.map(e => e.id.themeId);
        setRelTopics(topicIds);
        setNewRelTopics(topicIds);
    }

    // Get all members for dropdown
    async function fetchMembers() {
        const response = await fetch('/api/main_members/get_names');
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
        fetchRelPartners(productId);
        fetchRelStakeholders(productId);
        fetchRelTopics(productId);
        fetchMembers();
        fetchPartners();
        fetchProductType();
        fetchTargetStakeholder();
        fetchTopic();
    }, [productId])

    async function updateProduct() {
        const response = await fetch('/api/main_products/update', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(product)
        });
        if (!response.ok) {
            console.error("Product update request responded with failure.");
        }
    }

    async function deleteRelMember(id) {
        const response = await fetch(`/api/relp_product_member/delete_by_id?productId=${productId}&memberId=${id}`);
        if (!response.ok) {
            console.error("Member Relation delete request responded with failure.");
        }
    }

    async function addRelMember(id) {
        let newRel = {id: {productId: productId, memberId: id}};
        const response = await fetch('/api/relp_product_member/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        if (!response.ok) {
            console.error("Member Relation add request responded with failure.");
        }
    }

    async function deleteRelPartner(id) {
        const response = await fetch(`/api/relp_product_partner/delete_by_id?productId=${productId}&partnerId=${id}`);
        if (!response.ok) {
            console.error("Partner Relation delete request responded with failure.");
        }
    }

    async function addRelPartner(id) {
        let newRel = {id: {productId: productId, partnerId: id}};
        const response = await fetch('/api/relp_product_partner/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        if (!response.ok) {
            console.error("Partner Relation add request responded with failure.");
        }
    }

    async function deleteRelStakeholder(id) {
        const response = await fetch(`/api/relp_product_target_stakeholder/delete_by_id?productId=${productId}&targetStakeholderId=${id}`);
        if (!response.ok) {
            console.error("Stakeholder Relation delete request responded with failure.");
        }
    }

    async function addRelStakeholder(id) {
        let newRel = {id: {productId: productId, targetStakeholderId: id}};
        const response = await fetch('/api/relp_product_target_stakeholder/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        if (!response.ok) {
            console.error("Stakeholder Relation add request responded with failure.");
        }
    }

    async function deleteRelTopic(id) {
        const response = await fetch(`/api/relp_product_topic/delete_by_id?productId=${productId}&themeId=${id}`);
        if (!response.ok) {
            console.error("Topic Relation delete request responded with failure.");
        }
    }

    async function addRelTopic(id) {
        let newRel = {id: {productId: productId, themeId: id}};
        const response = await fetch('/api/relp_product_topic/add', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json',
            },
            body: JSON.stringify(newRel)
        });
        if (!response.ok) {
            console.error("Topic Relation add request responded with failure.");
        }
    }

    const updateRelMembers = () => {
        relMembers.forEach((id) => {
            if (!newRelMembers.includes(id)) {
                deleteRelMember(id);
            }
        })
        newRelMembers.forEach((id) => {
            if (!relMembers.includes(id)) {
                addRelMember(id);
            }
        })
    }

    const updateRelPartners = () => {
        relPartners.forEach((id) => {
            if (!newRelPartners.includes(id)) {
                deleteRelPartner(id);
            }
        })
        newRelPartners.forEach((id) => {
            if (!relPartners.includes(id)) {
                addRelPartner(id);
            }
        })
    }

    const updateRelStakeholders = () => {
        relStakeholder.forEach((id) => {
            if (!newRelStakeholder.includes(id)) {
                deleteRelStakeholder(id);
            }
        })
        newRelStakeholder.forEach((id) => {
            if (!relStakeholder.includes(id)) {
                addRelStakeholder(id);
            }
        })
    }

    const updateRelTopics = () => {
        relTopics.forEach((id) => {
            if (!newRelTopics.includes(id)) {
                deleteRelTopic(id);
            }
        })
        newRelTopics.forEach((id) => {
            if (!relTopics.includes(id)) {
                addRelTopic(id);
            }
        })
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (handleValidation() && Object.keys(product).length !== 0) {
            console.log('Validation passed!');
            console.log(product);
            updateProduct();
            updateRelMembers();
            updateRelPartners();
            updateRelStakeholders();
            updateRelTopics();
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!product.title || product.title.length === 0) {
            newErrors.title = 'Title cannot be empty.';
        }

        let [dateIsValid, dateError] = validateDate(product.date);

        if (!dateIsValid) {
            if (dateError === 'empty')
                newErrors.date = 'Date cannot be empty.';
            if (dateError === 'format')
                newErrors.date = 'Please use the format YYYY-MM-DD.';
            if (dateError === 'month')
                newErrors.date = 'Month is invalid.';
            if (dateError === 'day')
                newErrors.date = 'Day is invalid.';
        }

        if (product.peerReviewed === null) {
            newErrors.peerReviewed = 'Peer reviewed cannot be undefined.';
        }

        if (!product.type || product.type.length === 0) {
            newErrors.type = 'Type cannot be empty.';
        }

        if (!product.authorsAll || product.authorsAll.length === 0) {
            newErrors.authorsAll = 'All authors cannot be empty.';
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.memberAuthors = 'Member Authors cannot be empty.';
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    return (
        <div className="EditProduct FormPage">
            <h2>{t('page_titles.edit_product')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox 
                        name='title'
                        labelText={t('product.title')}
                        text={product.title}
                        required={true}
                        errorMessage={errors.title}
                        onChange={handleProductChange}
                    />
                    <Date
                        name='date'
                        labelText={t('product.date')}
                        textValue={product.date}
                        errorMessage={errors.date}
                        onChange={handleProductChange}
                    />
                    <Checkbox
                        name='onGoing'
                        labelText={t('product.ongoing')}
                        checkedNum={product.onGoing}
                        onChange={handleProductChange}
                    />
                    <Checkbox
                        name='peerReviewed'
                        labelText={t('product.peer_review')}
                        checkedNum={product.peerReviewed}
                        errorMessage={errors.peerReviewed}
                        onChange={handleProductChange}
                    />
                    <Dropdown
                        name='type'
                        labelText='Type:'
                        selectedChoice={product.type}
                        choices={productType.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr
                        }))}
                        errorMessage={errors.type}
                        onChange={handleProductChange}
                    />
                    <Textbox 
                        name='doi'
                        labelText={t('product.doi')}
                        text={product.doi}
                        onChange={handleProductChange}
                    />
                    <Textarea 
                        name='authorsAll'
                        labelText={t('product.all_authors')}
                        text={product.authorsAll}
                        errorMessage={errors.authorsAll}
                        rows={10} cols={30}
                        onChange={handleProductChange}
                    />
                    <DropdownSelectList
                        name='memberAuthors'
                        labelText='Member Authors:'
                        noneOptionText='Add member author'
                        choices={members.map(e => ({
                            id: e.id,
                            name: e.firstName + ' ' + e.lastName
                        }))}
                        selectedChoices={newRelMembers}
                        errorMessage={errors.memberAuthors}
                        onChange={handleRelMembersChange}
                    />
                    <DropdownSelectList
                        name='targetStakeholders'
                        labelText='Target Stakeholders:'
                        noneOptionText='Add target stakeholder'
                        choices={targetStakeholder.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        selectedChoices={newRelStakeholder}
                        onChange={handleRelStakeholdersChange}
                    />
                    <DropdownSelectList
                        name='partners'
                        labelText='Partners:'
                        noneOptionText='Add partner'
                        choices={partners.map(e => ({
                            id: e.id,
                            name: e.name
                        }))}
                        selectedChoices={newRelPartners}
                        onChange={handleRelPartnersChange}
                    />
                    <DropdownSelectList
                        name='topics'
                        labelText='Topics:'
                        noneOptionText='Add topic'
                        choices={topic.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        selectedChoices={newRelTopics}
                        onChange={handleRelTopicsChange}
                    />
                    <Textarea 
                        name='notes'
                        labelText={t('product.notes')}
                        text={product.notes}
                        rows={10} cols={30}
                        onChange={handleProductChange}
                    />
                </div>
                <div className='buttons'>
                    <Button text={t('button.submit')} type={1} htmlButtonType='submit' />
                    <Button text={t('button.cancel')} type={2} />
                </div>
            </form>
        </div>
    );
}

export default EditProduct;
