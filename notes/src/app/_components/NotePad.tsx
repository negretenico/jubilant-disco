import { Maybe } from "../_types/types";

export const NotePad = ({ content }: { content: Maybe<string> }) => {
  return <div contentEditable="true">Type here... 📝</div>;
};
