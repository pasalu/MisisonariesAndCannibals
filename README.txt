A solution to the Missionaries and Cannibals problem (https://en.wikipedia.org/wiki/Missionaries_and_cannibals_problem).

USAGE: java MissionariesAndCannibals seats missionaries cannibals [N]
e.g. java MissionariesAndCannibals 2 3 3 120
2 <= [seats] <= 4
2 <= [missionaries][cannibals] <= 6
N > 0 (Optional) - The maximum number of nodes to expand

I chose Java because out of the languages that were given, it was the one I was most familiar with. I used
the java 1.8 compiler that came with IntelliJ IDEA 14.1.4.

The Graph was written by me and it is represented internally as a hash table with the key as a generic type and a value
of a linked list of that generic type. For the project, the add method is the only one that is needed.

The state is represented as strings with separate values for the missionaries/cannibals left/right (e.g. MMM for 3
missionaries). The location of the boat is represented as a string consisting of either LEFT or RIGHT.

One big problem that dominated the implementation time was finding a way to represent and generate the possible actions.


