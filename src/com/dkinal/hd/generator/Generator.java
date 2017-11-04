package com.dkinal.hd.generator;

import java.util.LinkedList;
import java.util.List;

public class Generator {

    static List<Patient> patientList = new LinkedList<>();
    static List<Doctor> doctorList = new LinkedList<>();
    static List<OperatingRoom> operatingRoomList = new LinkedList<>();

    private int patients;
    private int doctors;

    Generator(int patients, int doctors) {
        this.patients = patients;
        this.doctors = doctors;
    }

    void generatePatients() {

    }
}
