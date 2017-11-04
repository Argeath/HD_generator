package com.dkinal.hd.generator;

class OperatingRoom {
    int id; // numer sali
    String section; // oddzial

    static int lastId = -1;

    static OperatingRoom operatingRoom(String section) {
        OperatingRoom r = new OperatingRoom();
        r.id = ++lastId;
        r.section = section;

        return r;
    }
}
