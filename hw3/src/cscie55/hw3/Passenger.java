package cscie55.hw3;

public class Passenger {

    /* FIELDS */
    public static final int UNDEFINED_FLOOR = -1;               // undefined floor
    private int currentFloor = 1;                               // current floor of passenger
    private int destinationFloor = UNDEFINED_FLOOR;             // floor that passenger wants to go to
    //private Building building;                                  // building
    private int id;                                             // passenger id


    /* CONSTRUCTOR */
    public Passenger (int id)
    {
        this.id = id;


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

    // setter when elevator arrives at destination
    public void arrive()
    {
        currentFloor = destinationFloor;                        // set current floor to the destination
        destinationFloor = UNDEFINED_FLOOR;                     // set destination to undefined
    }

    // define toString() that indicates currentFloor and destinationFloor
    public String toString()
    {
        return "Current Floor = " + Integer.toString(currentFloor) + ", Destination Floor = " + Integer.toString(destinationFloor);
    }
}
