CP275 will talk about assembly language in some detail... For this assignment, we'll gloss over all the details and try to build a tool to help with HMMM assembly programming.

HMMM is a simple assembly language from the CSforAll circulum and is used in my CP115 classes. HMMM assembly is annoying to write, in large part, because the line numbering is such a pain to keep updated when changing the code. The main feature of our tool/compiler will be to take unnumbered HMMM and create HMMM with the correct numbers.

Lines of code in HMMM must start with an integer. Lines containing assembly instructions must be numbered sequentially starting with instruction 0. Following the instruction number is the operation and after the operation are the arguments to the operation. For some operations, one of the arguments will be an instruction number. When modifications to a hmmm program are made, the instructions must be renumbered and the instruction where jump instructions link to may need to be changed.

Example HMMM program:
0 setn r1 10
# :target:
1 write r1
2 jumpn 1 # :target:

Corresponding lbl program:
setn r1 10
:target:
write r1
jumpn :target:

The compiler we write for this assignment should take the .lbl program and produce the HMMM program shown.

Due to the possibility that labels will be used as arguments before they are encountered as lines, the compiler will need to operate in two passes. The first pass will read the contents of the file into memory, create a LineIn instance for each line, and prepare a lookup table for labels. The second pass will write out the final output file.

The main class for the compiler will be named Compiler. The compiler will take in the name of the input file as a command line argument and will produce an output file with the suffix hmmm.  If no file name is provided as a command line argument, the file name does not end in .lbl or the file can't be opened, the compiler will produce a usage message and exit with return code of 1. If the output filename does not end in .hmmm or the file can't be written, the compiler will produce a usage message and exit with return code of 1.

The LblReader should use a loop to read each line from the input file and create a list of Line instances using the provided LineFactory implementation. If the createLine method of LineFactory is called for each line from the source file, in order, LineFactory createLine() will pass the line location it is given to the constructor for the Line instance and maintain a HashMap, that maps label strings to their corrasponding LineI instance. The LineFactory does not connect lines via the setNextLine method, this will need to be done by the LblReader.

There should be a class named Line that is a parent class for CommentLine, LabelLine, and InstrLine.
- The Line class should be abstract, must implement the LineI interface, and must provide the implementation of the lineLocation, sourceText, setNextLine, and getNextLine declared in LineI.
- The output of a CommentLine will be identical to the input line.
- The output of a LabelLine will be '# ' followed by the input line.
- The output of an InstrLine will have the instruction address followed by the instruction text. For InstrLines that don't contain a label, the instruction text is identical to the input line. For InstrLines that do contain a label, the label in the input line must be replaced with the address of the corrasponding LabelLine and the label should appear as a comment (there are examples in the data directory). An InstrLine will contain at most 1 target label.

The setNextLine and getNextLine methods exist to support having the lines accessed as a linked list. Instructions that use a label as a jump target are jumping to the first instruction following the label line (may be several lines later due to comments).

###################
# Files:
###################
src/programs/Compiler.java               - program to process lbl files into hmmm
src/lines/LineI.java                     - Interface hmmm lines
src/lines/LineFactory.java               - Factory class to generate LineI instances
src/lines/LineLocation.java              - Where a line came from
src/lines/DuplicateLabelException.java   - Exception thrown on duplicate labels
src/lines/NoSuchLabelException.java      - Exception when a label in undefined
src/lines/NoNextInstructionException.java - Exception when no target address
src/fileaccess/LblReaderI.java           - Interface for .lbl readers
src/fileaccess/HmmmWriterI.java          - Interface for .hmmmm writers
src/fileaccess/BadFileTypeException.java - Exception if the suffix doesn't work
src/fileaccess/NoLinesException.java     - Exception if no lines have been read
test/TestLines.java                      - JUnits for this project
data/rec_fact.lbl     - A fairly complex .lbl file
data/test1.lbl        - A fairly simple .lbl file
data/test2.lbl        - A fairly simple .lbl file
data/rec_fact_ref.hmmm - Output for rec_fact.lbl using the reference implementation
data/test1_ref.hmmm    - Output for test1.lbl using the refernece implementation
data/test2_ref.hmmm    - Output for test2.lbl using the refernece implementation

