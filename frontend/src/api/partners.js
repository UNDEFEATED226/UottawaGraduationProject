// --------------------------- GET ---------------------------

export const getPartner = async (id) => {
    const response = await fetch(`/api/main_partners/find_by_id?id=${id}`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getPartners = async () => {
    const response = await fetch('/api/main_partners/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getPartnerMembers = async (partnerId) => {
    const response = await fetch(`/api/relp_partner_member/find_all_by_partner_id?partnerId=${partnerId}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

// -------------------------- UPDATE --------------------------

export const addPartner = async (partner) => {
    const response = await fetch('/api/main_partners/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(partner)
    });
    return response.ok;
}

export const addPartnerMember = async (partnerId, memberId) => {
    let newRel = {id: {partnerId: partnerId, memberId: memberId}};
    const response = await fetch('/api/relp_partner_member/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

export const deletePartnerMember = async (partnerId, memberId) => {
    const response = await fetch(`/api/relp_partner_member/delete_by_id?partnerId=${partnerId}&memberId=${memberId}`);
    return response.ok;
}

export const updatePartner = async (partner) => {
    const response = await fetch('/api/main_partners/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(partner)
    });
    return response.ok;
}

export const updatePartnerMembers = async (partnerId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deletePartnerMember(partnerId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addPartnerMember(partnerId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}