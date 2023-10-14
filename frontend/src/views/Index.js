import Header from "components/Headers/Header";
import LeftSide from "components/Sidebar/LeftSide";
import Section from "components/Section";

import "components/css/Global.css";

const Index = (props) => {
  return (
    <>
        <Header />
        <main className="main-container">
          <LeftSide/>
          <div className="inner-content">
            <Section/>
          </div>
        </main>
    </>
  );
};

export default Index;
