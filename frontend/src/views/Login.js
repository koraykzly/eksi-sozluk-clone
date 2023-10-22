import React from "react";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="form-container">
      <h1>giriş</h1>
      <form>
        <div className="input-section">
          <label className="input-label">e-mail adresi</label>
          <input className="form-input"></input>
        </div>

        <div className="input-section">
          <label className="input-label">şifre</label>
          <input className="form-input"></input>
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
      {/* <ul>
        <li>
          <Link className="form-helepr-links">şifremi unuttum</Link>
        </li>
        <li>
          <Link className="form-helepr-links">kayıtlı kullanıcı olunası</Link>
        </li>
      </ul> */}

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
