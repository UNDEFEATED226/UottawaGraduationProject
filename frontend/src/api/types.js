
export const getFaculties = async () => {
    const response = await fetch('/api/types_faculty/find_all');
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
