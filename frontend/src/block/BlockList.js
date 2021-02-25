import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import { useBlockEditor } from "./edit/useBlockEditor";
import Block from "./view/Block";

const BlockList = ({ blocks }) => {
    const {focusedBlockIndex, setFocusedBlockIndex, next} = useNoteEditorContext();
    const { modifyBlockContent, changes, addBlock, added } = useBlockEditor(blocks);

    return (
        <>
        {blocks.map((block, index) => (
            <Fragment key={`${index}:${block.id}`}>
                {focusedBlockIndex === index ? (
                    <BlockEdit {...block} 
                        onContentChange={content => {
                            modifyBlockContent(index, content);
                        }} 
                        onKeyEnter={() => {
                            if (index + 1 === blocks.length + added.length) {
                                addBlock();
                            }
                            next();
                        }}
                        />
                ) : (
                    <Block {...block} onBlockClick={() => setFocusedBlockIndex(index)}/>
                )}
            </Fragment>
        ))}
        {added.map((block, index) => {
            const actualIndex = blocks.length + index;
            if (focusedBlockIndex === actualIndex) {
                return (
                    <Fragment key={index}>
                    <BlockEdit {...block} 
                        onContentChange={content => {
                            modifyBlockContent(actualIndex, content);
                            console.log(changes().updatedBlocks);
                        }} 
                        onKeyEnter={() => {
                            if (actualIndex + 1 === blocks.length + added.length) {
                                addBlock();
                            }
                            next();
                        }}
                        />
                    </Fragment>
                )
            }
            return (
            <Fragment key={index}>
                <Block {...block} onBlockClick={() => setFocusedBlockIndex(index)}/>
            </Fragment>
        )})}
        </>
    );
}
 
export default BlockList;