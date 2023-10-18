import "assets/css/Global.css";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import BottomNavigation from "./BottomNavigation";
import AuthSection from "./AuthSection";
import UserSection from "./UserSection";

const Header = ({ isAuthenticated, selectTag, topicSectionRef, tag }) => {
  let userSection = <UserSection />;
  if (isAuthenticated === undefined || isAuthenticated === false) {
    userSection = <AuthSection />;
  }

  const updateTopicSection = (tag) => {
    selectTag(tag);
    topicSectionRef();
  };

  return (
    <>
      <div className="header">
        <div className="top-bar">
          <Logo />
          <SearchBar />
          {userSection}
          <BottomNavigation
            tag={tag}
            isAuthenticated={isAuthenticated}
            updateTopicSection={updateTopicSection}
          />
        </div>
      </div>
    </>
  );
};

export default Header;
