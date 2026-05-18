import { useEffect, useRef, useState } from "react";
import { Link, useLocation } from "react-router-dom";

import "@/assets/css/Global.css";
import { searchApi } from "@/api/ApiService";
import Search from "@/components/TempIcons/Search";

const getSearchResults = (data) => {
  const topics = Array.isArray(data?.topics) ? data.topics : [];
  const users = Array.isArray(data?.users) ? data.users : [];

  return [
    ...topics.map((topic) => ({
      key: `topic-${topic.id}`,
      title: topic.name,
      path: `/topic/${topic.id}`,
    })),
    ...users.map((user) => {
      const username = user.name;

      return {
        key: `user-${user.id}`,
        title: `@${username}`,
        path: `/user/${username}`,
      };
    }),
  ].filter((result) => result.title);
};

const SearchBar = () => {
  const location = useLocation();
  const searchContainerRef = useRef(null);
  const [query, setQuery] = useState("");
  const [debouncedQuery, setDebouncedQuery] = useState("");
  const [results, setResults] = useState([]);
  const [isResultsOpen, setIsResultsOpen] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedQuery(query);
    }, 400);

    return () => clearTimeout(timer);
  }, [query]);

  useEffect(() => {
    if (debouncedQuery.trim().length < 1) {
      setResults([]);
      setIsResultsOpen(false);
      return;
    }

    const controller = new AbortController();

    searchApi(debouncedQuery, controller.signal)
      .then((response) => {
        const searchResults = getSearchResults(response.data);
        setResults(searchResults);
        setIsResultsOpen(searchResults.length > 0);
      })
      .catch((err) => {
        if (err.name === "CanceledError" || err.code === "ERR_CANCELED") {
          return;
        }

        console.error(err);
        setResults([]);
        setIsResultsOpen(false);
      });

    return () => controller.abort();
  }, [debouncedQuery]);

  useEffect(() => {
    const handleDocumentMouseDown = (event) => {
      if (!searchContainerRef.current?.contains(event.target)) {
        setIsResultsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleDocumentMouseDown);

    return () => {
      document.removeEventListener("mousedown", handleDocumentMouseDown);
    };
  }, []);

  useEffect(() => {
    setQuery("");
    setDebouncedQuery("");
    setResults([]);
    setIsResultsOpen(false);
  }, [location.pathname, location.search]);

  return (
    <div ref={searchContainerRef} className="search-container">
      <div>
        <input
          className="search-input not-focus"
          placeholder="başlık, #entry, @yazar"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          onFocus={() => setIsResultsOpen(results.length > 0)}
        />
        <button className="search-button">
          <Search />
        </button>
      </div>

      {isResultsOpen && results.length > 0 ? (
        <ul className="search-results">
          {results.map((result) => (
            <li key={result.key}>
              <Link
                to={result.path}
                className="not-underlined"
                onClick={() => setIsResultsOpen(false)}
              >
                {result.title}
              </Link>
            </li>
          ))}
        </ul>
      ) : null}
    </div>
  );
};

export default SearchBar;
