/*Create Users table.*/
CREATE TABLE Users (
    UID           INTEGER NOT NULL,
    NAME          VARCHAR(30),
    PASSWORD   	  VARCHAR(64) FOR BIT DATA NOT NULL,
    PRIMARY KEY (UID)
);

/*Set encryption password to store passwords.*/
SET encryption password = '3Ncrypt6';

/*Create Players table.*/
CREATE TABLE Players (
    NUMBER        INTEGER NOT NULL,
    AGE           INTEGER NOT NULL,
    NAME          VARCHAR(40) NOT NULL PRIMARY KEY,
    POS           VARCHAR(2) NOT NULL,
    GP            INTEGER NOT NULL,
    G             INTEGER NOT NULL,
    A             INTEGER NOT NULL,
    PTS           INTEGER NOT NULL,
    PIM           INTEGER NOT NULL,
    +\/-           INTEGER NOT NULL,
    xGF           DECIMAL(4,2),
    xGA           DECIMAL(4,2)
);

/*Create Games table.*/
CREATE TABLE Games (
    DATE          DATE NOT NULL PRIMARY KEY,
    OPP           VARCHAR(3) NOT NULL,
    GF            INTEGER NOT NULL,
    GA            INTEGER NOT NULL,
    SHOTS         INTEGER NOT NULL,
    HITS          INTEGER NOT NULL,
    FOW           INTEGER NOT NULL,
    PPO           INTEGER NOT NULL,
    PPG           INTEGER NOT NULL,
    PIM           INTEGER NOT NULL
);

/*Create FreeAgents table.*/
CREATE TABLE FreeAgents (
    NAME          VARCHAR(40) NOT NULL,
    TEAM          VARCHAR(3) NOT NULL,
    AGE           INTEGER NOT NULL,
    POS           VARCHAR(2) NOT NULL,
    G             INTEGER NOT NULL,
    A             INTEGER NOT NULL,
    PTS           INTEGER NOT NULL,
    +\/-           INTEGER NOT NULL,
    CAP           INTEGER NOT NULL,
    PRIMARY KEY (NAME,TEAM)
    
);  

/*Insert test values into Users table.*/
INSERT INTO Users values (101, 'Bob', encrypt('J@v@b3@n23'));
INSERT INTO Users values (102, 'Alice', encrypt('C0l0mb1anr0@$t'));
