package com.dkinal.hd.generator;

import java.util.*;

class Patient implements BulkInsertable, CSVInsertable {
    String name;
    String surname;
    RandomDataGenerator.Gender gender;
    String pesel;
    Date birthDate;
    String section; // oddzial
    int room; // pokoj
    String address; // adres zameldowania
    int bed; // lozko

    static Map<String, Map<String, Object>> updates = new HashMap<>();

    static Patient randomPatient() {
        Patient p = new Patient();

        p.gender = RandomDataGenerator.randomGender();
        p.name = RandomDataGenerator.randomName(p.gender);
        p.surname = RandomDataGenerator.randomSurname(p.gender);
        p.pesel = RandomDataGenerator.randomPesel();
        p.birthDate = RandomDataGenerator.getBirthDate(p.pesel);
//        p.section = RandomDataGenerator.randomSection();
//        p.room = ThreadLocalRandom.current().nextInt(300) + 100;
        p.address = RandomDataGenerator.randomAddress();
//        p.bed = ThreadLocalRandom.current().nextInt(5) + 1; // 1 - 5

        return p;
    }

    void update() {
        surname = RandomDataGenerator.randomSurname(gender);
        address = RandomDataGenerator.randomAddress();

        updates.put(pesel, Map.of("Nazwisko", surname));
    }

    @Override
    public String toBulk() {
        return Bulk.build(List.of(pesel, name, surname));
    }

    @Override
    public String toCSV() {
        return Bulk.build(List.of(pesel, section, address, room+"", name, surname, Generator.DATE_FORMAT.format(birthDate), bed+""));
    }

    static String CSVHeader = Bulk.build(List.of("Pesel", "Oddział", "Adres zameldowania", "Pokój", "Imię", "Nazwisko", "Data urodzenia", "Łóżko"));

    public static String generateSQLUpdates() {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Map<String, Object>> e: updates.entrySet()) {
            sb.append("UPDATE dbo.Pacjent SET ");

            for(Map.Entry<String, Object> o: e.getValue().entrySet()) {
                sb.append(o.getKey()).append(" = '").append(o.getValue()).append("',");
            }

            sb.deleteCharAt(sb.length() - 1); // remove last ,

            sb.append(" WHERE PESEL = '").append(e.getKey()).append("';\r\n");
        }

        return sb.toString();
    }
}
