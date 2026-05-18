import DownVote from "./TempIcons/DownVote";
import Favorite from "./TempIcons/Favorite";
import UpVote from "./TempIcons/UpVote";
import Share from "./TempIcons/Share";
import Dots from "./TempIcons/Dots";
import { Link } from "react-router-dom";
import { useState } from "react";
import EntryDropDownMenu from "./EntryDropDownMenu";

function formatDateTime(value) {
  const date = new Date(value);

  return {
    year: date.getFullYear(),
    month: String(date.getMonth() + 1).padStart(2, "0"),
    day: String(date.getDate()).padStart(2, "0"),
    hours: String(date.getHours()).padStart(2, "0"),
    minutes: String(date.getMinutes()).padStart(2, "0"),
  };
}

function formatDate(createdAt, updatedAt) {
  const created = formatDateTime(createdAt);

  let result = `${created.year}.${created.month}.${created.day} ${created.hours}:${created.minutes}`;

  if (updatedAt != null) {
    const updated = formatDateTime(updatedAt);

    const isSameDay =
      created.year === updated.year &&
      created.month === updated.month &&
      created.day === updated.day;

    result += isSameDay
      ? ` ~ ${updated.hours}:${updated.minutes}`
      : ` ~ ${updated.year}.${updated.month}.${updated.day} ${updated.hours}:${updated.minutes}`;
  }

  return result;
}

const Entry = ({ data }) => {
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
            {formatDate(data.createdAt, data.updatedAt)}
          </Link>
        </div>
      </div>
    </li>
  );
};

export default Entry;
