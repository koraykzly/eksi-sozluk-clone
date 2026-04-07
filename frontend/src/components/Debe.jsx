import React from "react";
import { Link } from "react-router-dom";

const Debe = ({ title, entryId }) => {
  return (
    <li>
      <Link to={`/entry/${entryId}`} className="not-underlined">
        {title}
      </Link>
    </li>
  );
};

export default Debe;
