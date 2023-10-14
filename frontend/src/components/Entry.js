import DownVote from "./TempIcons/DownVote";
import Favorite from "./TempIcons/Favorite";
import UpVote from "./TempIcons/UpVote";

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
              <Favorite color={"#bdbdbd"} />
            </a>
            <a>{data.favCount}</a>
          </span>
        </div>

        <div className="user-info">
          <a className="username-info">{data.username}</a>
          <a className="user-datetime">{data.datetime}</a>
        </div>
      </div>
    </li>
  );
};

export default Entry;
