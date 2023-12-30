CREATE DATABASE IF NOT EXISTS cha104g1; 
USE cha104g1;

DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS like_store;
DROP TABLE IF EXISTS customized;
DROP TABLE IF EXISTS customized_order;
DROP TABLE IF EXISTS customized_detail;
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS refunds;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS sup_order;
DROP TABLE IF EXISTS picking;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS store_emp;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS items_class;
DROP TABLE IF EXISTS customized_items;
DROP TABLE IF EXISTS meal_type;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS p_emp;
DROP TABLE IF EXISTS stores;


-- 中群
CREATE TABLE stores( 
storeId	INT	PRIMARY KEY AUTO_INCREMENT	NOT NULL,
storeAccount	VARCHAR(31)	UNIQUE KEY	NOT NULL,
storePw	VARCHAR(31)		NOT NULL,
storeLv	TINYINT		NOT NULL ,
createDate	DATE	NOT NULL,
payDate	DATE	NOT NULL,
photo	LONGBLOB,
storeDesc	VARCHAR(1000),
pushUp	TINYINT	NOT NULL,
ownerName	VARCHAR(15)	NOT NULL,
ownerMob	CHAR(10) NOT NULL,
ownerId	CHAR(10) NOT NULL,
ownerAddress	VARCHAR(128)	NOT NULL,
ownerEmail	VARCHAR(128)	NOT NULL,
storeName	VARCHAR(15)		NOT NULL,
storeAddress	VARCHAR(128)	NOT NULL,
storePhone	CHAR(10)	NOT NULL,
storeState	TINYINT	NOT NULL ,
scorePeople	INT	,
total_score	INT	,
storeLat	DECIMAL(10,8)	NOT NULL ,
storeLag	DECIMAL(11,8)	NOT NULL ,
openTime	VARCHAR(225),
restDay	CHAR(7)	,
store_gui	VARCHAR(8)		
);

-- 逸晉
CREATE TABLE p_emp( 
    pEmpId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pEmpPw VARCHAR(15) NOT NULL,
    pEmpName VARCHAR(15) NOT NULL,
    pEmpEmail VARCHAR(31) NOT NULL,
    pEmpAccount VARCHAR(15) NOT NULL,
    pEmpPerm TINYINT NOT NULL CHECK (pEmpPerm IN (0, 1, 2)),
    pEmpState TINYINT NOT NULL CHECK (pEmpState IN (0, 1, 2))
);

-- 逸晉
CREATE TABLE customer (
    customerId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customerPw VARCHAR(15) NOT NULL,
    customerMob CHAR(10) NOT NULL,
    mobChecked CHAR(1) NOT NULL CHECK (mobChecked IN ('N', 'Y')),
    customerEmail VARCHAR(31) NOT NULL,
    customerNic VARCHAR(15) NOT NULL,
    customerAddress VARCHAR(127) NOT NULL,
    custtomerState TINYINT NOT NULL CHECK (custtomerState IN (0, 1, 2))
);

-- 旭東
CREATE TABLE meal_type (
 mealTypeNo        INT NOT NULL AUTO_INCREMENT,
 mealTypeName      VARCHAR(31) NOT NULL,
 CONSTRAINT meal_meal_typeno_pk PRIMARY KEY (mealTypeNo))
 AUTO_INCREMENT = 1;
 
 -- 雯馨
CREATE TABLE customized_items (
 custedItemsNo		INT NOT NULL AUTO_INCREMENT,
 custedName			VARCHAR(31) NOT NULL,
 CONSTRAINT customized_items_custedItemsNo_pk PRIMARY KEY (custedItemsNo))
 AUTO_INCREMENT = 1;
 
 -- 芯郁
CREATE TABLE items_class (
    itemClassId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemClassName VARCHAR(10) NOT NULL,
    storeId INT NOT NULL
);
 
-- 旭東
CREATE TABLE supplier (
 supId             INT NOT NULL AUTO_INCREMENT,
 supplierName      VARCHAR(10) NOT NULL,
 supplierContact   VARCHAR(10) NOT NULL,
 supplierPhone     CHAR(12) NOT NULL,
 storeId           INT NOT NULL,
 supplierState     TINYINT,
 CONSTRAINT supp_sup_id_pk PRIMARY KEY (supId))
 AUTO_INCREMENT = 1;
 
 -- 逸晉
