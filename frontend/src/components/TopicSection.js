import "assets/css/Global.css";
import { useRef, useState } from "react";
// import apiClient from "api/apiClient";
import { getPopularTopicsApi, getTodayTopicsApi } from "api/ApiService";
import { useEffect } from "react";
import Topic from "./Topic";
import { getDebeApi } from "api/ApiService";
import Debe from "./Debe";
import ClickableBox from "./ClickableBox";

// type: bugün, gündem, debe
const TopicSection = ({ type, topicSectionRef }) => {
  const [data, setData] = useState([]);

  const getTodayTopics = () => {
    getTodayTopicsApi().then((response) => {
      setData(response.data);
    });
  };

  useEffect(() => {
    if (type === "bugün") {
      getTodayTopics();
    } else if (type === "gündem") {
      getPopularTopicsApi().then((response) => {
        setData(response.data);
      });
    } else if (type === "debe") {
      getDebeApi().then((response) => {
        setData(response.data);
      });
    }
  }, [type]);

  var title = type;
  if (type === "debe") {
    title = "dünün en beğenilen entry'leri";
  }
  const renderList = () => {
    if (type === "debe") {
      return data.map((item, index) => {
        return (
          <Debe entryId={item.entryId} title={item.topicTitle} key={index} />
        );
      });
    } else {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle} key={index} />;
      });
    }
  };

  return (
    <div className="left-side">
      <nav ref={topicSectionRef} className="partial-left-side">
        <div className="left-side-title">
          <h2>{title}</h2>
        </div>

        {type === "bugün" ? (
          <ClickableBox text={"yenile"} onClick={getTodayTopics} />
        ) : null}

        <ul>{renderList()}</ul>
      </nav>
    </div>
  );
};

export default TopicSection;
