import React from "react";
import { useParams } from "react-router-dom";
import NotImplementedYet from "components/NotImplementedYet";

const Entry = () => {
  const { id } = useParams();

  return <NotImplementedYet>{`Entry.js (${id})`}</NotImplementedYet>;
};

export default Entry;
