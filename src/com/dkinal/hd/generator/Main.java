package com.dkinal.hd.generator;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Date T0 = new Date(114, 0, 1);
        Date T1 = new Date(116, 0, 1);
        Date T2 = new Date(117, 0, 1);

        Generator g = new Generator();
        g.generatePatients(100);
        g.generateDoctors(10);
        g.generateOperatingRooms(5, 15); // 5-15 per section
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
        g.generateNewPatients(15);
        g.updateProceduresInTime(T2);
        g.generateNewProcedures(T1, T2);
        g.updateSomePatients();

        // Generate Updates SQL

        // Patient.updates
        // Procedure.updates

        // Generate CSV

    }

    private static void writeToFile(String filename, String data) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-16"))) {
            out.write(data);
        } catch (IOException e) {
        }
    }
}
