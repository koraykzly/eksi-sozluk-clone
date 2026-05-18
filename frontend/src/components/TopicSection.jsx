import "@/assets/css/Global.css";
import { useState, useEffect } from "react";
import {
  getDebeApi,
  getDraftsApi,
  getPopularTopicsApi,
  getTodayTopicsApi,
  getNaiveUsersEntries,
  getEntriesRelatedTag,
} from "@/api/ApiService";
import Topic from "./Topic";
import Debe from "./Debe";
import ClickableBox from "./ClickableBox";


// type: bugün, gündem, debe, kenar
const TopicSection = ({ type, topicSectionRef }) => {
  const [data, setData] = useState([]);

  const getTodayTopics = () => {
    getTodayTopicsApi().then((response) => {
      setData(response.data.content);
    });
  };

  useEffect(() => {
    
    if (type === "bugün") {
      getTodayTopics();
    } else if (type === "gündem") {
      getPopularTopicsApi().then((response) => {
        setData(response.data.content);
      });
    } else if (type === "debe") {
      getDebeApi().then((response) => {
        setData(response.data.content);
      });

    } else if (type === "kenar") {
      getDraftsApi().then((response) => {
        setData(response.data.content);
      });
    } else if (type === "çaylaklar") {
      getNaiveUsersEntries().then((response) => {
        setData(response.data.content);
      });
    } else if (type.startsWith("#")) {
      getEntriesRelatedTag(type.slice(1)).then((response) => {
        setData(response.data.content);
      });
    }

  }, [type]);

  var title = type;
  if (type === "debe") {
    title = "dünün en beğenilen entry'leri";
  } else if(type === "çaylaklar") {
    title = "çaylaklar bugün";
  }

  const renderList = () => {
    if (type === "bugün") {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle}
          entryCount={item.entryCountSinceMidnight} key={index} />;
      });
    } else if (type === "gündem") {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle} 
          entryCount={item.entryCountLast24Hours} key={index} />;
      });
    } else if (type === "debe") {
      return data.map((item, index) => {
        return <Debe entryId={item.entryId} title={item.topicTitle} key={index} />;
      });     
    } else if (type === "kenar") {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle} key={index} />;
      });
    } else if (type === "çaylaklar") {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle}
          entryCount={item.entryCountSinceMidnight} key={index} />;
      });
    } else if (type.startsWith("#")) {
      return data.map((item, index) => {
        return <Topic id={item.topicId} title={item.topicTitle}
          entryCount={item.entryCountSinceMidnight} key={index} />;
      });
    }
  };

  return (
    <div className="left-side">
      <nav ref={topicSectionRef} className="partial-left-side">
        <div className="left-side-title">
          <h2>{title}</h2>
          {/* {loading ? <Loading /> : null} */}
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
