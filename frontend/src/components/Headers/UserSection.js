import "assets/css/Global.css";
import { useAuth } from "hooks/useAuth";
import { Link } from "react-router-dom";

const UserSection = () => {
  const auth = useAuth()

  return (
    <>
      <div className="auth-container">
        <ul className="list">
          <li>
            <Link to={`user/${auth.username}`}>ben</Link>
          </li>
          <li>
          <Link to="message">mesaj</Link>
          </li>
          <li>
            <Link to="events" className="events">olay</Link>
          </li>
          <li>
            <a>...</a></li>
        </ul>
      </div>
    </>
  );
};

export default UserSection;
