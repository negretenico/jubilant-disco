import { Note } from "../_types/types";
const Tag = ({ content }: { content: string }) => {
  return (
    <span className="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">
      {content}
    </span>
  );
};
export const NotePreview = ({
  note,
}: {
  note: Omit<Note, "id" | "content" | "createdAt">;
}) => {
  return (
    <div className="max-w-sm rounded overflow-hidden shadow-lg">
      <div className="w-full">
        <p>{note.summary}</p>
      </div>
      <div className="px-6 py-4">
        <div className="font-bold text-xl mb-2">{note.title}</div>
        <p className="text-gray-700 text-base">{note.updatedAt}</p>
      </div>
      <div className="px-6 pt-4 pb-2">
        {note.tags.map((tag, i) => {
          return <NotePreview.Tag key={`${tag}-${i}`} content={tag} />;
        })}
      </div>
    </div>
  );
};
NotePreview.Tag = Tag;
