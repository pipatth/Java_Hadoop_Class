package cscie55.hw2;

public class Elevator
{
    /* FIELDS */
    public static final int CAPACITY = 10;          // elevator capacity
    private int current_floor = 1;                  // current floor (start with ground floor)
    private boolean going_up = true;                // direction of travel (going up)
    private int n_passengers;                       // number of passengers in the elevator
    private int [] stop_requested_n = new int[7];   // array for number of passenger destined for that floor
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
        if (going_up)
        {
            current_floor++;
        }
        else
        {
            current_floor--;
        }

        // modify direction of travel
        if (current_floor == building.getFloors())
        {
            going_up = false;                       // if top floor, has to go down)
        }
        else if (current_floor == 1)                // if ground floor, has to go up
        {
            going_up = true;
        }

        // unload passenger
        unloadPassenger(current_floor);

        // check if there is incoming passenger

    }

    // define boardPassenger(int floor) which adds a passenger destined for the indicated floor
    public void boardPassenger(int destinationFloorNumber) throws ElevatorFullException
    {
        // check if elevator is full, throw exception
        if (n_passengers == CAPACITY)
        {
            throw new ElevatorFullException();
        }
        else
        {
            // update stop_requested_n array (add one)
            stop_requested_n[destinationFloorNumber - 1]++;

            // update n_passengers
            n_passengers++;
        }
    }

    // define unloadPassenger() which let passenger(s) destined for that floor out
    private void unloadPassenger(int floor)
    {
        // get the number of passengers who want to get out
        int passenger_out = stop_requested_n[floor - 1];

        // update stop_requested_n array (reset to zero)
        stop_requested_n[floor - 1] = 0;

        // update n_passengers
        n_passengers = n_passengers - passenger_out;
    }

    // define currentFloor() to return floor number
    public int currentFloor()
    {
        return current_floor;
    }

    // define passengers() to return number of passengers in elevator
    public int passengers()
    {
        return n_passengers;
    }


    // define toString() that indicates the number of passengers on board and current floor
    public String toString()
    {
        return "Floor " + Integer.toString(current_floor) + ": " + Integer.toString(n_passengers) + " passengers";
    }

}
