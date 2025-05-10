"use client";
import { UseQueryResult, useSuspenseQuery } from "@tanstack/react-query";
import { QUERY_KEYS } from "../_tanstack/query/queryKeys";
import { QUERY_FUNCTIONS } from "../_tanstack/query/queryFunctions";
import { Note } from "../_types/types";
import { NotePad } from "../_components/NotePad";

export default async function NotePage({
  params,
}: {
  params: Promise<{ slug: string }>;
}) {
  const { slug } = await params;
  const { data: note } = useSuspenseQuery({
    queryKey: QUERY_KEYS.getNote,
    queryFn: () => QUERY_FUNCTIONS.getNote(slug),
  }) as UseQueryResult<Note>;
  return <NotePad content={note?.content} />;
}
