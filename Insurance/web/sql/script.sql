DROP TABLE IF EXISTS insurance.roleUser CASCADE;
DROP TABLE IF EXISTS insurance.user CASCADE;
DROP TABLE IF EXISTS insurance.role CASCADE;
DROP TABLE IF EXISTS insurance.contractKind;

CREATE TABLE insurance.user(
 userName VARCHAR(255) UNIQUE PRIMARY KEY,
 pswd VARCHAR(255),
 firstName VARCHAR(255),
 lastName VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE insurance.role(
 roleName ENUM('ADMIN', 'BROKER', 'INSURED') UNIQUE PRIMARY KEY 
) ENGINE=InnoDB;

CREATE TABLE insurance.roleUser(
userName VARCHAR(255),
roleName ENUM('ADMIN', 'BROKER', 'INSURED'),
CONSTRAINT pk_roleUser PRIMARY KEY (userName,roleName),
CONSTRAINT fk_userUR FOREIGN KEY (userName) REFERENCES insurance.user(userName),
CONSTRAINT fk_roleUR FOREIGN KEY (roleName) REFERENCES insurance.role(roleName)
) ENGINE=InnoDB;

CREATE TABLE contractKind (
	id INTEGER PRIMARY KEY, 
	title VARCHAR(255), 
	description VARCHAR(255), 
	minAmount INTEGER, 
	category ENUM('HABITATION', 'AUTOMOBILE', 'VIE')
) ENGINE=InnoDB;

INSERT INTO insurance.user (userName, pswd, firstName, lastName) VALUES 
("thomas", "D38681074467C0BC147B17A9A12B9EFA8CC10BCF545F5B0BCCCCF5A93C4A2B79", "Thomas","Ostro"), 
("john", "96D9632F363564CC3032521409CF22A852F2032EEC099ED5967C0D000CEC607A", "John","Wick"),
("arthur", "BEFA156F0283EB0062BEB9B86E16A413E1CF8C5135E5518D5C4FA321CE0C7B6B", "Arthur","Ruhtra");
INSERT INTO insurance.role (roleName) VALUES ("ADMIN"), ("BROKER"), ("INSURED");
INSERT INTO insurance.roleUser (userName, roleName) VALUES ("thomas", "ADMIN"), ("john", "BROKER"), ("arthur", "INSURED");

