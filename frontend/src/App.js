import { BrowserRouter, Route } from "react-router-dom";
import AuthContextProvider from "./auth/AuthContext";
import AuthOnly from "./auth/AuthOnly";
import Login from "./auth/login/Login";
import Register from "./auth/register/Register";
import CreateNote from "./note/create/CreateNote";
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
        <AuthOnly redirect="/welcome">
          <Home />
        </AuthOnly>
      </Route>
      <Route path="/welcome">
        <AuthOnly shouldBeAuthenticated={false} redirect="/">
          <Welcome />
        </AuthOnly>
      </Route>
      <Route path="/login">
        <AuthOnly shouldBeAuthenticated={false} redirect="/">
          <Login />
        </AuthOnly>
      </Route>
      <Route path="/register">
        <AuthOnly shouldBeAuthenticated={false} redirect="/">
          <Register />
          </AuthOnly>
      </Route>
      <Route path="/note/:id">
        <AuthOnly redirect="/welcome">
          <NoteView />
        </AuthOnly>
      </Route>
      <Route path="/create">
        <AuthOnly redirect="/welcome">
          <CreateNote />
        </AuthOnly>
      </Route>
    </NavigationWrapper>
    </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
