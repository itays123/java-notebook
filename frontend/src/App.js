import { BrowserRouter, Route } from "react-router-dom";
import AuthContextProvider from "./auth/AuthContext";
import NoteView from "./note/view/NoteView";
import NavigationWrapper from "./shared/Navigation/NavigationWrapper";

function App() {
  return (
    <BrowserRouter>
    <AuthContextProvider>
    <NavigationWrapper>
      <Route path="/note/:id">
        <NoteView />
      </Route>
    </NavigationWrapper>
    </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
