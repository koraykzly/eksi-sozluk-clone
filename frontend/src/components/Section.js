import Entry from "./Entry";
import EntryContainer from "./EntryContainer";
import "./css/Global.css";

const Section = () => {
  return (
    <>
      <div className="section">
        <div className="topic">
          <h1>
            <a>14 ekim 2023</a>
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
            <Entry />
            <Entry />
            <Entry />
            <Entry />
          </EntryContainer>
        </div>
      </div>
    </>
  );
};

export default Section;
