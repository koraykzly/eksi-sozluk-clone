import React from "react";
import { Link } from "react-router-dom";

const Topic = ({id, title}) => {
  return (
    <Link to={`${id}`} className="not-underlined">
      <li>
        <a className="not-underlined">{title}</a>
      </li>
    </Link>
  );
};

export default Topic;
