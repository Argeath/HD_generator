package com.dkinal.hd.generator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Procedure implements BulkInsertable {
    int id;
    String patientPesel;
    String doctorPesel;
    int operatingRoomId;
    ProcedureType procedureType;
    Date dateOfRegister;
    Date dateOfProcedure;
    String status;
    int reservationInSeconds;
    Date dateOfEnd;

    static int lastId = 0;

    static Map<Integer, Map<String, String>> updates = new HashMap<>();

    Procedure(Patient patient, ProcedureType procedureType, Doctor doctor, OperatingRoom room, int reservation, Date register, Date procedure, Date current) {
        this.id = lastId++;
        this.patientPesel = patient.pesel;
        this.doctorPesel = doctor.pesel;
        this.operatingRoomId = room.id;
        this.procedureType = procedureType;
        this.dateOfRegister = register;
        this.dateOfProcedure = procedure;
        this.reservationInSeconds = reservation;

        this.updateInTime(current, false);
    }

    void updateInTime(Date current, boolean generateUpdates) {
        String statusBefore = status;

        if (dateOfEnd == null) {
            if (current.after(dateOfProcedure) && ThreadLocalRandom.current().nextInt(10) < 1) { // 10%
                status = "NIE_POJAWIL_SIE";
                dateOfEnd = dateOfProcedure;

                if (generateUpdates) {
                    updates.put(id, Map.of("Status", status, "DataZakonczenia", Generator.DATETIME_FORMAT.format(dateOfEnd)));
                }
                return;
            }

            long randomEnd = dateOfProcedure.getTime()
                    + (reservationInSeconds + ThreadLocalRandom.current().nextInt(30 * 60) - 15 * 60) * 1000; // +- 15min

            if (randomEnd < current.getTime()) {
                dateOfEnd = new Date(randomEnd);
            }
        }

        if (current.before(dateOfProcedure) && dateOfEnd == null) {
            status = "OCZEKUJE";
        } else if (current.after(dateOfProcedure) && dateOfEnd == null) {
            status = "W_TRAKCIE";
        } else if (current.after(dateOfEnd)) {
            status = "WYKONANY";
        }

        if (generateUpdates && !statusBefore.equals(status)) {
            updates.put(id, Map.of("Status", status, "DataZakonczenia",  Generator.DATETIME_FORMAT.format(dateOfEnd)));
        }
    }

    @Override
    public String toBulk() {
        return Bulk.build(List.of(id + "",
                                  patientPesel,
                                  doctorPesel,
                                  operatingRoomId + "",
                                  procedureType.id + "",
                                  Generator.DATETIME_FORMAT.format(dateOfRegister),
                                  Generator.DATETIME_FORMAT.format(dateOfProcedure),
                                  status,
                                  reservationInSeconds + "",
                                  dateOfEnd == null ? "null" : Generator.DATETIME_FORMAT.format(dateOfEnd)));
    }

    public static String generateSQLUpdates() {
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Integer, Map<String, String>> e: updates.entrySet()) {
            sb.append("UPDATE dbo.Zabieg SET ");

            for(Map.Entry<String, String> o: e.getValue().entrySet()) {
                sb.append(o.getKey()).append(" = '").append(o.getValue()).append("',");
            }

            sb.deleteCharAt(sb.length() - 1); // remove last ,

            sb.append(" WHERE ID = ").append(e.getKey()).append(";\r\n");
        }

        return sb.toString();
    }
}
