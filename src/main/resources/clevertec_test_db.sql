DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS discount_card;

CREATE TABLE product (
    id SERIAL NOT NULL,
    name VARCHAR(50),
    price DOUBLE PRECISION NOT NULL,
    on_action boolean NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE discount_card (
    id SERIAL NOT NULL,
    number INTEGER NOT NULL,
    discount_value DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO product (name, price, on_action)
VALUES ('Mars', 1.48, false),
('Snickers', 1.99, true),
('Bounty', 1.59, false),
('Nuts', 27.59, false),
('Maize', 0.99, false),
('Rice', 1.22, false),
('Fish', 22.11, false),
('Orange', 10.22, false),
('Big Papa Jones Corn in little package', 1.59, true),
('Tomato juice', 9.22, false),
('Lemon', 0.77, false),
('Chicken meat', 6.55, false),
('Beef meat', 9.29, false),
('Milk 2.5%', 2.59, false),
('Milk 3.5%', 2.99, false),
('Cheese', 15.69, false),
('Corn', 3.25, true),
('Bread black', 2.01, false),
('Bread white', 2.51, false),
('Donuts', 3.55, false),
('Cookie Mams Breakfast', 5.59, true),
('Cookie Grandmas Dinner', 6.99, true);

INSERT INTO discount_card (number, discount_value)
VALUES (1234, 4),
(1235, 2),
(1236, 1),
(1237, 4),
(1238, 7),
(1239, 4),
(1240, 1.5),
(1241, 2.3),
(1242, 5.5),
(1243, 4.5),
(1244, 3),
(1245, 0.5);
