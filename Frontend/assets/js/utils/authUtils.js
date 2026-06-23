const AuthUtils = {
    getToken: () => Storage.get('token'),
    setToken: (token) => Storage.set('token', token),
    removeToken: () => Storage.remove('token'),
    getUser: () => Storage.get('user'),
    setUser: (user) => Storage.set('user', user),
    isAuthenticated: () => !!Storage.get('token'),
    logout: () => {
        AuthUtils.removeToken();
        Storage.remove('user');
        window.location.href = 'login.html';
    }
};
