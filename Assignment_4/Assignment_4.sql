/*Users table creation.*/
CREATE TABLE Users (
    UID             INTEGER NOT NULL,
    NAME            VARCHAR(30),
    PASSWORD   		VARCHAR(64) FOR BIT DATA NOT NULL,
    PRIMARY KEY (UID)
);

/*Set encryption password to store passwords.*/
SET encryption password = '3Ncrypt6';

/*Insert test values into Users table.*/
INSERT INTO Users values (101, 'Bob', encrypt('J@v@b3@n23'));
INSERT INTO Users values (102, 'Alice', encrypt('C0l0mb1anr0@$t'));

/*Quick query to test values.*/
SELECT * FROM Users;

/*Create Customers table.*/
CREATE TABLE Customers (
    CID             INTEGER NOT NULL,
    NAME            VARCHAR(30),
    PHONE           VARCHAR(20),
    PRIMARY KEY (CID)          
);

/*Create Products table.*/
CREATE TABLE Products (
    PID             INTEGER NOT NULL,
    DESC            VARCHAR(100),
    PRICE           DECIMAL(10,2),
    PRIMARY KEY (PID)
);

/*Create Orders table.*/
CREATE TABLE Orders (
    OID             INTEGER NOT NULL,
    CID             INTEGER NOT NULL,
    PID             INTEGER NOT NULL,
    PRIMARY KEY (OID),
    FOREIGN KEY (CID) REFERENCES Customers(CID),
    FOREIGN KEY (PID) REFERENCES Products(PID)
);
