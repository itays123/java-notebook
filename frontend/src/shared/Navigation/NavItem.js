import { NavLink } from "react-router-dom";

const NavItem = ({ to, children }) => {
    return ( 
        <NavLink to={to} className="flex items-center px-1 md:px-2 hover:bg-cream-dark">{children}</NavLink>
     );
}
 
export default NavItem;