import React, { useState } from "react";

const SubmitEntrySection = ({ title }) => {
  // const MIN_HEIGHT = 105;
  // const [textareaHeight, setTextareaHeight] = useState(MIN_HEIGHT);

  // console.log(textareaHeight);

  // const handleFocus = (event) => {
  //   const textarea = event.target;
  //   console.log('handleFocus', textarea.scrollHeight)
  //   if (textarea.scrollHeight <= MIN_HEIGHT) {
  //     setTextareaHeight(true);
  //   }
  // };

  const handleFocus = () => {  }


  return (
    <>
      <div className="clear"></div>
      <div className="submit-entry-container">
        <div className="entry-field">
          <div className="inner-entry-field">
            {/* edit buttons */}
            <div className="edit-buttons">
              <button>(bkz:)</button>
              <button>hede</button>
              <button>*</button>
              <button>-spoiler-</button>
              <button>http://</button>
              <button>görsel</button>
            </div>

            {/* submit field */}
            {/* <div> */}
            <textarea
              onFocus={handleFocus}
              className="not-focus"
              placeholder={`"${title}" hakkında bilgi verin`}
            ></textarea>
            {/* </div> */}
            <div className="entry-submit-buttons">
              <button className="entry-submit-button">yolla</button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default SubmitEntrySection;
