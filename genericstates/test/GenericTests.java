import fileaccess.DFAGen;
import fileaccess.InvalidStateMachineException;
import fileaccess.StateMachineGenI;
import org.junit.jupiter.api.TestReporter;
import programs.DFAMatcher;
import statemachine.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GenericTests {

    @org.junit.jupiter.api.Test
    void basicState(TestReporter r) {
        StateI<Character> s0 = new State<>("bob");
        StateI<String> s1 = new State<String>("marci");
        s1.setAccept(true);
        StateI<InvalidStateMachineException> s2 = new State<InvalidStateMachineException>(null);
        s2.setAccept(true);
        StateI<List<Set<String>>> s3 = new State<List<Set<String>>>(null);
        s3.setAccept(false);

        assertFalse(s0.isAccept());
        assertTrue(s1.isAccept());
        assertTrue(s2.isAccept());
        assertFalse(s3.isAccept());

        assertNull(s2.getLabel());
        assertNull(s3.getLabel());
        assertEquals("bob", s0.getLabel());
        assertEquals("marci", s1.getLabel());

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void basicTransition(TestReporter r) {
        StateI<Character> s0 = new State<Character>("bob");
        StateI<Character> s1 = new State<Character>("marci");
        s1.setAccept(true);

        assertFalse(s0.isAccept());
        assertTrue(s1.isAccept());
        assertEquals("bob", s0.getLabel());
        assertEquals("marci", s1.getLabel());

        TransitionI<Character> t = new Transition<>('.', s1);
        assertSame(s1, t.nextState());
        assertEquals('.', t.getSymbol());

        StateI<String> str0 = new State<>("bob");
        StateI<String> str1 = new State<>("marci");
        TransitionI<String> t2 = new Transition<>("yay", str1);
        assertSame(str1, t2.nextState());
        assertEquals("yay", t2.getSymbol());

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testAddTransition(TestReporter r) {
        StateI<Character> s0 = new State<Character>("bob");
        StateI<Character> s1 = new State<Character>("marci");
        s1.setAccept(true);

        assertFalse(s0.isAccept());
        assertTrue(s1.isAccept());
        assertEquals("bob", s0.getLabel());
        assertEquals("marci", s1.getLabel());

        s0.addTransition('.', s1);
        s0.addTransition('-', s0);

        Set<TransitionI<Character>> txs = s0.getTransitions();
        assertEquals(2, txs.size());
        for(TransitionI<Character> t:txs) {
            if(t.getSymbol().equals('.')) {
                assertSame(s1, t.nextState());
            } else if(t.getSymbol().equals('-')) {
                assertSame(s0, t.nextState());
            } else {
                fail("A transition on "+t.getSymbol()+" should not be present in this test");
            }
        }

        StateI<String> str0 = new State<>("bob");
        StateI<String> str1 = new State<>("marci");
        str1.setAccept(true);

        assertFalse(str0.isAccept());
        assertTrue(str1.isAccept());
        assertEquals("bob", str0.getLabel());
        assertEquals("marci", str1.getLabel());

        str0.addTransition("yay", str1);
        str0.addTransition("nay", str0);

        Set<TransitionI<String>> txstr = str0.getTransitions();
        assertEquals(2, txstr.size());
        for(TransitionI<String> t:txstr) {
            if(t.getSymbol().equals("yay")) {
                assertSame(str1, t.nextState());
            } else if(t.getSymbol().equals("nay")) {
                assertSame(str0, t.nextState());
            } else {
                fail("A transition on "+t.getSymbol()+" should not be present in this test");
            }
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void DFAGenGoodFileReads(TestReporter r) {
        StateMachineGenI<Character> smg = new DFAGen();
        String filename;

        filename = "data/goodSample00.dfa";
        try {
            StateI<Character> ss = smg.read(new File(filename));
            assertEquals("s0", ss.getLabel());
            assertFalse(ss.isAccept());
            DFATraverser<Character> m = new DFATraverser<>();
            assertTrue(m.isAccepted(ss, new IterableString("cat")));
            assertEquals("s3", m.getFinalState().getLabel());
            assertTrue(m.isAccepted(ss, new IterableString("dog")));
            assertEquals("s3", m.getFinalState().getLabel());
            assertFalse(m.isAccepted(ss, new IterableString( "catdog")));
            assertFalse(m.isAccepted(ss, new IterableString("dragon")));
        } catch(InvalidStateMachineException e) {
            fail(filename+" should be a valid .dfa file");
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        filename = "data/goodSample01.dfa";
        try {
            StateI<Character> ss = smg.read(new File(filename));
            assertEquals("s0", ss.getLabel());
            assertFalse(ss.isAccept());
            DFATraverser<Character> m = new DFATraverser<>();
            assertTrue(m.isAccepted(ss, new IterableString("cat")));
            assertEquals("s3", m.getFinalState().getLabel());
            assertTrue(m.isAccepted(ss, new IterableString("dog")));
            assertEquals("s6", m.getFinalState().getLabel());
            assertFalse(m.isAccepted(ss, new IterableString( "catdog")));
            assertFalse(m.isAccepted(ss, new IterableString("dragon")));
        } catch(InvalidStateMachineException e) {
            fail(filename+" should be a valid .dfa file");
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        filename = "data/goodSample02.dfa";
        try {
            StateI<Character> ss = smg.read(new File(filename));
            assertEquals("reject", ss.getLabel());
            assertTrue(ss.isAccept());
            DFATraverser<Character> m = new DFATraverser<>();

            assertTrue(m.isAccepted(ss, new IterableString("")));
            assertTrue(m.isAccepted(ss,new IterableString("010%5")));
            assertTrue(m.isAccepted(ss, new IterableString("1001%5%010%5")));
            assertTrue(m.isAccepted(ss, new IterableString("1001%5%")));
            assertFalse(m.isAccepted(ss, new IterableString("1")));
            assertTrue(m.isAccepted(ss, new IterableString("010%5")));
            assertFalse(m.isAccepted(ss, new IterableString("1001%5%%010%5%")));
            assertFalse(m.isAccepted(ss, new IterableString("1001%5%5")));
            m.traverse(ss, new IterableString(""));
            assertEquals("reject", m.getFinalState().getLabel());
            m.traverse(ss, new IterableString("1001%5%010%5"));
            assertEquals("boo", m.getFinalState().getLabel());
        } catch(InvalidStateMachineException e) {
            fail(filename+" should be a valid .dfa file");
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        filename = "data/goodSample03.dfa";
        try {
            StateI<Character> ss = smg.read(new File(filename));
            assertEquals("s0", ss.getLabel());
            assertFalse(ss.isAccept());
            DFATraverser<Character> m = new DFATraverser<>();
            assertTrue(m.isAccepted(ss, new IterableString("001")));
            assertEquals("s3", m.getFinalState().getLabel());
            assertFalse(m.isAccepted(ss, new IterableString("110")));
        } catch(InvalidStateMachineException e) {
            fail(filename+" should be a valid .dfa file");
        } catch (FileNotFoundException e) {
            fail(filename+" should exist and be readable");
        } catch (IOException e) {
            fail("IOExceptions should not happen during testing");
        }

        filename = "data/goodSample04.dfa";
        try {
            StateI<Character> ss = smg.read(new File(filename));
            assertEquals("@!#", ss.getLabel());
            assertFalse(ss.isAccept());
            DFATraverser<Character> m = new DFATraverser<>();
            assertTrue(m.isAccepted(ss, new IterableString("Y")));
            assertEquals("bob()", m.getFinalState().getLabel());
            assertTrue(m.isAccepted(ss, new IterableString("NN")));
            assertTrue(m.isAccepted(ss, new IterableString("NYY")));
            assertTrue(m.isAccepted(ss, new IterableString("NYNYY")));
            assertFalse(m.isAccepted(ss, new IterableString("YY")));
            assertFalse(m.isAccepted(ss, new IterableString("NYNYN")));
            m.traverse(ss, new IterableString("NYNN"));
            assertEquals("bob()", m.getFinalState().getLabel());
        } catch(InvalidStateMachineException e) {
            fail(filename+" should be a valid .dfa file");
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
