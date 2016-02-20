package cscie55.hw2;

public class Building
{
    /* FIELDS */
    public static final int FLOORS = 7;             // number of floors
    public Elevator elevator;                       // elevator
    public Floor [] floors = new Floor[FLOORS];     // arrays of floors

    /* CONSTRUCTOR */
    // create Elevator and multiple floors
    public Building()
    {
        this.elevator = new Elevator(this);
        for (int i = 0; i < FLOORS; i++)
            this.floors[i] = new Floor(this, i + 1);
    }

    /* METHODS */

    // return elevator object
    public Elevator elevator()
    {
        return elevator;
    }

    // return floor object
    public Floor floor(int floorNumber)
    {
        return floors[floorNumber - 1];
    }

}
