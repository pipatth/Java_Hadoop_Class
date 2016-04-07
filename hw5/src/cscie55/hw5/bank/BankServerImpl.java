package cscie55.hw5.bank;

import cscie55.hw5.bank.command.Command;
import cscie55.hw5.bank.command.CommandStop;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by pipat on 3/30/16.
 */
public class BankServerImpl implements BankServer
{
    /* FIELDS */
    private Queue<Command> commandQueue;                                     // queue containing commands to be execute
    public CommandExecutionThread [] commandExecutionThreads;               // array of threads
    private Bank bank;
    private int nThreads;


    /* CONSTRUCTOR */
    public BankServerImpl(Bank bank, int nThreads, boolean executeCommandInsideMonitor)
    {
        commandQueue = new LinkedList<Command>();                           // create commandQueue using linkedlist
        commandExecutionThreads = new CommandExecutionThread[nThreads];     // create array of threads
        this.bank = bank;                                                   // bank
        this.nThreads = nThreads;

        // add new commandExecutionThreads into array
        for (int i = 0; i < nThreads; i++)
        {
            commandExecutionThreads[i] = new CommandExecutionThread(bank, commandQueue, executeCommandInsideMonitor);
        }

        // start threads
        for (CommandExecutionThread thread : commandExecutionThreads)
        {
            thread.start();
        }
    }


    /* PUBLIC METHODS */

    // method to execute command (add command to the queue)
    @Override
    public void execute(Command command)
    {
        synchronized (commandQueue)                                         // synchronized queue in every transaction
        {
            commandQueue.add(command);                                      // add command to queue
            commandQueue.notifyAll();                                       // notify that command is added to queue
        }
    }

    // method to stop bankserver (add stop() command to queue)
    @Override
    public void stop() throws InterruptedException
    {
        synchronized (commandQueue)                                         // synchronized queue in every transaction
        {
            for (int i = 0; i < nThreads; i++)
            {
                commandQueue.add(new CommandStop());                        // add stop command to queue for all threads
            }
            commandQueue.notifyAll();                                       // notify that stop is added to queue
        }

        // join threads
        for (CommandExecutionThread thread : commandExecutionThreads)
        {
            thread.join();
        }
    }

    // getter for sum of balances (execute Bank.totalBalances())
    @Override
    public long totalBalances()
    {
        return bank.totalBalances();
    }
}
