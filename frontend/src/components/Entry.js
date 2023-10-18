import DownVote from "./TempIcons/DownVote";
import Favorite from "./TempIcons/Favorite";
import UpVote from "./TempIcons/UpVote";

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
  if (data == null) {
    data = {
      content: "lorem ipsum",
      username: "koraykzly",
      datetime: "14.10.2023 20:24",
      favCount: 5,
    };
  }

  return (
    <li className="entry-item">
      <div className="entry-content">{data.content}</div>
      <div className="entry-footer">
        <div className="feedback-container">
          <div className="feedback">
            <a>Share</a>
            <a>...</a>
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
              <Favorite style={{height: "15px"}} color={"#bdbdbd"} />
            </a>
            <a>{data.favCount}</a>
          </span>
        </div>

        <div className="user-info">
          <a className="username-info">{data.username}</a>
          <a className="user-datetime">{formatDate(data.dateTime)}</a>
        </div>
      </div>
    </li>
  );
};

export default Entry;
