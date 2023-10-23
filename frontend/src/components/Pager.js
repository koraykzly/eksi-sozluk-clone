import React from "react";
import { Link, useNavigate } from "react-router-dom";

// const toPage = (i, nav) => {
//   nav(`?page=${i}&size=10`);
// };

const optionGenerator = (to, currentPage) => {
  const list = [];
  for (let i = 1; i <= to; i++) {
    list.push(<option key={i} value={i}>{i}</option>);
  }
  return list;
};

const Pager = ({ currentPage, totalPage }) => {
  const nav = useNavigate();

  const handleOptionChange = (event) => {
    const selectedValue = event.target.value;
    nav(`?page=${selectedValue}`); // Yönlendirme yapılıyor
  };

  return (
    <div className="pager">
      {currentPage > 1 ? <Link to={`?page=${currentPage - 1}`}>«</Link> : null}
      <select value={currentPage} onChange={handleOptionChange}>
        {optionGenerator(totalPage, currentPage)}
      </select>
      /<Link to={`?page=${totalPage}`}>{totalPage}</Link>
      {currentPage !== totalPage ? (
        <Link to={`?page=${currentPage + 1}`}>»</Link>
      ) : null}
    </div>
  );
};

export default Pager;
