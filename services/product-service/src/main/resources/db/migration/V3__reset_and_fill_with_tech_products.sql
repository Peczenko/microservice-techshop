-- V3__Reset_and_Insert_Tech_Products.sql

-- Clean all data (order matters because of foreign key constraints)
TRUNCATE TABLE photo, product, category RESTART IDENTITY CASCADE;


-- Insert new tech categories
INSERT INTO category (name, description) VALUES
                                             ('Smartphones', 'Mobile devices with advanced features'),
                                             ('Laptops', 'Portable computers for work and play'),
                                             ('Televisions', 'Modern smart TVs and LED displays'),
                                             ('Tablets', 'Portable touchscreen devices'),
                                             ('Smartwatches', 'Wearable smart devices with health tracking'),
                                             ('Headphones', 'High quality audio devices'),
                                             ('Cameras', 'Digital cameras for photography'),
                                             ('Gaming Consoles', 'Home gaming systems and accessories'),
                                             ('Networking Devices', 'Routers, switches, and networking equipment'),
                                             ('Printers', 'Office and home printers for document printing');

-- Insert 10 products for Smartphones (category_id = 1)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Samsung Galaxy S23', 'Latest Samsung flagship smartphone', 799.99, 100, 1),
                                                                          ('Apple iPhone 14', 'Apple smartphone with advanced camera and performance', 999.99, 80, 1),
                                                                          ('Google Pixel 7', 'Google smartphone with pure Android experience', 599.99, 120, 1),
                                                                          ('OnePlus 11', 'High performance smartphone with fast charging', 699.99, 90, 1),
                                                                          ('Xiaomi 13', 'Affordable flagship with high-end specs', 649.99, 110, 1),
                                                                          ('Sony Xperia 1 IV', 'Premium smartphone with advanced display', 899.99, 70, 1),
                                                                          ('Motorola Edge 40 Pro', 'Edge-to-edge display and robust performance', 749.99, 85, 1),
                                                                          ('Nokia X30', 'Durable smartphone with clean software', 499.99, 95, 1),
                                                                          ('Oppo Find X5 Pro', 'Innovative smartphone with excellent photography', 799.99, 100, 1),
                                                                          ('Asus ROG Phone 7', 'Gaming smartphone with high refresh rate', 899.99, 60, 1);

-- Insert 10 products for Laptops (category_id = 2)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Dell XPS 15', 'High-performance laptop with sleek design', 1499.99, 50, 2),
                                                                          ('Apple MacBook Pro 16', 'Powerful laptop for creative professionals', 2399.99, 30, 2),
                                                                          ('HP Spectre x360', 'Convertible laptop with touch screen', 1299.99, 40, 2),
                                                                          ('Lenovo ThinkPad X1 Carbon', 'Durable business laptop with great keyboard', 1399.99, 45, 2),
                                                                          ('ASUS ROG Zephyrus G15', 'Gaming laptop with powerful GPU', 1599.99, 35, 2),
                                                                          ('Microsoft Surface Laptop 5', 'Sleek design with excellent performance', 1299.99, 50, 2),
                                                                          ('Acer Swift 5', 'Lightweight laptop with long battery life', 1099.99, 55, 2),
                                                                          ('Razer Blade 15', 'Premium gaming laptop with sleek design', 1999.99, 25, 2),
                                                                          ('MSI Creator 17', 'Laptop designed for content creation', 1799.99, 20, 2),
                                                                          ('Samsung Galaxy Book Pro', 'Ultra-thin laptop with AMOLED display', 1199.99, 50, 2);

-- Insert 10 products for Televisions (category_id = 3)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Samsung QN90B Neo QLED', 'High-end 4K QLED TV with excellent picture quality', 2499.99, 15, 3),
                                                                          ('LG OLED C2 Series', 'OLED TV with perfect blacks and vibrant colors', 2299.99, 18, 3),
                                                                          ('Sony Bravia XR A90J', 'Premium OLED TV with cognitive processor XR', 2799.99, 12, 3),
                                                                          ('TCL 6-Series R646', 'Affordable QLED TV with excellent contrast', 1399.99, 20, 3),
                                                                          ('Vizio P-Series Quantum X', 'High performance TV with Quantum Dot technology', 1699.99, 17, 3),
                                                                          ('Hisense U8H', 'Smart TV with impressive brightness and color accuracy', 1599.99, 22, 3),
                                                                          ('Philips OLED+936', 'OLED TV with integrated sound system', 2899.99, 10, 3),
                                                                          ('Panasonic JZ2000', 'Top-tier TV with outstanding processing power', 2799.99, 8, 3),
                                                                          ('Sharp AQUOS 8K', '8K TV for ultimate clarity and detail', 3999.99, 5, 3),
                                                                          ('Samsung The Frame 2022', 'Art-inspired TV that doubles as decor', 1499.99, 14, 3);

