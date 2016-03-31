package cscie55.hw5.bank;

import cscie55.hw5.bank.command.Command;
import java.util.Queue;

/**
 * Created by pipat on 3/30/16.
 */
public class CommandExecutionThread extends Thread
{
    /* FIELDS */
    public Bank bank;
    public Queue<Command> commandQueue;
    public boolean executeCommandInsideMonitor;


    /* CONSTRUCTORS */
    public CommandExecutionThread(Bank bank, Queue<Command> commandQueue, boolean executeCommandInsideMonitor)
    {
        super();
        this.bank = bank;
        this.commandQueue = commandQueue;
        this.executeCommandInsideMonitor = executeCommandInsideMonitor;
    }


    /* PUBLIC METHODS */

    // method to run
    @Override
    public void run()
    {
        // remove first command from queue and execute the command
        Command command = commandQueue.remove();
        if (command.isStop())           // catch a stop command
        {
            return;
        }
        else
        {
            try {
                command.execute(bank);
            } catch (InsufficientFundsException insufficientFundsException) {
                return;
            }
        }


    }



}
