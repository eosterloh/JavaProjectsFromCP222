import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharClass {
    @org.junit.jupiter.api.Test
    void testLookup(TestReporter r) {
        // Make a lookup and check that it has no char classes
        CharClassLookupI lookup = new CharClassLookup();
        Set<String> keys = lookup.getNames();
        assertEquals(0, keys.size());

        // Add a character set
        try {
            lookup.addCC("digits", "0123456789");
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }
        keys = lookup.getNames();
        assertEquals(1, keys.size());
        CharClassI oc = lookup.getCC("digits");
        assertNotNull(oc);

        // Add a character set
        lookup.addCC("dupdigits", oc);
        keys = lookup.getNames();
        assertEquals(2, keys.size());
        CharClassI cc = lookup.getCC("digits");
        assertNotNull(cc);
        assertSame(oc, cc);
        cc = lookup.getCC("dupdigits");
        assertSame(oc, cc);
        assertNotNull(cc);

        // Add a character set
        try {
            lookup.addCC("alpha","abc");
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }
        keys = lookup.getNames();
        assertEquals(3, keys.size());
        cc = lookup.getCC("digits");
        assertNotNull(cc);
        assertSame(oc, cc);
        cc = lookup.getCC("dupdigits");
        assertSame(oc, cc);
        assertNotNull(cc);
        cc = lookup.getCC("alpha");
        assertNotNull(cc);
        assertNotSame(oc, cc);

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testBaseCharClass(TestReporter r) {
        // Make a lookup and check that it has no char classes
        CharClassLookupI lookup = new CharClassLookup();
        Set<String> keys = lookup.getNames();
        assertEquals(0, keys.size());

        String digits = "0123456789";
        String letter = "abcdef";

        // Add a character set
        try {
            lookup.addCC("digits", digits);
            lookup.addCC("letter", letter);
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }
        keys = lookup.getNames();
        assertEquals(2, keys.size());
        CharClassI dig = lookup.getCC("digits");
        CharClassI let = lookup.getCC("letter");

        try {
            for (int i = 0; i < letter.length(); i++) {
                assertFalse(dig.match(letter.charAt(i)));
                assertTrue(let.match(letter.charAt(i)));
            }
            for (int i = 0; i < digits.length(); i++) {
                assertTrue(dig.match(digits.charAt(i)));
                assertFalse(let.match(digits.charAt(i)));
            }
        } catch (NotASCIIException e) {
            fail("All character should be valid");
        }

        for(char c:dig.getCharacters()) {
            assertTrue(digits.contains(c+""));
        }
        for(char c:let.getCharacters()) {
            assertTrue(letter.contains(c+""));
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testSizeCharClass(TestReporter r) {
        // Make a lookup and check that it has no char classes
        CharClassLookupI lookup = new CharClassLookup();
        Set<String> keys = lookup.getNames();
        assertEquals(0, keys.size());

        String d1 = "0123456789";
        String d2 = "0123045";

        // Add a character set
        try {
            lookup.addCC("d1", d1);
            lookup.addCC("d2", d2);
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }
        keys = lookup.getNames();
        assertEquals(2, keys.size());
        CharClassI d1cc = lookup.getCC("d1");
        CharClassI d2cc = lookup.getCC("d2");

        // Check the sizes to verify uniqueness
        assertEquals(10, d1cc.getSize());
        System.out.println(Arrays.toString(d2cc.getCharacters()));
        assertEquals(6, d2cc.getSize());

        // Check the characters are matched
        try {
            for (int i = 0; i < d1.length(); i++) {
                assertTrue(d1cc.match(d1.charAt(i)));
            }
            for (int i = 0; i < d2.length(); i++) {
                assertTrue(d2cc.match(d2.charAt(i)));
            }
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testOpsCharClass(TestReporter r) {
        // Make a lookup and check that it has no char classes
        CharClassLookupI lookup = new CharClassLookup();
        Set<String> keys = lookup.getNames();
        assertEquals(0, keys.size());

        String digits = "0123456789";
        String letter = "abcdef";

        // Add a character set
        try {
            lookup.addCC("digits", digits);
            lookup.addCC("letter", letter);
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }
        keys = lookup.getNames();
        assertEquals(2, keys.size());
        CharClassI dig = lookup.getCC("digits");
        CharClassI let = lookup.getCC("letter");

        try {
            for (int i = 0; i < letter.length(); i++) {
                assertFalse(dig.match(letter.charAt(i)));
                assertTrue(let.match(letter.charAt(i)));
            }
            for (int i = 0; i < digits.length(); i++) {
                assertTrue(dig.match(digits.charAt(i)));
                assertFalse(let.match(digits.charAt(i)));
            }
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }

        // Test Union
        CharClassI dl = dig.union(let);
        try {
            String postest = digits + letter;
            String negtest = "xyz$%";
            for(int i=0; i<postest.length(); i++) {
                assertTrue(dl.match(postest.charAt(i)));
            }
            for(int i=0; i<negtest.length(); i++) {
                assertFalse(dl.match(negtest.charAt(i)));
            }
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        }

        // Test Intersection
        try {
            CharClassI ci = dl.intersect(dig);
            for(int i=0; i<digits.length(); i++) {
                assertTrue(ci.match(digits.charAt(i)));
            }
            for(int i=0; i<letter.length(); i++) {
                assertFalse(ci.match(letter.charAt(i)));
            }
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        }

        // Test Inversion
        try {
            CharClassI ci = dig.invert();
            for(int i=0; i<'0'; i++) {
                assertTrue(ci.match((char)i));
            }
            for(int i='0'; i<='9'; i++) {
                assertFalse(ci.match((char)i));
            }
            for(int i='9'+1; i<128; i++) {
                assertTrue(ci.match((char)i));
            }
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testBasicFile(TestReporter r) {
        // Some strings
        String digit    = "0123456789";
        String ucletter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lcletter = "abcdefghijklmnopqrstuvwxyz";
        String letter   = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Read sample1.ccr
        CCFileI fr = new CCFile();
        try {
            CharClassLookupI s2 = fr.readCCFile(new File("data/sample2.ccr"));
            Set<String> keys = s2.getNames();
            // class names registered
            assertTrue(keys.contains("digit"));
            assertTrue(keys.contains("ucletter"));
            assertTrue(keys.contains("lcletter"));
            assertTrue(keys.contains("letter"));
            // get the classes and test each
            CharClassI cc = s2.getCC("digit");
            for(int i=0; i<digit.length(); i++) {
                System.out.println(digit.charAt(i) + " " + Arrays.toString(cc.getCharacters()));
                assertTrue(cc.match(digit.charAt(i)));
            }
            assertFalse(cc.match('a'));
            cc = s2.getCC("ucletter");
            for(int i=0; i<ucletter.length(); i++) {
                assertTrue(cc.match(ucletter.charAt(i)));
            }
            assertFalse(cc.match('a'));
            cc = s2.getCC("lcletter");
            for(int i=0; i<lcletter.length(); i++) {
                assertTrue(cc.match(lcletter.charAt(i)));
            }
            assertFalse(cc.match('A'));
            cc = s2.getCC("letter");
            for(int i=0; i<letter.length(); i++) {
                assertTrue(cc.match(letter.charAt(i)));
            }
            assertFalse(cc.match('5'));
        } catch (IOException e) {
            fail("Should not have IO issues");
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            fail("File should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testSubFile(TestReporter r) {
        // Some strings
        String digit    = "0123456789";
        String ucletter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lcletter = "abcdefghijklmnopqrstuvwxyz";
        String letter   = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Read sample1.ccr
        CCFileI fr = new CCFile();
        try {
            CharClassLookupI s1 = fr.readCCFile(new File("data/sample1.ccr"));
            Set<String> keys = s1.getNames();
            // class names registered
            assertTrue(keys.contains("digit"));
            assertTrue(keys.contains("ucletter"));
            assertTrue(keys.contains("lcletter"));
            assertTrue(keys.contains("letter"));
            // get the classes and test each
            CharClassI cc = s1.getCC("digit");
            for(int i=0; i<digit.length(); i++) {
                assertTrue(cc.match(digit.charAt(i)));
            }
            assertFalse(cc.match('a'));
            cc = s1.getCC("ucletter");
            for(int i=0; i<ucletter.length(); i++) {
                assertTrue(cc.match(ucletter.charAt(i)));
            }
            assertFalse(cc.match('a'));
            cc = s1.getCC("lcletter");
            for(int i=0; i<lcletter.length(); i++) {
                assertTrue(cc.match(lcletter.charAt(i)));
            }
            assertFalse(cc.match('A'));
            cc = s1.getCC("letter");
            for(int i=0; i<letter.length(); i++) {
                assertTrue(cc.match(letter.charAt(i)));
            }
            assertFalse(cc.match('5'));
        } catch (IOException e) {
            fail("Should not have IO issues");
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            fail("File should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testHardFile(TestReporter r) {
        // Read sample1.ccr
        CCFileI fr = new CCFile();
        try {
            CharClassLookupI s3 = fr.readCCFile(new File("data/sample3.ccr"));
            Set<String> keys = s3.getNames();
            // class names registered
            assertTrue(keys.contains("ws"));
            assertTrue(keys.contains("colon"));
            assertTrue(keys.contains("symbol"));
            assertTrue(keys.contains("wchr"));
            assertTrue(keys.contains("sigils"));
            // get the classes and test each
            CharClassI cc = s3.getCC("ws");
            assertTrue(cc.match(' '));
            assertTrue(cc.match('\n'));
            assertTrue(cc.match('\t'));
            assertFalse(cc.match('\r'));

            String sig = ":$@#&_abc";
            cc = s3.getCC("sigils");
            for(int i=0; i<sig.length(); i++) {
                assertTrue(cc.match(sig.charAt(i)));
            }
            assertFalse(cc.match('^'));
        } catch (IOException e) {
            fail("Should not have IO issues");
        } catch (NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            fail("File should be valid");
        }

        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }

    @org.junit.jupiter.api.Test
    void testBadFiles(TestReporter r) {
        CCFileI fr = new CCFile();
        try {
            fr.readCCFile(new File("data/bad1.ccr"));
            fail("The file contains a class without characters");
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            // pass
        } catch (IOException e) {
            fail("File should be valid");
        }
        try {
            fr.readCCFile(new File("data/bad2.ccr"));
            fail("The file redefines a reserved character class name");
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            // pass
        } catch (IOException e) {
            fail("File should be valid");
        }
        try {
            fr.readCCFile(new File("data/bad3.ccr"));
            fail("The file redefines a character class");
        } catch(NotASCIIException e) {
            fail("All characters should be valid");
        } catch (InvalidLineException e) {
            // pass
        } catch (IOException e) {
            fail("File should be valid");
        }
    }
    /*
    @org.junit.jupiter.api.Test
    void template(TestReporter r) {
        // Generate a report line
        String TEST = Thread.currentThread().getStackTrace()[1].getMethodName();
        r.publishEntry(TEST + " -> passed");
    }
     */
}
