import "../css/Global.css";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import AuthSection from "./AuthSection";
import Nav from "./Nav";

const Header = () => {
  return (
    <>
      <div className="header">
        <div className="top-bar">
          <Logo />
          <SearchBar />
          <AuthSection />
          <Nav />
        </div>
      </div>
    </>
  );
};

export default Header;
