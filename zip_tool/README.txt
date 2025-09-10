This is a tool to assist in zipping and submitting CP222 programming

To use the tool, open the project in your IDE like you do for starter code. Then run
the main in ZipTool.

A file selection dialog will open, select the project directory you would like to
zip files from. Then enter the name the project directory should have for the grading
script. Verify that the file listing is what you expect, if not you can remove the
extraneous files using the interface provided. Finally, another file selection dialog
will open for selecting the name and location for the zip file.

** Change Log **
9/26 21:00 - Initial version
9/27 14:00 - Fixed MacOS .DS_STORE being included and Windows path separators
9/27 16:00 - Fixed non-hidden '.' files on Windows being included
10/2 18:15 - Made code JAVA 8 compatible to address older JDKs on some laptops