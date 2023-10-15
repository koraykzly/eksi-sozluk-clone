import React, { useState } from "react";
// import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import AuthProvider from "context/AuthContext";

import Index from "views/Index";
import User from "views/User";
import Message from "views/Message";
import Event from "views/Event";
import Header from "components/Headers/Header";
import Entry from "views/Entry";

import { useAuth } from "hooks/useAuth";

import Login from "views/Login";
import Register from "views/Register";

import TopicSection from "components//TopicSection";
import EntrySection from "components/EntrySection";

const App = () => {
  const auth = useAuth();

  const [tag, selectTag] = useState("gündem");
  const [topic, selectTopic] = useState(1);

  return (
    <AuthProvider>
      <BrowserRouter>
        <Header isAuthenticated={!auth.isAuthenticated} selectTag={selectTag} />
        <main className="main-container">
          <TopicSection type={tag} selectTopic={selectTopic} />
          <div className="inner-content">
            <Routes>
              <Route path="/" element={<Index topicId={topic} />} exact />
              <Route
                path="/:topicId"
                element={<EntrySection topicId={topic} />}
              />
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
              <Route path="/user/:username" element={<User />} />
              <Route path="/message" element={<Message />} />
              <Route path="/events" element={<Event />} />
              <Route path="/entry/:id" element={<Entry />} />
              <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
          </div>
        </main>
      </BrowserRouter>
    </AuthProvider>
  );
};

export default App;
