CREATE TABLE WifiAccessPoint(
id VARCHAR(100) NOT NULL,
programa VARCHAR(100) NOT NULL,
colonia VARCHAR(100) DEFAULT NULL,
lat VARCHAR(100) DEFAULT NULL,
lon INT,
alcaldia INT,
PRIMARY KEY (id)
);