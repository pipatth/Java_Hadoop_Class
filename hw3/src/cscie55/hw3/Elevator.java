package cscie55.hw3;

import java.util.HashSet;
import java.util.Set;

public class Elevator
{
    /* FIELDS */
    public static final int CAPACITY = 10;                  // elevator capacity
    private int currentFloor = 1;                           // current floor (start with ground floor)
    private boolean goingUp = true;                         // direction of travel (going up)
    private int nPassengers;                                // number of passengers in the elevator
    private int [] stopRequested;                           // array for number of passenger destined for that floor
    private boolean [] stopBool;                            // array to keep track of stop
    private Building building;                              // building
    private Set<Passenger> passengerSet;

    /* CONSTRUCTOR */
    public Elevator(Building building)
    {
        this.building = building;
        this.stopRequested = new int[this.building.FLOORS]; // get number of floors from building
        this.stopBool = new boolean[this.building.FLOORS];  // create stopBool array
        this.passengerSet = new HashSet<Passenger>();       // create passengerSet
    }

    /* PUBLIC METHODS */

    // define move() method that modifies elevator's state
    public void move() {

        // modify current floor (go up or down)
        if (goingUp) {
            currentFloor++;
        } else {
            currentFloor--;
        }

        // modify direction of travel
        if (currentFloor == building.FLOORS) {
            goingUp = false;                        // if top floor, has to go down)
        } else if (currentFloor == 1)               // if ground floor, has to go up
        {
            goingUp = true;
        }

        // unload and board passenger if needed
        if (stopBool[currentFloor - 1])             // if stopBool is true, then unloadPassenger and boardPassenger
        {
            // unload passenger
            unloadPassenger(currentFloor);

            // board passenger if not full
            Floor cfloor = building.floors[currentFloor - 1];
            int i = cfloor.passengersWaiting();
            while (i > 0)
            {
                try {
                    boardPassenger(1);              // passenger goes to 1st floor
                    cfloor.leaveFloor();            // passenger leave floor
                    i--;
                }
                catch (ElevatorFullException elevatorFullException) {
                    System.out.println(elevatorFullException);
                    stopBool[currentFloor - 1] = true;  // if elevator is full, then need to come back
                    break;
                }
            }
        }
    }

    // define boardPassenger(int floor) which adds a passenger destined for the indicated floor
    public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException
    {

        // check if elevator is full, throw exception
        if (nPassengers == CAPACITY)
        {
            throw new ElevatorFullException();
        }
        else
        {
            // update stopRequested array (add one)
            stopRequested[destinationFloorNumber - 1]++;

            // update stopBool array (true)
            stopBool[destinationFloorNumber - 1] = true;

            // update nPassengers
            nPassengers++;

            // add to passengerSet
//            passengerSet.add()
        }
    }

    // define callElevator() which let passenger call for elevator
    public void callElevator(int floor)
    {
        // update stopBool array (true)
        stopBool[floor - 1] = true;
    }

    // define currentFloor() to return floor number
    public int currentFloor()
    {
        return currentFloor;
    }

    // define passengers() to return number of passengers in elevator
    public Set<Passenger> passengers()
    {
        // return Set<Passenger>;
        return passengerSet;
    }

    // define toString() that indicates the number of passengers on board and current floor
    public String toString()
    {
        return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(nPassengers) + " passengers";
    }

    // define goingUp() that return true if elevator is going up
    public boolean goingUp()
    {
        return goingUp;
    }

    // define goingDown() that return true if elevator is going down
    public boolean goingDown()
    {
        return !goingUp;
    }


    /* PRIVATE METHODS */

    // define unloadPassenger() which let passenger(s) destined for that floor out
    private void unloadPassenger(int floor)
    {
        // get the number of passengers who want to get out
        int passenger_out = stopRequested[floor - 1];

        // update stopRequested array (reset to zero)
        stopRequested[floor - 1] = 0;

        // update stopBool array (reset to false)
        stopBool[floor - 1] = false;

        // update nPassengers
        nPassengers = nPassengers - passenger_out;
    }

}
