# Jbliant Disco

Welcome to the **Jubliant Disco** — your flexible, dynamic solution for feature management.

**What we do:**  
You tell us what feature flags you want to store — from raw bytes, booleans, JSON, to even JSX snippets — and we handle
the rest. Our service evaluates your feature flags based on your custom rules and returns exactly what you need,
empowering you to release features safely and efficiently.

---

## Getting Started (Local Development)

To run the application locally, ensure you have Java 21 installed and set the following JVM options:

```bash
-Dspring.profiles.active=local
-Dspring.data.mongodb.uri=<your-mongodb-uri>
-Dspring.data.mongodb.database=<your-database-name>



