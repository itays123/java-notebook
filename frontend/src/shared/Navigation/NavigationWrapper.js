const NavigationWrapper = ({ children }) => {
    return ( 
        <div className="app flex flex-col overflow-y-hidden h-screen">
     <div className="header-wrapper h-16"></div>
     <div className="content-wrapper flex-grow relative">
         { children }
     </div>
     </div>
     );
}
 
export default NavigationWrapper;