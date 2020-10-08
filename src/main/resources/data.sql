INSERT INTO user (id, email, password, name, role) VALUES 
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin', 'ROLE_ADMIN'),
(2, 'customer@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User', 'ROLE_CUSTOMER');

INSERT INTO PRODUCT_CATEGORY (id, NAME) VALUES 
(201, 'Fruit & Vegetable'),
(202, 'Garment');

INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QTY, CATEGORY_ID) VALUES 
(101, 'Apple', '20.5', 11, 201),
(102, 'Orange', '10.25', 22, 201),
(103, 'Shirt', '220.35', 33, 202),
(104, 'Pant', '410.25', 44, 202);
