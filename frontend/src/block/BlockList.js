import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import { useBlockEditor } from "./edit/useBlockEditor";
import Block from "./view/Block";

const BlockList = ({ blocks }) => {
    const {focusedBlockIndex, setFocusedBlockIndex} = useNoteEditorContext();
    const { modifyBlockContent, changes } = useBlockEditor(blocks);

    return (
        <>
        {blocks.map((block, index) => (
            <Fragment key={`${index}:${block.id}`}>
                {focusedBlockIndex === index ? (
                    <BlockEdit {...block} 
                        onContentChange={content => {
                            modifyBlockContent(index, content);
                            console.log(changes().updatedBlocks);
                        }} />
                ) : (
                    <Block {...block} onBlockClick={() => setFocusedBlockIndex(index)}/>
                )}
            </Fragment>
        ))}
        </>
    );
}
 
export default BlockList;