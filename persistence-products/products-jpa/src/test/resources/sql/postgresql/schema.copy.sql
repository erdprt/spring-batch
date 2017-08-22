SET search_path TO sch_billings;

COPY REF_BILLING_STATUS FROM 'G:/postgresql/clusters/petstores/scripts/databases/billings/schemas/business/datas/REF_BILLING_STATUS.csv' WITH DELIMITER as ';' CSV HEADER;
COPY REF_PRODUCT_STATUS FROM 'G:/postgresql/clusters/petstores/scripts/databases/billings/schemas/business/datas/REF_PRODUCT_STATUS.csv' WITH DELIMITER as ';' CSV HEADER;
COPY REF_CATEGORY FROM 'G:/postgresql/clusters/petstores/scripts/databases/billings/schemas/business/datas/REF_CATEGORY.csv' WITH DELIMITER as ';' CSV HEADER;
COPY REF_PRODUCT FROM 'G:/postgresql/clusters/petstores/scripts/databases/billings/schemas/business/datas/REF_PRODUCT.csv' WITH DELIMITER as ';' CSV HEADER;
COPY REF_INVENTORY FROM 'G:/postgresql/clusters/petstores/scripts/databases/billings/schemas/business/datas/REF_INVENTORY.csv' WITH DELIMITER as ';' CSV HEADER;