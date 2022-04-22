
// --------------------------- GET ---------------------------

export const getProductAndRelations = async (id) => {
    const data = await Promise.all([
        getProduct(id),
        getProductMembers(id),
        getProductPartners(id),
        getProductStakeholders(id),
        getProductTopics(id),
    ]);
    if (data.includes(null)) return null;
    return {
        product: data[0],
        productMembers: data[1],
        productPartners: data[2],
        productStakeholders: data[3],
        productTopics: data[4],
    };
}

export const getProduct = async (id) => {
    const response = await fetch(`/api/main_products/find_by_id?id=${id}`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getProductMembers = async (id) => {
    const response = await fetch(`/api/relp_product_member/find_all_by_product_id?productId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getProductPartners = async (id) => {
    const response = await fetch(`/api/relp_product_partner/find_all_by_product_id?productId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.partnerId);
    }
    return null;
}

export const getProductStakeholders = async (id) => {
    const response = await fetch(`/api/relp_product_target_stakeholder/find_all_by_product_id?productId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.targetStakeholderId);
    }
    return null;
}

export const getProductTopics = async (id) => {
    const response = await fetch(`/api/relp_product_topic/find_all_by_product_id?productId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.themeId);
    }
    return null;
}

// -------------------------- UPDATE --------------------------

export const updateProduct = async (product) => {
    const response = await fetch('/api/main_products/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(product)
    });
    return response.ok;
}

export const updateProductMembers = async (productId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteProductMember(productId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addProductMember(productId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteProductMember = async (productId, memberId) => {
    const response = await fetch(`/api/relp_product_member/delete_by_id?productId=${productId}&memberId=${memberId}`);
    return response.ok;
}

export const addProductMember = async (productId, memberId) => {
    let newRel = {id: {productId: productId, memberId: memberId}};
    const response = await fetch('/api/relp_product_member/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

export const updateProductPartners = async (productId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteProductPartner(productId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addProductPartner(productId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteProductPartner = async (productId, partnerId) => {
    const response = await fetch(`/api/relp_product_partner/delete_by_id?productId=${productId}&partnerId=${partnerId}`);
    return response.ok;
}

export const addProductPartner = async (productId, partnerId) => {
    let newRel = {id: {productId: productId, partnerId: partnerId}};
    const response = await fetch('/api/relp_product_partner/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

export const updateProductStakeholders = async (productId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteProductStakeholder(productId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addProductStakeholder(productId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteProductStakeholder = async (productId, targetStakeholderId) => {
    const response = await fetch(`/api/relp_product_target_stakeholder/delete_by_id?productId=${productId}&targetStakeholderId=${targetStakeholderId}`);
    return response.ok;
}

export const addProductStakeholder = async (productId, targetStakeholderId) => {
    let newRel = {id: {productId: productId, targetStakeholderId: targetStakeholderId}};
    const response = await fetch('/api/relp_product_target_stakeholder/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

export const updateProductTopics = async (productId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteProductTopic(productId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addProductTopic(productId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteProductTopic = async (productId, themeId) => {
    const response = await fetch(`/api/relp_product_topic/delete_by_id?productId=${productId}&themeId=${themeId}`);
    return response.ok;
}

export const addProductTopic = async (productId, themeId) => {
    let newRel = {id: {productId: productId, themeId: themeId}};
    const response = await fetch('/api/relp_product_topic/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}
