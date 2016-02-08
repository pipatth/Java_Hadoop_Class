package cscie55.hw2;

public class Floor
{
    /* FIELDS */
    private int floorNumber;                        // floor number
    private int n_waiting;                          // number of passenger waiting
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
        return n_waiting;
    }

    // passenger call elevator
    public void waitForElevator()
    {
        // change number of passenger waiting
        n_waiting++;

        //

        // if (n_waiting > 0) ********************
        {

        }
    }
}
