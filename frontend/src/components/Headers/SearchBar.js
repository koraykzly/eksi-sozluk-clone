import "assets/css/Global.css";
import Search from "components/TempIcons/Search";

const SearchBar = () => {
  return (
    <>
      <div className="search-container">
        <input
          className="search-input not-focus"
          placeholder="başlık, #entry, @yazar"
        ></input>
        <button className="search-button">
          <Search />
        </button>
      </div>
    </>
  );
};

export default SearchBar;
