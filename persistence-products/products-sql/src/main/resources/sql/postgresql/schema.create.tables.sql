SET search_path TO sch_products;

-- CREATE SEQUENCES SECTION -------------------------------------------------

CREATE SEQUENCE TAB_METADONNEES_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE TAB_PERSON_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE TAB_ADDRESS_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE TAB_PRODUCT_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE REF_INVENTORY_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE TAB_ORDERS_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE TAB_BILLING_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE REF_PRODUCT_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE REF_PRODUCT_STATUS_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

CREATE SEQUENCE REF_CATEGORY_SEQ
 INCREMENT BY 1
 START WITH 1
 NO MAXVALUE
 NO MINVALUE
 CYCLE
 CACHE 100
;

-- CREATE TABLES SECTION -------------------------------------------------

-- TABLE TAB_PERSON

CREATE TABLE TAB_PERSON(
 PER_ID BIGINT NOT NULL,
 PER_LASTNAME VARCHAR NOT NULL,
 PER_FIRSTNAME VARCHAR NOT NULL,
 PER_BIRTH_DATE DATE NOT NULL,
 PER_NATIONALITY VARCHAR NOT NULL,
 ADR_ID BIGINT NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL,
 PER_IDENTIFIER CHARACTER(38),
 PER_CODE VARCHAR NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE TAB_PRODUCT

CREATE TABLE TAB_PRODUCT(
 PROD_ID BIGINT NOT NULL,
 PROD_IDENTIFIER CHARACTER(38) NOT NULL,
 PROD_QUANTITY INTEGER NOT NULL,
 PROD_PRICE NUMERIC(10,2) NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL,
 REF_PROD_ID BIGINT NOT NULL,
 ORD_ID BIGINT NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE REF_CATEGORY

CREATE TABLE REF_CATEGORY(
 REF_CAT_ID CHARACTER(10) NOT NULL,
 REF_CAT_LABEL VARCHAR NOT NULL,
 REF_CAT_DESC VARCHAR NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL
)
WITH (OIDS=FALSE)
;
COMMENT ON COLUMN REF_CATEGORY.REF_CAT_ID IS 'CATEGORY CODE'
;

-- TABLE TAB_ADDRESS

CREATE TABLE TAB_ADDRESS(
 ADR_ID BIGINT NOT NULL,
 ADR_STREET VARCHAR NOT NULL,
 ADR_CITY VARCHAR NOT NULL,
 ADR_ZIPCODE VARCHAR NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE TAB_METADONNEES

CREATE TABLE TAB_METADONNEES(
 MTD_ID BIGINT NOT NULL,
 MTD_FROM VARCHAR NOT NULL,
 MTD_TO VARCHAR NOT NULL,
 MTD_CONTENT VARCHAR NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE REF_INVENTORY

CREATE TABLE REF_INVENTORY(
 REF_INV_ID BIGINT NOT NULL,
 REF_INV_PRICE NUMERIC(10,2) NOT NULL,
 REF_INV_AVAILABLE BOOLEAN NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL,
 REF_INV_QUANTITY INTEGER NOT NULL,
 REF_PROD_ID BIGINT NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE TAB_ORDERS

CREATE TABLE TAB_ORDERS(
 ORD_ID BIGINT NOT NULL,
 ORD_PRICE NUMERIC(10,2) NOT NULL,
 MTD_ID BIGINT NOT NULL,
 PER_ID BIGINT NOT NULL,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE TAB_BILLING

CREATE TABLE TAB_BILLING(
 BIL_ID BIGINT NOT NULL,
 BIL_CREATED TIMESTAMP NOT NULL,
 BIL_UPDATED TIMESTAMP NOT NULL,
 ORD_ID BIGINT NOT NULL,
 REF_BIL_STA_ID CHARACTER(10) NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE REF_BILLING_STATUS

CREATE TABLE REF_BILLING_STATUS(
 REF_BIL_STA_ID CHARACTER(10) NOT NULL,
 REF_BIL_STA_LABEL VARCHAR NOT NULL,
 REF_BIL_STAT_DESC VARCHAR,
 TRA_CREATED TIMESTAMP NOT NULL,
 TRA_UPDATED TIMESTAMP NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE REF_PRODUCT

CREATE TABLE REF_PRODUCT(
 REF_PROD_ID BIGINT NOT NULL,
 REF_PROD_CODE VARCHAR,
 REF_PROD_LABEL VARCHAR,
 TRA_CREATED TIMESTAMP,
 TRA_UPDATED TIMESTAMP,
 REF_CAT_ID CHARACTER(10) NOT NULL,
 REF_PROD_STA_ID BIGINT NOT NULL
)
WITH (OIDS=FALSE)
;

-- TABLE REF_PRODUCT_STATUS

CREATE TABLE REF_PRODUCT_STATUS(
 REF_PROD_STA_ID BIGINT NOT NULL,
 REF_PROD_STA_CODE VARCHAR,
 REF_PROD_STA_LABEL VARCHAR,
 TRA_CREATED TIMESTAMP,
 TRA_UPDATED TIMESTAMP
)
WITH (OIDS=FALSE)
;                 