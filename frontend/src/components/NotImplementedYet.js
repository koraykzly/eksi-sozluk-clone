import React from "react";

const NotImplementedYet = ({children}) => {
  return (
    <div
      style={{
        textAlign: "center",
        width: "100%",
        marginTop: "50px"
      }}
    >
      <p>This page not implemented yet :)</p>
      <p>{children}</p>
    </div>
  );
};

export default NotImplementedYet;
