CREATE TABLE IF NOT EXISTS franchises (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS branches (
    id int NOT NULL AUTO_INCREMENT,
    id_franchise int NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_franchise) REFERENCES franchises(id)
);

CREATE TABLE IF NOT EXISTS products (
    id int NOT NULL AUTO_INCREMENT,
    id_branch int NOT NULL,
    name varchar(255) NOT NULL,
    stock int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_branch) REFERENCES branches(id)
);