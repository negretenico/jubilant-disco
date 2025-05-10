import { db } from "@/app/_data/db";
import { Note, Result } from "@/app/_types/types";
export const MUTATION_FUNCTIONS = {
  add: async (note: Note): Promise<Result<string>> => {
    try {
      return {
        success: true,
        data: await db.notes.add(note),
      };
    } catch (e: any) {
      return {
        success: false,
        error: e.message,
      };
    }
  },
};
