import apiClient from './apiClient';

// Entries

export const insertEntryApi = (content, topicId) => {
    return apiClient.post('/api/entries/', {
        content: content,
        topicId: topicId
    });
};

export const getEntryApi = (entryId) => {
    return apiClient.get(`/api/entries/${entryId}`);
};

export const addEntryToFavoriteApi = (entryId) => {
    return apiClient.post(`/api/entries/favorities/${entryId}`);
};

// auth

export const authenticateUserApi = (username, password) => {
    return apiClient.post('/api/auth/login', {
        username: username,
        password: password
    });
};

export const registerUserApi = (username, email, password, birthday, gender) => {
    return apiClient.post('/api/auth/signup', {
        username: username,
        email: email,
        password: password,
        birthday: birthday,
        gender: gender
    });
};


// user

export const getUserApi = (username) => {
    return apiClient.get(`/api/users/${username}`);
};

export const getUserEntriesApi = (username) => {
    return apiClient.get(`/api/users/${username}/entries`);
};

export const getUserFollowersApi = (username) => {
    return apiClient.get(`/api/users/${username}/followers`);
};

export const getUserFavoriteEntriesApi = (username) => {
    return apiClient.get(`/api/users/${username}/favorites`);
};


// topic

export const getTodayTopicsApi = () => {
    return apiClient.get('/api/topics/today');
};

export const getPopularTopicsApi = () => {
    return apiClient.get('/api/topics/popular');
};

export const getTopicTagsApi = () => {
    return apiClient.get('/api/topics/tags');
};

export const insertTopicApi = (entryContent, topicTitle) => {
    return apiClient.post('/api/topics/', {
        entryContent: entryContent,
        topicTitle: topicTitle,
        // userId: userId
    });
};

export const getEntriesByTopicIdApi = (topicId) => {
    return apiClient.post(`/api/topics/id/${topicId}`);
};