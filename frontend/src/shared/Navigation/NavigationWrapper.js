import Navbar from "./Navbar"

const NavigationWrapper = ({ children }) => {
    return ( 
        <div className="app flex flex-col overflow-y-hidden h-screen">
     <div className="header-wrapper h-16">
         <Navbar />
     </div>
     <div className="content-wrapper flex-grow relative bg-cream-light">
         { children }
     </div>
     </div>
     );
}
 
export default NavigationWrapper;