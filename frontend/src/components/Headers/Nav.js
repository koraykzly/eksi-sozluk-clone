import "../css/Global.css";

const Nav = () => {
  return (
    <nav className="nav">
      <ul>
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

export default Nav;
