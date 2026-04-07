import DownVote from "./TempIcons/DownVote";
import Favorite from "./TempIcons/Favorite";
import UpVote from "./TempIcons/UpVote";
import Share from "./TempIcons/Share";
import Dots from "./TempIcons/Dots";
import { Link } from "react-router-dom";
import { useState } from "react";
import EntryDropDownMenu from "./EntryDropDownMenu";

function formatDate(inputDate) {
  const date = new Date(inputDate);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");
  return `${year}.${month}.${day} ${hours}:${minutes}`;
}

const Entry = ({ data }) => {
  // temp data
  if (data === undefined) {
    data = {
      id: 1,
      content: "lorem ipsum",
      username: "koraykzly",
      datetime: "14.10.2023 20:24",
      favCount: 5,
    };
  }

  const [menu, openMenu] = useState(false);

  return (
    <li className="entry-item">
      <div className="entry-content">{data.content}</div>
      <div className="entry-footer">
        <div className="feedback-container">
          <div className="feedback">
            <a>
              <Share />
            </a>
            <a onClick={() => openMenu(true)}>
              <Dots />
            </a>
            <EntryDropDownMenu isOpen={menu} setIsOpen={openMenu}/>
          </div>
          <span className="vote-section">
            <a>
              <UpVote color={"#bdbdbd"} />
            </a>
            <a className="vote-down">
              <DownVote color={"#bdbdbd"} />
            </a>
          </span>
          <span className="favorite-section">
            <a>
              <Favorite />
            </a>
            <a>{data.favCount}</a>
          </span>
        </div>

        <div className="user-info">
          <Link to={`/user/${data.username}`} className="username-info">
            {data.username}
          </Link>
          <Link to={`/entry/${data.id}`} className="user-datetime">
            {formatDate(data.dateTime)}
          </Link>
        </div>
      </div>
    </li>
  );
};

export default Entry;
