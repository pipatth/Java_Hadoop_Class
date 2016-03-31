package cscie55.hw3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Elevator {
    /* FIELDS */
    public static final int CAPACITY = 10;                  // elevator capacity
    private int currentFloor = 1;                           // current floor (start with ground floor)
    private boolean goingUp = true;                         // direction of travel (going up)
    private int[] stopRequested;                           // array for number of passenger destined for that floor
    private boolean[] stopBool;                            // array to keep track of stop
    private Building building;                              // building
    private Set<Passenger> passElevator;


    /* CONSTRUCTOR */
    public Elevator(Building bldg) {
        building = bldg;
        stopRequested = new int[bldg.FLOORS]; // get number of floors from building
        stopBool = new boolean[bldg.FLOORS];  // create stopBool array
        passElevator = new HashSet<Passenger>();       // create passElevator
    }


    /* PUBLIC METHODS */

    // move() method modifies elevator's state
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

            // get set of passengers waiting who want to travel to the same direction
            Floor cfloor = building.floors[currentFloor - 1];
            Set<Passenger> passUp = cfloor.getPassGoingUp();
            Set<Passenger> passDown = cfloor.getPassGoingDown();
            if (goingUp) {
                Iterator<Passenger> iter = passUp.iterator();
                while (iter.hasNext()) {
                    try {
                        boardPassenger(iter.next());
                        iter.remove();
                    } catch (ElevatorFullException elevatorFullException) {
                        System.out.println(elevatorFullException);
                        stopBool[currentFloor - 1] = true;  // if elevator is full, then need to come back
                        break;
                    }
                }
                if (!passDown.isEmpty())    // if there is passenger waiting to go down, then need to come back
                {
                    stopBool[currentFloor - 1] = true;
                }
            } else {
                Iterator<Passenger> iter = passDown.iterator();
                while (iter.hasNext()) {
                    try {
                        boardPassenger(iter.next());
                        iter.remove();
                    } catch (ElevatorFullException elevatorFullException) {
                        System.out.println(elevatorFullException);
                        stopBool[currentFloor - 1] = true;  // if elevator is full, then need to come back
                        break;
                    }
                }
                if (!passUp.isEmpty())    // if there is passenger waiting to go up, then need to come back
                {
                    stopBool[currentFloor - 1] = true;
                }
            }
        }
    }

    // callElevator() lets passenger call for elevator
    public void callElevator(int floor) {
        // update stopBool array (true)
        stopBool[floor - 1] = true;
    }

    // getter currentFloor() returns floor number
    public int currentFloor() {
        return currentFloor;
    }

    // getter passengers() returns number of passengers in elevator
    public Set<Passenger> passengers() {
        return passElevator;
    }

    // toString() indicates the number of passengers on board and current floor
    public String toString() {
        return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(passElevator.size()) + " passengers";
    }

    // getter goingUp() returns true if elevator is going up
    public boolean goingUp() {
        return goingUp;
    }

    // getter goingDown() returns true if elevator is going down
    public boolean goingDown() {
        return !goingUp;
    }



    /* PRIVATE METHODS */

    // boardPassenger(Passenger passenger) adds a passenger to the elevator
    private void boardPassenger(Passenger passenger) throws ElevatorFullException {

        // check if elevator is full, throw exception
        if (passElevator.size() == CAPACITY) {
            throw new ElevatorFullException();
        } else {
            // update passenger
            passenger.boardElevator();

            // add passenger to passElevator
            passElevator.add(passenger);

            // update stopRequested array (add one)
            stopRequested[passenger.destinationFloor() - 1]++;

            // update stopBool array (set to true)
            stopBool[passenger.destinationFloor() - 1] = true;
        }
    }

    // unloadPassenger() lets passenger(s) out at their destination floor
    private void unloadPassenger(int floor) {
        // remove passenger from passElevator if destined to that floor using Iterator
        Iterator<Passenger> iter = passElevator.iterator();
        while (iter.hasNext()) {
            Passenger ps = iter.next();
            if (ps.destinationFloor() == floor) {
                iter.remove();
                ps.arrive();
            }
        }

        // update stopRequested array (reset to zero)
        stopRequested[floor - 1] = 0;

        // update stopBool array (reset to false)
        stopBool[floor - 1] = false;
    }
}
