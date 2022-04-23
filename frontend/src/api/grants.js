
// --------------------------- GET ---------------------------

export const getAllGrants = async () => {
    const response = await fetch(`/api/main_grants/find_all`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getGrantAndRelations = async (id) => {
    const data = await Promise.all([
        getGrant(id),
        getGrantMembers(id),
        getGrantMemberInvestigators(id),
        getGrantTopics(id),
    ]);
    if (data.includes(null)) return null;
    return {
        grant: data[0],
        grantMembers: data[1],
        grantMemberInvestigators: data[2],
        grantTopics: data[3],
    };
}

export const getGrant = async (id) => {
    const response = await fetch(`/api/main_grants/find_by_id?id=${id}`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getGrantMembers = async (id) => { 
    const response = await fetch(`/api/relp_grant_member/find_all_by_grant_id?grantId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getGrantMemberInvestigators = async (id) => {
    const response = await fetch(`/api/relp_grant_member_investigator/find_all_by_grant_id?grantId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getGrantTopics = async (id) => {
    const response = await fetch(`/api/relp_grant_topic/find_all_by_grant_id?grantId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.themeId);
    }
    return null;
}

// -------------------------- UPDATE --------------------------

export const updateGrant = async (grant) => {
    const response = await fetch('/api/main_grants/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(grant)
    });
    return response.ok;
}

// ------------------- UPDATE GRANT MEMBERS -------------------

export const updateGrantMembers = async (grantId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteGrantMember(grantId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addGrantMember(grantId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteGrantMember = async (grantId, memberId) => {
    const response = await fetch(`/api/relp_grant_member/delete_by_id?grantId=${grantId}&memberId=${memberId}`);
    return response.ok;
}

export const addGrantMember = async (grantId, memberId) => {
    let newRel = {id: {grantId: grantId, memberId: memberId}};
    const response = await fetch('/api/relp_grant_member/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ------------------- UPDATE GRANT MEMBER INVESTIGATORS -------------------

export const updateGrantMemberInvestigators = async (grantId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteGrantMemberInvestigator(grantId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addGrantMemberInvestigator(grantId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteGrantMemberInvestigator = async (grantId, memberId) => {
    const response = await fetch(`/api/relp_grant_member_investigator/delete_by_id?grantId=${grantId}&memberId=${memberId}`);
    return response.ok;
}

export const addGrantMemberInvestigator = async (grantId, memberId) => {
    let newRel = {id: {grantId: grantId, memberId: memberId}};
    const response = await fetch('/api/relp_grant_member_investigator/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ---------------------- UPDATE GRANT TOPICS ----------------------

export const updateGrantTopics = async (grantId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteGrantTopic(grantId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addGrantTopic(grantId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteGrantTopic = async (grantId, themeId) => {
    const response = await fetch(`/api/relp_grant_topic/delete_by_id?grantId=${grantId}&themeId=${themeId}`);
    return response.ok;
}

export const addGrantTopic = async (grantId, themeId) => {
    let newRel = {id: {grantId: grantId, themeId: themeId}};
    const response = await fetch('/api/relp_grant_topic/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}
