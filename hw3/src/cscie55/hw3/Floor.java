package cscie55.hw3;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Floor
{
    /* FIELDS */
    private int floorNumber;                        // floor number
    private Set<Passenger> passGoingUp;
    private Set<Passenger> passGoingDown;
    private Set<Passenger> passResident;
    private Building building;                      // building


    /* CONSTRUCTOR */
    public Floor(Building bldg, int flr)
    {
        building = bldg;
        floorNumber = flr;
        passGoingUp = new TreeSet<Passenger>();
        passGoingDown = new TreeSet<Passenger>();
        passResident = new TreeSet<Passenger>();
    }


    /* PUBLIC METHODS */

    // method for passenger to call elevator
    public void waitForElevator(Passenger passenger, int destinationFloor)
    {
        // change destination floor in passenger
        passenger.waitForElevator(destinationFloor);

        // modify sets if passenger is on the floor and going somewhere
        if (passenger.currentFloor() == floorNumber & passenger.destinationFloor() != passenger.UNDEFINED_FLOOR)
        {
            // going up
            if (passenger.destinationFloor() > floorNumber)
            {
                passGoingUp.add(passenger);
                passResident.remove(passenger);
            }
            // going down
            else if (passenger.destinationFloor() < floorNumber)
            {
                passGoingDown.add(passenger);
                passResident.remove(passenger);
            }
            // else the passenger might have currentFloor = destinationFloor, then do nothing
        }

        // call an elevator
        building.elevator.callElevator(floorNumber);

    }

    // getter for resident (if passenger is on the floor)
    public boolean isResident(Passenger passenger)
    {
        // return true if passenger is on the floor and not going anywhere
        if (passenger.currentFloor() == floorNumber & passenger.destinationFloor() == passenger.UNDEFINED_FLOOR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // setter to add passenger to resident of ground floor
    public void enterGroundFloor(Passenger passenger)
    {
        // add passenger to ground floor's resident set
        building.floors[0].passResident.add(passenger);
    }

    // getter for passGoingUp (if passenger wants to go up)
    public Set<Passenger> getPassGoingUp()
    {
        return passGoingUp;
    }

    // getter for passGoingDown (if passenger wants to go up)
    public Set<Passenger> getPassGoingDown()
    {
        return passGoingDown;
    }


}
