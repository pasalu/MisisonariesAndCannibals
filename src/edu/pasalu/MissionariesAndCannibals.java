/*
 * Programmer: Peter Salu
 * Created on: September 13, 2015
 * Description: Solution to the Missionaries and Cannibals problem.
 */
package edu.pasalu;

import java.util.Vector;

public class MissionariesAndCannibals {
   static final int SEATS_INDEX = 0;
   static final int MISSIONARIES_INDEX = 1;
   static final int CANNIBALS_INDEX = 2;
   static final int NODES_INDEX = 3;

    public static void main(String[] args) {
        try {
            int[] arguments = validateArguments(args);
            cannibalsAndMissionaries(
                    arguments[MISSIONARIES_INDEX],
                    arguments[CANNIBALS_INDEX],
                    arguments[SEATS_INDEX],
                    arguments[NODES_INDEX]
            );
        } catch (NumberFormatException e) {
            printUsageAndExit();
        }
    }

    private static int[] validateArguments(String[] args) {
        final int MINIMUM_NUMBER_OF_SEATS = 2;
        final int MAXIMUM_NUMBER_OF_SEATS = 4;
        final int MINIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS = 2;
        final int MAXIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS = 6;
        final int MINIMUM_NUMBER_OF_ARGUMENTS = 3;
        final int MAXIMUM_NUMBER_OF_ARGUMENTS = 4;
        final int MINIMUM_NUMBER_OF_NODES_TO_EXPAND = 0;
        final int UNLIMITED_NODES = -1;

        if (args.length < MINIMUM_NUMBER_OF_ARGUMENTS ||
                args.length > MAXIMUM_NUMBER_OF_ARGUMENTS) {
            printUsageAndExit();
        }

        int seats = Integer.parseInt(args[SEATS_INDEX]);
        int missionaries = Integer.parseInt(args[MISSIONARIES_INDEX]);
        int cannibals = Integer.parseInt(args[CANNIBALS_INDEX]);
        int nodesToExpand = args.length == MAXIMUM_NUMBER_OF_ARGUMENTS ?
                Integer.parseInt(args[NODES_INDEX]) : UNLIMITED_NODES;

        if (seats < MINIMUM_NUMBER_OF_SEATS || seats > MAXIMUM_NUMBER_OF_SEATS) {
            printUsageAndExit();
        }
        if (missionaries < MINIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS ||
                missionaries > MAXIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS) {
            printUsageAndExit();
        }
        if (cannibals < MINIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS ||
                cannibals > MAXIMUM_NUMBER_OF_MISSIONARIES_AND_CANNIBALS) {
            printUsageAndExit();
        }
        if (args.length == MAXIMUM_NUMBER_OF_ARGUMENTS && nodesToExpand < MINIMUM_NUMBER_OF_NODES_TO_EXPAND) {
            printUsageAndExit();
        }

        return new int[]{seats, missionaries, cannibals, nodesToExpand};
    }

    private static void printUsageAndExit() {
        final String MESSAGE =
                "USAGE: java MissionariesAndCannibals seats missionaries cannibals [N]\n"
                + "e.g. java MissionariesAndCannibals 2 3 3 120\n"
                + "2 <= [seats] <= 4\n"
                + "2 <= [missionaries][cannibals] <= 6\n"
                + "N > 0 (Optional) - The maximum number of nodes to expand\n";
        final int ERROR_STATUS = 1;

        System.out.println(MESSAGE);
        System.exit(ERROR_STATUS);
    }

    /**
     * Solves the cannibals and missionaries problem.
     * @param missionaries The number of missionaries.
     * @param cannibals The number of cannibals.
     * @param seats The number of seats on the boat.
     * @param nodesToExpand Maximum number of nodes to expand.
     */
    private static void cannibalsAndMissionaries(int missionaries, int cannibals, int seats, int nodesToExpand) {
        final Node NO_PARENT = null;
        final String NO_ACTION = "";
        final int NO_MISSIONARIES = 0;
        final int NO_CANNIBALS = 0;
        final String BOAT_LEFT = "LEFT";
        final String BOAT_RIGHT = "RIGHT";
        final int MISSIONARIES_INDEX = 0;
        final int CANNIBALS_INDEX = 1;

        Graph<Node> graph = new Graph<>();
        Node initialNode = new Node(
                NO_PARENT,
                NO_ACTION,
                missionaries,
                cannibals,
                NO_MISSIONARIES,
                NO_CANNIBALS,
                BOAT_LEFT,
                seats
        );
        Vector<String> actions = initialNode.state.actions();

        for (String action : actions) {
            int[] numberOfMsAndCs = numberOfMsAndCs(action);
            int missionariesLeft = initialNode.state.missionariesLeft.length();
            int cannibalsLeft = initialNode.state.cannibalsLeft.length();
            int missionariesRight = initialNode.state.missionariesRight.length();
            int cannibalsRight = initialNode.state.cannibalsRight.length();
            final String newBoat;

            if (initialNode.state.boat.equals(BOAT_LEFT)) {
                newBoat = BOAT_RIGHT;
                missionariesLeft -= numberOfMsAndCs[MISSIONARIES_INDEX];
                cannibalsLeft -= numberOfMsAndCs[CANNIBALS_INDEX];
                missionariesRight += numberOfMsAndCs[MISSIONARIES_INDEX];
                cannibalsRight += numberOfMsAndCs[CANNIBALS_INDEX];
            } else {
                newBoat = BOAT_LEFT;
                missionariesLeft += numberOfMsAndCs[MISSIONARIES_INDEX];
                cannibalsLeft += numberOfMsAndCs[CANNIBALS_INDEX];
                missionariesRight -= numberOfMsAndCs[MISSIONARIES_INDEX];
                cannibalsRight -= numberOfMsAndCs[CANNIBALS_INDEX];
            }

            Node child = new Node(
                    initialNode,
                    action,
                    missionariesLeft,
                    cannibalsLeft,
                    missionariesRight,
                    cannibalsRight,
                    newBoat,
                    seats);

            graph.add(initialNode, child);
            System.out.println(child);
            System.out.println(child.state.isSafeState());
            System.out.println();
        }
        System.out.println(graph);
    }

    private static int[] numberOfMsAndCs(String s) {
        final int LAST_M = s.lastIndexOf(Node.MISSIONARIES);
        final int NOT_FOUND = -1;
        final int NUMBER_OF_MS;
        final int NUMBER_OF_CS;
        final int NONE = 0;

        //s consists of entirely of C's.
        if (LAST_M == NOT_FOUND){
            NUMBER_OF_MS = NONE;
            NUMBER_OF_CS = s.length();
        } else {
            NUMBER_OF_MS = LAST_M + 1;
            NUMBER_OF_CS = s.length() - NUMBER_OF_MS;
        }

        return new int[] {NUMBER_OF_MS, NUMBER_OF_CS};
    }

    /**
     * Splits a string of 'M' and ending in 'C'.
     * @param s A string consisting of only 'M' and 'C'.
     * @return The string s split into 2 parts.
     */
    private static String[] splitMsAndCs(String s) {
        final int STRING_BEGIN = 0;
        final int LAST_M = s.lastIndexOf(Node.MISSIONARIES);

        return new String[] {s.substring(STRING_BEGIN, LAST_M), s.substring(LAST_M)};
    }
}
