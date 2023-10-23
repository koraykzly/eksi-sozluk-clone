import "assets/css/Global.css";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import BottomNavigation from "./BottomNavigation";
import AuthSection from "./AuthSection";
import UserSection from "./UserSection";
import { useAuth } from "hooks/useAuth";

const Header = ({ selectTag, scrollUpTopicSection, tag }) => {
  const auth = useAuth();

  let userSection = <UserSection />;
  if (!auth.isAuthenticated) {
    userSection = <AuthSection />;
  }

  const updateTopicSection = (tag) => {
    selectTag(tag);
    scrollUpTopicSection();
  };

  return (
    <div className="header">
      <div className="top-bar">
        <Logo />
        <SearchBar />
        {userSection}
        <BottomNavigation
          tag={tag}
          isAuthenticated={auth.isAuthenticated}
          updateTopicSection={updateTopicSection}
        />
      </div>
    </div>
  );
};

export default Header;
