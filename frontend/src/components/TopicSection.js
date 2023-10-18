import "assets/css/Global.css";
import { useRef, useState } from "react";
// import apiClient from "api/apiClient";
import { getPopularTopicsApi, getTodayTopicsApi } from "api/ApiService";
import { useEffect } from "react";
import Topic from "./Topic";

// type: bugün, gündem
const TopicSection = ({ type, topicSectionRef }) => {
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

  let title = type;
  if (type === "debe") {
    title = "dünün en beğenilen entry'leri";
  }

  return (
    <div className="left-side">
      <nav ref={topicSectionRef} className="partial-left-side">
        <div className="left-side-title">
          <h2>{title}</h2>
        </div>

        {type === "bugün" ? (
          <div className="left-side-box">
            <a className="refresh-text">yenile</a>
          </div>
        ) : null}

        <ul>
          {data.map((item, index) => {
            return <Topic id={item.id} title={item.title} key={index} />;
          })}
        </ul>
      </nav>
    </div>
  );
};

export default TopicSection;
