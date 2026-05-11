# Flyway migration directory

This directory is scanned by Flyway on application startup.

Place database migration scripts here:

- Versioned migrations: `VYYYYMMDD.NNN__description.sql`
- Repeatable migrations: `R__description.sql`

Examples:

- `V20260422.001__add_patient_health_index.sql`
- `V20260422.002__create_monitoringDevice_alarm_table.sql`
- `R__refresh_report_view.sql`

Rules:

- Do not edit or delete a versioned migration after it has been executed in any shared environment.
- Add a new versioned migration for every schema or seed-data change.
- Keep each migration focused on one business change.
- See `doc/flyway-migration-guidelines.md` for the full project convention.
