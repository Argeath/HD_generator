package com.dkinal.hd.generator;

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

        // TODO: save to Pacjent.bulk
        String patients = g.generatePatientsBulk();

        // TODO: Save to Lekarz.bulk
        String doctors = g.generateDoctorsBulk();

        // TODO: Save to TypZabiegu.bulk
        String procedureTypes = g.generateProcedureTypesBulk();

        // TODO: Save to MozeWykonywac.bulk
        String doctorsAllowedProcedures = g.generateDoctorsAllowedProceduresBulk();

        // TODO: Save to SalaOperacyjna.bulk
        String operatingRooms = g.generateOperatingRoomsBulk();

        // TODO: Save to JestPrzystosowana.bulk
        String operatingRoomsAllowedProcedures = g.generateOperatingRoomsAllowedProceduresBulk();

        // TODO: Save to Zabieg.bulk
        String procedures = g.generateProceduresBulk();

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
}
