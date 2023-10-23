import React, { useRef, useEffect, useCallback } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "hooks/useAuth";

const DropDownMenu = ({ isOpen, setIsOpen }) => {
  const auth = useAuth();
  const nav = useNavigate();
  const dropdownRef = useRef(null);

  const handleClickOutside = useCallback((event) => {
    if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
      setIsOpen(false);
    }
  }, [dropdownRef]);
  

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleLogout = () => {
    console.log("clicked handleLogout");
    auth.logout();
    nav("/");
  };

  return isOpen ? (
    <ul ref={dropdownRef} className="drop-down-menu f-left">
      <li>
        <Link>kanallar</Link>
      </li>
      <li>
        <Link>çöp</Link>
      </li>
      <li>
        <Link>ayarlar</Link>
      </li>
      <li>
        <Link>gece modunu kapat</Link>
      </li>
      <li>
        <Link>takip/engellenmiş</Link>
      </li>
      <li onClick={handleLogout} className="li-last">
        <Link>terk</Link>
      </li>
    </ul>
  ) : null;
};

export default DropDownMenu;
