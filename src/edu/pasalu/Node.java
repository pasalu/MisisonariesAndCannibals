package edu.pasalu;

/**
 * Programmer: Peter Salu
 * Created on: September 16, 2015
 * Description: A node for a Cannibals and Missionaries Graph.
 */
public class Node {
    public final Node parent;
    public final String action;

    public Node(String action, Node parent){
        this.action = action;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return action;
    }
}
