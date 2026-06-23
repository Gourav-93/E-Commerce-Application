const API_BASE_URL = 'http://localhost:8080';

const api = async (endpoint, options = {}) => {
    const url = `${API_BASE_URL}${endpoint}`;
    
    const headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };

    const token = AuthUtils.getToken();
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const config = {
        ...options,
        headers
    };

    if (config.body && typeof config.body === 'object') {
        config.body = JSON.stringify(config.body);
    }

    try {
        const response = await fetch(url, config);
        
        if (response.status === 401) {
            AuthUtils.logout();
            throw new Error('Unauthorized');
        }

        let data = null;
        const text = await response.text();
        if (text) {
            try {
                data = JSON.parse(text);
            } catch (e) {
                data = text; // some spring apis return plain strings
            }
        }
        
        if (!response.ok) {
            throw new Error((data && data.message) || data || 'API Error');
        }
        
        return data;
    } catch (error) {
        console.error('API call failed:', error);
        throw error;
    }
};
