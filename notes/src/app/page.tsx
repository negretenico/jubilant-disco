"use client";
import { useSuspenseQuery } from "@tanstack/react-query";
import { QUERY_KEYS } from "./_tanstack/query/queryKeys";
import { QUERY_FUNCTIONS } from "./_tanstack/query/queryFunctions";
import { NotePreview } from "./_components/NotePreview";
import { db } from "./_data/db";

export default function Home() {
  // @ts-ignore
  // window.db = db;
  const { data: allNotes } = useSuspenseQuery({
    queryKey: QUERY_KEYS.getAllNotes,
    queryFn: QUERY_FUNCTIONS.getAllNotes,
  });
  if (!allNotes.success) {
    return (
      <div>
        Hey man, we struggled to get your notes. Trust we can try again or you
        can add a new one
      </div>
    );
  }
  if (allNotes.success && allNotes.data.length === 0) {
    return <div>Hey man, you need to add some notes</div>;
  }
  return (
    <div className="grid ">
      {allNotes.data.map((note, i) => {
        return <NotePreview key={`preview-${note.id}`} note={note} />;
      })}
    </div>
  );
}
