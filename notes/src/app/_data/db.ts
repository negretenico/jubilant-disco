import Dexie, { EntityTable } from "dexie";
import { Note } from "../_types/types";

export const db = new Dexie("NotesDatabase") as Dexie & {
  notes: EntityTable<
    Note,
    "id" // primary key "id" (for the typings only)
  >;
};

// Schema declaration:
db.version(1).stores({
  notes: "++id", // primary key "id" (for the runtime!)
});
