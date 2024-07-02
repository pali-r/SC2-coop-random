package com.application;

import javax.lang.model.type.ArrayType;
import java.util.AbstractList;
import java.util.ArrayList;

public class CallMeDatabase {
    ArrayList<String> commanderNames = new ArrayList<>();

    public CallMeDatabase() {
        addAllCommandersToTheList();
    }

    private void addAllCommandersToTheList() {
        commanderNames.addAll(addCommanderPrestiges("Raynor"));
        commanderNames.addAll(addCommanderPrestiges("Kerrigan"));
        commanderNames.addAll(addCommanderPrestiges("Artanis"));
    }

    private ArrayList<String> addCommanderPrestiges(String commanderName) {
        ArrayList<String> returningString = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            returningString.add(commanderName + " P" + i);
        }
        return returningString;
    }

    public ArrayList<String> getAllCommanders() {
        return commanderNames;
    }
}
