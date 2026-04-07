import React from "react";
import { Link } from "react-router-dom";

const Topic = ({ id, title }) => {
  return (
    <li>
      <Link to={`/topic/${id}`} className="not-underlined">
        {title}
      </Link>
    </li>
  );
};

export default Topic;
