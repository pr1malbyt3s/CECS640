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
    NAME          VARCHAR(40) NOT NULL PRIMARY KEY,
    NUM           INTEGER NOT NULL,
    AGE           INTEGER NOT NULL,
    POS           VARCHAR(2) NOT NULL,
    GP            INTEGER NOT NULL,
    G             INTEGER NOT NULL,
    A             INTEGER NOT NULL,
    PTS           INTEGER NOT NULL,
    PIM           INTEGER NOT NULL,
    xGF           DECIMAL(4,2) NOT NULL,
    xGA           DECIMAL(4,2) NOT NULL
);

/*Create Games table.*/
CREATE TABLE Games (
    DATE          DATE NOT NULL PRIMARY KEY,
    OPP           VARCHAR(30) NOT NULL,
    GF            INTEGER NOT NULL,
    GA            INTEGER NOT NULL,
    SF            INTEGER NOT NULL,
    xGF           DECIMAL(3,2) NOT NULL,
    xGA           DECIMAL(3,2) NOT NULL,
    HDSF          INTEGER NOT NULL,
    HDSA          INTEGER NOT NULL,
    PDO           DECIMAL(4,3) NOT NULL
);

/*Create FreeAgents table.*/
CREATE TABLE FreeAgents (
    NAME          VARCHAR(40) NOT NULL,
    TEAM          VARCHAR(30) NOT NULL,
    AGE           INTEGER NOT NULL,
    POS           VARCHAR(2) NOT NULL,
    G             INTEGER NOT NULL,
    A             INTEGER NOT NULL,
    PTS           INTEGER NOT NULL,
    PM            INTEGER NOT NULL,
    CAP           INTEGER NOT NULL,
    PRIMARY KEY (NAME,TEAM)
    
);  

/*Insert values into Users table.*/
INSERT INTO Users values (101, 'Don', encrypt('J@v@b3@n23'));
INSERT INTO Users values (102, 'Rod', encrypt('C0l0mb1anr0@$t'));

/*Insert values into Players table.*/
INSERT INTO Players values('Andrei Svechnikov',37,20,'R',68,24,37,61,54,83.16,44.56);
INSERT INTO Players values('Brady Skjei',76,26,'D',7,0,1,1,4,5.11,7.02);
INSERT INTO Players values('Brett Pesce',22,25,'D',61,4,14,18,27,57.0,74.72);
INSERT INTO Players values('Brock McGinn',23,26,'L',68,7,10,17,17,32.74,46.56);
INSERT INTO Players values('Dougie Hamilton',19,26,'D',47,14,26,40,32,75.32,44.13);
INSERT INTO Players values('Haydn Fleury',4,23,'D',45,4,10,14,8,24.11,25.56);
INSERT INTO Players values('Jaccob Slavin',74,25,'D',68,6,30,36,10,84.63,71.25);
INSERT INTO Players values('Jake Gardiner',51,29,'D',68,4,20,24,30,58.67,46.41);
INSERT INTO Players values('Joel Edmundson',6,26,'D',68,7,13,20,72,46.26,67.7);
INSERT INTO Players values('Jordan Martinook',48,27,'L',45,2,11,13,22,18.88,20.13);
INSERT INTO Players values('Jordan Staal',11,31,'C',68,8,19,27,40,63.8,55.11);
INSERT INTO Players values('Justin Williams',14,38,'R',20,8,3,11,6,18.24,10.41);
INSERT INTO Players values('Martin Necas',88,21,'C',64,16,20,36,20,41.07,35.62);
INSERT INTO Players values('Morgan Geekie',43,21,'C',2,3,1,4,2,1.58,0.78);
INSERT INTO Players values('Nino Niederreiter',21,27,'R',67,11,18,29,42,49.12,37.24);
INSERT INTO Players values('Ryan Dzingel',18,28,'C',64,8,21,29,30,44.51,33.65);
INSERT INTO Players values('Sebastian Aho',20,22,'C',68,38,28,66,26,89.83,63.4);
INSERT INTO Players values('Teuvo Teravainen',86,25,'L',68,15,48,63,8,90.06,56.71);
INSERT INTO Players values('Trevor van Riemsdyk',57,28,'D',49,1,7,8,10,27.74,28.61);
INSERT INTO Players values('Vincent Trocheck',16,26,'C',7,1,1,2,16,5.39,5.31);
INSERT INTO Players values('Warren Foegele',13,24,'L',68,13,17,30,34,48.21,39.22);                                            
                                              
