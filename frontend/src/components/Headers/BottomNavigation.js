import "assets/css/Global.css";
import { useAuth } from "hooks/useAuth";
import { NAV_TAGS } from "constants/tags";

const BottomNavigation = ({ isAuthenticated, selectTag }) => {

  console.log('render BottomNavigation',)

  return (
    <nav className="nav">
      <ul>
        {isAuthenticated ? <li onClick={() => selectTag('bugün')}>bugün</li> : null}

        <li onClick={() => selectTag('gündem')}>gündem</li>
        <li onClick={() => selectTag('debe')}>debe</li>
        <li onClick={() => selectTag('sorunsallar')}>sorunsallar</li>

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
          <img src="https://ekstat.com/img/new-logos/pena-text-logo-dark@2x.png"></img>
        </div>
        <div className="external-site">
          <img src="https://ekstat.com/img/new-logos/eksiseyler-text-logo-dark@2x.png"></img>
        </div>
      </div>
    </nav>
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
