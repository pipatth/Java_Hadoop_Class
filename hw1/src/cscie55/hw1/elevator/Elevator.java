package cscie55.hw1.elevator;

public class Elevator
{
    /* FIELDS */
    public static final int FLOORS = 7;             // number of floors
    private int currentFloor = 1;                   // current floor (start with ground floor)
    private boolean goingUp = true;                 // direction of travel (going up)
    private int nPassengers;                        // number of passengers in the elevator
    private int [] stopRequested = new int[7];      // array for number of passenger destined for that floor

    /* CONSTRUCTOR */
    public Elevator()
    {
    }

    /* METHODS */
    // define move() method that modifies elevator's state
    public void move()
    {
        // modify current floor (go up or down)
        if (goingUp)
        {
            currentFloor++;
        }
        else
        {
            currentFloor--;
        }

        // modify direction of travel
        if (currentFloor == FLOORS)
        {
            goingUp = false;                       // if top floor, has to go down)
        }
        else if (currentFloor == 1)                // if ground floor, has to go up
        {
            goingUp = true;
        }

        // unload passenger
        unloadPassenger(currentFloor);
    }

    // define boardPassenger(int floor) which adds a passenger destined for the indicated floor
    public void boardPassenger(int floor)
    {
        // update stop_requested_n array (add one)
        stopRequested[floor - 1]++;

        // update n_passengers
        nPassengers++;
    }

    // define unloadPassenger() which let passenger(s) destined for that floor out
    private void unloadPassenger(int floor)
    {
        // get the number of passengers who want to get out
        int passenger_out = stopRequested[floor - 1];

        // update stop_requested_n array (reset to zero)
        stopRequested[floor - 1] = 0;

        // update n_passengers
        nPassengers = nPassengers - passenger_out;
    }

    // define toString() that indicates the number of passengers on board and current floor
    public String toString()
    {
        if (nPassengers == 1)
        {
            return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(nPassengers) + " passenger";
        }
        else
        {
            return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(nPassengers) + " passengers";
        }
    }
}
