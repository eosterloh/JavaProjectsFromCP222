import fileaccess.DFAGen;
import fileaccess.InvalidStateMachineException;
import fileaccess.StateMachineGenI;
import org.junit.jupiter.api.TestReporter;
import statemachine.StateI;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionalTests {

    @org.junit.jupiter.api.Test
    void fileTest00(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample00.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Line is not blank, a state, a transition, or a comment" , e.getStateMachineMessage());
            assertEquals(5 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest01(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample01.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("State line missing the state label" , e.getStateMachineMessage());
            assertEquals(5 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest02(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample02.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Stata line contains too many fields" , e.getStateMachineMessage());
            assertEquals(5 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest03(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample03.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Unsupported attribute halt", e.getStateMachineMessage());
            assertEquals(3 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest04(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample04.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Duplicate start state declared", e.getStateMachineMessage());
            assertEquals(5 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest05(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample05.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("State with label cat already exists", e.getStateMachineMessage());
            assertEquals(5 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest06(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample06.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Too many fields for transition line", e.getStateMachineMessage());
            assertEquals(6 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest07(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample07.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Insufficient fields for transition line", e.getStateMachineMessage());
            assertEquals(9 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest08(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample08.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Transition symbol is not a single character", e.getStateMachineMessage());
            assertEquals(7 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest09(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample09.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Source (s7) for the transition is unknown", e.getStateMachineMessage());
            assertEquals(26 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest10(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample10.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Destination (s7) for the transition is unknown", e.getStateMachineMessage());
            assertEquals(26 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest11(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample11.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("Transition from s1 on . already exists", e.getStateMachineMessage());
            assertEquals(34 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void fileTest12(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename = "badSample12.dfa";

        try {
            StateI<Character> ss = smg.read(new File("data/"+filename));
            fail(filename+" contains at least one defect");
        } catch(InvalidStateMachineException e) {
            assertEquals("No start state found before EOF", e.getStateMachineMessage());
            assertEquals(7 , e.getStateMachineLinenum());
            assertEquals(filename, e.getStateMachineFile().getName());
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
}
