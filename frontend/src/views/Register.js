import DatetimePicker from "components/DatetimePicker";
import GenderPicker from "components/GenderPicker";
import React, { useState } from "react";
import { Link } from "react-router-dom";

const Register = () => {
  const [gender, setGender] = useState("boşver");
  const [dateDay, setDateDay] = useState("");
  const [dateMonth, setDateMonth] = useState("");
  const [dateYear, setDateYear] = useState("");

  return (
    <div className="form-container">
      <h1>yeni kullanıcı kaydı</h1>
      <form>
        <div className="input-section">
          <label className="input-label">nick</label>
          <input className="form-input"></input>
        </div>

        <div className="input-section">
          <label className="input-label">email</label>
          <input className="form-input"></input>
        </div>

        <DatetimePicker
          values={{ dateDay, dateMonth, dateYear }}
          onChangeFunctions={{
            setDateDay,
            setDateMonth,
            setDateYear,
          }}
        />
        <GenderPicker gender={gender} onGenderChange={setGender} />

        <div className="input-section"></div>
      </form>
    </div>
  );
};

export default Register;
