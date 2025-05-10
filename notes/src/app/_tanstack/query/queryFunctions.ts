import { db } from "@/app/_data/db";
import { Maybe, Note, Result } from "@/app/_types/types";

export const QUERY_FUNCTIONS = {
  getNote: async (id: string): Promise<Result<Maybe<Note>>> => {
    try {
      return {
        success: true,
        data: await db.notes.get(["id"]),
      };
    } catch (e: any) {
      return {
        success: false,
        error: e.message,
      };
    }
  },
  getAllNotes: async (): Promise<Result<Note[]>> => {
    try {
      return {
        success: true,
        data: await db.notes.toArray(),
      };
    } catch (e: any) {
      return {
        success: false,
        error: e.message,
      };
    }
  },
};
