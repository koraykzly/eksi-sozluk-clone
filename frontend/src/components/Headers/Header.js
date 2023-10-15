import "assets/css/Global.css";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
// import UserSection from "./UserSection";
import BottomNavigation from "./BottomNavigation";
import AuthSection from "./AuthSection";
import UserSection from "./UserSection";

const Header = ({ isAuthenticated, selectTag }) => {
  
  let userSection = <UserSection />;
  if (isAuthenticated === undefined || isAuthenticated === false) {
    userSection = <AuthSection />;
  }

  return (
    <>
      <div className="header">
        <div className="top-bar">
          <Logo />
          <SearchBar />
          {userSection}
          <BottomNavigation isAuthenticated={isAuthenticated} selectTag={selectTag}/>
        </div>
      </div>
    </>
  );
};

export default Header;
