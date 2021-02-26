import { useEffect, useRef, useState } from "react";

const BlockEdit = ({ type: initialType, content: initialContent, onContentChange, onKeyEnter, onBlur, onEmptyDelete }) => {
    const [content, setContent] = useState(initialContent || '');
    const [type, setType] = useState(initialType || 'P');
    const ref = useRef();

    useEffect(() => {
        ref.current.focus();
    }, []);
    
    return ( 
        <div>
            <input 
                className={`block-${type} focus:outline-none`}
                type="text" 
                ref={ref}
                value={content} 
                onChange={e => {
                    setContent(e.target.value);
                    onContentChange(e.target.value);
                }}
                onKeyPress={e => e.key === 'Enter' && onKeyEnter(content, type)} 
                onKeyUp={e => {
                    if (content === '') {
                        if (e.key === 'Backspace' || e.key === 'Delete') onEmptyDelete(e.key === 'Backspace');
                    }
                }}
                onBlur={() => {
                    onBlur(content, type);
                }}
                />
        </div>
     );
}
 
export default BlockEdit;