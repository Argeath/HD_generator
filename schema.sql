--CREATE DATABASE ZabiegHarm

USE ZabiegHarm

DROP TABLE IF EXISTS Zabieg
DROP TABLE IF EXISTS JestPrzystosowana
DROP TABLE IF EXISTS MozeWykonywac
DROP TABLE IF EXISTS Lekarz
DROP TABLE IF EXISTS Pacjent
DROP TABLE IF EXISTS SalaOperacyjna
DROP TABLE IF EXISTS TypZabiegu

CREATE TABLE Lekarz
(
    PESEL nchar(11) PRIMARY KEY,
    Imie nvarchar(40) NOT NULL,
    Nazwisko nvarchar(40) NOT NULL,
)

CREATE TABLE Pacjent
(
    PESEL nchar(11) PRIMARY KEY,
    Imie nvarchar(40) NOT NULL,
    Nazwisko nvarchar(40) NOT NULL,
)

CREATE TABLE SalaOperacyjna
(
    NumerSali INTEGER PRIMARY KEY,
    Oddzial nvarchar(50) NOT NULL,
)

CREATE TABLE TypZabiegu
(
    ID INTEGER IDENTITY(1, 1) PRIMARY KEY,
    Nazwa nvarchar(50) NOT NULL,
)

CREATE TABLE MozeWykonywac
(
    FK_PESEL_LEK nchar(11) NOT NULL FOREIGN KEY REFERENCES Lekarz,
    FK_ID_TYP_ZAB INTEGER NOT NULL FOREIGN KEY REFERENCES TypZabiegu,
)

CREATE TABLE JestPrzystosowana
(
    FK_NR_SALI INTEGER NOT NULL FOREIGN KEY REFERENCES SalaOperacyjna,
    FK_ID_TYP_ZAB INTEGER NOT NULL FOREIGN KEY REFERENCES TypZabiegu,
)

CREATE TABLE Zabieg
(
	ID INTEGER IDENTITY(1, 1) PRIMARY KEY,
	FK_PESEL_PACJ nchar(11) NOT NULL FOREIGN KEY REFERENCES Pacjent,
	FK_PESEL_LEK nchar(11) NOT NULL FOREIGN KEY REFERENCES Lekarz,
	FK_NUMER_SALI INTEGER NOT NULL FOREIGN KEY REFERENCES SalaOperacyjna,
	FK_ID_TYP_ZAB INTEGER NOT NULL FOREIGN KEY REFERENCES TypZabiegu,
	DataZapisania DATETIME,
	DataZabiegu DATETIME,
	Status VARCHAR(30) NOT NULL CHECK (Status IN('NIE_POJAWIL_SIE', 'WYKONANY', 'PRZELOZONY', 'W_TRAKCIE', 'OCZEKUJE')),
	CzasRezerwacji INTEGER,
	DataZakonczenia DATETIME,
)
