
export const getEventTypes = async () => {
    const response = await fetch('/api/types_event/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getFaculties = async () => {
    const response = await fetch('/api/types_faculty/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getGrantSources = async () => {
    const response = await fetch('/api/types_grant_source/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getGrantStatuses = async () => {
    const response = await fetch('/api/types_grant_status/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getPartnerScopes = async () => {
    const response = await fetch('/api/types_partnership_scope/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getPartnerTypes = async () => {
    const response = await fetch('/api/types_partnership_type/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getProductTypes = async () => {
    const response = await fetch('/api/types_product/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getTargetStakeholders = async () => {
    const response = await fetch('/api/types_target_stakeholder/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getTopics = async () => {
    const response = await fetch('/api/types_topic/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getTraineeLevels = async () => {
    const response = await fetch('/api/types_trainee_level/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}
