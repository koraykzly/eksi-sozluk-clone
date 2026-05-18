import axios from "axios";

export const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

let refreshPromise = null;

const clearAuthData = () => {
  localStorage.removeItem("userData");
  localStorage.removeItem("accessToken");
  localStorage.removeItem("refreshToken");
};

apiClient.interceptors.request.use((config) => {
  const accessToken = localStorage.getItem("accessToken");

  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }

  return config;
});

apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;

    if (
      error.response?.status !== 401 ||
      originalRequest?._retry ||
      originalRequest?.url === "/api/auth/refresh"
    ) {
      return Promise.reject(error);
    }

    originalRequest._retry = true;

    try {
      const refreshToken = localStorage.getItem("refreshToken");

      if (!refreshToken) {
        clearAuthData();
        return Promise.reject(error);
      }

      if (!refreshPromise) {
        refreshPromise = axios.post(
          `${import.meta.env.VITE_API_URL}/api/auth/refresh`,
          { refresh: refreshToken }
        );
      }

      const response = await refreshPromise;
      refreshPromise = null;

      const accessToken = response.data.access;
      localStorage.setItem("accessToken", accessToken);

      originalRequest.headers = originalRequest.headers ?? {};
      originalRequest.headers.Authorization = `Bearer ${accessToken}`;

      return apiClient(originalRequest);
    } catch (refreshError) {
      refreshPromise = null;
      clearAuthData();
      window.location.href = "/login";

      return Promise.reject(refreshError);
    }
  }
);

export default apiClient;
