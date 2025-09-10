Tigrish v0.1

Tigrish is a non-turing complete stack based language. This version of the language supports comments, integer values, variables, and a couple operators. The language can be thought of in a natural language context, roughly, as an OSV language. Alternatively, we might say that the language is a postfix encoding of the language's parse tree.

You'll need to write TigrishReader, which implements TigrishReaderI. The Tigrish reader is responsible for reading .tgr files. Unlike the other assignments this block, the interface expects the TigrishReader will read only the next token/Node from the file rather than reading the full file into memory.

  - Tigrish comment tokens start with a '#' and continue until the newline character.
  - Tigrish integer tokens are any contiguous sequence of base 10 digit characters [0-9]
  - Tigrish variable tokens are any letter [a-zA-Z] followed by any number of letters, digits, or '_' [a-zA-Z0-9_]
  - Tigrish operator tokens are '+', '-', '*', '/', '=', '<', '>' [+-*/=<>]

 There are a few ways to go about token detection... Multiple tokens may appear on the same line. No tokens span across lines. All tokens can be disambiguated based on their first character. Whitespaces are ignored/not symatically meaningful for any tokens other than the comment token.

In a more complex language/compiler, tokens and parse tree nodes would be different things. In a more complex language: the lexer converts the sequence of characters from the file into a sequence of tokens, the parser converts the sequence of tokens into a parse tree. Tigrish v0.1 is simple enough that these steps can be easily combine. Every Tigrish token is also an executable node of the parse tree.

Your Tigrish token/Nodes will extend the provide abstract Node class. If the token should also have a value associated with it (e.g. integers and variables), the ValueI interface must also be implemented.

The basic execution cycle of a Tigrish program can be seen in programs.Tigrish. An execution Environment and TigrishReaderI are instantiated. The Environment represents the registers and memory for the Tigrish virtual machine. At each step of execution, the next Node is read from the TigrishReaderI and is executed in the Environment.

Execution of each node type makes the following changes to the environment:
  - Tigrish comment nodes do nothing.
  - Tigrish integer nodes push themselves onto the stack in the environment
  - Tigrish variable nodes remember their environment and push themselves onto the stack
  - Tigrish operator nodes do different things depending on the operator
    + pops the top two nodes from the stack, add them, push an integer node containing the result onto the stack
    - pops the top two nodes from the stack, subtract them, push an integer node containing the result onto the stack
    * pops the top two nodes from the stack, multiplies them, push an integer node containing the result onto the stack
    / pops the top two nodes from the stack, divide them, push an integer node containing the result onto the stack
    = pop the top node (the variable) then pop the next node (the value), store the value in the variable in the environment
    < pop the top node (the variable), prompt the user for an integer, store the integer in the variable
    > pop the top node and display its value

Only integer nodes and variable nodes have values.
  - The value for an integer node is whatever value was set when the node was created
  - The value for a variable node is retrieved by fetching the associated value from the environment at the time the variable is used

This assignment does not check for specific files in the submitted zip file. Do not change the starter code, since these changes would be overwritten for testing.

JUnit tests will be written and made available later. Additional sample programs will also be made available later. ...it feels important to get the start code released early...
