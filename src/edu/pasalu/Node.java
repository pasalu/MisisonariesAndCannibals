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
    public static final String MISSIONARIES = "M";
    public static final String CANNIBALS = "C";

    class State {
        public final String missionariesLeft;
        public final String cannibalsLeft;
        public final String boat;
        public final int seats;
        public final String missionariesRight;
        public final String cannibalsRight;

        State(
                int missionariesLeft,
                int cannibalsLeft,
                int missionariesRight,
                int cannibalsRight,
                String boat,
                int seats
        ) {
            this.missionariesLeft = repeat(MISSIONARIES, missionariesLeft);
            this.cannibalsLeft = repeat(CANNIBALS, cannibalsLeft);
            this.boat = boat;
            this.seats = seats;
            this.missionariesRight = repeat(MISSIONARIES, missionariesRight);
            this.cannibalsRight = repeat(CANNIBALS, cannibalsRight);
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

        /**
         * A state is considered safe is cannibals never outnumber missionaries.
         * @return True if the state is safe, false otherwise.
         */
        @SuppressWarnings("ConstantConditions")
        public boolean isSafe() {
            final int NO_MISSIONARIES = 0;
            boolean isLeftSafe = missionariesLeft.length() >= cannibalsLeft.length();
            boolean isRightSafe = missionariesRight.length() >= cannibalsRight.length();

            //It's fine for the cannibals to be alone.
            if (!isLeftSafe && missionariesLeft.length() == NO_MISSIONARIES) {
               isLeftSafe = !isLeftSafe;
            }
            if (!isRightSafe && missionariesRight.length() == NO_MISSIONARIES) {
                isRightSafe = !isRightSafe;
            }

            return isLeftSafe && isRightSafe;
        }

        public boolean isGoal() {
            return missionariesLeft.isEmpty() && cannibalsLeft.isEmpty() && boat.equals("RIGHT");
        }

        @Override
        public boolean equals(Object other) {
            //noinspection SimplifiableIfStatement
            if (other instanceof State) {
                return this.toString().equals(other.toString());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
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
     * @param missionariesLeft The number of missionaries on the left of the river.
     * @param cannibalsLeft The number of cannibals on the left of the river.
     * @param missionariesRight The number of missionaries on the right of the river.
     * @param cannibalsRight The number of cannibals on the right of the river.
     * @param boat The location of the boat LEFT | RIGHT.
     */
    public Node(
            Node parent,
            String action,
            int missionariesLeft,
            int cannibalsLeft,
            int missionariesRight,
            int cannibalsRight,
            String boat,
            int seats
    )
    {
        final String LEFT = "<-";
        final String RIGHT = "->";
        final String BOAT_LEFT = "LEFT";
        final Node NO_PARENT = null;
        final String NO_ACTION = "";
        this.parent = parent;

        //The first node will have no parent and thus no action to get it here.
        if (this.parent != NO_PARENT){
            if (boat.equals(BOAT_LEFT)) {
                this.action =  action + LEFT;
            } else {
                this.action = action + RIGHT;
            }
        } else {
            this.action = NO_ACTION;
        }

        this.state = new State(missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, boat, seats);
    }

    @Override
    public String toString() {
        return "{action: " + action + ", state: " + state.toString() + "}";
    }
}
