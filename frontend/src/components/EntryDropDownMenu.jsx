import { useCallback, useEffect, useRef } from "react";
import { Link } from "react-router-dom";

const EntryDropDownMenu = ({ isOpen, setIsOpen }) => {
  const dropdownRef = useRef(null);

  const handleClickOutside = useCallback(
    (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsOpen(false);
      }
    },
    [dropdownRef]
  );

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return isOpen ? (
    <ul ref={dropdownRef} className="drop-down-menu f-right">
      <li>
        <Link>mesaj gönder</Link>
      </li>
      <li>
        <Link>şikayet</Link>
      </li>
      <li>
        <Link>modlog</Link>
      </li>
      <li>
        <Link>engelle</Link>
      </li>
    </ul>
  ) : null;
};

export default EntryDropDownMenu;
