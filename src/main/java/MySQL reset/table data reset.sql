-- 中群
INSERT   INTO stores	(	storeId	,	storeAccount	,	storePw	,	storeLv	,	createDate	,	payDate	,	photo	,	storeDesc	,	pushUp	,	ownerName	,	ownerMob	,	ownerId	,	ownerAddress	,	ownerEmail	,	storeName	,	storeAddress	,	storePhone	,	storeState	,	scorePeople	,	totalScore	,	storeLat	,	storeLag	,	openTime	,	restDay	,	storeGui	)
VALUES 	(	1	,	"A001"	,	"A00111"	,	1	,	"2023-12-13"	,	"2023-12-13"	,	NULL	,	"快來快來快來快來快來快來快來快來快來快來快來快來快來快來"	,	1	,	"王某"	,	"0912345678"	,	"K133543945"	,	"中壢市中央路"	,	"W@gmail.com"	,	"三商巧福"	,	"中壢市中央路"	,	"025575304"	,	1	,	2	,	6	,	12.46396364	,	124.58402857	,	"11:00~14:00,17:00~19:00"	,	"1111111"	,	73549374	),
	(	2	,	"W9898"	,	"787318"	,	1	,	"2023-12-13"	,	"2023-12-13"	,	NULL	,	"好吃的都在這"	,	1	,	"陳某"	,	"0954224532"	,	"O219575642",	"中壢市民權路"	,	"chen@gmail.com"	,	"梅亭"	,	"中壢市民權路"	,	"025535736"	,	1	,	1	,	4	,	5.49375649	,	111.96749386	,	"11:00~14:00,17:00~19:00"	,	"0111110"	,	null	),
	(	3	,	"A123"	,	"1241433"	,	1	,	"2023-12-13"	,	"2023-12-13"	,	NULL	,	"便宜又大碗"	,	1	,	"徐某"	,	"0987576342"	,	"J128674926"	,	"中壢市中正路"	,	"shu@gmail.com"	,	"米諾早餐店"	,	"中壢市中正路"	,	"025596327"	,	1	,	0	,	0	,	84.75937284	,	87.857224	,	"11:00~14:00,17:00~19:00"	,	"0111111"	,	null	);


-- 逸晉
INSERT INTO p_emp (pEmpPw, pEmpName, pEmpEmail, pEmpAccount, pEmpPerm, pEmpState)
VALUES 
    (CONCAT('PW', LPAD(FLOOR(RAND() * 1000), 3, '0')), CONCAT('Name', FLOOR(RAND() * 10)), CONCAT('emp', FLOOR(RAND() * 1000), '@example.com'), CONCAT('Acc', FLOOR(RAND() * 10)), FLOOR(RAND() * 3), FLOOR(RAND() * 3))
;
INSERT INTO p_emp (pEmpPw, pEmpName, pEmpEmail, pEmpAccount, pEmpPerm, pEmpState)
VALUES 
    (CONCAT('PW', LPAD(FLOOR(RAND() * 1000), 3, '0')), CONCAT('Name', FLOOR(RAND() * 10)), CONCAT('emp', FLOOR(RAND() * 1000), '@example.com'), CONCAT('Acc', FLOOR(RAND() * 10)), FLOOR(RAND() * 3), FLOOR(RAND() * 3))
;
INSERT INTO p_emp (pEmpPw, pEmpName, pEmpEmail, pEmpAccount, pEmpPerm, pEmpState)
VALUES 
    (CONCAT('PW', LPAD(FLOOR(RAND() * 1000), 3, '0')), CONCAT('Name', FLOOR(RAND() * 10)), CONCAT('emp', FLOOR(RAND() * 1000), '@example.com'), CONCAT('Acc', FLOOR(RAND() * 10)), FLOOR(RAND() * 3), FLOOR(RAND() * 3))
;

INSERT  p_emp (pEmpPw, pEmpName, pEmpEmail, pEmpAccount, pEmpPerm, pEmpState)
VALUES  (	"dog"	, "狗哥" , "dog@mail", "dog",2,1 );

-- 逸晉
INSERT INTO Customer (customerPw, customerMob, mobChecked, customerEmail, customerNic, customerAddress, customerState)
VALUES
(CONCAT('pw_', FLOOR(RAND() * 1000)), LPAD(FLOOR(RAND() * 10000000000), 10, '0'), IF(RAND() < 0.5, 'N', 'Y'), CONCAT('user', FLOOR(RAND() * 1000), '@example.com'), CONCAT('nic_', FLOOR(RAND() * 1000)), CONCAT('Address_', FLOOR(RAND() * 1000)), FLOOR(RAND() * 3))
;
-- 逸晉
INSERT INTO Customer (customerPw, customerMob, mobChecked, customerEmail, customerNic, customerAddress, customerState)
VALUES
(CONCAT('pw_', FLOOR(RAND() * 1000)), LPAD(FLOOR(RAND() * 10000000000), 10, '0'), IF(RAND() < 0.5, 'N', 'Y'), CONCAT('user', FLOOR(RAND() * 1000), '@example.com'), CONCAT('nic_', FLOOR(RAND() * 1000)), CONCAT('Address_', FLOOR(RAND() * 1000)), FLOOR(RAND() * 3))
;
-- 逸晉
INSERT INTO Customer (customerPw, customerMob, mobChecked, customerEmail, customerNic, customerAddress, customerState)
VALUES
(CONCAT('pw_', FLOOR(RAND() * 1000)), LPAD(FLOOR(RAND() * 10000000000), 10, '0'), IF(RAND() < 0.5, 'N', 'Y'), CONCAT('user', FLOOR(RAND() * 1000), '@example.com'), CONCAT('nic_', FLOOR(RAND() * 1000)), CONCAT('Address_', FLOOR(RAND() * 1000)), FLOOR(RAND() * 3))
;

