import { BrowserRouter } from "react-router-dom";
import NavigationWrapper from "./shared/Navigation/NavigationWrapper";

function App() {
  return (
    <BrowserRouter>
    <NavigationWrapper></NavigationWrapper>
    </BrowserRouter>
  );
}

export default App;
