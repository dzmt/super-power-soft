package by.smirnov;

import by.smirnov.exception.IncorrectFileException;
import by.smirnov.exception.TimestampFollowingException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getMaxEmployeesAtOffice() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File test4 = new File(classLoader.getResource("test4.txt").getFile());
        File test23 = new File(classLoader.getResource("test23.txt").getFile());
        File test1 = new File(classLoader.getResource("test1.txt").getFile());
        File testAll = new File(classLoader.getResource("test-all.txt").getFile());
        File testIncorrectFile = new File(classLoader.getResource("test-incorrect-file.txt").getFile());
        File testFollowingException = new File(classLoader.getResource("test-following-exception.txt").getFile());


        assertEquals(4, Main.getMaxEmployeesAtOffice(test4.getAbsolutePath()));
        assertEquals(23, Main.getMaxEmployeesAtOffice(test23.getAbsolutePath()));
        assertEquals(1, Main.getMaxEmployeesAtOffice(test1.getAbsolutePath()));
        assertEquals(6, Main.getMaxEmployeesAtOffice(testAll.getAbsolutePath()));

        Throwable timestampFollowingException = assertThrows(TimestampFollowingException.class, () -> {
            Main.getMaxEmployeesAtOffice(testFollowingException.getAbsolutePath());
        });

        assertEquals("Exception: End of work time is before start of work time.", timestampFollowingException.getMessage());

        Throwable incorrectFileException = assertThrows(IncorrectFileException.class, () -> {
            Main.getMaxEmployeesAtOffice(testIncorrectFile.getAbsolutePath());
        });

        assertEquals("Exception: Inncorrect time format. Please input file name with correct time data ('hh:mm hh:mm').", incorrectFileException.getMessage());
    }

}