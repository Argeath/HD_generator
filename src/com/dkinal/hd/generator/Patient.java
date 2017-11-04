package com.dkinal.hd.generator;

import java.util.Date;

class Patient {
    String name;
    String surname;
    PersonalDataGenerator.Gender gender;
    String pesel;
    Date birthDate;
    String section; // oddzial
    int room; // pokoj
    String address; // adres zameldowania
    int bed; // lozko

    static Patient randomPatient() {
        Patient p = new Patient();

        p.gender = PersonalDataGenerator.randomGender();
        p.name = PersonalDataGenerator.randomName(p.gender);
        p.surname = PersonalDataGenerator.randomSurname(p.gender);
        p.pesel = PersonalDataGenerator.randomPesel();
        p.birthDate = PersonalDataGenerator.getBirthDate(p.pesel);
        p.room = PersonalDataGenerator.random.nextInt(300) + 100;
        p.address = PersonalDataGenerator.randomAddress();
        p.bed = PersonalDataGenerator.random.nextInt(5) + 1; // 1 - 5

        return p;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", pesel='" + pesel + '\'' +
                ", birthDate=" + birthDate +
                ", section='" + section + '\'' +
                ", room=" + room +
                ", address='" + address + '\'' +
                '}';
    }
}
