package cscie55.hw5.bank;

import cscie55.hw5.bank.command.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by pipat on 3/30/16.
 */
public class BankServerImpl implements BankServer
{
    /* FIELDS */
    public Queue<Command> commandQueue;                                 // queue containing commands to be execute
    public ArrayList<CommandExecutionThread> commandExecutionThreads;   // array of threads


    /* CONSTRUCTOR */
    public BankServerImpl()
    {
        commandQueue = new LinkedList<Command>();                           // create commandQueue using linkedlist
        commandExecutionThreads = new ArrayList<CommandExecutionThread>();  // create array of threads
    }


    /* PUBLIC METHODS */

    // method to execute command (add command to the queue)
    @Override
    public void execute(Command command)
    {
        commandQueue.add(command);
    }

    // method to stop bankserver
    @Override
    public void stop() throws InterruptedException
    {

    }

    // getter for sum of balances (execute Bank.totalBalances())
    @Override
    public long totalBalances()
    {

    }
}
