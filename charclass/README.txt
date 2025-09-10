In this assignment, you'll be writing to code for abstracting the idea of a
character class. We'll use this in a later assignment for implementing a DFA
or NFA...which will be the basis of a tokenizer...which will be the basis of
a parser (if I can get the time to get the parser architecture described
suitably for an assignment).

The major operation for character classes are to match characters:
Given some character c, is that character in the class?

The character class you'll be implementing has some other features, like being
able to generate an inverse character class (a character class that matches
everything not in this character class) and compose with another character
class via union or intersection.

The implementation is built around 7-bit ASCII encoding. Depending on the JAVA
version you're running, it probably assumes UTF-16 or UTF-8... the details of
what that means is CP275 content, so we'll mostly skip it for this class. The
important detail is that characters are stored internally as integers and can
be used like integers. UTF-8 and UTF-16 integer values for 7-bit ASCII
characters are the same values. Valid 7-bit ASCII characters are in the range
0 to 127, inclusive.

In addition to the character class itself, you'll be implementing two
additional classes to help in working with character classes. The
CharClassLookup will provide functionality to name character classes and look
them up later. The CCFile will provide functionality to read a text file that
describes several named character classes into a CharClassLookup.

Your implementations must implement the corrasponding interface. The CCFile and
CharClassLookup implementation must have a constructor that taken no parameters,
The CharClass constructor is not specified by this assignment, CharClass
instances will be tested via your CharClassLookup implementation.


Files:
README.txt                - this readme file
README_ccr.txt            - a text file discribing the ccr file format
src/CCFileI.java          - interface for CCFile
src/CharClassI.java       - interface for CharClass
src/CharClassLookupI.java - interface for CharClassLookup
src/Main.java             - a program that exercises only some of the methods
src/InvalidLineException.java - exception for bad .ccr lines
src/NotASCIIException.java    - exception to signal a bad character
test/TestCharClass.java   - JUnit tests for this assignment
data/sample1.ccr          - a sample data file for CCFile to read
data/sample2.ccr          - a sample data file for CCFile to read
data/sample3.ccr          - a sample data file for CCFile to read
data/bad1.ccr             - a sample invalid data file for CCFile to read
data/bad2.ccr             - a sample invalid data file for CCFile to read
data/bad3.ccr             - a sample invalid data file for CCFile to read