/*Insert values into Games table.*/
INSERT INTO Games values('2019-10-03','Canadiens',4,3,43,4.64,2.67,16,6,0.986);
INSERT INTO Games values('2019-10-05','Capitals',3,2,28,2.5,3.18,8,9,1.048);
INSERT INTO Games values('2019-10-06','Lightning',4,3,44,3.55,1.01,13,3,0.86);
INSERT INTO Games values('2019-10-08','Panthers',6,3,35,4.18,4.24,14,17,1.111);
INSERT INTO Games values('2019-10-11','Islanders',5,2,40,4.58,1.98,13,3,1.02);
INSERT INTO Games values('2019-10-12','Blue Jackets',2,3,32,1.83,4.02,7,7,0.977);
INSERT INTO Games values('2019-10-15','Kings',2,0,23,2.68,3.19,5,7,1.087);
INSERT INTO Games values('2019-10-16','Sharks',2,5,38,2.57,3.57,12,10,0.825);
INSERT INTO Games values('2019-10-18','Ducks',2,4,24,1.59,2.73,6,6,0.883);
INSERT INTO Games values('2019-10-24','Blue Jackets',3,4,24,2.15,2.24,7,8,0.996);
INSERT INTO Games values('2019-10-26','Blackhawks',4,0,31,3.0,2.79,10,7,1.129);
INSERT INTO Games values('2019-10-29','Flames',2,1,28,3.33,2.82,4,10,1.037);
INSERT INTO Games values('2019-11-01','Red Wings',7,3,34,5.14,2.89,17,12,1.112);
INSERT INTO Games values('2019-11-02','Devils',3,5,27,2.58,3.61,9,11,0.919);
INSERT INTO Games values('2019-11-05','Flyers',1,4,34,3.3,1.56,8,5,0.887);
INSERT INTO Games values('2019-11-07','Rangers',2,4,47,4.49,2.49,11,5,0.832);
INSERT INTO Games values('2019-11-09','Senators',1,4,39,2.19,2.13,7,9,0.892);
INSERT INTO Games values('2019-11-11','Senators',8,2,43,4.44,2.61,10,7,1.117);
INSERT INTO Games values('2019-11-14','Sabres',5,4,29,1.82,2.32,6,10,1.051);
INSERT INTO Games values('2019-11-16','Wild',4,3,28,1.75,2.64,7,12,1.057);
INSERT INTO Games values('2019-11-19','Blackhawks',4,2,33,4.69,3.45,12,14,1.059);
INSERT INTO Games values('2019-11-21','Flyers',3,5,36,3.78,2.3,16,7,0.866);
INSERT INTO Games values('2019-11-23','Panthers',4,2,34,3.83,1.82,7,6,1.031);
INSERT INTO Games values('2019-11-24','Red Wings',2,0,36,2.7,1.28,4,6,1.056);
INSERT INTO Games values('2019-11-27','Rangers',2,3,43,3.99,4.03,18,11,0.927);
INSERT INTO Games values('2019-11-29','Predators',0,3,31,3.18,2.89,13,8,0.893);
INSERT INTO Games values('2019-11-30','Lightning',3,2,24,1.58,2.82,3,10,1.072);
INSERT INTO Games values('2019-12-03','Bruins',0,2,24,1.56,1.83,4,5,0.941);
INSERT INTO Games values('2019-12-05','Sharks',3,2,29,2.46,2.66,8,14,1.002);
INSERT INTO Games values('2019-12-07','Wild',6,2,40,4.22,2.53,8,6,1.067);
INSERT INTO Games values('2019-12-10','Oilers',6,3,32,2.42,3.4,7,12,1.104);
INSERT INTO Games values('2019-12-12','Canucks',0,1,43,2.94,2.2,10,7,0.962);
INSERT INTO Games values('2019-12-14','Flames',4,0,31,3.73,2.74,10,11,1.129);
INSERT INTO Games values('2019-12-17','Jets',6,3,28,3.28,2.98,14,9,1.123);
INSERT INTO Games values('2019-12-19','Avalanche',3,1,40,3.64,2.94,9,9,1.039);
INSERT INTO Games values('2019-12-21','Panthers',2,4,44,4.22,2.23,18,7,0.864);
INSERT INTO Games values('2019-12-23','Maple Leafs',6,8,40,2.66,3.42,8,12,0.945);
INSERT INTO Games values('2019-12-27','Rangers',3,5,42,4.18,2.63,9,12,0.863);
INSERT INTO Games values('2019-12-28','Capitals',6,4,29,3.38,3.28,5,10,1.069);
INSERT INTO Games values('2019-12-31','Canadiens',3,1,36,5.3,2.86,12,10,1.05);
INSERT INTO Games values('2020-01-03','Capitals',3,4,41,4.6,2.59,13,8,0.935);
INSERT INTO Games values('2020-01-05','Lightning',1,3,29,3.21,3.8,7,13,0.919);
INSERT INTO Games values('2020-01-07','Flyers',5,4,33,3.05,2.15,12,6,0.961);
INSERT INTO Games values('2020-01-10','Coyotes',3,0,29,2.73,2.28,12,7,1.103);
INSERT INTO Games values('2020-01-11','Kings',2,0,30,3.55,3.34,4,9,1.067);
INSERT INTO Games values('2020-01-13','Capitals',0,2,23,1.97,2.41,4,7,0.933);
INSERT INTO Games values('2020-01-16','Blue Jackets',2,3,34,3.12,1.24,8,2,0.892);
INSERT INTO Games values('2020-01-17','Ducks',1,2,26,2.48,3.14,9,10,0.984);
INSERT INTO Games values('2020-01-19','Islanders',2,1,32,2.32,2.84,8,7,0.994);
INSERT INTO Games values('2020-01-21','Jets',4,1,29,5.16,2.19,18,7,1.079);
INSERT INTO Games values('2020-01-31','Golden Knights',3,4,25,2.77,3.9,11,14,1.012);
INSERT INTO Games values('2020-02-02','Canucks',4,3,32,2.65,3.58,12,10,1.008);
INSERT INTO Games values('2020-02-04','Blues',3,6,28,2.26,2.56,7,9,0.876);
INSERT INTO Games values('2020-02-06','Coyotes',5,3,31,3.61,3.25,9,12,1.07);
INSERT INTO Games values('2020-02-08','Golden Knights',6,5,34,1.79,2.86,8,12,1.015);
INSERT INTO Games values('2020-02-11','Stars',1,4,25,1.7,3.39,5,11,0.932);
INSERT INTO Games values('2020-02-14','Devils',5,2,31,3.21,3.7,8,12,1.107);
INSERT INTO Games values('2020-02-16','Oilers',3,4,31,2.43,2.45,8,7,0.93);
INSERT INTO Games values('2020-02-18','Predators',4,1,30,3.63,1.78,11,5,1.099);
INSERT INTO Games values('2020-02-21','Rangers',2,5,29,2.81,3.97,8,10,0.93);
INSERT INTO Games values('2020-02-22','Maple Leafs',6,3,47,5.04,3.0,18,11,1.012);
INSERT INTO Games values('2020-02-25','Stars',1,4,41,3.25,1.9,7,6,0.774);
INSERT INTO Games values('2020-02-28','Avalanche',2,3,47,4.38,3.78,14,8,0.949);
INSERT INTO Games values('2020-02-29','Canadiens',3,4,30,2.42,2.75,8,11,0.997);
INSERT INTO Games values('2020-03-05','Flyers',1,4,29,2.16,2.8,7,10,0.909);
INSERT INTO Games values('2020-03-07','Islanders',3,2,28,2.31,1.81,10,9,1.03);
INSERT INTO Games values('2020-03-08','Penguins',6,2,36,2.93,2.54,10,10,1.1);
INSERT INTO Games values('2020-03-10','Red Wings',5,2,36,2.61,1.71,9,6,1.059);
                                              
/*Insert values into FreeAgents Table*/                                              
INSERT INTO FreeAgents values('Alex Pietrangelo', 'Blues', 30, 'D', 16, 36, 52, 11, 6500000);
INSERT INTO FreeAgents values('Taylor Hall', 'Coyotes', 28, 'L', 16, 36, 52, -14, 6000000);
INSERT INTO FreeAgents values('Mikael Granlund', 'Predators', 28, 'C', 17, 13, 30, -4, 5750000);
INSERT INTO FreeAgents values('Justin Schultz', 'Penguins', 29, 'D', 3, 9, 12, -13, 5500000);
INSERT INTO FreeAgents values('Tyson Barrie', 'Maple Leafs', 28, 'D', 5, 34, 39, -7, 5500000);
