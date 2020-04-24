INSERT INTO user (id, email, password, name, role) VALUES 
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin', 'ROLE_ADMIN'),
(2, 'customer@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User', 'ROLE_CUSTOMER');

INSERT INTO product(PRODUCT_ID,PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QTY) VALUES 
(1, 'Apple', '20.5', 90),
(2, 'Banana', '12.25', 40),
(3, 'Orange', '10.3', 70);