CREATE TABLE  store_emp (
    sEmpId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    sEmpName VARCHAR(15) NOT NULL,
    invPerm TINYINT NOT NULL CHECK (invPerm IN (0, 1)),
    purPerm TINYINT NOT NULL CHECK (purPerm IN (0, 1)),
    manuPerm TINYINT NOT NULL CHECK (manuPerm IN (0, 1)),
    orderPerm TINYINT NOT NULL CHECK (orderPerm IN (0, 1)),
    modifyPerm TINYINT NOT NULL CHECK (modifyPerm IN (0, 1)),
    storeId INT NOT NULL,  -- FK
    sEmpDeptno VARCHAR(15) NOT NULL,
    sEmpTitle VARCHAR(15) NOT NULL,
    sEmpState TINYINT NOT NULL CHECK (sEmpState IN (0, 1, 2))
);

-- 芯郁
CREATE TABLE material (
    itemNumber INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemName VARCHAR(10) NOT NULL,
    itemClassId INT NOT NULL,
    stockQuantity INT NOT NULL,
    quantityNot INT NOT NULL,
    itemUnit CHAR(3) NOT NULL,
    safetyStock INT NOT NULL,
    itemStatus TINYINT NOT NULL CHECK(itemStatus IN (0, 1, 2)),
    purDate DATETIME,
    storeId INT NOT NULL
    
); 
 
-- 芯郁
CREATE TABLE picking (
    pickingNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemNumber INT NOT NULL,
    storeId INT NOT NULL,
    sEmpId INT NOT NULL,
    pickingQuantity INT NOT NULL,
    pickingUnit CHAR(3) NOT NULL,
    pickingStatus TINYINT NOT NULL CHECK(pickingStatus IN (0, 1)),
    pickingClass TINYINT NOT NULL CHECK(pickingClass IN (0, 1)),
    pickingDate DATETIME NOT NULL,
    marks VARCHAR(128)
);

-- 旭東
CREATE TABLE sup_order (
 id                INT NOT NULL AUTO_INCREMENT,
 supId             INT NOT NULL,
 purNo             INT NOT NULL,
 amount            INT NOT NULL,
 unitPrice         INT NOT NULL,
 purDate           DATE,
 preDate           DATE,
 oStatus           TINYINT NOT NULL,
 deliDate          DATE,
 marks             VARCHAR(255),
 storeId           INT NOT NULL,

 CONSTRAINT sup_id_pk PRIMARY KEY (id))
 AUTO_INCREMENT = 1;

-- 雯馨
CREATE TABLE meals (
 mealNo				INT NOT NULL AUTO_INCREMENT,
 mealName			VARCHAR(31) NOT NULL,
 mealPrice			INT NOT NULL,
 mealTypeNo			INT NOT NULL,
 mealOnsale			TINYINT NOT NULL,
 storeId			INT NOT NULL,
 mealPicture		LONGBLOB,
 cookingTime		TIME NOT NULL,
 CONSTRAINT meals_mealNo_pk PRIMARY KEY (mealNo))
 AUTO_INCREMENT = 1;

-- 中群
CREATE TABLE orders( 
orderId	INT	PRIMARY KEY AUTO_INCREMENT	NOT NULL,
orderState	TINYINT		NOT NULL,
orderTime	DATETIME		NOT NULL,
doneTime	DATETIME		,
finishTime	DATETIME		,
delayTime	DATETIME		,
customerId	INT	NOT NULL,
totalPrice	INT		NOT NULL,
storeId	INT		NOT NULL,
delayDesc	VARCHAR(255)		,
comtVal	TINYINT		,
comtCont	VARCHAR(255)		,
comtTime	DATETIME		,
remitDate	DATETIME		,
remitState	CHAR(1)		NOT NULL DEFAULT "N",
payDate	DATETIME		NOT NULL,
payMethod	TINYINT		NOT NULL,
payState	TINYINT		NOT NULL
);
   
 -- 中群
CREATE TABLE refunds( 
id  INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
orderId INT NOT NULL ,
refundState CHAR(1) NOT NULL DEFAULT 'N' ,
refundDollar INT NOT NULL,
refundDate DATETIME NOT NULL
);

 
 -- 承運
CREATE TABLE order_detail (
    orderDtlNo INT AUTO_INCREMENT PRIMARY KEY,
    mealNo INT NOT NULL,
    mealQty INT NOT NULL,
    orderId INT NOT NULL,
    priceBought INT NOT NULL
);
 
 
-- 雯馨
CREATE TABLE customized_detail (
 custedDtlNo		INT NOT NULL AUTO_INCREMENT,
 custedItemsNo		INT NOT NULL,
 custedDtlName		VARCHAR(31) NOT NULL,
 CONSTRAINT customized_detail_custedDtlNo_pk PRIMARY KEY (custedDtlNo))
 AUTO_INCREMENT = 1;
 
 -- 承運
CREATE TABLE customized_order (
    custedOrderNo INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    orderDtlNo INT NOT NULL,
    custedDtlNo INT NOT NULL
);

-- 雯馨
CREATE TABLE customized (
 mealNo				INT NOT NULL,
 custedItemsNo		INT NOT NULL,
 custedStatus		TINYINT,
PRIMARY KEY (mealNo,custedItemsNo)
);


