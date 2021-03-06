package edu.pasalu;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Programmer: Peter Salu
 * Created on: September 17, 2015
 * Description: A graph represented by an adjacency list.
 */
public class Graph<T> {
    private Hashtable<T, LinkedList<T>> graph;

    public Graph() {
        graph = new Hashtable<>();
    }

    /**
     * Add child as adjacent to parent.
     * @param parent The child to be adjacent to.
     * @param child The child to be adjacent from.
     */
    public void add(T parent, T child) {
        if (graph.get(parent) == null) {
            graph.put(parent, new LinkedList<>());
        }

        graph.get(parent).add(child);
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
