"use server";

import { FeatureFlag } from "../_types/type";

export const FeatureFlagTable = async () => {
  const res = await fetch("http://localhost:8080/api/v1/feature-flags", {
    cache: "no-store", // ensures fresh data if needed
  });

  if (!res.ok) throw new Error("Failed to fetch flags");
  const flags: Pick<FeatureFlag, "id" | "flagName">[] = await res.json();
  return (
    <>
      {flags.map((i) => {
        return <div>Hi</div>;
      })}
    </>
  );
};
