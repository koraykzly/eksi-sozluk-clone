import { getRandomEntriesApi } from "@/api/ApiService";
import "@/assets/css/Global.css";

import { useEffect, useState } from "react";
import EntrySingle from "@/components/EntrySingle";
import EntryContainer from "@/components/EntryContainer";

const Index = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    getRandomEntriesApi().then((res) => {
      console.log(res);
      setData(res.data);
    });
  }, []);

  return (
    <EntryContainer>
      {data.map((item, index) => {
        return <EntrySingle key={index} data={item}></EntrySingle>;
      })}
    </EntryContainer>
  );
};

export default Index;
