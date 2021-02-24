import { BrowserRouter, Route } from "react-router-dom";
import AuthContextProvider from "./auth/AuthContext";
import Login from "./auth/login/Login";
import Register from "./auth/register/Register";
import NoteView from "./note/view/NoteView";
import Home from "./shared/Home";
import NavigationWrapper from "./shared/Navigation/NavigationWrapper";
import Welcome from "./shared/Welcome";

function App() {
  return (
    <BrowserRouter>
    <AuthContextProvider>
    <NavigationWrapper>
      <Route exact path="/">
        <Home />
      </Route>
      <Route path="/welcome">
        <Welcome />
      </Route>
      <Route path="/login">
        <Login />
      </Route>
      <Route path="/register">
        <Register />
      </Route>
      <Route path="/note/:id">
        <NoteView />
      </Route>
    </NavigationWrapper>
    </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
