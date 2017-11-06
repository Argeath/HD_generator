package com.dkinal.hd.generator;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date T0 = new Date(114, 0, 1); // 2014
        Date T1 = new Date(116, 0, 1); // 2016
        Date T2 = new Date(117, 0, 1); // 2017
        int pacjentowNaStart = 100;
        int nowychPacjentowPoT2 = 15;
        int lekarzyNaStart = 10;
        int minimumPokoiNaOddzial = 5;
        int maksimumPokoiNaOddzial = 15;


        Generator g = new Generator();
        g.generatePatients(pacjentowNaStart);
        g.generateDoctors(lekarzyNaStart);
        g.generateOperatingRooms(minimumPokoiNaOddzial, maksimumPokoiNaOddzial);
        g.generateProcedures(T0, T1);

        // Generate SQL

        String patients = g.generatePatientsBulk();
        writeToFile("bulks/Pacjent.bulk", patients);

        String doctors = g.generateDoctorsBulk();
        writeToFile("bulks/Lekarz.bulk", doctors);

        String procedureTypes = g.generateProcedureTypesBulk();
        writeToFile("bulks/TypZabiegu.bulk", procedureTypes);

        String doctorsAllowedProcedures = g.generateDoctorsAllowedProceduresBulk();
        writeToFile("bulks/MozeWykonywac.bulk", doctorsAllowedProcedures);

        String operatingRooms = g.generateOperatingRoomsBulk();
        writeToFile("bulks/SalaOperacyjna.bulk", operatingRooms);

        String operatingRoomsAllowedProcedures = g.generateOperatingRoomsAllowedProceduresBulk();
        writeToFile("bulks/JestPrzystosowana.bulk", operatingRoomsAllowedProcedures);

        String procedures = g.generateProceduresBulk();
        writeToFile("bulks/Zabieg.bulk", procedures);

        // Generate CSV
        String doctorsCSV = g.generateDoctorsCSV();
        writeToFile("Lekarze.csv", doctorsCSV);

        String patientsCSV = g.generatePatientsCSV();
        writeToFile("Pacjenci.csv", patientsCSV);


        // *** Update - T2 ***

        g.generateNewPatients(nowychPacjentowPoT2);
        g.updateProceduresInTime(T2);
        g.generateNewProcedures(T1, T2);
        g.updateSomePatients();

        // Generate Updates SQL
        String updates = "USE ZabiegHarm\r\n\r\n";
        updates += Procedure.generateSQLUpdates();
        updates += Patient.generateSQLUpdates();

        String newProcedures = g.generateNewProceduresBulk();
        writeToFile("bulks/Zabieg2.bulk", newProcedures);

        String newPatients = g.generateNewPatientsBulk();
        writeToFile("bulks/Pacjent2.bulk", newPatients);

        writeToFile("updates.sql", updates);

        // Generate CSV
        doctorsCSV = g.generateDoctorsCSV();
        writeToFile("Lekarze2.csv", doctorsCSV);

        patientsCSV = g.generatePatientsCSV();
        writeToFile("Pacjenci2.csv", patientsCSV);

    }

    private static void writeToFile(String filename, String data) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-16"))) {
            out.write(data);
        } catch (IOException e) {
        }
    }
}
