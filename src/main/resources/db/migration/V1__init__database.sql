CREATE TABLE IF NOT EXISTS Franchises (
    id int NOT NULL,
    name varchar(255) NOT NULL,
    date_create DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Branches (
    id int NOT NULL,
    id_franchise int NOT NULL,
    name varchar(255) NOT NULL,
    date_create DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id_franchise) REFERENCES Franchises(id)
);

CREATE TABLE IF NOT EXISTS Products (
    id int NOT NULL,
    id_branch int NOT NULL,
    name varchar(255) NOT NULL,
    stock int NOT NULL,
    date_create DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_branch) REFERENCES Branches(id)
);