import "assets/css/Global.css";
import { useState } from "react";
// import apiClient from "api/apiClient";
import { getPopularTopicsApi, getTodayTopicsApi } from "api/ApiService";
import { useEffect } from "react";
import { Link } from "react-router-dom";

// type: bugün, gündem
const TopicSection = ({ type, selectTopic }) => {
  // if (type === undefined) {
  //   type = "bugün";
  // }

  const [data, setData] = useState([]);

  useEffect(() => {
    if (type === "bugün") {
      getTodayTopicsApi().then((response) => {
        console.log(response.data);
        setData(response.data);
      });
    } else if (type === "gündem") {
      getPopularTopicsApi().then((response) => {
        setData(response.data);
      });
    }
  }, []);

  return (
    <div className="left-side">
      <nav className="partial-left-side">
        <div className="left-side-title">
          <h2>{type}</h2>
        </div>

        {type === "bugün" ? (
          <div className="left-side-box">
            <a className="refresh-text">yenile</a>
          </div>
        ) : null}

        <ul>
          {data.map((item, index) => {
            return (
              <Link to={`${item.id}`} className="not-underlined">
                <li key={index}>
                  <a className="not-underlined">{item.title}</a>
                </li>
              </Link>
            );
          })}
        </ul>
      </nav>
    </div>
  );
};

export default TopicSection;
