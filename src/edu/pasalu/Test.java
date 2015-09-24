package edu.pasalu;

/**
 * Programmer: Peter Salu
 * Created on: September 19, 2015
 * Description: Tests for Missionaries and Cannibals.
 */
public class Test {
    public static void graph() {
        Graph <Node> graph = new Graph<>();
        Node parent = new Node(null, "C->", 3, 3, 0, 0, "LEFT", 2);
        Node node = new Node(parent, "CM<-", 3, 2, 0, 0, "RIGHT", 2);

        graph.add(parent, node);
        System.out.println(graph.toString());
    }

    public static void actions() {
        Node node332 = new Node(null, "C->", 3, 3, 0, 0, "LEFT", 2);
        System.out.println(node332.state.actions());

        Node node331 = new Node(null, "C->", 3, 3, 0, 0, "LEFT", 1);
        System.out.println(node331.state.actions());

        Node node334 = new Node(null, "C->", 3, 3, 0, 0, "LEFT", 4);
        System.out.println(node334.state.actions());

        Node node324 = new Node(null, "C->", 3, 2, 0, 0, "LEFT", 4);
        System.out.println(node324.state.actions());
    }
}
