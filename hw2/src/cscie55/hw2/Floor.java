package cscie55.hw2;

public class Floor
{
    /* FIELDS */
    private int floorNumber;                        // floor number
    private int nWaiting;                           // number of passenger waiting
    private Building building;                      // building

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
        return nWaiting;
    }

    // passenger call elevator
    public void waitForElevator()
    {
        // change number of passenger waiting
        nWaiting++;

        // call an elevator
        building.elevator.callElevator(floorNumber);

    }

    // passenger leave the floor (get in to elevator)
    public void leaveFloor()
    {
        // change number of passenger waiting
        if (nWaiting > 0)
        {
            nWaiting--;
        }
    }
}
