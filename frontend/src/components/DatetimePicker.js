import React from "react";

function* range(start, end) {
  for (let i = start; i <= end; i++) {
    yield i;
  }
}

function* reverseRange(start, end) {
  for (let i = start; end <= i; i--) {
    yield i;
  }
}

const MONTHS = [
  "ocak",
  "şubat",
  "mart",
  "nisan",
  "mayıs",
  "haziran",
  "temmuz",
  "ağustos",
  "eylül",
  "ekim",
  "kasım",
  "aralık",
];

const DatetimePicker = ({ values, onChangeFunctions }) => {
  /*
   * values={ dateDay, dateMonth, dateYear }
   * onChangeFunctions={ setDateDay, setDateMonth, setDateYear }
   */
  return (
    <>
      <div className="form-date">
        <label>doğum tarihi</label>
        <div className="form-date-inner">
          <select
            value={values?.dateDay}
            onChange={(e) => onChangeFunctions?.setDateDay(e.target.value)}
          >
            <option value=""></option>
            {[...range(1, 31)].map((item) => {
              return <option value={item}>{item}</option>;
            })}
          </select>
          <select
            value={values?.dateMonth}
            onChange={(e) => onChangeFunctions?.setDateMonth(e.target.value)}
          >
            <option value=""></option>
            {MONTHS.map((item) => {
              return <option value={item}>{item}</option>;
            })}
          </select>
          <select
            value={values?.dateYear}
            onChange={(e) => onChangeFunctions?.setDateYear(e.target.value)}
          >
            <option value=""></option>
            {[...reverseRange(2006, 1900)].map((item) => {
              return <option value={item}>{item}</option>;
            })}
          </select>
        </div>
      </div>
      <div className="clear"></div>
    </>
  );
};

export default DatetimePicker;
