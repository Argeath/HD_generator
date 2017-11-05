package com.dkinal.hd.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ProcedureType implements BulkInsertable {
    int id;
    String name;

    ProcedureType(String name) {
        this.name = name;
        this.id = lastId++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcedureType that = (ProcedureType) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toBulk() {
        return Bulk.build(List.of(id+"", name));
    }

    static String[] procedureTypes = {
            "abdominoplastyka", "adenotomia", "adenomektomia", "adrenalektomia", "amputacja", "angioplastyka",
            "antromastoidektomia", "appendektomia", "artroskopia", "astragalektomia", "hemikorporektomia",
            "heminefrektomia", "hemisferektomia", "hepatektomia", "hipofizektomia", "histerektomia", "kalozotomia",
            "kolektomia", "kraniektomia", "kraniotomia", "laminektomia", "laryngektomia", "laryngofisura",
            "ligacja jajowodu", "litotrypsja", "lobektomia", "lobotomia", "mastektomia", "metastazektomia",
            "mukosektomia", "nacięcie krocza", "nefrektomia", "nefrotomia", "ooforektomia", "operacja Bassiniego",
            "operacja Billrotha", "operacja Brickera", "operacja Drapanasa", "operacja Girarda", "operacja Halstedta",
            "operacja Hartmanna", "operacja Jurasza", "operacja Lichtensteina", "operacja Milesa", "operacja Mogga",
            "operacja Nussa", "operacja Puestowa", "operacja Rutkowa", "operacja Rydygiera", "operacja Shouldice",
            "operacja Strassmana", "operacja Traverso-Longmire", "operacja Warrena", "operacja Whipple", "orbitotomia",
            "orchidektomia", "owarektomia", "owariektomia", "papilotomia", "paracenteza", "pankreatoduodenektomia",
            "penektomia", "perikardiektomia", "plastyka Heinekego-Mikulicza", "prostatektomia", "segmentektomia",
            "sfinkterotomia", "splenektomia", "waginektomia", "wagotomia", "wazektomia", "wulwektomia",
            "zabieg Bentalla", "zabieg Crilea", "zabieg Halsteda", "zabieg Pateya", "zabieg Urbana"
    };

    private static int lastId = 0;

    final static List<ProcedureType> procedureTypeList = new LinkedList<>();

    static {
        for(String type: procedureTypes) {
            procedureTypeList.add(new ProcedureType(type));
        }
    }

    static ProcedureType randomProcedureType() {
        return procedureTypeList.get(ThreadLocalRandom.current().nextInt(procedureTypeList.size()));
    }
}
