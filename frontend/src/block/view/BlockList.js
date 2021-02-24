import Block from "./Block";

const BlockList = ({ blocks }) => {
    return blocks.map((block, index) => (
        <Block {...block} key={`${index}:${block.id}:${block.type}:${block.content}`} />
    ));
}
 
export default BlockList;