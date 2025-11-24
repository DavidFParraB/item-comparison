-- Insertar datos en la tabla ITEM
INSERT INTO "ITEM" VALUES
(999.99, 'Monitores', 'Televisor de 55 pulgadas con tecnologia OLED.', 'MLA101', 'Smart TV 4K', '4.8', '...'),
(450.0, 'Monitores', 'Televisor de 50 pulgadas, ideal para gaming.', 'MLA205', 'Smart TV Full HD', '4.2', '...'),
(999.99, 'Monitores', 'Televisor de 55 pulgadas con tecnologia OLED de ultima generacionn.', 'MLA100', 'Smart TV 4K OLED 55', '4.8', '...'),
(1850.5, 'Monitores', 'Pantalla gigante con resolucion ultra alta y 120Hz.', 'MLA330', 'TV QLED 75 Alta Gama', '4.9', '...'),
(199.99, 'Monitores', 'Modelo compacto, ideal para cocinas o dormitorios.', 'MLA415', 'Smart TV Ba1sico 32', '3.9', '...');

-- Insertar datos en la tabla ITEM_CHARACTERISTICS
INSERT INTO "ITEM_CHARACTERISTICS" VALUES
('size', 'MLA101', '55'),
('technology', 'MLA101', 'OLED'),
('brand', 'MLA101', 'SAMSUNG'),
('size', 'MLA205', '50'),
('technology', 'MLA205', 'LED'),
('brand', 'MLA205', 'LG'),
('size', 'MLA100', '55'),
('resolution', 'MLA100', '4K'),
('technology', 'MLA100', 'OLED'),
('brand', 'MLA100', 'SAMSUNG'),
('size', 'MLA330', '75'),
('resolution', 'MLA330', '4k'),
('TasaRefresco', 'MLA330', '120Hz'),
('brand', 'MLA330', 'TCL'),
('size', 'MLA415', '32'),
('resolution', 'MLA415', 'HD'),
('technology', 'MLA415', 'LED'),
('brand', 'MLA415', 'HISENSE');