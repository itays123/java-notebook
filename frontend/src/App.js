import { BrowserRouter, Route } from "react-router-dom";
import AuthContextProvider from "./auth/AuthContext";
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
      <Route path="/note/:id">
        <NoteView />
      </Route>
    </NavigationWrapper>
    </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
