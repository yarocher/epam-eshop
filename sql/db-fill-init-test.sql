INSERT INTO product (name, description, price, amount, category) VALUES ('cup1', 'desc of cup1', 234.12, 324, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('cup2', 'desc of cup2', 224.32, 235, 'cups');
INSERT INTO product (name, description, price, amount, category) VALUES ('book1', 'desc of book1', 24.32, 311, 'books');

INSERT INTO user (login, password) VALUES ('ivanov123@gmail.com', 'ivanov');
INSERT INTO user (login, password, state) VALUES ('petrov44@mail.ru', 'petrov44', 'BLOCKED');
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
