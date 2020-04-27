INSERT INTO user (id, email, password, name, role) VALUES 
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin', 'ROLE_ADMIN'),
(2, 'customer@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User', 'ROLE_CUSTOMER');

INSERT INTO PRODUCT_CATEGORY (id, NAME) VALUES 
(1, 'Fruit & Vegetable'),
(2, 'Garment');

INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QTY, CATEGORY_ID) VALUES 
(101, 'Apple', '20.5', 100, 1),
(102, 'Orange', '10.25', 200, 1),
(103, 'Shirt', '220.35', 60, 2),
(104, 'Pant', '410.25', 30, 2);
