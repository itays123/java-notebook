import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import Block from "./view/Block";

const BlockList = () => {
    const {focusedBlockIndex, setFocusedBlockIndex, next, prev, blocks, modifyBlockContent, modifyBlockType, addBlock, saveBlock, deleteBlock} = useNoteEditorContext();

    return (
        <>
        {[...blocks.keys()].map((index, indexInNote) => { 
            const block = blocks.get(index);
            return (
            <Fragment key={`${index}:${block.id}`}>
                {focusedBlockIndex === indexInNote ? (
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