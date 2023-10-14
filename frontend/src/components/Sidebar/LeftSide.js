import "../css/Global.css";

const Logo = () => {
  return (
    <div className="left-side">
      <nav className="partial-left-side">
        <div className="left-side-title">
          <h2>bugün</h2>
        </div>

        <div className="left-side-box">
          <a className="refresh-text">yenile</a>
        </div>

        <ul>
          <li><a>topic-1</a></li>
          <li><a>topic-1</a></li>
          <li><a>topic-1</a></li>
          <li><a>topic-1</a></li>
          <li><a>topic-1</a></li>
        </ul>
      </nav>
    </div>
  );
};

export default Logo;
