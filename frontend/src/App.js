import React, { useState } from "react";
// import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import AuthProvider from "context/AuthContext";

import { useRef } from "react";

import Index from "views/Index";
import User from "views/User";
import Message from "views/Message";
import Event from "views/Event";
import Header from "components/Headers/Header";
import EntryPage from "views/EntryPage";

import { useAuth } from "hooks/useAuth";

import Login from "views/Login";
import Register from "views/Register";

import TopicSection from "components//TopicSection";
import EntrySection from "components/EntrySection";
import Footer from "components/Footer";

const App = () => {
  const [tag, selectTag] = useState("gündem");
  const topicSectionRef = useRef(null);

  const scrollUpTopicSection = () => {
    if (topicSectionRef.current) {
      topicSectionRef.current.scrollTop = 0;
    }
  };

  return (
    <AuthProvider>
      <BrowserRouter>
        <Header
          selectTag={selectTag}
          tag={tag}
          scrollUpTopicSection={scrollUpTopicSection}
        />
        <main className="main-container">
          <TopicSection topicSectionRef={topicSectionRef} type={tag} />
          <div className="inner-content">
            <div className="section">
              <Routes>
                <Route
                  path="/"
                  element={<Index clearTag={() => selectTag("bugün")} />}
                  exact
                />
                <Route path="topic/:topicId" element={<EntrySection />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/user/:username" element={<User />} />
                <Route path="/message" element={<Message />} />
                <Route path="/events" element={<Event />} />
                <Route path="/entry/:id" element={<EntryPage />} />
                <Route path="*" element={<Navigate to="/" replace />} />
              </Routes>
            </div>
            <Footer />
          </div>
        </main>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
