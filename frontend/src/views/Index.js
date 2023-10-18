import { getRandomEntriesApi } from "api/ApiService";
import "assets/css/Global.css";
import Entry from "components/Entry";
import NotImplementedYet from "components/NotImplementedYet";
import { useEffect, useState } from "react";
import EntrySingle from "components/EntrySingle";
import EntryContainer from "components/EntryContainer";

const Index = ({clearTag}) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getRandomEntriesApi().then((res) => {
      setData(res.data);
    });
  }, []);

  clearTag()
  // return <NotImplementedYet>Fill with random entries</NotImplementedYet>;
  return (
    <EntryContainer>
      {data.map((item, index) => {
        return <EntrySingle key={index} data={item}></EntrySingle>;
      })}
    </EntryContainer>
  );
};

export default Index;
