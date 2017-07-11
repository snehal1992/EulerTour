# EulerTour

CS 4V95.003, CS 5V81.012.  Implementation of data structures and algorithms
Fall 2016;  Thu, Sep 8
Mandatory Project 1: Finding Euler tours

Ver 1.0: Initial description (Sep 8, 4:00 PM).

This is a mandatory project, to be done individually. 
Max excellence credits (EC): 1.0 
Only submissions before 1st deadline are eligible for EC.

Due: 11:59 PM, Sun, Oct 2 (1st deadline),  Sun, Oct 16 (2nd deadline).

Project Description:

A graph G is called Eulerian if it is connected and the degree of
every vertex is an even number.  It is known that such graphs aways
have a tour (a cycle that may not be simple) that goes through every
edge of the graph exactly once.  Such a tour is called an Euler tour. 
In this project, write code that finds an Euler tour in a given graph.

Initial code base: Graph classes.

The algorithm that you need to implement is known as Hierholzer's
algorithm (see https://en.wikipedia.org/wiki/Eulerian_path).
Your algorithm should run in O(|E|) time.

The project has several deliverables:
1. Create a class for storing circular (singly linked) lists:
   public class CircularList<T> implements Iterable<T> { ... }
   To get O(E) run time, You may need to build an index on its nodes.

2. Read the graph using Graph.readGraph() (from console or input file).

3. Write a function that takes a graph and decomposes its edges into subtours:
   List<CircularList<Vertex>> breakGraphIntoTours(Graph g)

4. Write a function that takes a list of tours and stitches them into one:
   CircularList<Vertex> stitchTours(List<CircularList<Vertex>> listOfTours)

5. [This step is optional for students of CS 4V95]:
   Write a function that takes a graph g, and a tour, and checks if it is
   a valid Eulerian tour of g:
   boolean verifyTour(Graph g, CircularList<Vertex> tour)

6. In addition, write a driver program that reads the input graph, finds
   a tour in it, verifies that it is correct, and prints out the tour using
   the format given below.  For CS 5V81 students, the verification step
   is required.  For CS 4V95 students, it is optional.

Input format
If the program is started with a command line parameter, then args[0]
is the name of a file from which input is read.  Otherwise, read the
input from the standard input (console).  Use Graph.readGraph(in)
to read the input graph.  You can assume that the graph is simple.
No error handling is required to check for input format errors.

Output format
The output must be printed to standard output (console).
If the graph has no Euler tour, your program outputs the message
"Graph is not Eulerian".

Otherwise, if the graph has an Euler tour, then it prints the nodes of an
Euler tour, in correct order, and the tour must start at node 1.

Use the following code for printing output:
for(Vertex u: tour) { System.out.println(u); }

Do not print anything else in the output.
Note that a graph can have many different Euler tours.
Your program needs to find just one of the solutions.

Sample input
6 10
1 2 1
1 3 1
1 4 1
1 6 1
2 3 1
3 6 1
3 4 1
4 5 1
4 6 1
5 6 1

Sample output
1
2
3
6
4
5
6
1
3
4

Explanation
The actual tour is 1->2->3->6->4->5->6->1->3->4->1.
Additional input files will be provided later.
