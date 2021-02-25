const Block = ({ type, content, onBlockClick = () => {} }) => {
    return ( 
        <div className={`block-${type}`} onClick={() => onBlockClick()}>{ content }</div>
     );
}
 
export default Block;