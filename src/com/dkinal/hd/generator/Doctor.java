package com.dkinal.hd.generator;

import java.util.Date;

class Doctor {

    String name;
    String surname;
    PersonalDataGenerator.Gender gender;
    String pesel;
    Date birthDate;
    String section; // oddzial
    int room; // pokoj
    String address; // adres zameldowania
    String position; // stanowisko

    static Doctor randomDoctor() {
        Doctor p = new Doctor();

        p.gender = PersonalDataGenerator.randomGender();
        p.name = PersonalDataGenerator.randomName(p.gender);
        p.surname = PersonalDataGenerator.randomSurname(p.gender);
        p.pesel = PersonalDataGenerator.randomPesel();
        p.birthDate = PersonalDataGenerator.getBirthDate(p.pesel);
        p.room = PersonalDataGenerator.random.nextInt(300) + 100;
        p.address = PersonalDataGenerator.randomAddress();
        p.position = PersonalDataGenerator.randomDoctorPosition();

        return p;
    }

    @Override
    public String toString() {
        return "Doctor{" +
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
