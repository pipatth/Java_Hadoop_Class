package cscie55.hw3;

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
        elevator = new Elevator(this);
        for (int i = 0; i < FLOORS; i++)
            floors[i] = new Floor(this, i + 1);
    }


    /* PUBLIC METHODS */

    // getter to return elevator object
    public Elevator elevator()
    {
        return elevator;
    }

    // getter to return floor object
    public Floor floor(int floorNumber)
    {
        return floors[floorNumber - 1];
    }

    // method when passenger enter the building
    public void enter(Passenger passenger)
    {
        // enter at ground floor
        floors[0].enterGroundFloor(passenger);
    }

}
