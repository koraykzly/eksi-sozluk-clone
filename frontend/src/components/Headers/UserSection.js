import "assets/css/Global.css";
import Dots from "components/TempIcons/Dots";
import Message from "components/TempIcons/Message";
import User from "components/TempIcons/User";
import Event from "components/TempIcons/Event";
import { useAuth } from "hooks/useAuth";
import { Link } from "react-router-dom";
import DropDownMenu from "components/DropDownMenu";
import { useState } from "react";

const UserSection = () => {
  const auth = useAuth();
  const [menu, openMenu] = useState(false);

  // fix later
  let event = true;

  return (
    <>
      <div className="auth-container">
        <ul className="auth-section-list">
          <li>
            <Link to={`user/${auth.username}`}>
              <User />
              ben
            </Link>
          </li>
          <li>
            <Link to="message">
              <Message />
              mesaj
            </Link>
          </li>
          <li>
            <Link to="events" className={event ? "events-highlighted" : ""}>
              <Event />
              <span>olay</span>
            </Link>
          </li>
          <li>
            <a onClick={() => openMenu(true)}>
              <Dots />
            </a>
            <DropDownMenu isOpen={menu} setIsOpen={openMenu} />
          </li>
        </ul>
      </div>
    </>
  );
};

export default UserSection;
