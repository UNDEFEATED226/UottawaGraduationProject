
export const doLogin = async (credentials) => {
    const response = await fetch('/api/login', {
        method: 'POST',
        body: new URLSearchParams(credentials)
    });
    return response.ok;
}

export const doLogout = async () => {
    const response = await fetch('/api/logout');
    return response.ok;
}
