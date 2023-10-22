import React from "react";

const GenderPicker = ({ gender, onGenderChange }) => {
  const handleClick = (selected, e) => {
    e.preventDefault();
    onGenderChange(selected);
  };
  return (
    <>
      <div className="gender-container">
        <label>cinsiyet</label>
        <div className="gender-inner">
          <div className="btn-group">
            <button
              // data-value="kadın"
              className={gender === "kadın" ? "btn-active" : ""}
              onClick={(e) => handleClick("kadın", e)}
            >
              kadın
            </button>
            <button
              className={gender === "erkek" ? "btn-active" : ""}
              onClick={(e) => handleClick("erkek", e)}
            >
              erkek
            </button>
            <button
              className={gender === "başka" ? "btn-active" : ""}
              onClick={(e) => handleClick("başka", e)}
            >
              başka
            </button>
            <button
              className={gender === "boşver" ? "btn-active" : ""}
              onClick={(e) => handleClick("boşver", e)}
            >
              boşver
            </button>
          </div>
        </div>
      </div>
      <div className="clear"></div>
    </>
  );
};

export default GenderPicker;
