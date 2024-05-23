CREATE TABLE restaurant (
                            id SERIAL PRIMARY KEY,
                            location VARCHAR(50)
);

CREATE TABLE units (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(20)
);

CREATE TABLE ingredient_template (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(20),
                                     price DOUBLE PRECISION,
                                     id_unit INT REFERENCES units(id)
);

CREATE TABLE movements (
                          id SERIAL PRIMARY KEY,
                          type VARCHAR(10),
                          quantity NUMERIC,
                          date TIMESTAMP,
                          quantity_remaining DOUBLE PRECISION,
                          id_ingredient_template INT REFERENCES ingredient_template(id)
);

CREATE TABLE price (
                       id SERIAL PRIMARY KEY,
                       date DATE,
                       selling_price DOUBLE PRECISION,
                       cost_price DOUBLE PRECISION
);

CREATE TABLE menus (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(20),
                       price DOUBLE PRECISION,
                       id_restaurant INT REFERENCES restaurant(id),
                       id_price INT REFERENCES price(id)
);

CREATE TABLE ingredients (
                            id SERIAL PRIMARY KEY,
                            id_ingredient_template INT,
                            id_menus INT,
                            quantity_necessary DOUBLE PRECISION,
                            CONSTRAINT ingredient_ingredient_template_FK FOREIGN KEY (id_ingredient_template) REFERENCES ingredient_template(id),
                            CONSTRAINT ingredient_menus_FK FOREIGN KEY (id_menus) REFERENCES menus(id)
);


INSERT INTO restaurant (location) VALUES
                                      ('Antananarivo'),
                                      ('Toamasina'),
                                      ('Antsirabe'),
                                      ('Mahajanga'),
                                      ('Fianarantsoa');

INSERT INTO units (name) VALUES
                            ('Piece'),
                            ('KG'),
                            ('Litre'),
                            ('Gram'),
                            ('Milliliter');

INSERT INTO ingredient_template (name, price, id_unit) VALUES
                                                           ('Bread', 500, 1),
                                                           ('Sausage', 20000, 2),
                                                           ('Mayonnaise', 10000, 3),
                                                           ('Ketchup', 5000, 3),
                                                           ('Chicken', 15000, 2);

INSERT INTO movements (type, quantity, date, quantity_remaining, id_ingredient_template) VALUES
                                                                                            ('Entrance', 500, '2024-05-01 08:00:00', 500, 1),
                                                                                            ('Entrance', 10, '2024-05-01 08:00:00', 10, 3),
                                                                                            ('Entrance', 50, '2024-05-02 08:10:00', 50, 2),
                                                                                            ('Exit', 10, '2024-05-02 10:10:00', 40, 2),
                                                                                            ('Entrance', 20, '2024-05-03 09:00:00', 20, 5);

INSERT INTO price (date, selling_price, cost_price) VALUES
                                                        ('2024-05-01', 5000, 3750),
                                                        ('2024-05-01', 6000, 4500),
                                                        ('2024-05-01', 5500, 4200),
                                                        ('2024-05-01', 7000, 5200),
                                                        ('2024-05-01', 8000, 6000);

INSERT INTO menus (name, price, id_restaurant, id_price) VALUES
                                                             ('Hot Dog', 5000, 1, 1),
                                                             ('Burger', 6000, 2, 2),
                                                             ('Wings', 5500, 3, 3),
                                                             ('Salad', 7000, 4, 4),
                                                             ('Fried Chicken', 8000, 5, 5);

INSERT INTO ingredients (id_ingredient_template, id_menus, quantity_necessary) VALUES
                                                                                  (1, 1, 1),
                                                                                  (2, 1, 0.125),
                                                                                  (3, 1, 0.05),
                                                                                  (4, 1, 0.05),
                                                                                  (5, 2, 0.2);
