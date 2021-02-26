import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import { useBlockEditor } from "./edit/useBlockEditor";
import Block from "./view/Block";

const BlockList = ({ blocks: initialBlocks }) => {
    const {focusedBlockIndex, setFocusedBlockIndex, next, prev} = useNoteEditorContext();
    const { blocks, modifyBlockContent, addBlock, saveBlock, deleteBlock } = useBlockEditor(initialBlocks);

    return (
        <>
        {[...blocks.values()].map((block, index) => (
            <Fragment key={`${index}:${block.id}`}>
                {focusedBlockIndex === index ? (
                    <BlockEdit {...block} 
                        onContentChange={content => {
                            modifyBlockContent(index, content);
                        }} 
                        onKeyEnter={(content, type) => {
                            saveBlock(index, content, type);
                            if (index + 1 === blocks.size) {
                                addBlock();
                            }
                            next();
                        }}
                        onEmptyDelete={(isBackspace) => {
                            deleteBlock(index);
                            return isBackspace ? prev() : next();
                        }}
                        onBlur={(content, type) => {
                            saveBlock(index, content, type);
                        }}
                        />
                ) : (
                    <Block {...block} onBlockClick={() => setFocusedBlockIndex(index)}/>
                )}
            </Fragment>
        ))}
        </>
    );
}
 
export default BlockList;