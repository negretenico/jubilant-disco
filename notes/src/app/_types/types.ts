export type Maybe<T> = T | undefined | null;
export type Result<T> =
  | { success: true; data: T }
  | { success: false; error: string };
export type Note = {
  id: number;
  title: string;
  content: string;
  createdAt: string;
  updatedAt: string;
  tags: string[];
  summary: string;
};
