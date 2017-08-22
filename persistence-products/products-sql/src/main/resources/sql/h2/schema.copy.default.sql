INSERT INTO ref_category (ref_cat_id,ref_cat_label,ref_cat_desc,tra_created,tra_updated) VALUES ('CAT1','LABEL FOR CATEGORY 1','DESCRIPTION FOR CATEGORY 1',current_timestamp,current_timestamp);
INSERT INTO ref_category (ref_cat_id,ref_cat_label,ref_cat_desc,tra_created,tra_updated) VALUES ('CAT2','LABEL FOR CATEGORY 2','DESCRIPTION FOR CATEGORY 2',current_timestamp,current_timestamp);
INSERT INTO ref_category (ref_cat_id,ref_cat_label,ref_cat_desc,tra_created,tra_updated) VALUES ('CAT3','LABEL FOR CATEGORY 3','DESCRIPTION FOR CATEGORY 3',current_timestamp,current_timestamp);
INSERT INTO ref_category (ref_cat_id,ref_cat_label,ref_cat_desc,tra_created,tra_updated) VALUES ('CAT4','LABEL FOR CATEGORY 4','DESCRIPTION FOR CATEGORY 4',current_timestamp,current_timestamp);

INSERT INTO ref_product_status (ref_prod_sta_id,ref_prod_sta_code,ref_prod_sta_label,tra_created,tra_updated) VALUES (1,'ACTIVE','PRODUCT ACTIVE',current_timestamp, current_timestamp);
INSERT INTO ref_product_status (ref_prod_sta_id,ref_prod_sta_code,ref_prod_sta_label,tra_created,tra_updated) VALUES (2,'DEPRECATED','PRODUCT DEPRECATED',current_timestamp, current_timestamp);
INSERT INTO ref_product_status (ref_prod_sta_id,ref_prod_sta_code,ref_prod_sta_label,tra_created,tra_updated) VALUES (3,'FUTURE','PRODUCT FUTURE',current_timestamp, current_timestamp);

INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(1, 'PROD1','LABEL FOR PRODUCT PROD1',current_timestamp,current_timestamp, 'CAT1',1);
INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(2, 'PROD2','LABEL FOR PRODUCT PROD2',current_timestamp,current_timestamp, 'CAT2',2);
INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(3, 'PROD3','LABEL FOR PRODUCT PROD3',current_timestamp,current_timestamp, 'CAT3',3);
INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(4, 'PROD4','LABEL FOR PRODUCT PROD4',current_timestamp,current_timestamp, 'CAT4',3);
INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(5, 'PROD5','LABEL FOR PRODUCT PROD5',current_timestamp,current_timestamp, 'CAT4',3);
INSERT INTO ref_product (ref_prod_id,ref_prod_code,ref_prod_label,tra_created,tra_updated,ref_cat_id,ref_prod_sta_id) VALUES(6, 'PROD6','LABEL FOR PRODUCT PROD6',current_timestamp,current_timestamp, 'CAT4',3);

INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (1,55.50,true,current_timestamp,current_timestamp,1000,1);
INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (2,65.50,true,current_timestamp,current_timestamp,2000,2);
INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (3,75.50,true,current_timestamp,current_timestamp,3000,3);
INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (4,85.50,true,current_timestamp,current_timestamp,4000,4);
INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (5,95.50,true,current_timestamp,current_timestamp,5000,5);
INSERT INTO ref_inventory (ref_inv_id,ref_inv_price,ref_inv_available,tra_created, tra_updated,ref_inv_quantity, ref_prod_id ) VALUES (6,105.50,true,current_timestamp,current_timestamp,6000,6);

INSERT INTO TAB_ADDRESS (ADR_ID,ADR_STREET,ADR_CITY,ADR_ZIPCODE,TRA_CREATED,TRA_UPDATED) VALUES (1,'streeName1','city1','00001',current_timestamp,current_timestamp);

INSERT INTO TAB_PERSON (PER_ID,PER_LASTNAME,PER_FIRSTNAME,PER_BIRTH_DATE,PER_NATIONALITY,ADR_ID,PER_IDENTIFIER,PER_CODE,TRA_CREATED,TRA_UPDATED) 
						VALUES (1,'lastName1','firstName1','2014-01-01','FRANCE',1,'fdbf4708-aa5a-40b6-bc4a-9f4c842a81f3','PERS1',current_timestamp,current_timestamp);