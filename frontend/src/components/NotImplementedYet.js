import React from "react";

const NotImplementedYet = ({children}) => {
  return (
    <div
      style={{
        textAlign: "center",
        width: "100%",
        margin: "100px auto"
      }}
    >
      <p>This page not implemented yet :)</p>
      <p>{children}</p>
    </div>
  );
};

export default NotImplementedYet;
