const BlockTypePicker = ({ type = 'P', onChange = () => {} }) => {
    return ( 
        <div className="type-picker flex-shrink focus:outline-none">
            <select onChange={e => onChange(e.target.value)} value={type}>
                <option value="P" className="focus:outline-none">Normal Text</option>
                <option value="H1" className="focus:outline-none">Heading 1</option>
                <option value="H2" className="focus:outline-none">Heading 2</option>
                <option value="H3" className="focus:outline-none">Heading 3</option>
            </select>
        </div>
     );
}
 
export default BlockTypePicker;