import "../css/Global.css";

const SearchBar = () => {
  return (
    <>
      <div className="search-container">
        <input
          className="search-input"
          placeholder="başlık, #entry, @yazar"
        ></input>
        <button className="search-button">
          {/* <FontAwesomeIcon icon="fa-solid fa-magnifying-glass" /> */}
          Se
        </button>
      </div>
    </>
  );
};

export default SearchBar;
