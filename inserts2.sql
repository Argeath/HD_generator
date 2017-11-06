USE ZabiegHarm

BULK INSERT dbo.Pacjent FROM '/home/bulks/Pacjent2.bulk' WITH (FIELDTERMINATOR='|')
BULK INSERT dbo.Zabieg FROM '/home/bulks/Zabieg2.bulk' WITH (FIELDTERMINATOR='|')
