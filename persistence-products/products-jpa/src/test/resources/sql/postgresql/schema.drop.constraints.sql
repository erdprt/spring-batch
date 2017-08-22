SET search_path TO sch_billings;

-- DROP RELATIONSHIPS SECTION -------------------------------------------------

ALTER TABLE REF_PRODUCT DROP CONSTRAINT REF_PRODUCT_REF_PRODUCT_STATUS_FK
;
ALTER TABLE REF_INVENTORY DROP CONSTRAINT REF_INVENTORY_REF_PRODUCT_FK
;
ALTER TABLE TAB_PRODUCT DROP CONSTRAINT TAB_PRODUCT_REF_PRODUCT_FK
;
ALTER TABLE TAB_BILLING DROP CONSTRAINT TAB_BILLING_REF_BILLING_STATUS_FK
;
ALTER TABLE REF_PRODUCT DROP CONSTRAINT REF_PRODUCT_REF_CATEGORY_FK
;
ALTER TABLE TAB_BILLING DROP CONSTRAINT BILLING_ORDERS_FK
;
ALTER TABLE TAB_ORDERS DROP CONSTRAINT ORDERS_PRODUCT_FK
;
ALTER TABLE TAB_ORDERS DROP CONSTRAINT ORDERS_PERSON_FK
;
ALTER TABLE TAB_ORDERS DROP CONSTRAINT METADONNEES_ORDERS_FK
;
ALTER TABLE TAB_PERSON DROP CONSTRAINT PERSON_ADDRESS_FK
;

-- DROP KEYS FOR TABLES SECTION -------------------------------------------------

ALTER TABLE REF_PRODUCT_STATUS DROP CONSTRAINT REF_PROD_STA_ID_PK
;
ALTER TABLE REF_PRODUCT DROP CONSTRAINT REF_PROD_ID_PK
;
ALTER TABLE REF_BILLING_STATUS DROP CONSTRAINT BIL_STA_ID_PK
;
ALTER TABLE TAB_BILLING DROP CONSTRAINT BIL_ID_PK
;
ALTER TABLE TAB_ORDERS DROP CONSTRAINT ORD_ID_PK
;
ALTER TABLE REF_INVENTORY DROP CONSTRAINT INV_ID_KEY
;
ALTER TABLE TAB_METADONNEES DROP CONSTRAINT MTD_ID_PK
;
ALTER TABLE TAB_ADDRESS DROP CONSTRAINT ADR_ID_PK
;
ALTER TABLE REF_CATEGORY DROP CONSTRAINT CAT_ID_PK
;
ALTER TABLE TAB_PRODUCT DROP CONSTRAINT PROD_ID_PK
;
ALTER TABLE TAB_PERSON DROP CONSTRAINT PER_ID_PK
;