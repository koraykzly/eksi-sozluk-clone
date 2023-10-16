import "assets/css/Global.css";
import { Link } from "react-router-dom";

const Logo = () => {
  return (
    <>
      <div className="logo-container">
        <Link to="/">
          {/* <img src="https://ekstat.com/img/new-design/eksisozluk_logo_darktheme.svg" /> */}
        </Link>
      </div>
    </>
  );
};

export default Logo;