-- 旭東
INSERT INTO meal_type VALUES (1,'飯');
INSERT INTO meal_type VALUES (2,'麵');
INSERT INTO meal_type VALUES (3,'粥');

-- 雯馨
INSERT INTO customized_items VALUES (1, '辣度');
INSERT INTO customized_items VALUES (2, '加蔥');
INSERT INTO customized_items VALUES (3, '加酸菜');
-- 芯郁
INSERT INTO items_class (itemClassName, storeId) VALUES ( '食材', 1);
INSERT INTO items_class (itemClassName, storeId) VALUES ( '醬料', 2);
INSERT INTO items_class (itemClassName, storeId) VALUES ( '餐具', 3);

-- 旭東
INSERT INTO supplier VALUES (1,'Ti','帥哥','034251108',1,1);
INSERT INTO supplier VALUES (2,'Ba','handsome','0227120589',2,1);
INSERT INTO supplier VALUES (3,'Me','阿格麗','034506333',3,1);

-- 逸晉
 INSERT INTO store_emp (sEmpName, invPerm, purPerm, manuPerm, orderPerm, modifyPerm, storeId, sEmpDeptno, sEmpTitle, sEmpState)
 VALUES
     (CONCAT('Name', FLOOR(RAND() * 10)), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), 1, CONCAT('Dept', FLOOR(RAND() * 10)), CONCAT('Title', FLOOR(RAND() * 10)), FLOOR(RAND() * 3));
 INSERT INTO store_emp (sEmpName, invPerm, purPerm, manuPerm, orderPerm, modifyPerm, storeId, sEmpDeptno, sEmpTitle, sEmpState)
 VALUES
     (CONCAT('Name', FLOOR(RAND() * 10)), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), 1, CONCAT('Dept', FLOOR(RAND() * 10)), CONCAT('Title', FLOOR(RAND() * 10)), FLOOR(RAND() * 3));
 INSERT INTO store_emp (sEmpName, invPerm, purPerm, manuPerm, orderPerm, modifyPerm, storeId, sEmpDeptno, sEmpTitle, sEmpState)
 VALUES
     (CONCAT('Name', FLOOR(RAND() * 10)), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), FLOOR(RAND() * 2), 1, CONCAT('Dept', FLOOR(RAND() * 10)), CONCAT('Title', FLOOR(RAND() * 10)), FLOOR(RAND() * 3));




-- 芯郁
INSERT INTO Material (itemName, itemClassId, stockQuantity, quantityNot
, itemUnit, safetyStock, itemStatus, purDate, storeId) VALUES
( '牛肉', 1, 10, 2, '斤', 20, 1, null, 1 );
INSERT INTO Material (itemName, itemClassId, stockQuantity, quantityNot
, itemUnit, safetyStock, itemStatus, purDate, storeId) VALUES
( '番茄', 1, 8, 0, '個', 20, 1, null, 1 );
INSERT INTO Material (itemName, itemClassId, stockQuantity, quantityNot
, itemUnit, safetyStock, itemStatus, purDate, storeId) VALUES
( '免洗碗', 3, 60, 20, '個', 80, 0, '2023-12-11 00:00:00', 1 );
-- 芯郁
INSERT INTO Picking (itemNumber, storeId, sEmpId, pickingQuantity, pickingUnit, 
pickingStatus, pickingClass, pickingDate, marks) VALUES
( 1, 1, 1, 5,'包', 0, 0, '2023-12-11 00:00:00', null);
INSERT INTO Picking (itemNumber, storeId, sEmpId, pickingQuantity, pickingUnit, 
pickingStatus, pickingClass, pickingDate, marks) VALUES
( 2, 2, 1, 5,'個', 0, 0, '2023-12-12 00:00:00', null);
INSERT INTO Picking (itemNumber, storeId, sEmpId, pickingQuantity, pickingUnit, 
pickingStatus, pickingClass, pickingDate, marks) VALUES
( 3, 3, 1, 5,'個', 0, 1, '2023-12-13 00:00:00', '食材過期');

