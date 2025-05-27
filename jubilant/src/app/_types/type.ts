export type FeatureFlag = {
  id: string;
  flagName: string;
  environments: Record<string, Environment>;
  description: string;
};
type Environment = {
  variant: Record<string, any>;
  rule: Rule[];
  description: string;
  defaultValue: any;
};
type Condition = {
  field: string;
  value: string;
  operator: Operator;
};
type Rule = {
  variant: string;
  condition: Condition;
};
enum Operator {
  EQUALS,
  NOT_EQUALS,
  CONTAINS,
  STARTS_WITH,
  GREATER_THAN,
  LESS_THAN,
}
