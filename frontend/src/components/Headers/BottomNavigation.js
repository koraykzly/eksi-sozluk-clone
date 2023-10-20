import "assets/css/Global.css";
import { useAuth } from "hooks/useAuth";
import { NAV_TAGS } from "constants/tags";
import { Link } from "react-router-dom";
import { useState } from "react";

const BottomNavigation = ({ isAuthenticated, updateTopicSection, tag }) => {
  // console.log("render BottomNavigation");

  const isActive = (current) => {
    if (current === tag) {
      return "active-tag";
    }
    return "";
  };

  const setCurrentTag = (_tag) => {
    updateTopicSection(_tag);
  };

  return (
    <>
      <div className="clear"></div>
      <nav className="nav">
        <ul>
          {isAuthenticated ? (
            <li
              className={isActive("bugün")}
              onClick={() => setCurrentTag("bugün")}
            >
              bugün
            </li>
          ) : null}

          <li
            className={isActive("gündem")}
            onClick={() => setCurrentTag("gündem")}
          >
            gündem
          </li>
          <li
            className={isActive("debe")}
            onClick={() => setCurrentTag("debe")}
          >
            debe
          </li>
          <li
            className={isActive("sorunsallar")}
            onClick={() => setCurrentTag("sorunsallar")}
          >
            sorunsallar
          </li>

          {isAuthenticated ? (
            <>
              <li>takip</li>
              <li>son</li>
              <li>kenar</li>
              <li>çaylaklar</li>
            </>
          ) : null}

          {NAV_TAGS.slice(0, 3).map((item, index) => {
            return <li key={index}>{`#${item}`}</li>;
          })}
          <li>...</li>
        </ul>
        <div className="right-nav">
          <div className="external-site">
            <Link className="logo-pena"></Link>
            {/* <img src="https://ekstat.com/img/new-logos/pena-text-logo-dark@2x.png"></img> */}
          </div>
          <div className="external-site">
            <Link className="logo-eksi-seyler"></Link>
            {/* <img src="https://ekstat.com/img/new-logos/eksiseyler-text-logo-dark@2x.png"></img> */}
          </div>
        </div>
      </nav>
    </>
  );
};

export default BottomNavigation;

/*
<li>bugün</li>
<li>gündem</li>
<li>debe</li>
<li>sorunsallar</li>
<li>takip</li>
<li>son</li>
<li>kenar</li>
<li>çaylaklar</li>
<li>#spor</li>
<li>#ilişkiler</li>
<li>#siyaset</li>
*/
