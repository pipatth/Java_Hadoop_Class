package cscie55.hw3;

import java.util.HashSet;
import java.util.Set;

public class Floor
{
    /* FIELDS */
    private int floorNumber;                        // floor number
    private int nWaiting;                           // number of passenger waiting
    private Set<Passenger> floorPassengerSet;       // set of passenger on the floor
    private Building building;                      // building


    /* CONSTRUCTOR */
    public Floor(Building building, int floorNumber)
    {
        this.building = building;
        this.floorNumber = floorNumber;
        this.floorPassengerSet = new HashSet<Passenger>();
    }


    /* PUBLIC METHODS */

    // return number of passengers who are waiting at that floor ***
    private int passengersWaiting()
    {
        return nWaiting;
    }

    // passenger call elevator ***
    public void waitForElevator(Passenger passenger, int destinationFloor)
    {



        // change number of passenger waiting
        nWaiting++;

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

    // add passenger to resident of ground floor
    public void enterGroundFloor(Passenger passenger)
    {
        // add passenger to floorPassengerSet on ground floor
        building.floors[0].floorPassengerSet.add(passenger);
    }


    // passenger leave the floor (get in to elevator)
    protected void leaveFloor()
    {
        // change number of passenger waiting
        if (nWaiting > 0)
        {
            nWaiting--;
        }

        // remove passenger from set

    }


    /* PRIVATE METHODS */



}
