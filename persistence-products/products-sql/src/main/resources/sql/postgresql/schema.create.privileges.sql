SET search_path TO sch_products;

CREATE ROLE user_app WITH LOGIN PASSWORD 'user_app';

GRANT CONNECT ON DATABASE bas_billings TO user_app;
GRANT USAGE ON SCHEMA sch_billings TO user_app;

GRANT INSERT, SELECT, UPDATE, DELETE ON ALL TABLES IN SCHEMA sch_billings TO user_app;
GRANT SELECT, UPDATE, USAGE ON ALL SEQUENCES IN SCHEMA sch_billings TO user_app;