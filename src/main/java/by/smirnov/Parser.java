package by.smirnov;

import by.smirnov.exception.IncorrectFileException;
import by.smirnov.exception.TimestampFollowingException;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private final String LINE_PATTERN = "(([01][0-9])|(2[0-3])):([0-5][0-9]) (([01][0-9])|(2[0-3])):([0-5][0-9])";

    private List<Timestamp> employeeTimestamp = new ArrayList<>();


    public List<Timestamp> getEmployeeTimestamp() {
        return employeeTimestamp;
    }

    public void parseFile(String filePath) throws Exception {
        Scanner scanner = new Scanner(new FileReader(filePath));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] time = null;

            if (isCorrectTimestamps(line)) {
                time = line.split(" ");
            } else {
                throw new IncorrectFileException("Exception: Inncorrect time format. Please input file name with correct time data ('hh:mm hh:mm').");
            }

            LocalTime startWork = LocalTime.parse(time[0]);
            LocalTime endWork = LocalTime.parse(time[1]);

            if (isStartWorkBeforeEndWork(startWork, endWork)) {
                Employee employee = new Employee();

                Timestamp start = new Timestamp(startWork, employee);
                Timestamp end = new Timestamp(endWork.plusNanos(1), employee);

                employeeTimestamp.add(start);
                employeeTimestamp.add(end);
            } else {
                throw new TimestampFollowingException("Exception: End of work time is before start of work time.");
            }
        }
    }

    private boolean isCorrectTimestamps(String timestampLine) {
        return timestampLine.matches(LINE_PATTERN);
    }

    private boolean isStartWorkBeforeEndWork(LocalTime start, LocalTime end) {
        return start.isBefore(end);
    }
}
