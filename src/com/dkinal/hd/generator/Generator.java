package com.dkinal.hd.generator;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Generator {

    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final static long YEAR = 365L * 24 * 60 * 60 * 1000;

    List<Patient> patientList = new LinkedList<>();
    List<Patient> newPatientList = new LinkedList<>();

    List<Doctor> doctorList = new LinkedList<>();

    List<Procedure> procedureList = new LinkedList<>();
    List<Procedure> newProcedureList = new LinkedList<>();

    List<OperatingRoom> operatingRoomList = new LinkedList<>();

    void generatePatients(int patients) {
        for (int i = 0; i < patients; i++) {
            patientList.add(Patient.randomPatient());
        }
    }

    void generateNewPatients(int patients) {
        for (int i = 0; i < patients; i++) {
            newPatientList.add(Patient.randomPatient());
        }
    }

    void generateDoctors(int doctors) {
        for (int i = 0; i < doctors; i++) {
            doctorList.add(Doctor.randomDoctor());
        }
    }

    void generateOperatingRooms(int minRoomsPerSection, int maxRoomsPerSection) {
        for (String section : RandomDataGenerator.sections) {
            int amount = ThreadLocalRandom.current().nextInt(maxRoomsPerSection - minRoomsPerSection + 1) + minRoomsPerSection;

            for (int i = 0; i < amount; i++) {
                operatingRoomList.add(new OperatingRoom(section));
            }
        }
    }

    void generateProcedures(Date T0, Date T1) {
        for (Patient patient : patientList) {
            Procedure p = generateProcedure(T0, T1, patient);

            procedureList.add(p);
        }
    }

    void generateNewProcedures(Date T0, Date T1) {

        // 10% of old patients
        for (Patient patient : patientList) {
            if(ThreadLocalRandom.current().nextInt(10) > 1) { // 10%
                continue;
            }

            Procedure p = generateProcedure(T0, T1, patient);

            newProcedureList.add(p);
        }

        // New patients
        for (Patient patient : newPatientList) {
            Procedure p = generateProcedure(T0, T1, patient);

            newProcedureList.add(p);
        }
    }

    Procedure generateProcedure(Date T0, Date T1, Patient patient) {
        Doctor doctor = doctorList.get(ThreadLocalRandom.current().nextInt(doctorList.size()));
        OperatingRoom room = null;
        ProcedureType procedureType = null;

        int tries = 0;
        do {
            procedureType = doctor.allowedProcedures.get(ThreadLocalRandom.current().nextInt(doctor.allowedProcedures.size()));
            room = findRoomForProcedure(procedureType);
        } while (room == null || ++tries > 3);

        if (room == null) {
            return null;
        }

        int reservationInSeconds = ThreadLocalRandom.current().nextInt(30 * 60, 4 * 60 * 60); // 30 min - 4h

        Date register = RandomDataGenerator.randomDate(T0, T1);
        Date dateOfProcedure = RandomDataGenerator.randomDate(register, new Date(T1.getTime() + YEAR)); // + 1 YEAR

        patient.section = room.section;
        patient.room = ThreadLocalRandom.current().nextInt(300) + 100;
        patient.bed = ThreadLocalRandom.current().nextInt(5) + 1; // 1 - 5

        return new Procedure(patient, procedureType, doctor, room, reservationInSeconds, register, dateOfProcedure, T1);
    }

    void updateSomePatients() {
        for(Patient patient: patientList) {
            if(ThreadLocalRandom.current().nextInt(100) < 3) { // 3%
                patient.update();
            }
        }
    }

    void updateProceduresInTime(Date Tn) {
        for(Procedure procedure : procedureList) {
            procedure.updateInTime(Tn, true);
        }
    }

    String generatePatientsBulk() {
        return generateBulk(patientList);
    }

    String generateDoctorsBulk() {
        return generateBulk(doctorList);
    }

    String generateDoctorsAllowedProceduresBulk() {
        StringBuilder sb = new StringBuilder();

        for(Doctor doctor: doctorList) {
            for(ProcedureType type: doctor.allowedProcedures) {
                sb.append(doctor.pesel).append('|').append(type.id).append("\r\n");
            }
        }

        return sb.toString();
    }

    String generateOperatingRoomsBulk() {
        return generateBulk(operatingRoomList);
    }

    String generateOperatingRoomsAllowedProceduresBulk() {
        StringBuilder sb = new StringBuilder();

        for(OperatingRoom room: operatingRoomList) {
            for(ProcedureType type: room.allowedProcedures) {
                sb.append(room.id).append('|').append(type.id).append("\r\n");
            }
        }

        return sb.toString();
    }

    String generateProcedureTypesBulk() {
        return generateBulk(ProcedureType.procedureTypeList);
    }

    String generateProceduresBulk() {
        return generateBulk(procedureList);
    }

    private String generateBulk(List<? extends BulkInsertable> list) {
        StringBuilder sb = new StringBuilder();

        for(BulkInsertable p: list) {
            sb.append(p.toBulk()).append("\r\n");
        }

        return sb.toString();
    }

    private OperatingRoom findRoomForProcedure(ProcedureType procedureType) {
        return operatingRoomList.stream().filter(r -> r.allowedProcedures.contains(procedureType)).findFirst().orElse(null);
    }


}
