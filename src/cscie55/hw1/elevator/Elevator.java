package cscie55.hw1.elevator;

public class Elevator
{
    // define a constant for the number of floors
    public static final int FLOORS = 7;

    // define current floor (start with ground floor) and direction of travel (going up)
    int current_floor = 1;
    boolean going_up = true;

    // define number of passengers in the elevator
    int n_passengers;

    // define array for number of passenger destined for that floor
    int [] stop_requested_n = new int[7];

    // define move() method that modifies elevator's state
    public void move()
    {
        // modify current floor
        // move up or down
        if (going_up)
        {
            current_floor++;
        }
        else
        {
            current_floor--;
        }

        // modify direction of travel
        // if top floor, has to go down
        if (current_floor == FLOORS)
        {
            going_up = false;
        }
        // if ground floor, has to go up
        else if (current_floor == 1)
        {
            going_up = true;
        }

        // unload passenger
        unloadPassenger(current_floor);

    }

    // define boardPassenger(int floor) which adds a passenger destined for the indicated floor
    public void boardPassenger(int floor)
    {
        // update stop_requested_n array (add one)
        stop_requested_n[floor - 1]++;

        // update n_passengers
        n_passengers++;

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

    // define toString() that indicates the number of passengers on board and current floor
    public String toString()
    {
        return "Floor " + Integer.toString(current_floor) + ": " + Integer.toString(n_passengers) + " passengers";
    }


    public static void main(String argv[])
    {
        System.out.println("This is an elavator class");
    }

}
