import Entry from "./Entry";
import { useState, useEffect } from "react";
import EntryContainer from "./EntryContainer";
import "assets/css/Global.css";
import { getEntriesByTopicIdApi } from "api/ApiService";
import { useNavigate, useParams } from "react-router-dom";

const EntrySection = () => {
  const { topicId } = useParams();

  const [data, setData] = useState([]);
  const [title, setTitle] = useState("");
  const [error, setError] = useState(false);

  useEffect(() => {
    getEntriesByTopicIdApi(topicId)
      .then((response) => {
        console.log(response.data);
        setData(response.data.entries);
        setTitle(response.data.title);
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
  }, [topicId]);

  if (error) {
    return <>Error</>;
  }

  return (
    <>
      <div className="section">
        <div className="topic">
          <h1>
            <a>{title}</a>
          </h1>

          {/* subtitle menu */}
          <div className="subtitle-container">
            <div className="subtitle-menu">
              <div className="subtitle-item">
                <a>şükela</a>
              </div>
              <div className="subtitle-item">
                <a>başlıkta ara</a>
              </div>
              <div className="subtitle-item">
                <a>takip et</a>
              </div>
              <div className="subtitle-item">
                <a>soru sor</a>
              </div>
            </div>

            <div className="pager">
              <select>
                <option>1</option>
                <option>2</option>
              </select>
              <a>15</a>
              <a>»</a>
            </div>
          </div>

          <EntryContainer>
            {data.map((item, index) => {
              return <Entry key={index} data={item} />;
            })}
          </EntryContainer>
        </div>
      </div>
    </>
  );
};

export default EntrySection;
