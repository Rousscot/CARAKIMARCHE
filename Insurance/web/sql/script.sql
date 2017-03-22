DROP TABLE if exists insurance.roleUser CASCADE;
DROP TABLE if exists insurance.user CASCADE;
DROP TABLE if exists insurance.role CASCADE;

CREATE TABLE insurance.user(
 id int PRIMARY KEY AUTO_INCREMENT,
 userName VARCHAR(255) UNIQUE,
 pswd VARCHAR(255),
 firstName VARCHAR(255),
 lastName VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE insurance.role(
 id int PRIMARY KEY AUTO_INCREMENT,
 roleName VARCHAR(255) UNIQUE
) ENGINE=InnoDB;

CREATE TABLE insurance.roleUser(
userName VARCHAR(255),
roleName VARCHAR(255),
CONSTRAINT pk_roleUser PRIMARY KEY (userName,roleName),
CONSTRAINT fk_userUR FOREIGN KEY (userName) REFERENCES insurance.user(userName),
CONSTRAINT fk_roleUR FOREIGN KEY (roleName) REFERENCES insurance.role(roleName)
) ENGINE=InnoDB;


INSERT INTO insurance.user (userName, pswd, firstName, lastName) VALUES 
("thomas", "D38681074467C0BC147B17A9A12B9EFA8CC10BCF545F5B0BCCCCF5A93C4A2B79", "Thomas","Ostro"), 
("john", "96D9632F363564CC3032521409CF22A852F2032EEC099ED5967C0D000CEC607A", "John","Wick"),
("arthur", "BEFA156F0283EB0062BEB9B86E16A413E1CF8C5135E5518D5C4FA321CE0C7B6B", "Arthur","Ruhtra");
INSERT INTO insurance.role (roleName) VALUES ("ADMIN"), ("BROKER"), ("INSURED");
INSERT INTO insurance.roleUser (userName, roleName) VALUES ("thomas", "ADMIN"), ("john", "BROKER"), ("arthur", "INSURED");

