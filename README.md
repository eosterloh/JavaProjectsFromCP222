# CP222 Programming Projects

This repository contains various programming assignments and projects for CP222. Each project focuses on different aspects of programming concepts and implementations.

## Project Structure

### Character Class Implementation (`/charclass`)
A project focused on implementing character class abstractions for use in DFA/NFA implementations. Features:
- Character class matching and operations
- Support for 7-bit ASCII encoding
- File-based character class registry (.ccr format)
- Inverse character class generation
- Class composition via union/intersection

### DFA GUI Implementation (`/dfa_gui`)
A GUI application for working with Deterministic Finite Automata (DFA). Features:
- File loading for .dfa and .txt files
- Interactive string matching
- Visual feedback for matches (green) and non-matches (red)
- Error handling with informative messages
- User-friendly interface with file selection

### Generic States (`/genericstates`)
Implementation of state machines using Java Generics. Features:
- State and Transition class implementations
- DFA generation from file descriptions
- Support for various symbol types
- Comprehensive error checking

### First Assignment (`/first_assignment`)
Basic implementation of the Recall interface. Features:
- Simple file operations
- Interface implementation practice
- Basic exception handling

### Maze Implementation (`/maze`)
A maze generation and traversal project. Features:
- 8x8 grid maze generation
- Username-based deterministic generation
- Move file processing
- Debugger-based exploration

### Macro Assembler (`/macroassembler`)
A tool for HMMM assembly programming. Features:
- Automatic line numbering
- Label processing
- Two-pass compilation
- Support for comments and instructions

### Tigrish Language (`/tigrish`)
A non-turing complete stack-based language implementation. Features:
- Token/Node based execution
- Support for integers, variables, and operators
- Comment handling
- Stack-based execution environment

### Day Projects
Various daily assignments including:
- `Day11`: GUI components
- `Day12`: File access and pony-themed implementations
- `Day13`: Encryption implementations
- `Day2`: Node-based implementations
- `Day4`: Point and Link implementations
- `Day5`: Pony-themed grid implementations

### Zip Tool (`/zip_tool`)
Utility for zipping and submitting CP222 programming assignments. Features:
- Project directory selection
- File filtering
- Submission preparation
- Platform-independent operation

## Getting Started

Each project contains its own README with specific instructions. Projects typically include:
- Source code in `src/` directory
- Test files in `test/` directory
- Data files in `data/` directory
- Project-specific documentation

## File Formats

### .ccr Files (Character Class Registry)
- Line-based format
- Support for character class definitions
- Whitespace-separated items
- Pre-defined classes for space, newline, and tab

### .dfa Files (Deterministic Finite Automata)
- Line-based format
- Support for states and transitions
- Attribute specification
- Comment handling

### .lbl Files (HMMM Assembly)
- Label-based assembly format
- Automatic instruction numbering
- Comment support
- Jump target resolution

## Testing

Most projects include JUnit tests in their respective `test/` directories. Run these tests to verify your implementations.

## Building and Running

Each project is structured as a separate Java project with its own build configuration. Most projects include:
- Source files
- Test files
- Sample data
- Project-specific documentation

## Notes

- Projects are designed to build specific programming skills
- Focus on clean implementation and thorough testing
- Pay attention to error handling and edge cases
- Follow the specific requirements in each project's README

## Contributing

This is a coursework repository. Contributions should follow course guidelines and academic integrity policies.

## License

Refer to course policies for usage and distribution rights.
