package edu.pasalu;

/**
 * Programmer: Peter Salu
 * Created on: September 19, 2015
 * Description: Tests for Missionaries and Cannibals.
 */
public class Test {
    public static void graph() {
        Graph <Node> graph = new Graph<>();
        Node parent = new Node("C->", null);
        Node node = new Node("CM<-", parent);

        graph.add(parent, node);
        System.out.println(graph.toString());
    }
}
