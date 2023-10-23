import { useAuth } from "hooks/useAuth";
import React, { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const username = useRef(null);
  const password = useRef(null);
  const [error, setError] = useState(null);

  const auth = useAuth();
  const nav = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    const success = await auth.login(
      username.current.value,
      password.current.value
    );
    if (success) {
      nav("/");
    } else {
      setError("hatalı kullanıcı ya da şifre, ama hangisi söylemem.");
    }
  };

  return (
    <div className="form-container">
      <h1>giriş</h1>

      {error ? <p className="form-error">{error}</p> : null}

      <form onSubmit={handleLogin}>
        <div className="input-section">
          <label className="input-label">e-mail adresi</label>
          <input ref={username} className="form-input"></input>
        </div>

        <div className="input-section">
          <label className="input-label">şifre</label>
          <input ref={password} className="form-input" type="password"></input>
        </div>

        <div className="input-section">
          <label>
            <input type="checkbox"></input>
            unutma bunları sorucam sonra
          </label>
        </div>

        <button className="form-button">giriş yapmaya çabala</button>
      </form>

      <h1>giremeyiş</h1>
      <Link to="/password-reset" className="form-helper-links">
        şifremi unuttum
      </Link>
      <Link to="/register" className="form-helper-links">
        kayıtlı kullanıcı olunası
      </Link>
    </div>
  );
};

export default Login;
