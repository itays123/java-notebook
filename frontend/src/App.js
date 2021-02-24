import { BrowserRouter } from "react-router-dom";
import AuthContextProvider from "./auth/AuthContext";
import NavigationWrapper from "./shared/Navigation/NavigationWrapper";

function App() {
  return (
    <BrowserRouter>
    <AuthContextProvider>
    <NavigationWrapper></NavigationWrapper>
    </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
