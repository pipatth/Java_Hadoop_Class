package cscie55.hw3;

import java.util.Comparator;

public class Passenger implements Comparable<Passenger> {

    /* FIELDS */
    public static final int UNDEFINED_FLOOR = -1;               // undefined floor
    private int currentFloor = 1;                               // current floor of passenger
    private int destinationFloor = UNDEFINED_FLOOR;             // floor that passenger wants to go to
    private int id;                                             // passenger id


    /* CONSTRUCTOR */
    public Passenger (int i)
    {
        id = i;
    }


    /* PUBLIC METHODS */

    // getter for currentFloor
    public int currentFloor()
    {
        return currentFloor;
    }

    // getter for destinationFloor
    public int destinationFloor()
    {
        return destinationFloor;
    }

    // setter for destinationFloor
    public void waitForElevator(int newDestinationFloor)
    {
        destinationFloor = newDestinationFloor;
    }

    // setter for currentFloor when passenger board elevator
    public void boardElevator()
    {
        currentFloor = UNDEFINED_FLOOR;
    }

    // setter arrive() when elevator arrives at destination
    public void arrive()
    {
        currentFloor = destinationFloor;                        // set current floor to the destination
        destinationFloor = UNDEFINED_FLOOR;                     // set destination to undefined
    }

    // getter toString() indicates currentFloor and destinationFloor
    public String toString()
    {
        return "id = " + Integer.toString(id) + ", Curr. Floor = " + Integer.toString(currentFloor) + ", Dest. Floor = " + Integer.toString(destinationFloor);
    }

    // Compare id
    @Override
    public int compareTo(Passenger p)
    {
        return Integer.compare(id, p.id);
    }
}
