SET search_path TO sch_products;

SELECT SETVAL('TAB_METADONNEES_SEQ',(SELECT MAX(MTD_ID) FROM TAB_METADONNEES),true);
SELECT SETVAL('TAB_PERSON_SEQ',(SELECT MAX(PER_ID) FROM TAB_PERSON),true);
SELECT SETVAL('TAB_ADDRESS_SEQ',(SELECT MAX(ADR_ID) FROM TAB_ADDRESS),true);
SELECT SETVAL('TAB_PRODUCT_SEQ',(SELECT MAX(PROD_ID) FROM TAB_PRODUCT),true);
SELECT SETVAL('REF_INVENTORY_SEQ',(SELECT MAX(REF_INV_ID) FROM REF_INVENTORY),true);
SELECT SETVAL('TAB_ORDERS_SEQ',(SELECT MAX(ORD_ID) FROM TAB_ORDERS),true);
SELECT SETVAL('TAB_BILLING_SEQ',(SELECT MAX(BIL_ID) FROM TAB_BILLING),true);
SELECT SETVAL('REF_PRODUCT_SEQ',(SELECT MAX(REF_PROD_ID) FROM REF_PRODUCT),true);
SELECT SETVAL('REF_PRODUCT_STATUS_SEQ',(SELECT MAX(REF_PROD_STA_ID) FROM REF_PRODUCT_STATUS),true);