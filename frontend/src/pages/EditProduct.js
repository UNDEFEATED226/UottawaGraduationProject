import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date, { validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import { useNavigate, useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

import {
    addProduct,
    getProductAndRelations,
    updateProduct,
    updateProductMembers,
    updateProductPartners,
    updateProductStakeholders,
    updateProductTopics
} from 'api/products';

import { getMemberNames } from 'api/members';
import { getAllPartners } from 'api/partners';
import { getProductTypes, getTargetStakeholders, getTopics } from 'api/types';

const EditProduct = ({isNew}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { productId } = useParams();
    const navigate = useNavigate();

    // Main_Product data
    const [product, setProduct] = useState({});

    // Member authors relation
    const [relMembers, setRelMembers] = useState([]);
    const [newRelMembers, setNewRelMembers] = useState([]);

    // Product's partners relation
    const [relPartners, setRelPartners] = useState([]);
    const [newRelPartners, setNewRelPartners] = useState([]);

    // Product's target stakeholders relation
    const [relStakeholders, setRelStakeholders] = useState([]);
    const [newRelStakeholders, setNewRelStakeholders] = useState([]);

    // Chosen Topics relation
    const [relTopics, setRelTopics] = useState([]);
    const [newRelTopics, setNewRelTopics] = useState([]);

    // For populating dropdown options
    const [members, setMembers] = useState([]);
    const [partners, setPartners] = useState([]);
    const [productTypes, setProductTypes] = useState([]);
    const [targetStakeholders, setTargetStakeholders] = useState([]);
    const [topics, setTopics] = useState([]);

    // See handleValidation function
    const [errors, setErrors] = useState({});

    const fetchData = useCallback(async () => {
        const results = await getProductAndRelations(productId);
        if (results != null) {
            setProduct(results.product);
            setRelMembers(results.productMembers);
            setNewRelMembers(results.productMembers);
            setRelPartners(results.productPartners);
            setNewRelPartners(results.productPartners);
            setRelStakeholders(results.productStakeholders);
            setNewRelStakeholders(results.productStakeholders);
            setRelTopics(results.productTopics);
            setNewRelTopics(results.productTopics);
        }
        else pushNotification('negative', t('error.unable_fetch'));
    }, [productId, pushNotification, t])

    const fetchTypes = useCallback(async () => {
        const results = await Promise.all([
            getMemberNames(),
            getAllPartners(),
            getProductTypes(),
            getTargetStakeholders(),
            getTopics(),
        ]);
        if (!results.includes(null)) {
            setMembers(results[0]);
            setPartners(results[1]);
            setProductTypes(results[2]);
            setTargetStakeholders(results[3]);
            setTopics(results[4]);
        }
        else pushNotification('negative', t('error.unable_fetch_types'));
    }, [pushNotification, t])

    useEffect(() => {
        if (!isNew) fetchData();
        fetchTypes();
    }, [fetchData, fetchTypes, isNew])

    const handleProductChange = (key, value) => {
        setProduct(curr => ({ ...curr, [key]: value }));
    }

    const handleRelMembersChange = (_, newMembers) => {
        setNewRelMembers(newMembers);
    }

    const handleRelPartnersChange = (_, newPartners) => {
        setNewRelPartners(newPartners);
    }
    
    const handleRelStakeholdersChange = (_, newStakeholders) => {
        setNewRelStakeholders(newStakeholders);
    }
    
    const handleRelTopicsChange = (_, newTopics) => {
        setNewRelTopics(newTopics);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (handleValidation()) {
            pushNotification('info', t('feedback.submitting'));
            let id = productId;
            if (isNew) {
                id = await addProduct(product);
                if (id == null) {
                    pushNotification('negative', t('error.unable_submit'));
                    return;
                }
            }
            const results = await Promise.all([
                isNew ? true : updateProduct(product),
                updateProductMembers(id, relMembers, newRelMembers),
                updateProductPartners(id, relPartners, newRelPartners),
                updateProductStakeholders(id, relStakeholders, newRelStakeholders),
                updateProductTopics(id, relTopics, newRelTopics),
            ]);
            if (!results.includes(false)) {
                if (isNew) {
                    pushNotification('positive', t('feedback.submit_success'));
                    navigate(`/edit_product/${id}`);
                }
                else {
                    await fetchData();
                    pushNotification('positive', t('feedback.submit_success'));
                }
            }
            else {
                pushNotification('negative', t('error.unable_submit'));
            }
        }
        else {
            pushNotification('negative', t('error.invalid_submit'));
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!product.title) {
            newErrors.title = t('error.empty_title');
        }

        let [dateIsValid, dateError] = validateDate(product.date);

        if (!dateIsValid) {
            if (dateError === 'empty')
                newErrors.date = t('error.empty_date');
            if (dateError === 'format')
                newErrors.date = t('error.invalid_date');
            if (dateError === 'month')
                newErrors.date = t('error.invalid_month');
            if (dateError === 'day')
                newErrors.date = t('error.invalid_day');
        }

        if (product.type == null) {
            newErrors.type = t('error.empty_type');
        }

        if (!product.authorsAll) {
            newErrors.authorsAll = t('error.empty_all_authors');
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.memberAuthors = t('error.empty_member_authors');
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm(t('prompt.cancel_unsaved'))) {
            if (isNew) {
                navigate('/my_products');
            }
            else {
                window.scrollTo(0, 0);
                await fetchData();
                setErrors({});
                pushNotification('info', t('feedback.changes_reverted'));
            }
        }
    }

    return (
        <div className="EditProduct FormPage">
            <h2>{isNew ? t('page_titles.new_product') : t('page_titles.edit_product')}</h2>
            <form onSubmit={handleSubmit}>
                <div className='fields'>
                    <Textbox 
                        name='title'
                        labelText={t('product.title')}
                        text={product.title}
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
                        onChange={handleProductChange}
                    />
                    <Dropdown
                        name='type'
                        labelText={t('product.type')}
                        selectedChoice={product.type}
                        choices={productTypes.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.typeEn : e.typeFr
                        }))}
                        hideNoneOption
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
                        labelText={t('product.member_authors')}
                        dropdownLabel={t('dropdown.add_member_author')}
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
                        labelText={t('product.target_stakeholders')}
                        dropdownLabel={t('dropdown.add_target_stakeholder')}
                        choices={targetStakeholders.map(e => ({
                            id: e.id,
                            name: i18n.resolvedLanguage === "en" ? e.nameEn : e.nameFr
                        }))}
                        selectedChoices={newRelStakeholders}
                        onChange={handleRelStakeholdersChange}
                    />
                    <DropdownSelectList
                        name='partners'
                        labelText={t('product.partners')}
                        dropdownLabel={t('dropdown.add_partner')}
                        choices={partners.map(e => ({
                            id: e.id,
                            name: e.name
                        }))}
                        selectedChoices={newRelPartners}
                        onChange={handleRelPartnersChange}
                    />
                    <DropdownSelectList
                        name='topics'
                        labelText={t('product.topics')}
                        dropdownLabel={t('dropdown.add_topic')}
                        choices={topics.map(e => ({
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
                    <Button text={t('button.cancel')} type={2} clickHandler={handleCancel} />
                </div>
            </form>
        </div>
    );
}

export default EditProduct;
