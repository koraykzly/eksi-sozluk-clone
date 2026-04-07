import React from "react";

const ClickableBox = ({ text, onClick }) => {
  return (
    <div className="left-side-box">
      <a className="refresh-text" onClick={onClick}>{text}</a>
    </div>
  );
};

export default ClickableBox;
