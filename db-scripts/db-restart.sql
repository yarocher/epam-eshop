DROP TABLE IF EXISTS attributes;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS category_key;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS key_value;
DROP TABLE IF EXISTS `values`;
DROP TABLE IF EXISTS `keys`;

CREATE TABLE IF NOT EXISTS `keys` (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS `values` (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS key_value (
	key_id INT  NOT NULL,
	value_id INT NOT NULL ,
	FOREIGN KEY (key_id) REFERENCES `keys`(id) ON DELETE CASCADE,
	FOREIGN KEY (value_id) REFERENCES `values`(id) ON DELETE CASCADE,
	PRIMARY KEY (key_id, value_id)
);
CREATE TABLE IF NOT EXISTS categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS category_key(
	category_id INT NOT NULL ,
	key_id INT NOT NULL ,
	FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE,
	FOREIGN KEY (key_id) REFERENCES `keys`(id) ON DELETE CASCADE,
	PRIMARY KEY (category_id, key_id)
);
CREATE TABLE IF NOT EXISTS products (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (255) NOT NULL,
	description VARCHAR (2000),
	amount INT DEFAULT 0 CHECK (amount >= 0),
	price DECIMAL (12, 2) NOT NULL CHECK (price >= 0),
	date_creation DATETIME DEFAULT CURRENT_TIMESTAMP,	
	date_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,	
	category_id INT NOT NULL,
	FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS attributes (
	product_id INT NOT NULL ,
	category_id INT NOT NULL ,
	key_id INT NOT NULL ,
	value_id INT NOT NULL ,
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ,
	FOREIGN KEY (category_id) REFERENCES categories(id)  ON DELETE CASCADE,
	FOREIGN KEY (key_id) REFERENCES `keys`(id)  ON DELETE CASCADE,
	FOREIGN KEY (value_id) REFERENCES `values`(id)  ON DELETE CASCADE,
	FOREIGN KEY (category_id, key_id) REFERENCES category_key (category_id, key_id) ON DELETE CASCADE ,
	FOREIGN KEY (key_id, value_id) REFERENCES key_value (key_id, value_id) ON DELETE CASCADE ,
	PRIMARY KEY (product_id, category_id, key_id, value_id)
		
);
