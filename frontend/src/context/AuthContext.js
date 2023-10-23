import { createContext, useState } from "react";
import { apiClient } from "api/apiClient";
import { authenticateUserApi } from "api/ApiService";
import { useEffect } from "react";

export const AuthContext = createContext({
  isAuthenticated: false,
  login: Promise.resolve(),
  logout: Promise.resolve(),
  username: null
});

export default function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const storedToken = localStorage.getItem("accessToken");
    if (storedToken !== null) {
      setAuthenticated(true);

      const username = localStorage.getItem("userData");
      setUsername(JSON.parse(username));

      const jwtToken = "Bearer " + storedToken;
      apiClient.interceptors.request.use((config) => {
        config.headers.Authorization = jwtToken;
        return config;
      });

      apiClient.interceptors.response.use((config) => {
        return config;
      });
    } else {
      setAuthenticated(false);
    }
  }, []);

  async function login(email, password) {
    try {
      const response = await authenticateUserApi(email, password);

      if (response.status === 200) {

        localStorage.setItem("accessToken", response.data.access);
        localStorage.setItem(
          "userData",
          JSON.stringify(response.data.username)
        );

        const jwtToken = "Bearer " + response.data.access;
        apiClient.interceptors.request.use((config) => {
          config.headers.Authorization = jwtToken;
          return config;
        });

        apiClient.interceptors.response.use((config) => {
          return config;
        });

        
        setAuthenticated(true);
        setUsername(response.data.username);

        
        return true;
      } else {
        logout();
        return false;
      }
    } catch (error) {
      logout();
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
    localStorage.removeItem("userData");
    localStorage.removeItem("accessToken");
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, username }}
    >
      {children}
    </AuthContext.Provider>
  );
}
