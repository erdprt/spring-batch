SET search_path TO sch_billings;

-- ADD KEYS FOR TABLE TAB_PERSON

ALTER TABLE TAB_PERSON ADD CONSTRAINT PER_ID_PK PRIMARY KEY (PER_ID)
;
-- ADD KEYS FOR TABLE TAB_PRODUCT

ALTER TABLE TAB_PRODUCT ADD CONSTRAINT PROD_ID_PK PRIMARY KEY (PROD_ID)
;

-- ADD KEYS FOR TABLE REF_CATEGORY

ALTER TABLE REF_CATEGORY ADD CONSTRAINT CAT_ID_PK PRIMARY KEY (REF_CAT_ID)
;

-- ADD KEYS FOR TABLE TAB_ADDRESS

ALTER TABLE TAB_ADDRESS ADD CONSTRAINT ADR_ID_PK PRIMARY KEY (ADR_ID)
;

-- ADD KEYS FOR TABLE TAB_METADONNEES

ALTER TABLE TAB_METADONNEES ADD CONSTRAINT MTD_ID_PK PRIMARY KEY (MTD_ID)
;

-- ADD KEYS FOR TABLE REF_INVENTORY

ALTER TABLE REF_INVENTORY ADD CONSTRAINT INV_ID_KEY PRIMARY KEY (REF_INV_ID)
;

-- ADD KEYS FOR TABLE TAB_ORDERS

ALTER TABLE TAB_ORDERS ADD CONSTRAINT ORD_ID_PK PRIMARY KEY (ORD_ID)
;

-- ADD KEYS FOR TABLE TAB_BILLING

ALTER TABLE TAB_BILLING ADD CONSTRAINT BIL_ID_PK PRIMARY KEY (BIL_ID)
;

-- ADD KEYS FOR TABLE REF_BILLING_STATUS

ALTER TABLE REF_BILLING_STATUS ADD CONSTRAINT BIL_STA_ID_PK PRIMARY KEY (REF_BIL_STA_ID)
;

-- ADD KEYS FOR TABLE REF_PRODUCT

ALTER TABLE REF_PRODUCT ADD CONSTRAINT REF_PROD_ID_PK PRIMARY KEY (REF_PROD_ID)
;

-- ADD KEYS FOR TABLE REF_PRODUCT_STATUS

ALTER TABLE REF_PRODUCT_STATUS ADD CONSTRAINT REF_PROD_STA_ID_PK PRIMARY KEY (REF_PROD_STA_ID)
;

-- CREATE RELATIONSHIPS SECTION ------------------------------------------------- 

ALTER TABLE TAB_PERSON ADD CONSTRAINT PERSON_ADDRESS_FK FOREIGN KEY (ADR_ID) REFERENCES TAB_ADDRESS (ADR_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_ORDERS ADD CONSTRAINT METADONNEES_ORDERS_FK FOREIGN KEY (MTD_ID) REFERENCES TAB_METADONNEES (MTD_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_ORDERS ADD CONSTRAINT ORDERS_PERSON_FK FOREIGN KEY (PER_ID) REFERENCES TAB_PERSON (PER_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_ORDERS ADD CONSTRAINT ORDERS_PRODUCT_FK FOREIGN KEY (PROD_ID) REFERENCES TAB_PRODUCT (PROD_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_BILLING ADD CONSTRAINT BILLING_ORDERS_FK FOREIGN KEY (ORD_ID) REFERENCES TAB_ORDERS (ORD_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE REF_PRODUCT ADD CONSTRAINT REF_PRODUCT_REF_CATEGORY_FK FOREIGN KEY (REF_CAT_ID) REFERENCES REF_CATEGORY (REF_CAT_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_BILLING ADD CONSTRAINT TAB_BILLING_REF_BILLING_STATUS_FK FOREIGN KEY (REF_BIL_STA_ID) REFERENCES REF_BILLING_STATUS (REF_BIL_STA_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE TAB_PRODUCT ADD CONSTRAINT TAB_PRODUCT_REF_PRODUCT_FK FOREIGN KEY (REF_PROD_ID) REFERENCES REF_PRODUCT (REF_PROD_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE REF_INVENTORY ADD CONSTRAINT REF_INVENTORY_REF_PRODUCT_FK FOREIGN KEY (REF_PROD_ID) REFERENCES REF_PRODUCT (REF_PROD_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE REF_PRODUCT ADD CONSTRAINT REF_PRODUCT_REF_PRODUCT_STATUS_FK FOREIGN KEY (REF_PROD_STA_ID) REFERENCES REF_PRODUCT_STATUS (REF_PROD_STA_ID) ON DELETE NO ACTION ON UPDATE NO ACTION
;