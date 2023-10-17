import React from "react";
import { Link } from "react-router-dom";

const Footer = () => {
  return (
    <footer>
      <ul>
        <li>
          <Link to="/iletisim">iletişim</Link>
        </li>
        <li>
          <Link to="/seffaflik-raporlari">şeffaflık raporları</Link>
        </li>
        <li>
          <Link to="/sozluk-kurallari">sözlük kuralları</Link>
        </li>
        <li>
          <Link to="/reklam">reklam</Link>
        </li>
        <li>
          <Link to="/kariyer">kariyer</Link>
        </li>
        <li>
          <Link to="/kullanim-kosullari">kullanım koşulları</Link>
        </li>
        <li>
          <Link to="/gizlilik-politikasi">gizlilik politikamız</Link>
        </li>
        <li>
          <Link to="/sss">sss</Link>
        </li>
        <li>
          <Link to="/istatistikler">istatistikler</Link>
        </li>
        <li>
          <Link to="/modlog">modlog</Link>
        </li>
        <li>
          <Link to="/sub-etha">sub-etha</Link>
        </li>
        <li>
          <a
            href="https://www.instagram.com/"
            target="_blank"
            rel="noopener noreferrer"
          >
            instagram
          </a>
        </li>
        <li>
          <a
            href="https://twitter.com/"
            target="_blank"
            rel="noopener noreferrer"
          >
            twitter
          </a>
        </li>
        <li>
          <a
            href="https://www.facebook.com/"
            target="_blank"
            rel="noopener noreferrer"
          >
            facebook
          </a>
        </li>
      </ul>
    </footer>
  );
};

export default Footer;
