
export const getPartners = async () => {
    const response = await fetch('/api/main_partners/find_all');
    if (response.ok) {
        return await response.json();
    }
    return null;
}
