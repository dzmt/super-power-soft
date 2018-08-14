package by.smirnov;

import java.util.List;

public class Office {
    private int currentEmployees = 0;
    private int maxEmployees = 0;

    public int getMaxEmployees(){
        return maxEmployees;
    }

    private void emulateWorkDay(List<Timestamp> timestamps) {
        timestamps.forEach((timestamp -> {
            if (!timestamp.getEmployee().isAtWork()) {
                comeToOffice(timestamp);
            } else {
                leftOffice(timestamp);
            }
        }));
    }

    public int emulateWorkDayAndGetMaxEmployees(List<Timestamp> timestamps) {
        emulateWorkDay(timestamps);
        return getMaxEmployees();
    }

    private boolean compareCurrentWithMax() {
        return currentEmployees > maxEmployees;
    }

    private void changeMaxEmployees() {
        if (compareCurrentWithMax()) {
            maxEmployees = currentEmployees;
        }
    }

    private void comeToOffice (Timestamp timestamp) {
        currentEmployees++;
        timestamp.getEmployee().setAtWork(true);
        changeMaxEmployees();
    };

    private void leftOffice (Timestamp timestamp) {
        currentEmployees--;
        timestamp.getEmployee().setAtWork(false);
    }
}
