INSERT INTO product (name, description, price, amount, category) VALUES ('Green cup', 'Simple green cup', 1.2, 324, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('Big red cup', 'Big cup (volume 500 ml)', 1.4, 127, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('Cup with cats', 'Small cute cup with cats on print', 1.0, 529, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('Cup Star Wars', 'Big cup (volume 500 ml) with Star Wars print', 1.8, 22, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('Cup', 'Simple white cup', 0.2, 723, 'cups');

INSERT INTO product (name, description, price, amount, category) VALUES ('Red pen', 'Classic red pen', 0.4, 311, 'pens');
INSERT INTO product (name, description, price, amount, category) VALUES ('Black gel pen', 'Gel black pen, made in USA', 0.4, 251, 'pens');
INSERT INTO product (name, description, price, amount, category) VALUES ('Blue pen', 'School blue pen', 0.4, 142, 'pens');
INSERT INTO product (name, description, price, amount, category) VALUES ('Black pen', 'Black pen with golden (iron) pieces', 0.9, 323, 'pens');
INSERT INTO product (name, description, price, amount, category) VALUES ('Blue pen', 'Washable blue pen', 1.3, 42, 'pens');

INSERT INTO product (name, description, price, amount, category) VALUES ('Apple', 'Sweet green apple', 0.5, 513, 'fruits');
INSERT INTO product (name, description, price, amount, category) VALUES ('Banana', 'Big banana', 0.6, 501, 'fruits');
INSERT INTO product (name, description, price, amount, category) VALUES ('Orange', 'Fresh orange', 0.8, 394, 'fruits');
INSERT INTO product (name, description, price, amount, category) VALUES ('Mango', 'Very tasty mango', 1.6, 501, 'fruits');
INSERT INTO product (name, description, price, amount, category) VALUES ('Pineapple', 'Fresh yellow pineapple', 1.8, 394, 'fruits');

INSERT INTO user (login, password) VALUES ('ivanov123@gmail.com', 'ivanov');
INSERT INTO user (login, password) VALUES ('petrov44@mail.ua', 'petrov');
INSERT INTO user (login, password) VALUES ('taras2007@gmail.su', 'tara27');
INSERT INTO user (login, password) VALUES ('left-right@gmail.com', 'lr12345');
INSERT INTO user (login, password) VALUES ('uncle7bob@mail.com', '777uncle');
INSERT INTO user (login, password, role) VALUES ('admin', 'admin', 'ADMIN');

INSERT INTO `order` (user_id, state) VALUES (1, 'PAYED');

INSERT INTO order_item (order_id, product_id, amount) VALUES (1, 1, 3);
INSERT INTO order_item (order_id, product_id, amount) VALUES (1, 3, 2);

INSERT INTO `order` (user_id) VALUES (1);

INSERT INTO order_item (order_id, product_id, amount) VALUES (2, 2, 5);
INSERT INTO order_item (order_id, product_id, amount) VALUES (2, 3, 1);

INSERT INTO `order` (user_id, state) VALUES (2, 'CANCELLED');

INSERT INTO order_item (order_id, product_id, amount) VALUES (3, 1, 2);
INSERT INTO order_item (order_id, product_id, amount) VALUES (3, 2, 9);
INSERT INTO order_item (order_id, product_id, amount) VALUES (3, 3, 2);
