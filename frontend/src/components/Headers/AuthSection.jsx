import "@/assets/css/Global.css";
import { Link } from "react-router-dom";

const AuthSection = () => {
  return (
    <>
      <div className="auth-container">
        <ul className="auth-section-list">
          <li>
            <Link to="/login">giris</Link>
          </li>
          <li>
            <Link to="/register">kayıt ol</Link>
          </li>
        </ul>
      </div>
    </>
  );
};

export default AuthSection;
