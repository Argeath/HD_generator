USE ZabiegHarm

DELETE FROM Zabieg
DELETE FROM JestPrzystosowana
DELETE FROM MozeWykonywac
DELETE FROM Lekarz
DELETE FROM Pacjent
DELETE FROM SalaOperacyjna
DELETE FROM TypZabiegu

BULK INSERT dbo.Lekarz FROM '/home/bulks/Lekarz.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.Pacjent FROM '/home/bulks/Pacjent.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.SalaOperacyjna FROM '/home/bulks/SalaOperacyjna.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.TypZabiegu FROM '/home/bulks/TypZabiegu.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.MozeWykonywac FROM '/home/bulks/MozeWykonywac.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.JestPrzystosowana FROM '/home/bulks/JestPrzystosowana.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.Zabieg FROM '/home/bulks/Zabieg.bulk' WITH (FIELDTERMINATOR='|')
