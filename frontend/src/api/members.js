
export const getMember = async () => {
    const response = await fetch('/api/main_members/find_current_user');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const getMemberNames = async () => {
    const response = await fetch('/api/main_members/get_names');
    if (response.ok) {
        return await response.json();
    }
    return null;
}

export const updateMember = async (member) => {
    const response = await fetch('/api/main_members/update', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(member)
    });
    return response.ok;
}
