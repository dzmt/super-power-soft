package by.smirnov;

import java.time.LocalTime;

public class Timestamp {
    private LocalTime time;
    private Employee employee;

    public Timestamp(LocalTime time, Employee employee) {
        this.time = time;
        this.employee = employee;
    }

    public LocalTime getTime() {
        return time;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setWorker(Employee worker) {
        this.employee = worker;
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "time=" + time +
                '}';
    }
}
