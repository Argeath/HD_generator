package com.dkinal.hd.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class OperatingRoom implements BulkInsertable {
    int id; // numer sali
    String section; // oddzial

    List<ProcedureType> allowedProcedures = new LinkedList<>();

    private static int lastId = 0; // skip 0

    OperatingRoom(String section) {
        lastId += ThreadLocalRandom.current().nextInt(15) + 1;
        this.id = lastId;
        this.section = section;

        this.allowedProcedures = RandomDataGenerator.randomProcedureTypes(3, 15);
    }

    @Override
    public String toBulk() {
        return Bulk.build(List.of(id+"", section));
    }
}
