SET search_path TO sch_products;

-- DROP TABLES SECTION ---------------------------------------------------

REINDEX TABLE REF_PRODUCT_STATUS
;
REINDEX TABLE REF_PRODUCT
;
REINDEX TABLE REF_BILLING_STATUS
;
REINDEX TABLE TAB_BILLING
;
REINDEX TABLE TAB_ORDERS
;
REINDEX TABLE REF_INVENTORY
;
REINDEX TABLE TAB_METADONNEES
;
REINDEX TABLE TAB_ADDRESS
;
REINDEX TABLE REF_CATEGORY
;
REINDEX TABLE TAB_PRODUCT
;
REINDEX TABLE TAB_PERSON
;