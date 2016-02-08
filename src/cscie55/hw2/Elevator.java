package cscie55.hw2;

public class Elevator
{
    /* FIELDS */
    public static final int CAPACITY = 10;          // elevator capacity
    private int currentFloor = 1;                  // current floor (start with ground floor)
    private boolean goingUp = true;                // direction of travel (going up)
    private int nPassengers;                       // number of passengers in the elevator
    private int [] stopRequested = new int[7];   // array for number of passenger destined for that floor
    private Building building;                      // building

    /* CONSTRUCTOR */
    public Elevator(Building building)
    {
        this.building = building;
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
        if (currentFloor == building.getFloors())
        {
            goingUp = false;                       // if top floor, has to go down)
        }
        else if (currentFloor == 1)                // if ground floor, has to go up
        {
            goingUp = true;
        }

        // unload passenger
        unloadPassenger(currentFloor);

        // check if there is incoming passenger

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

            // update nPassengers
            nPassengers++;
        }
    }

    // define unloadPassenger() which let passenger(s) destined for that floor out
    private void unloadPassenger(int floor)
    {
        // get the number of passengers who want to get out
        int passenger_out = stopRequested[floor - 1];

        // update stopRequested array (reset to zero)
        stopRequested[floor - 1] = 0;

        // update nPassengers
        nPassengers = nPassengers - passenger_out;
    }

    // define currentFloor() to return floor number
    public int currentFloor()
    {
        return currentFloor;
    }

    // define passengers() to return number of passengers in elevator
    public int passengers()
    {
        return nPassengers;
    }


    // define toString() that indicates the number of passengers on board and current floor
    public String toString()
    {
        return "Floor " + Integer.toString(currentFloor) + ": " + Integer.toString(nPassengers) + " passengers";
    }

}
