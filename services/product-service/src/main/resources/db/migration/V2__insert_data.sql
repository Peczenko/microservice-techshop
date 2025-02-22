INSERT INTO category (name, description) VALUES
                                             ('Electronics', 'Electronic gadgets and devices'),
                                             ('Books', 'Various kinds of books and literature'),
                                             ('Clothing', 'Men and Women clothing items'),
                                             ('Home & Kitchen', 'Appliances and home decor'),
                                             ('Sports', 'Sports equipment and outdoor gear'),
                                             ('Toys', 'Toys and games for kids'),
                                             ('Automotive', 'Car accessories and tools'),
                                             ('Beauty', 'Beauty and personal care products'),
                                             ('Garden', 'Garden tools and outdoor living'),
                                             ('Office', 'Office supplies and stationery');

-- Insert sample products for Electronics (category_id = 1)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Smartphone', 'Latest smartphone with high resolution screen', 699.99, 50, 1),
                                                                          ('Laptop', 'High performance laptop', 1299.99, 30, 1),
                                                                          ('Tablet', '10-inch tablet with responsive screen', 399.99, 40, 1),
                                                                          ('Headphones', 'Noise-cancelling over-ear headphones', 199.99, 80, 1);

-- Insert sample products for Books (category_id = 2)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Bestselling Novel', 'A captivating novel by a bestselling author', 19.99, 100, 2),
                                                                          ('Non-fiction Book', 'Informative non-fiction book on history', 24.99, 70, 2),
                                                                          ('Comic Book', 'Popular comic book series', 9.99, 150, 2);

-- Insert sample products for Clothing (category_id = 3)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('T-shirt', 'Cotton T-shirt for everyday wear', 9.99, 200, 3),
                                                                          ('Jeans', 'Blue denim jeans', 49.99, 150, 3),
                                                                          ('Jacket', 'Warm winter jacket', 89.99, 60, 3);

-- Insert sample products for Home & Kitchen (category_id = 4)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Blender', 'High-speed blender for smoothies', 59.99, 40, 4),
                                                                          ('Coffee Maker', 'Automatic coffee maker with timer', 79.99, 35, 4),
                                                                          ('Sofa', 'Comfortable 3-seater sofa', 499.99, 10, 4);

-- Insert sample products for Sports (category_id = 5)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Tennis Racket', 'Lightweight tennis racket', 129.99, 25, 5),
                                                                          ('Running Shoes', 'Comfortable running shoes', 89.99, 50, 5),
                                                                          ('Bicycle Helmet', 'High-quality bicycle helmet for safety', 39.99, 40, 5);

-- Insert sample products for Toys (category_id = 6)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Building Blocks', 'Educational building blocks set', 29.99, 120, 6),
                                                                          ('Puzzle Game', 'Challenging puzzle game', 14.99, 80, 6),
                                                                          ('Action Figure', 'Collectible action figure', 19.99, 90, 6);

-- Insert sample products for Automotive (category_id = 7)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Car Seat Cover', 'Protective car seat cover', 39.99, 70, 7),
                                                                          ('Oil Filter', 'High quality oil filter', 12.99, 100, 7),
                                                                          ('Tire Pressure Gauge', 'Accurate tire pressure gauge', 8.99, 150, 7);

-- Insert sample products for Beauty (category_id = 8)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Facial Cream', 'Moisturizing facial cream', 24.99, 80, 8),
                                                                          ('Shampoo', 'Natural shampoo for daily use', 14.99, 90, 8),
                                                                          ('Perfume', 'Long-lasting fragrance perfume', 49.99, 40, 8);

-- Insert sample products for Garden (category_id = 9)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Lawn Mower', 'Efficient electric lawn mower', 299.99, 15, 9),
                                                                          ('Garden Hose', 'Durable 50ft garden hose', 39.99, 40, 9),
                                                                          ('Outdoor Chair', 'Comfortable outdoor chair', 59.99, 25, 9);

-- Insert sample products for Office (category_id = 10)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Stapler', 'Reliable office stapler', 12.99, 100, 10),
                                                                          ('Notebook', 'College-ruled notebook', 4.99, 200, 10),
                                                                          ('Pen Pack', 'Pack of 10 ballpoint pens', 9.99, 150, 10);
