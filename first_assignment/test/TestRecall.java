import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestRecall {

    @org.junit.jupiter.api.Test
    void positiveFill(TestReporter r) {
        RecallI rc = new Recall();
        int[] arr;
        arr = new int[10];
        rc.fillArray(arr, 3);
        for(int i=0; i<10; i++) {
            assertEquals(arr[i], i*3);
        }

        arr = new int[128];
        rc.fillArray(arr, 10);
        for(int i=0; i<128; i++) {
            assertEquals(arr[i], i*10);
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void negativeFill(TestReporter r) {
        RecallI rc = new Recall();
        int[] arr;
        arr = new int[10];
        rc.fillArray(arr, -3);
        for(int i=0; i<10; i++) {
            assertEquals(Math.abs(-27 + i*3), arr[i]);
        }

        arr = new int[11];
        rc.fillArray(arr, -2);
        for(int i=0; i<10; i++) {
            assertEquals(Math.abs(-20 + i*2), arr[i]);
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void zeroFill(TestReporter r) {
        RecallI rc = new Recall();
        int[] arr = new int[5];
        rc.fillArray(arr, 0);
        for(int i=0; i<5; i++) {
            assertEquals(0, arr[i]);
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void findLineNull(TestReporter r) {
        RecallI rc = new Recall();
        try {
                rc.findLineWith("kitten", true);
                fail("Filename should be unset! Exception should have been thrown");
        } catch (UnsetFilenameException e) {
                // passed
        } catch (IOException e) {
                fail("Wrong exception thrown");
        }
        rc.setFilename("README.txt");
        rc.setFilename(null);
        try {
            rc.findLineWith("kitten", true);
            // passed
        } catch (UnsetFilenameException e) {
            fail("Filename should not have been changed on last call");
        } catch (IOException e) {
            fail("Wrong exception thrown");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void findLineBadFileName(TestReporter r) {
        RecallI rc = new Recall();
        rc.setFilename("RecallStuff.java");
        try {
            rc.findLineWith("kitten", true);
            fail("File doesn't exist. IOException should have been thrown");
        } catch (UnsetFilenameException e) {
            fail("Wrong exception thrown");
        } catch (IOException e) {
            // passed
        }
        rc.setFilename(null);
        try {
            rc.findLineWith("kitten", true);
            fail("File doesn't exist. IOException should have been thrown");
        } catch (UnsetFilenameException e) {
            fail("Wrong exception thrown");
        } catch (IOException e) {
            // passed
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void findLineGood(TestReporter r) {
        RecallI rc = new Recall();
        rc.setFilename("src/RecallI.java");
        try {
            String txt;
            txt = rc.findLineWith("filename", true);
            assertEquals("    /** Sets the filename to use for later file operations", txt);
            txt = rc.findLineWith("filename", false);
            assertEquals("    <p>For non-null Strings, this will update the filename to use when ", txt);
            txt = rc.findLineWith("filename", true);
            assertEquals("    /** Sets the filename to use for later file operations", txt);
            rc.setFilename("src/RunSample1.java");
            txt = rc.findLineWith("filling", false);
            assertEquals("        // Try filling an array from 0 to 27 counting by 3", txt);
            txt = rc.findLineWith("filling", false);
            assertNull(txt);
        } catch (UnsetFilenameException e) {
            fail("File was set");
        } catch (IOException e) {
            fail("File should not cause IOException");
        }
        rc.setFilename(null);
        try {
            rc.findLineWith("kitten", true);
            //passed
        } catch (UnsetFilenameException e) {
            fail("Setting the filename should have had no effect");
        } catch (IOException e) {
            fail("Wrong exception thrown");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void template(TestReporter r) {
        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
}