-- Insert 10 products for Tablets (category_id = 4)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Apple iPad Pro 12.9', 'Powerful tablet with stunning display', 1099.99, 60, 4),
                                                                          ('Samsung Galaxy Tab S8+', 'High-end Android tablet with S Pen', 849.99, 70, 4),
                                                                          ('Microsoft Surface Pro 9', 'Versatile 2-in-1 tablet and laptop', 999.99, 55, 4),
                                                                          ('Lenovo Tab P12 Pro', 'Premium tablet for productivity and entertainment', 649.99, 65, 4),
                                                                          ('Amazon Fire HD 10 Plus', 'Affordable tablet with vibrant display', 179.99, 80, 4),
                                                                          ('Huawei MatePad Pro', 'Tablet with powerful performance and design', 599.99, 50, 4),
                                                                          ('Xiaomi Pad 5', 'High-resolution tablet at a competitive price', 399.99, 75, 4),
                                                                          ('Asus ZenPad 3S 10', 'Sleek and lightweight tablet for everyday use', 329.99, 85, 4),
                                                                          ('Dell Latitude 7220', 'Enterprise-grade tablet for business use', 799.99, 40, 4),
                                                                          ('Google Pixel Tablet', 'Google’s own tablet with seamless integration', 499.99, 60, 4);

-- Insert 10 products for Smartwatches (category_id = 5)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Apple Watch Series 8', 'Smartwatch with advanced health tracking', 399.99, 70, 5),
                                                                          ('Samsung Galaxy Watch 5', 'Feature-rich smartwatch with sleek design', 299.99, 80, 5),
                                                                          ('Garmin Fenix 7', 'Rugged smartwatch for outdoor activities', 699.99, 30, 5),
                                                                          ('Fitbit Sense 2', 'Health and fitness smartwatch with stress tracking', 249.99, 90, 5),
                                                                          ('Huawei Watch GT 3', 'Long battery life smartwatch with elegant design', 279.99, 65, 5),
                                                                          ('Fossil Gen 6', 'Stylish smartwatch with Wear OS features', 329.99, 50, 5),
                                                                          ('TicWatch Pro 3', 'Dual-layer display for extended battery life', 299.99, 55, 5),
                                                                          ('Suunto 9 Peak', 'High-end GPS smartwatch for athletes', 499.99, 40, 5),
                                                                          ('Withings ScanWatch', 'Hybrid smartwatch with health monitoring', 279.99, 60, 5),
                                                                          ('Amazfit GTR 4', 'Affordable smartwatch with robust features', 199.99, 75, 5);

-- Insert 10 products for Headphones (category_id = 6)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Sony WH-1000XM5', 'Industry-leading noise cancellation headphones', 399.99, 80, 6),
                                                                          ('Bose Noise Cancelling Headphones 700', 'Premium noise cancelling headphones', 379.99, 70, 6),
                                                                          ('Sennheiser Momentum 4 Wireless', 'High fidelity headphones with superior sound', 349.99, 65, 6),
                                                                          ('Apple AirPods Max', 'Over-ear headphones with spatial audio', 549.99, 50, 6),
                                                                          ('Jabra Elite 85h', 'Smart noise cancelling headphones with long battery life', 299.99, 85, 6),
                                                                          ('Beats Studio3', 'High-performance wireless noise cancelling headphones', 279.99, 90, 6),
                                                                          ('Bang & Olufsen Beoplay H95', 'Luxury headphones with exceptional sound quality', 799.99, 30, 6),
                                                                          ('AKG N700NC', 'Reliable noise cancelling headphones with crisp audio', 249.99, 75, 6),
                                                                          ('Skullcandy Crusher ANC', 'Powerful bass headphones with active noise cancellation', 299.99, 60, 6),
                                                                          ('Bowers & Wilkins PX7', 'Premium wireless headphones with immersive sound', 349.99, 55, 6);

