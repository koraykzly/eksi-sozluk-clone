import Entry from "./Entry";
import { useState, useEffect } from "react";
import EntryContainer from "./EntryContainer";
import "assets/css/Global.css";
import { getEntriesByTopicIdApi } from "api/ApiService";
import { useParams, useLocation } from "react-router-dom";
import Pager from "./Pager";
import SubmitEntrySection from "./SubmitEntrySection";
import TopicSubmenu from "./TopicSubmenu";

const EntrySection = () => {
  const { topicId } = useParams();

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const _page = queryParams.get("page");

  let page = _page - 1;
  if (_page === undefined || _page === null) {
    page = 0;
  }

  // console.log("render entry section", page);

  const [data, setData] = useState([]);
  const [title, setTitle] = useState("");
  const [error, setError] = useState(false);
  const [totalPage, setTotalPage] = useState(0);

  useEffect(() => {
    getEntriesByTopicIdApi(topicId, page)
      .then((response) => {
        setData(response.data.entries.content);
        setTitle(response.data.title);
        setTotalPage(response.data.entries.totalPages);
        window.scrollTo(0, 0);
      })
      .catch((err) => {
        if (err.response.status === 404) {
          // handlee later
          setError(true);
        }
      });

    return () => {
      setError(false);
    };
  }, [topicId, page]);

  if (error) {
    return <>Error</>;
  }

  return (
    <div className="topic">
      <h1>
        <a>{title}</a>
      </h1>

      {/* subtitle menu */}
      <div className="submenu-container">
        <TopicSubmenu />
        {totalPage > 1 ? (
          <Pager currentPage={page + 1} totalPage={totalPage} />
        ) : null}
      </div>

      <EntryContainer>
        {data.map((item, index) => {
          return <Entry key={index} data={item} />;
        })}
      </EntryContainer>

      {totalPage > 1 ? (
        <Pager currentPage={page + 1} totalPage={totalPage} />
      ) : null}

      <SubmitEntrySection title={title}></SubmitEntrySection>
    </div>
  );
};

export default EntrySection;
