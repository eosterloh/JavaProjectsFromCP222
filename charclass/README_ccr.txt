Documentation regarding the .ccr file format

.ccr files are used to describe what the contents of Character Class Registries should be. This allows the specific character classes supported by a program to be defined and changed without requiring the program source code to be modified.

The file format is line based; each line contains one registry entry. Lines that are blank or start with '#' are ignored. All other lines contain two or more items separated by whitespaces. The first item on the line is the character class name; character class names can be any sequence of characters without whitespace. The rest of the items on the line are characters to be included in the character class. If the item starts and ends with ':', the item is itself a character class that was defined earlier in the .ccr; all characters from that class should be included in the class being defined. In all other cases, the characters in the item included in the class being defined.

A specific character class name should only be defined once within a .ccr file; a file that
contains two definitions for a character class name is invalid.

Since the file format is line based, there is no way to include whitespace or non-printing characters in a .ccr file. Before parsing a .ccr file, the following named character classes are expected to be defined before processing the file:
:space:   - contains a single space character ' '
:newline: - contains a single newline character '\n'
:tab:     - contains a single tab character '\t'
A .ccr valid file must not redefine these.

Sample .ccr files with comments can be found in the data directory.
