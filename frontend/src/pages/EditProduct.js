import './FormPage.css';
import Checkbox from 'components/Checkbox';
import Date, { validateDate } from 'components/Date';
import Dropdown from 'components/Dropdown';
import DropdownSelectList from 'components/DropdownSelectList';
import Textarea from 'components/Textarea';
import Textbox from 'components/Textbox';
import Button from 'components/Button';

import {
    getProductAndRelations,
    updateProduct,
    updateProductMembers,
    updateProductPartners,
    updateProductStakeholders,
    updateProductTopics
} from 'api/products';

import { getMemberNames } from 'api/members';
import { getPartners } from 'api/partners';
import { getProductTypes, getTargetStakeholders, getTopics } from 'api/types';

import { useOutletContext, useParams } from 'react-router-dom';
import { useCallback, useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';

const EditProduct = ({userId}) => {

    const { t, i18n } = useTranslation();
    const { pushNotification } = useOutletContext();
    const { productId } = useParams();

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
    const [targetStakeholder, setTargetStakeholders] = useState([]);
    const [topics, setTopics] = useState([]);

    // See handleValidation function
    const [errors, setErrors] = useState({});

    const fetchEverything = useCallback(async () => {
        const results = await Promise.all([
            getProductAndRelations(productId),
            getMemberNames(),
            getPartners(),
            getProductTypes(),
            getTargetStakeholders(),
            getTopics(),
        ]);
        if (!results.includes(null)) {
            setProduct(results[0].product);
            setRelMembers(results[0].productMembers);
            setNewRelMembers(results[0].productMembers);
            setRelPartners(results[0].productPartners);
            setNewRelPartners(results[0].productPartners);
            setRelStakeholders(results[0].productStakeholders);
            setNewRelStakeholders(results[0].productStakeholders);
            setRelTopics(results[0].productTopics);
            setNewRelTopics(results[0].productTopics);
            setMembers(results[1]);
            setPartners(results[2]);
            setProductTypes(results[3]);
            setTargetStakeholders(results[4]);
            setTopics(results[5]);
        }
        else pushNotification('negative', 'Failed to get your data!');
    }, [productId, pushNotification])

    useEffect(() => {
        fetchEverything();
    }, [fetchEverything])

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

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (handleValidation()) {
            pushNotification('info', 'Submitting...');
            const results = await Promise.all([
                updateProduct(product),
                updateProductMembers(productId, relMembers, newRelMembers),
                updateProductPartners(productId, relPartners, newRelPartners),
                updateProductStakeholders(productId, relStakeholders, newRelStakeholders),
                updateProductTopics(productId, relTopics, newRelTopics),
            ]);
            if (!results.includes(false)) {
                await fetchEverything();
                pushNotification('positive', 'Submitted successfully!');
            }
            else {
                pushNotification('negative', 'Submission failed! Please try again later.');
            }
        }
        else {
            pushNotification('negative', 'There are errors in the form.');
        }
    }

    const handleValidation = () => {
        let newErrors = {};

        if (!product.title) {
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

        if (product.peerReviewed == null) {
            newErrors.peerReviewed = 'Peer reviewed cannot be undefined.';
        }

        if (product.type == null) {
            newErrors.type = 'Type cannot be empty.';
        }

        if (!product.authorsAll) {
            newErrors.authorsAll = 'All authors cannot be empty.';
        }

        if (!newRelMembers || newRelMembers.length === 0) {
            newErrors.memberAuthors = 'Member Authors cannot be empty.';
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    }

    const handleCancel = async () => {
        if (window.confirm('Are you sure? Any unsaved changes will be lost.')) {
            window.scrollTo(0, 0);
            await fetchEverything();
            setErrors({});
            pushNotification('info', 'Changes reverted.');
        }
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
                        choices={productTypes.map(e => ({
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
                        selectedChoices={newRelStakeholders}
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
