package by.smirnov;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = null;
            if (args[0].isEmpty()) {
                throw new IOException("File name is not point.");
            }
            path = args[0];
            int maxEmployees = getMaxEmployeesAtOffice(path);
            System.out.println(maxEmployees);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public static int getMaxEmployeesAtOffice(String filepath) throws Exception {
        Parser parser = new Parser();
        parser.parseFile(filepath);

        List<Timestamp> timestamps = parser.getEmployeeTimestamp();
        Collections.sort(timestamps, Comparator.comparing((t) -> t.getTime()));

        Office office = new Office();
        office.emulateWorkDayAndGetMaxEmployees(timestamps);

        return office.getMaxEmployees();
    }
}
