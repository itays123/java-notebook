import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import { useBlockEditor } from "./edit/useBlockEditor";
import Block from "./view/Block";

const BlockList = ({ blocks: initialBlocks }) => {
    const {focusedBlockIndex, setFocusedBlockIndex, next, prev} = useNoteEditorContext();
    const { blocks, modifyBlockContent, modifyBlockType, addBlock, saveBlock, deleteBlock } = useBlockEditor(initialBlocks);

    return (
        <>
        {[...blocks.keys()].map((index) => { 
            const block = blocks.get(index);
            return (
            <Fragment key={`${index}:${block.id}`}>
                {focusedBlockIndex === index ? (
                    <BlockEdit {...block} 
                        onContentChange={content => {
                            modifyBlockContent(index, content);
                        }} 
                        onTypeChange={type => modifyBlockType(index, type)}
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
        )})}
        </>
    );
}
 
export default BlockList;