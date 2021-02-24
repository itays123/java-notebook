const Block = ({ type, content }) => {
    return ( 
        <div className={`block-${type}`}>{ content }</div>
     );
}
 
export default Block;