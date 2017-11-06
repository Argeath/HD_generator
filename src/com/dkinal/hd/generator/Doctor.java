package com.dkinal.hd.generator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Doctor implements BulkInsertable, CSVInsertable {

    String name;
    String surname;
    RandomDataGenerator.Gender gender;
    String pesel;
    Date birthDate;
    String section; // oddzial
    int room; // pokoj
    String address; // adres zameldowania
    String position; // stanowisko

    List<ProcedureType> allowedProcedures = new LinkedList<>();

    static Doctor randomDoctor() {
        Doctor p = new Doctor();

        p.gender = RandomDataGenerator.randomGender();
        p.name = RandomDataGenerator.randomName(p.gender);
        p.surname = RandomDataGenerator.randomSurname(p.gender);
        p.pesel = RandomDataGenerator.randomPesel();
        p.birthDate = RandomDataGenerator.getBirthDate(p.pesel);
        p.section = RandomDataGenerator.randomSection();
        p.room = ThreadLocalRandom.current().nextInt(300) + 100;
        p.address = RandomDataGenerator.randomAddress();
        p.position = RandomDataGenerator.randomDoctorPosition();

        p.allowedProcedures = RandomDataGenerator.randomProcedureTypes(3, 5);

        return p;
    }

    @Override
    public String toBulk() {
        return Bulk.build(List.of(pesel, name, surname));
    }

    @Override
    public String toCSV() {
        return Bulk.build(List.of(pesel, section, address, room+"", name, surname, position));
    }

    static String CSVHeader = Bulk.build(List.of("Pesel", "Oddział", "Adres zameldowania", "Gabinet", "Imię", "Nazwisko", "Stanowisko"));
}
