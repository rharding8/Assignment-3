**Problem 1: Minotaur**

The 'Minotaur' Class, and 'Servant' subclass (Extending Thread) handle all the logic here. Every Servant thread alternates between adding a new present into the chain (Until the amount of presents total is used up) and removing presents from the chain and incrementing a counter of Thank You Notes. When all presents have been added to the chain, the Servants exclusively focus on removing and thanking presents. The Servants all stop when there are no more presents left on the chain and no presents needing to be added.

Adding presents, a Synchronized action, also involves sorting the list by its internal tag value, which is generated randomly here. Removing presents and thanking them, also synchronized, can happen one of two ways. If the Servant can find the present by a random tag, they'll remove that specific present. Otherwise, if they can't find a specific one, they'll remove the first in the chain.

The Main script simply provides an output showing that the count of thank you notes is equal to the original count of presents (500,000). If I thought of it earlier, I would've done this with a test case and assertion instead of a main script.

**Problem 2: Mars Rover**

The second part of the main script runs the other program, the Mars Rover. My approach here was likely a result of me overthinking the problem, but it ultimately still works correctly and doesn't clash with the threads which is good enough for me. This program, run in the 'Rover' Class using 'Sensor' threads, features a two-dimensional array as the main structure used. There are as many elements in the array as increments to check the temperature for every sensor.

Race conditions are avoided simply by the fact that no sensor ever accesses the same one-dimensional subset of the array as any other, every sentence has its own row in the table entirely to itself. Once all increments (60 minutes) have passed, the handler function calls a complex report function, which generates the report by flattening the array into a sorted ArrayList to get the top 5 largest and smallest temperatures recorded. It then cycles through the entire table to find the 10-minute interval with the largest temperature difference and return said difference and on which sensor and during which interval it was found.

The program runs exceptionally fast, but it is flawed in that it stops after one cycle (Hour). I would have to refine how it works to keep it running for multiple cycles with reports each time.

**Compilation**

javac Main.java Minotaur.java Rover.java
java Main

The Main file runs BOTH programs, and gives outputs for both as well as an overall execution time.
