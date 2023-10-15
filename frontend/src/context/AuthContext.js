import { createContext, useState } from "react";
import { apiClient } from "api/apiClient";
import { authenticateUserApi } from "api/ApiService";

export const AuthContext = createContext({ 
    isAuthenticated: false, 
    login: Promise.resolve(),
    logout: Promise.resolve(),
    username: '', 
    token: '', 
})

export default function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);

  async function login(username, password) {
    try {
      const response = await authenticateUserApi(username, password);

      if (response.status === 200) {
        const jwtToken = "Bearer " + response.data.access;

        setAuthenticated(true);
        setUsername(username);
        setToken(jwtToken);

        apiClient.interceptors.request.use((config) => {
          console.log("intercepting and adding a token");
          config.headers.Authorization = jwtToken;
          return config;
        });

        apiClient.interceptors.response.use((config) => {
          console.log("intercepting response with config:", config);
          return config;
        });

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
    setToken(null);
    setUsername(null);
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, login, logout, username, token }}
    >
      {children}
    </AuthContext.Provider>
  );
}
