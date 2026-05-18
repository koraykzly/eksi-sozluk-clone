import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
// import NotImplementedYet from "@/components/NotImplementedYet";
import Entry from "@/components/Entry";
import { getEntryApi } from "@/api/ApiService";
import EntryContainer from "@/components/EntryContainer";
// import EntrySingle from "@/components/EntrySingle";
import TopicSubmenu from "@/components/TopicSubmenu";
import SubmitEntrySection from "@/components/SubmitEntrySection";

const EntryPage = () => {
  const { id } = useParams();

  const [entry, setEntry] = useState(null);

  useEffect(() => {
    getEntryApi(id).then((res) => {
      setEntry(res.data);
    });
  }, [id]);

  return entry !== null ? (
    <div className="topic">
      <h1>
        <a>{entry.topicTitle}</a>
      </h1>

      <div className="submenu-container">
        <TopicSubmenu />
      </div>
      <EntryContainer>
        <Entry data={entry}></Entry>
      </EntryContainer>
      <SubmitEntrySection title={entry.topicTitle}></SubmitEntrySection>
    </div>
  ) : null;
};

export default EntryPage;
