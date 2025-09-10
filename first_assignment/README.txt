This directory contains the Recall JAVA assignment for CP222.

RunSample1 and RunSample2 are two different JAVA programs that utilize the Recall class you are expected to write. You should be able to figure out how to run these and what their expected output should be by looking at the code and stepping through the execution.

When submitted for grading, you will need to submit a zip file with the same structure and first_assignment.zip. Your zip file must include all of the files in first_assignment.zip and the Recall.java file you have written that implements the RecallI interface. Your zip file must not include any IDE or OS metadata files. ...we'll talk more about the submission zip file expectations in class/office hours...


-------------------------------
Implementation Notes
-------------------------------
You only have 1 file to write, Recall.java. The Recall class must implement the RecallI interface. The JavaDoc comments in RecallI.java should also be present in your Recall.java file. 

The JavaDoc comments specify what the methods are expected to do without specifying the details of how the methods do this. You need to use your programming skills and experience to reason through the detailed steps and write the code.

The Recall class should have an empty constructor. See RunSample1.java and RunSample2.java to see how the Recall constructor is called.

While RunSample1 and RunSample2 must work with your implementation, they do not test very many cases. You may want to think carefully about the execution paths through your program and generate your own test code to verify those paths are working correctly based on the documentation in the JavaDoc comments.


Files:
README.txt       - File talking about the directory cotents
src/RunSample1.java  - Some demo code using a Sample instance 
src/RunSample2.java  - Some demo code using a Sample instance
src/RecallI.java     - An interface to practice reading/using interfaces
src/UnsetFilenameException.java - Exception to indicate the file is unset
