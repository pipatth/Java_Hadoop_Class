package cscie55.hw1.elevatortest;

import cscie55.hw1.elevator.Elevator;

public class ElevatorTest {

    public static void main(String argv[])
    {
        // create new elevator object
        Elevator elevator = new Elevator();

        // board 2 passengers to 3rd fl , 1 passenger to 5th fl
        elevator.boardPassenger(3);
        elevator.boardPassenger(3);
        elevator.boardPassenger(5);

        // move elevator to test
        // print out on ground fl
        System.out.println(elevator.toString());

        // move and print out
        for (int i = 1; i < elevator.FLOORS * 2 - 1; i++)
        {
            elevator.move();
            System.out.println(elevator.toString());
        }
    }
}
