USE Armazem;

SHOW VARIABLES LIKE "secure_file_priv";


-- -----------------------------------------------------
-- Load Table `mydb`.`Gestor`
-- -----------------------------------------------------
LOAD DATA INFILE 'csv/gestor.csv'
    INTO TABLE Gestor
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;


-- -----------------------------------------------------
-- Load Table `mydb`.`Palete`
-- -----------------------------------------------------
LOAD DATA INFILE 'csv/palete.csv'
    INTO TABLE Palete
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;


-- -----------------------------------------------------
-- Load Table `mydb`.`Pedido`
-- -----------------------------------------------------
LOAD DATA INFILE 'csv/pedido.csv'
    INTO TABLE Pedido
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;


-- -----------------------------------------------------
-- Table `mydb`.`Conteudo`
-- -----------------------------------------------------
LOAD DATA INFILE 'csv/conteudo.csv'
    INTO TABLE Conteudo
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;

-- -----------------------------------------------------
-- Load Table `mydb`.`Robots`
-- -----------------------------------------------------
LOAD DATA INFILE 'csv/robots.csv'
    INTO TABLE Robots
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
