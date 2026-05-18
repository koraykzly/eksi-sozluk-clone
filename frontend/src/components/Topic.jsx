import React from "react";
import { Link } from "react-router-dom";

const Topic = ({ id, title, entryCount }) => {
  return (
    <li>
      <Link to={`/topic/${id}`} className="not-underlined">
        {title}
        <small style={{"position": "absolute", "right": "0", top: "10px", "color": "#666", "fontSize": "1em"}}>
          {entryCount}
        </small>
      </Link>
    </li>
  );
};

export default Topic;
