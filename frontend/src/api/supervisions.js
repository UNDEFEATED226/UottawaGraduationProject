
// --------------------------- ADD ---------------------------

export const addSupervision = async (supervision) => {
    supervision.id = null;
    const response = await fetch('/api/main_supervision/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(supervision)
    });
    if (response.ok) {
        const body = await response.json();
        return body.id;
    }
    return null;
}

// --------------------------- GET ---------------------------

export const getAllSupervisions = async () => {
    const response = await fetch(`/api/main_supervision/find_all`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getSupervisionAndRelations = async (id) => {
    const data = await Promise.all([
        getSupervision(id),
        getSupervisionPrincipalSupervisors(id),
        getSupervisionCoSupervisors(id),
        getSupervisionThesisAdvisoryCommittees(id),
    ]);
    if (data.includes(null)) return null;
    return {
        supervision: data[0],
        supervisionPrincipalSupervisors: data[1],
        supervisionCoSupervisors: data[2],
        supervisionThesisAdvisoryCommittees: data[3],
    };
}

export const getSupervision = async (id) => {
    const response = await fetch(`/api/main_supervision/find_by_id?id=${id}`);
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getSupervisionPrincipalSupervisors = async (id) => {
    const response = await fetch(`/api/relp_supervision_principal_supervisor/find_all_by_supervision_id?supervisionId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getSupervisionCoSupervisors = async (id) => {
    const response = await fetch(`/api/relp_supervision_co_supervisor/find_all_by_supervision_id?supervisionId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

export const getSupervisionThesisAdvisoryCommittees = async (id) => {
    const response = await fetch(`/api/relp_supervision_thesis_advisory_committee/find_all_by_supervision_id?supervisionId=${id}`);
    if (response.ok) {
        const body = await response.json();
        return body.map(e => e.id.memberId);
    }
    return null;
}

// -------------------------- UPDATE --------------------------

export const updateSupervision = async (supervision) => {
    const response = await fetch('/api/main_supervision/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(supervision)
    });
    return response.ok;
}

// ----------------- UPDATE PRINCIPAL SUPERVISORS -----------------

export const updateSupervisionPrincipalSupervisors = async (supervisionId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteSupervisionPrincipalSupervisor(supervisionId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addSupervisionPrincipalSupervisor(supervisionId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteSupervisionPrincipalSupervisor = async (supervisionId, memberId) => {
    const response = await fetch(`/api/relp_supervision_principal_supervisor/delete_by_id?supervisionId=${supervisionId}&memberId=${memberId}`);
    return response.ok;
}

export const addSupervisionPrincipalSupervisor = async (supervisionId, memberId) => {
    let newRel = {id: {supervisionId: supervisionId, memberId: memberId}};
    const response = await fetch('/api/relp_supervision_principal_supervisor/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------- UPDATE CO-SUPERVISORS -----------------

export const updateSupervisionCoSupervisors = async (supervisionId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteSupervisionCoSupervisor(supervisionId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addSupervisionCoSupervisor(supervisionId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteSupervisionCoSupervisor = async (supervisionId, memberId) => {
    const response = await fetch(`/api/relp_supervision_co_supervisor/delete_by_id?supervisionId=${supervisionId}&memberId=${memberId}`);
    return response.ok;
}

export const addSupervisionCoSupervisor = async (supervisionId, memberId) => {
    let newRel = {id: {supervisionId: supervisionId, memberId: memberId}};
    const response = await fetch('/api/relp_supervision_co_supervisor/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}

// ----------------- UPDATE CO-SUPERVISORS -----------------

export const updateSupervisionThesisAdvisoryCommittees = async (supervisionId, oldRels, newRels) => {
    const promises = [];
    oldRels.forEach((id) => {
        if (!newRels.includes(id)) {
            promises.push(deleteSupervisionThesisAdvisoryCommittee(supervisionId, id));
        }
    })
    newRels.forEach((id) => {
        if (!oldRels.includes(id)) {
            promises.push(addSupervisionThesisAdvisoryCommittee(supervisionId, id));
        }
    })
    const result = await Promise.all(promises);
    return !result.includes(false);
}

export const deleteSupervisionThesisAdvisoryCommittee = async (supervisionId, memberId) => {
    const response = await fetch(`/api/relp_supervision_thesis_advisory_committee/delete_by_id?supervisionId=${supervisionId}&memberId=${memberId}`);
    return response.ok;
}

export const addSupervisionThesisAdvisoryCommittee = async (supervisionId, memberId) => {
    let newRel = {id: {supervisionId: supervisionId, memberId: memberId}};
    const response = await fetch('/api/relp_supervision_thesis_advisory_committee/add', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(newRel)
    });
    return response.ok;
}
