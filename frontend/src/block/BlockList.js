import { Fragment } from "react";
import { useNoteEditorContext } from "../note/edit/NoteEditorContext";
import BlockEdit from "./edit/BlockEdit";
import Block from "./view/Block";

const BlockList = ({ blocks }) => {
    const {focusedBlockIndex, setFocusedBlockIndex} = useNoteEditorContext();
    return blocks.map((block, index) => (
                <Fragment key={`${index}:${block.id}:${block.type}:${block.content}`}>
                    {focusedBlockIndex === index ? (
                        <BlockEdit {...block}  />
                    ) : (
                        <Block {...block} onBlockClick={() => setFocusedBlockIndex(index)}/>
                    )}
                </Fragment>
            ))
}
 
export default BlockList;