-- 中群
CREATE TABLE like_store( 
id	INT	PRIMARY KEY AUTO_INCREMENT	NOT NULL,
customerId	INT		NOT NULL,
storeId	INT		NOT NULL,
likeUnlike	CHAR(1)		NOT NULL
);

-- 芯郁
CREATE TABLE  service (
    custSerNo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pEmpId INT NOT NULL,
    storeId INT,
    customerId INT,
    custMessage VARCHAR(255) NOT NULL,
    custTime DATETIME NOT NULL    
);


-- ALTER TABLE items_class ADD CONSTRAINT items_class_storeid_fk FOREIGN KEY (storeId) REFERENCES stores(storeId);

-- ALTER TABLE store_emp ADD CONSTRAINT store_emp_storeId_fk FOREIGN KEY (storeId) REFERENCES Stores(storeId);

-- ALTER TABLE material ADD CONSTRAINT material_itemclassId_fk FOREIGN KEY (itemClassId) REFERENCES items_class(itemClassId);
-- ALTER TABLE material ADD CONSTRAINT material_storeid_fk FOREIGN KEY (storeId) REFERENCES stores(storeId);

-- ALTER TABLE picking ADD CONSTRAINT picking_itemnumber_fk FOREIGN KEY (itemNumber) REFERENCES material(itemNumber);
--  ALTER TABLE picking ADD CONSTRAINT picking_storeid_fk FOREIGN KEY (storeId) REFERENCES stores(storeId);
--  ALTER TABLE picking ADD CONSTRAINT picking_sempid_fk FOREIGN KEY (sEmpId) REFERENCES store_emp(sEmpId);

--  ALTER TABLE sup_order ADD CONSTRAINT sup_sup_id_fk FOREIGN KEY (supId) REFERENCES supplier (supId);

-- ALTER TABLE meals ADD CONSTRAINT meals_mealTypeNo_fk FOREIGN KEY (mealTypeNo) REFERENCES meal_type (mealTypeNo);
-- ALTER TABLE meals ADD CONSTRAINT meals_storeId_fk FOREIGN KEY (storeId) REFERENCES stores (storeId);

-- ALTER TABLE  orders ADD CONSTRAINT orders_customerId_FK FOREIGN KEY(customerId)  REFERENCES customer(customerId);
-- ALTER TABLE  orders ADD CONSTRAINT orders_storeId_FK FOREIGN KEY(storeId)  REFERENCES stores(storeId);

-- ALTER TABLE refunds ADD CONSTRAINT refunds_orderId_Fk FOREIGN KEY(orderId) REFERENCES orders(orderId);

-- ALTER TABLE order_detail ADD  CONSTRAINT order_detail_mealNo_fk FOREIGN KEY (mealNo) REFERENCES meals(mealNo);
-- ALTER TABLE order_detail ADD  CONSTRAINT order_detail_orderId_fk FOREIGN KEY (orderId) REFERENCES orders(orderId);

-- ALTER TABLE customized_detail ADD  CONSTRAINT customized_detail_custedItemsNo_fk FOREIGN KEY (custedItemsNo) REFERENCES customized_items (custedItemsNo);

-- ALTER TABLE customized_order ADD  CONSTRAINT customized_order_orderDtlNo_fk FOREIGN KEY (orderDtlNo) REFERENCES order_detail (orderDtlNo);
-- ALTER TABLE customized_order ADD  CONSTRAINT customized_order_custedDtlNo_fk FOREIGN KEY (custedDtlNo) REFERENCES customized_detail (custedDtlNo);

-- ALTER TABLE customized ADD  CONSTRAINT customized_mealNo_fk FOREIGN KEY (mealNo) REFERENCES meals (mealNo);
-- ALTER TABLE customized ADD  CONSTRAINT customized_custedItemsNo_fk FOREIGN KEY (custedItemsNo) REFERENCES customized_items (custedItemsNo);

-- ALTER TABLE like_store ADD CONSTRAINT like_store_customerId_FK FOREIGN KEY(customerId)  REFERENCES customer(customerId);
-- ALTER TABLE like_store ADD CONSTRAINT like_store_storeId_FK FOREIGN KEY(customerId)  REFERENCES customer(customerId);

-- ALTER TABLE service ADD CONSTRAINT service_pempid_fk FOREIGN KEY (pEmpId) REFERENCES p_emp(pEmpId);
-- ALTER TABLE service ADD CONSTRAINT service_storeid_fk FOREIGN KEY (storeId) REFERENCES stores(storeId);
-- ALTER TABLE service ADD CONSTRAINT service_customerid_fk FOREIGN KEY (customerId) REFERENCES customer(customerId);
