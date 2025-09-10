###############
# Assignment
###############
In this assignment, you'll use the debugger to explore object instances in
memory. Being familiar with how to look at data in the debugger should
help you to fix your code faster during the block.

You must submit a zip file with the same contents as the starting zip file
where you have changed the contents of move.txt to work with your CC SSO
username. Your CC SSO username is the username you use when using ssh or sftp
to access the class machine.

For this assignment you will only be graded on your move.txt file, your move file
will be checked using the code as originally distributed.

###############
# Running the Program
###############
The provided code generates a maze and applies a set of moves from a file. Maze
generation is deterministic and based on the username provided when running the
program.

Usage: java Maze <username> <movefile>

The generated maze is an 8 by 8 grid. The player starts in a random room. Alex
is always in upper left corner and the tea is always in the lower right corner.
The movefile provides a sequence of steps for the player to get from the player's
starting location to the tea and then to Alex.

The first line of the move file must contain a # character followed by the
expected username. The username given when running the program must match
the username given in the movefile.

Each line in the movefile that starts with a 'u', 'd', 'l', or 'r' is treated
as a move. All other lines/data is ignored.

###############
# Hints/Help?
###############
The included moves.txt file provides the move sequence to complete the maze
constructed for the username dellsworth. I solved this maze using a piece of
paper and the debugger. ...set a break point in Maze just after the variable
holding the player's starting room is set, then traverse the rooms using the
debugger (drawing out the map by hand; like you had to do in 1980s CRPGs).

...I imagine someone could write code to draw a plot of the maze... The
MazeGenerator logic has been lightly obfuscated and does not create a 2D
grid to make this discouraged approach a little harder to execute.

The Maze and Room java files are the only files that I think are useful for
solving the maze. The Room class describes how the Room instances the player
moves through are modeled. The Maze class holds the main for the program
and has places to put debugger breakpoints.

###############
# Files
###############
README.txt                  - Describes the contents of the codebase
moves.txt                   - A solution for the username dellsworth
src/Maze.java               - The class containing the main method for the program
src/Room.java               - How a Room is modeled in the program
src/DoneBrokeException.java - An exception that shouldn't happen in production that
                            indicates the maze generator didn't work
src/InvalidMoveException.java - An exception that occurs if a move can't be made
src/MazeGenerator.java      - Deterministically generates a maze from a triple of ints
