package cscie55.hw2;

import cscie55.hw2.Building;

public class Floor
{
    /* FIELDS */
    // define floor number
    private int floorNumber;

    // define number of passenger waiting
    private int n_waiting;

    // define which building
    private Building building = new Building();

    /* CONSTRUCTOR */
    public Floor(Building building, int floorNumber)
    {
        this.building = building;
        this.floorNumber = floorNumber;
    }

    /* METHODS */
    // return number of passengers who are waiting at that floor
    public int passengersWaiting()
    {
        return n_waiting;
    }

    // passenger call elevator
    void waitForElevator()
    {
        // change number of passenger waiting
        n_waiting++;

        //
    }
}
