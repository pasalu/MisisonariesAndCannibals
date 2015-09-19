/*
 * Programmer: Peter Salu
 * Created on: September 13, 2015
 * Description: Solution to the Missionaries and Cannibals problem.
 */
package edu.pasalu;

public class MissionariesAndCannibals {

    public static void main(String[] args) {
        try {
            validateArguments(args);
        } catch (NumberFormatException e) {
            printUsageAndExit();
        }

        Test.graph();
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
        final int SEATS_INDEX = 0;
        final int MISSIONARIES_INDEX = 1;
        final int CANNIBALS_INDEX = 2;
        final int NODES_INDEX = 3;

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
}