-- Insert 10 products for Cameras (category_id = 7)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Canon EOS R5', 'High-resolution mirrorless camera for professionals', 3899.99, 20, 7),
                                                                          ('Nikon Z7 II', 'Versatile full-frame mirrorless camera', 2999.99, 25, 7),
                                                                          ('Sony Alpha a7 IV', 'Advanced mirrorless camera with exceptional image quality', 2499.99, 30, 7),
                                                                          ('Fujifilm X-T5', 'APS-C mirrorless camera with classic design', 1699.99, 40, 7),
                                                                          ('Panasonic Lumix S5', 'Compact full-frame mirrorless camera for creators', 1999.99, 35, 7),
                                                                          ('Olympus OM-D E-M1 Mark III', 'Robust micro four thirds camera for action shots', 1799.99, 30, 7),
                                                                          ('Leica Q2', 'Fixed lens compact camera with superb optics', 4999.99, 15, 7),
                                                                          ('Sigma fp L', 'Compact full-frame camera with high resolution', 2499.99, 20, 7),
                                                                          ('Pentax K-1 Mark II', 'Durable DSLR for landscape photography', 1899.99, 25, 7),
                                                                          ('Canon EOS 5D Mark IV', 'Professional DSLR with excellent performance', 2499.99, 20, 7);

-- Insert 10 products for Gaming Consoles (category_id = 8)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Sony PlayStation 5', 'Next-generation gaming console with stunning graphics', 499.99, 40, 8),
                                                                          ('Microsoft Xbox Series X', 'Powerful gaming console for immersive play', 499.99, 35, 8),
                                                                          ('Nintendo Switch OLED', 'Versatile gaming console with vibrant display', 349.99, 50, 8),
                                                                          ('Sony PlayStation 5 Digital Edition', 'All-digital gaming console without disc drive', 399.99, 45, 8),
                                                                          ('Microsoft Xbox Series S', 'Compact next-gen console with high performance', 299.99, 50, 8),
                                                                          ('Valve Steam Deck', 'Portable gaming PC for gaming on the go', 399.99, 30, 8),
                                                                          ('Nvidia Shield TV Pro', 'Powerful streaming and gaming device', 199.99, 40, 8),
                                                                          ('Atari VCS', 'Modern console with retro gaming features', 299.99, 20, 8),
                                                                          ('Sega Genesis Mini', 'Retro console with built-in classic games', 89.99, 60, 8),
                                                                          ('Evercade Handheld', 'Portable retro gaming console for collectors', 149.99, 25, 8);

-- Insert 10 products for Networking Devices (category_id = 9)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('Cisco Catalyst 9300 Switch', 'Enterprise-grade switch for modern networks', 6999.99, 10, 9),
                                                                          ('Juniper EX4300', 'High-performance switch with advanced features', 5999.99, 12, 9),
                                                                          ('Netgear Nighthawk RAX200', 'Tri-band WiFi 6 router for high-speed connectivity', 499.99, 20, 9),
                                                                          ('TP-Link Archer AX6000', 'High-capacity router with WiFi 6 support', 399.99, 25, 9),
                                                                          ('Ubiquiti UniFi Dream Machine Pro', 'All-in-one network appliance for businesses', 899.99, 15, 9),
                                                                          ('MikroTik Cloud Router Switch 24', 'Versatile router with advanced routing features', 349.99, 30, 9),
                                                                          ('Linksys EA9500', 'High-performance router for large homes', 299.99, 18, 9),
                                                                          ('D-Link DIR-3060', 'Reliable router with advanced security features', 279.99, 22, 9),
                                                                          ('Aruba 2930F', 'Layer 3 switch with high availability and performance', 6499.99, 8, 9),
                                                                          ('Cisco Meraki MX68', 'Cloud-managed security and SD-WAN appliance', 1299.99, 12, 9);

-- Insert 10 products for Printers (category_id = 10)
INSERT INTO product (name, description, price, quantity, category_id) VALUES
                                                                          ('HP LaserJet Pro M404dn', 'Efficient monochrome laser printer for office use', 299.99, 20, 10),
                                                                          ('Canon imageCLASS MF743Cdw', 'Versatile color laser printer with multifunction capabilities', 499.99, 15, 10),
                                                                          ('Brother HL-L2350DW', 'Compact monochrome laser printer with wireless printing', 199.99, 25, 10),
                                                                          ('Epson EcoTank ET-4760', 'Cost-effective inkjet printer with refillable tanks', 399.99, 18, 10),
                                                                          ('Lexmark MB3442adw', 'High-speed monochrome laser printer for demanding offices', 449.99, 12, 10),
                                                                          ('Ricoh SP C262SFNw', 'Robust color printer for small to medium businesses', 349.99, 20, 10),
                                                                          ('Xerox Phaser 6510', 'Reliable color printer with fast printing speeds', 399.99, 15, 10),
                                                                          ('Samsung Xpress M2020W', 'Compact wireless laser printer for home offices', 149.99, 30, 10),
                                                                          ('OKI MC573dn', 'High-performance color printer with advanced features', 549.99, 10, 10),
                                                                          ('Kyocera ECOSYS P5026cdw', 'Efficient color printer with low running costs', 429.99, 14, 10);
