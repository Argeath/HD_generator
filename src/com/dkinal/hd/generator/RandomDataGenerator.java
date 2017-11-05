package com.dkinal.hd.generator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDataGenerator {

    enum Gender {
        MALE,
        FEMALE
    }

    private static String[] femaleNames = {
            "Ada", "Adela", "Adelajda", "Adrianna", "Agata", "Agnieszka", "Aldona", "Aleksandra", "Alicja", "Alina",
            "Amanda", "Amelia", "Anastazja", "Andżelika", "Aneta", "Anita", "Anna", "Antonina",
            "Barbara", "Beata", "Berenika", "Bernadeta", "Blanka", "Bogusława", "Bożena",
            "Cecylia", "Celina", "Czesława",
            "Dagmara", "Danuta", "Daria", "Diana", "Dominika", "Dorota",
            "Edyta", "Eliza", "Elwira", "Elżbieta", "Emilia", "Eugenia", "Ewa", "Ewelina",
            "Felicja", "Franciszka",
            "Gabriela", "Grażyna",
            "Halina", "Hanna", "Helena",
            "Iga", "Ilona", "Irena", "Irmina", "Iwona", "Izabela",
            "Jadwiga", "Janina", "Joanna", "Jolanta", "Jowita", "Judyta", "Julia", "Julita", "Justyna",
            "Kamila", "Karina", "Karolina", "Katarzyna", "Kazimiera", "Kinga", "Klaudia", "Kleopatra", "Kornelia",
            "Krystyna",
            "Laura", "Lena", "Leokadia", "Lidia", "Liliana", "Lucyna", "Ludmiła", "Luiza",
            "Łucja",
            "Magdalena", "Maja", "Malwina", "Małgorzata", "Marcelina", "Maria", "Marianna", "Mariola", "Marlena",
            "Marta", "Martyna", "Marzanna", "Marzena", "Matylda", "Melania", "Michalina", "Milena", "Mirosława",
            "Monika",
            "Nadia", "Natalia", "Natasza", "Nikola", "Nina",
            "Olga", "Oliwia", "Otylia",
            "Pamela", "Patrycja", "Paula", "Paulina",
            "Regina", "Renata", "Roksana", "Róża", "Rozalia",
            "Sabina", "Sandra", "Sara", "Sonia", "Stanisława", "Stefania", "Stella", "Sylwia",
            "Tamara", "Tatiana", "Teresa",
            "Urszula",
            "Weronika", "Wiesława", "Wiktoria", "Wioletta",
            "Zofia", "Zuzanna", "Zyta",
            "Żaneta"
    };

    private static String[] maleNames = {
            "Adam", "Adolf", "Adrian", "Albert", "Aleksander", "Aleksy", "Alfred", "Amadeusz", "Andrzej", "Antoni",
            "Arkadiusz", "Arnold", "Artur",
            "Bartłomiej", "Bartosz", "Benedykt", "Beniamin", "Bernard", "Błażej", "Bogdan", "Bogumił", "Bogusław",
            "Bolesław", "Borys", "Bronisław",
            "Cezary", "Cyprian", "Cyryl", "Czesław",
            "Damian", "Daniel", "Dariusz", "Dawid", "Dionizy", "Dominik", "Donald",
            "Edward", "Emanuel", "Emil", "Eryk", "Eugeniusz",
            "Fabian", "Feliks", "Ferdynand", "Filip", "Franciszek", "Fryderyk",
            "Gabriel", "Gerard", "Grzegorz", "Gustaw",
            "Henryk", "Herbert", "Hilary", "Hubert",
            "Ignacy", "Igor", "Ireneusz",
            "Jacek", "Jakub", "Jan", "Janusz", "Jarosław", "Jerzy", "Joachim", "Józef", "Julian", "Juliusz",
            "Kacper", "Kajetan", "Kamil", "Karol", "Kazimierz", "Klaudiusz", "Konrad", "Krystian", "Krzysztof",
            "Lech", "Leon", "Leszek", "Lucjan", "Ludwik",
            "Łukasz",
            "Maciej", "Maksymilian", "Marceli", "Marcin", "Marek", "Marian", "Mariusz", "Mateusz", "Michał",
            "Mieczysław", "Mikołaj", "Miłosz", "Mirosław",
            "Nikodem", "Norbert",
            "Olaf", "Olgierd", "Oskar",
            "Patryk", "Paweł", "Piotr", "Przemysław",
            "Radosław", "Rafał", "Remigiusz", "Robert", "Roman", "Rudolf", "Ryszard",
            "Sebastian", "Seweryn", "Sławomir", "Stanisław", "Stefan", "Sylwester", "Szymon",
            "Tadeusz", "Teodor", "Tomasz",
            "Wacław", "Waldemar", "Wiesław", "Wiktor", "Witold", "Władysław", "Włodzimierz", "Wojciech",
            "Zbigniew", "Zdzisław", "Zenon", "Zygmunt"
    };

    private static String[] femaleSurnames = {
            "nowak", "kowalska", "wiśniewska", "wójcik", "kowalczyk", "kamińska", "lewandowska", "dąbrowska",
            "zielińska", "szymańska", "woźniak", "kozłowska", "jankowska", "wojciechowska", "kwiatkowska", "mazur",
            "krawczyk", "piotrowska", "kaczmarek", "grabowska", "pawłowska", "michalska", "zając", "król", "nowakowska",
            "wieczorek", "jabłońska", "majewska", "adamczyk", "wróbel", "nowicka", "dudek", "olszewska", "jaworska",
            "malinowska", "stępień", "górska", "pawlak", "witkowska", "walczak", "rutkowska", "sikora"
    };

    private static String[] maleSurnames = {
            "nowak", "kowalski", "wiśniewski", "wójcik", "kowalczyk", "kamiński", "lewandowski", "dąbrowski",
            "zieliński", "szymański", "woźniak", "kozłowski", "jankowski", "mazur"
    };

    private static String[] streets = {
            "al. Zwycięstwa", "ul. Bulońska", "ul. Mieszewskiego", "ul. Do studzienki", "ul. Zacna", "ul. Białoruska",
            "ul. Romańska", "ul. Złota", "ul. Szeroka", "ul. Długa", "ul. Kartuska", "ul. Tadeusza Kościuszki",
            "ul. Bolesława Chrobrego", "ul. Adama Mickiewicza", "ul. Żywiecka", "ul. Saperów", "ul. Wajdeloty",
            "ul. Dmowskiego", "ul. Partyzantów", "ul. Stefana Batorego", "ul. Żaglowa", "ul. Swojska",
            "al. gen. Józefa Hallera", "ul. Dąbrowszczaków"
    };

    static String[] doctorPositions = {
            "Asystent",
            "Lekarz",
            "Salowa"
    };

    static String[] sections = {
            "Radiologiczny",
            "Chirurgiczny",
            "Ortopedyczny",
            "Ginekologiczny",
            "Kardiochirurgiczny",
            "Neurologiczny",
            "Neurochirurgiczny",
            "Onkologiczny",
            "Urologiczny"
    };

    private static Map<Gender, String[]> names = Map.of(Gender.FEMALE, femaleNames, Gender.MALE, maleNames);
    private static Map<Gender, String[]> surnames = Map.of(Gender.FEMALE, femaleSurnames, Gender.MALE, maleSurnames);

    static Gender randomGender() {
        return ThreadLocalRandom.current().nextBoolean() ? Gender.MALE : Gender.FEMALE;
    }

    static String randomName(Gender gender) {
        String[] nameList = names.get(gender);

        return formatName(nameList[ThreadLocalRandom.current().nextInt(nameList.length)]);
    }

    static String randomSurname(Gender gender) {
        String[] surnameList = surnames.get(gender);

        return formatName(surnameList[ThreadLocalRandom.current().nextInt(surnameList.length)]);
    }

    static String randomPesel() {
        StringBuilder sb = new StringBuilder();

        int age = ThreadLocalRandom.current().nextInt(18, 81); // 18-80
        int year = 117 - age; // 2017 - age
        sb.append(year);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        if (month < 10)
            sb.append(0);
        sb.append(month);
        int day = ThreadLocalRandom.current().nextInt(1,31);
        if (day < 10)
            sb.append(0);
        sb.append(day);

        for (int i = 0; i < 5; i++)
            sb.append(ThreadLocalRandom.current().nextInt(10));

        return sb.toString();
    }

    static String randomAddress() {
        StringBuilder sb = new StringBuilder();
        String street = streets[ThreadLocalRandom.current().nextInt(streets.length)];

        return sb.
                append(street)
                .append(" ")
                .append(ThreadLocalRandom.current().nextInt(1, 150))
                .append(", 80-1")
                .append(ThreadLocalRandom.current().nextInt(10, 100))
                .append(" Gdańsk")
                .toString();
    }

    static String randomDoctorPosition() {
        return doctorPositions[ThreadLocalRandom.current().nextInt(doctorPositions.length)];
    }

    static String randomSection() {
        return sections[ThreadLocalRandom.current().nextInt(sections.length)];
    }

    static List<ProcedureType> randomProcedureTypes(int min, int max) {
        List<ProcedureType> types = new LinkedList<>();
        int amount = ThreadLocalRandom.current().nextInt(max - min + 1) + min;
        for (int i = 0; i < amount; i++) {
            ProcedureType procedure = ProcedureType.randomProcedureType();

            if (!types.contains(procedure)) {
                types.add(procedure);
            }
        }

        return types;
    }

    static Date randomDate(Date from, Date to) {
        long timestamp = ThreadLocalRandom.current().nextLong(from.getTime(), to.getTime());
        return new Date(timestamp);
    }

    static Date getBirthDate(String pesel) {
        int year = Integer.parseInt(pesel, 0, 2, 10);
        int month = Integer.parseInt(pesel, 2, 4, 10);
        int day = Integer.parseInt(pesel, 4, 6, 10);

        return new GregorianCalendar(year + 1900, month, day).getTime();
    }

    private static String formatName(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }

}
