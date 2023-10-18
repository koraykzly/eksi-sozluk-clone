import "assets/css/Global.css";
import { useAuth } from "hooks/useAuth";
import { Link } from "react-router-dom";

const UserSection = () => {
  const auth = useAuth();

  // fix later
  let event = true;

  return (
    <>
      <div className="auth-container">
        <ul className="list">
          <li>
            <Link to={`user/${auth.username}`}>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 512 512"
                className="auth-icon"
              >
                <path d="m427 478c0-94-77-171-171-171-94 0-171 77-171 171l0 34-34 0 0-34c0-84 52-160 130-191-61-34-91-105-74-172 18-68 79-115 149-115 70 0 131 47 149 115 17 67-13 138-74 172 78 31 130 107 130 191l0 34-34 0z m-52-324c0-66-53-120-119-120-66 0-119 54-119 120 0 66 53 119 119 119 66 0 119-53 119-119z"></path>
              </svg>
              ben
            </Link>
          </li>
          <li>
            <Link to="message">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 512 512"
                className="auth-icon"
              >
                <path d="m416 384l0 128-128-128-192 0c-53 0-96-27-96-80l0-208c0-53 43-96 96-96l320 0c53 0 96 43 96 96l0 208c0 53-43 80-96 80z m64-288c0-35-29-64-64-64l-320 0c-35 0-64 29-64 64l0 208c0 35 29 48 64 48l208 0 80 80 0-80 32 0c35 0 64-13 64-48z"></path>
              </svg>
              mesaj
            </Link>
          </li>
          <li>
            <Link to="events" className={event ? "events-highlighted" : ""}>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 512 512"
                className="auth-icon"
              >
                <path d="m267 420c-8 0-14-8-14-13l-46-240-45 178c0 5-6 11-11 11l-132 0c-8 0-14-6-14-14 0-8 6-13 14-13l121 0 57-227c2-8 5-10 13-10 6 0 11 2 14 10l45 248 44-151c2-5 8-10 13-10 5 0 11 5 14 10l26 127 127 0c8 0 14 5 14 14 0 8-6 13-14 13l-137 0c-6 0-11-5-14-11l-19-83-43 148c0 8-8 13-13 13z"></path>
              </svg>
              <span>olay</span>
            </Link>
          </li>
          <li>
            <a>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 32 32"
                className="svg-dots"
              >
                <path d="M12.8145 16.3379C12.8145 15.6064 12.2266 15.0186 11.4814 15.0186C10.75 15.0186 10.1553 15.6064 10.1553 16.3379C10.1553 17.0693 10.75 17.6572 11.4814 17.6572C12.2197 17.6572 12.8145 17.0693 12.8145 16.3379ZM17.6064 16.3379C17.6064 15.6064 17.0254 15.0186 16.2939 15.0186C15.5625 15.0186 14.9746 15.6064 14.9746 16.3379C14.9746 17.0693 15.5625 17.6572 16.2939 17.6572C17.0186 17.6572 17.6064 17.0693 17.6064 16.3379ZM22.4258 16.3379C22.4258 15.6064 21.8379 15.0186 21.1064 15.0186C20.3613 15.0186 19.7734 15.6064 19.7734 16.3379C19.7734 17.0693 20.3613 17.6572 21.1064 17.6572C21.8311 17.6572 22.4258 17.0693 22.4258 16.3379Z"></path>
              </svg>
            </a>
          </li>
        </ul>
      </div>
    </>
  );
};

export default UserSection;
