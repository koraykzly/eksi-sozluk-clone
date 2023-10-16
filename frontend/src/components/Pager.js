import React from "react";
import { Link, generatePath, useNavigate } from "react-router-dom";

// const toPage = (i, nav) => {
//   nav(`?page=${i}&size=10`);
// };

const optionGenerator = (to) => {
  const list = [];
  for (let i = 0; i < to; i++) {
    list.push(<option>{i}</option>);
  }
  return list;
};

const Pager = ({ currentPage, totalPage, onChange }) => {
  const nav = useNavigate();

  if (totalPage === undefined) {
    totalPage = 4;
  }
  return (
    <div className="pager">
      <select>
        {optionGenerator(totalPage, nav)}
        {/* <option>1</option>
        <option>2</option> */}
      </select>
      {/* <a>{totalPage}</a> */}
      <Link to={`?page={totalPage}`}>{totalPage}</Link>
      <a>»</a>
    </div>
  );
};

export default Pager;