-- 旭東
INSERT INTO sup_order VALUES (1,1,1,7414,54,'2023-12-13','2023-12-14',7,'2023-12-15','',1);
INSERT INTO sup_order VALUES (2,2,3,7414,54,'2023-12-13','2023-12-14',7,'2023-12-15','',1);
INSERT INTO sup_order VALUES (3,3,5,7414,54,'2023-12-13','2023-12-14',7,'2023-12-15','',1);
-- 雯馨
INSERT INTO meals VALUES (1, '原汁牛肉麵', 145, 2, 2, 1, null, '00:30:00');
INSERT INTO meals VALUES (2, '紅燒排骨飯', 120, 1, 2, 1, null, '00:25:00');
INSERT INTO meals VALUES (3, '紅燒排骨麵', 120, 2, 2, 1, null, '00:20:00');

-- 中群
INSERT   INTO orders(		orderId	,	orderState	,	orderTime	,	doneTime	,	finishTime	,	delayTime	,	customerId	,	totalPrice	,	storeId	,	delayDesc	,	comtVal	,	comtCont	,	comtTime	,	remitDate	,	remitState	,	payDate	,	payMethod	,	payState	)
VALUES 	(	1	,	1	,	"2023-12-13 09:00:00"	,	"2023-12-13 09:10:00"	,	"2023-12-13 09:13:00"	,	"2023-12-13 10:13:00"	,	1	,	500	,	1	,	"過30分鐘先給別人"	,	2	,	"晚去還要等"	,	"2023-12-13 12:00:00"	,	NULL	,	"N"	,	"2023-12-13 09:00:00"	,	1	,	1	),
	(	2	,	1	,	"2023-12-13 09:00:00"	,	"2023-12-13 09:15:00"	,	"2023-12-13 09:19:00"	,	NULL	,	2	,	1000	,	1	,	NULL	,	5	,	"好吃"	,	"2023-12-13 13:00:00"	,	NULL	,	"N"	,	"2023-12-13 09:00:00"	,	1	,	1	),
	(	3	,	2	,	"2023-12-13 09:00:00"	,	"2023-12-13 09:20:00"	,	"2023-12-13 09:29:00"	,	NULL	,	3	,	1500	,	2	,	NULL	,	4	,	"推薦"	,	"2023-12-13 12:09:00"	,	NULL	,	"N"	,	"2023-12-13 09:00:00"	,	1	,	1	);

-- 中群
INSERT   INTO refunds	(	id	,	orderId	,	refundState	,	refundDollar	,creationDate ,	refundDate	)
VALUES 	(	1	,	1	,	"N"	,	500	,	"2023-12-13",	"2023-12-13"	) ,
	(	2	,	2	,	"N"	,	1000	,	"2023-12-13",	"2023-12-13"	),
	(	3	,	3	,	"N"	,	1500	,	"2023-12-13",	"2023-12-13"	) ;

-- 承運
INSERT INTO order_detail (mealNo, mealQty, orderId, priceBought) VALUES
(1, 2, 1, 30),
(2, 1, 1, 25),
(3, 3, 2, 40);


-- 雯馨
INSERT INTO customized_detail VALUES (1, 1, '不辣');
INSERT INTO customized_detail VALUES (2, 1, '微辣');
INSERT INTO customized_detail VALUES (3, 1, '小辣');
INSERT INTO customized_detail VALUES (4, 1, '中辣');
INSERT INTO customized_detail VALUES (5, 1, '大辣');
INSERT INTO customized_detail VALUES (6, 2, '不加蔥');
INSERT INTO customized_detail VALUES (7, 2, '加蔥');
INSERT INTO customized_detail VALUES (8, 3, '不加酸菜');
INSERT INTO customized_detail VALUES (9, 3, '加酸菜');

-- 承運
INSERT INTO customized_order (orderDtlNo, custedDtlNo) VALUES
(1, 1),
(2, 2),
(3, 3);

-- 雯馨
  INSERT INTO customized VALUES (1, 1, 1); 
  INSERT INTO customized VALUES (1, 2, 1);
  INSERT INTO customized VALUES (1, 3, 0);

 
  -- 中群
INSERT   INTO  like_store	(	id	,	customerId	,	storeId	,	likeUnlike	)
VALUES 	(	1	,	1	,	1	,	"L"	) ,
	(	2	,	1	,	2	,	"U"	) ,
	(	3	,	2	,	1	,	"L"	),
	(	4	,	3	,	1	,	"U"	) ;


-- 芯郁
INSERT INTO Service (custSerNo, pEmpId, storeId, customerId, custMessage, custTime)VALUES
(1, 1, 1, NULL, '你好,請問如何升級會員?', '2023-12-11 00:00:00');
INSERT INTO Service (custSerNo, pEmpId, storeId, customerId, custMessage, custTime)VALUES
(2, 1, 2, NULL, '你好,請問如何設定員工權限?', '2023-12-12 00:00:00');
INSERT INTO Service (custSerNo, pEmpId, storeId, customerId, custMessage, custTime)VALUES
(3, 1, NULL, 1, '你好,請問如何查詢收藏店家?', '2023-12-13 00:00:00');

