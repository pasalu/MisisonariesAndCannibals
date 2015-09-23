package edu.pasalu;

import java.util.Vector;

/**
 * Programmer: Peter Salu
 * Created on: September 16, 2015
 * Description: A node for a Cannibals and Missionaries Graph.
 */
public class Node {
    public final Node parent;
    public final String action;
    public final State state;

    class State {
        public final String missionariesLeft;
        public final String cannibalsLeft;
        public final String boat;
        public final int seats;
        public final String missionariesRight;
        public final String cannibalsRight;

        State(int missionaries, int cannibals, String boat, int seats) {
            final String MISSIONARIES = "M";
            final String CANNIBALS = "C";

            this.missionariesLeft = repeat(MISSIONARIES, missionaries);
            this.cannibalsLeft = repeat(CANNIBALS, cannibals);
            this.boat = boat;
            this.seats = seats;
            this.missionariesRight = "";
            this.cannibalsRight = "";
        }

        /**
         * Repeats a string n times.
         * @param s The string to repeat.
         * @param n The number of times to repeat the string.
         * @return The repeated string.
         */
        private String repeat(String s, int n) {
            String repeated = "";

            for (int i = 0; i < n; i++) {
                repeated += s;
            }

            return repeated;
        }

        /**
         * The list of actions that can be taken from the current state.
         * @return An array of actions.
         */
        public Vector<String> actions() {
            Vector<String> actions = new Vector<>();
            String missionaries;
            String cannibals;
            final int STRING_BEGIN = 0;

            if (boat.equals("LEFT")) {
                missionaries = missionariesLeft;
                cannibals = cannibalsLeft;
            } else {
                missionaries = missionariesRight;
                cannibals = cannibalsRight;
            }

            for (int seat = seats; seat > 0; seat--) {

                if (missionaries.length() >= seat) {
                    actions.add(missionaries.substring(STRING_BEGIN, seat));
                }
                if (cannibals.length() >= seat) {
                    actions.add(cannibals.substring(STRING_BEGIN, seat));
                }

                final int ONE_SEAT = 1;
                if (!missionaries.isEmpty() && seat > ONE_SEAT) {
                    //Find the starting point in missionaries.
                    int j = missionaries.length() >= seat ? (missionaries.length() - seat) + 1 : 0;
                    for (; ; j++) {
                        String ms = missionaries.substring(j);
                        String action = ms + cannibals;

                        //Make sure we have enough people to fill up the seat.
                        if (action.length() >= seat) {
                            action = action.substring(0, seat);
                            actions.add(action);
                        } else {
                            break;
                        }

                        final int ONE_CHARACTER = 1;
                        if (ms.length() <= ONE_CHARACTER) {
                            break;
                        }
                    }
                }
            }

            return actions;
        }

        @Override
        public String toString() {
            return missionariesLeft + cannibalsLeft + " " + boat + " " + missionariesRight + cannibalsRight;
        }
    }

    /**
     * Constructs a new Node.
     * @param parent The node that generated this one.
     * @param action The action taken to generate this node.
     * @param missionaries The number of missionaries.
     * @param cannibals The number of cannibals.
     * @param boat The location of the boat LEFT | RIGHT.
     */
    public Node(Node parent, String action, int missionaries, int cannibals, String boat, int seats){
        this.parent = parent;
        this.action = action;
        this.state = new State(missionaries, cannibals, boat, seats);
    }

    @Override
    public String toString() {
        return action + " " + state.toString();
    }
}
