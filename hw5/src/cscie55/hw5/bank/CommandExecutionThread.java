package cscie55.hw5.bank;

import cscie55.hw5.bank.command.Command;
import java.util.Queue;

/**
 * Created by pipat on 3/30/16.
 */
public class CommandExecutionThread extends Thread
{
    /* FIELDS */
    private Bank bank;
    private Queue<Command> commandQueue;
    private boolean executeCommandInsideMonitor;


    /* CONSTRUCTORS */
    public CommandExecutionThread(Bank bank, Queue<Command> commandQueue, boolean executeCommandInsideMonitor)
    {
        this.bank = bank;
        this.commandQueue = commandQueue;
        this.executeCommandInsideMonitor = executeCommandInsideMonitor;
    }


    /* PUBLIC METHODS */

    // method to run
    @Override
    public void run()
    {
        while (true)
        {
            Command cmd;
            do {
                synchronized (commandQueue)
                {
                    cmd = commandQueue.poll();
                    if (cmd == null)
                    {
                        try {
                            commandQueue.wait();
                        } catch (InterruptedException e) { }
                    }
                }
            } while (cmd == null);

            if (cmd.isStop())
            {
                break;
            }

            try {
                if (executeCommandInsideMonitor)
                {
                    synchronized (commandQueue)
                    {
                        cmd.execute(bank);
                    }
                }
                else
                {
                    cmd.execute(bank);
                }
            } catch (InsufficientFundsException e) { }
        }



//        synchronized (commandQueue)
//        {
//            // if queue is empty, surrender the monitor
//            while (commandQueue.isEmpty())
//            {
//                try {
//                    // acquire queue when someone puts something on it
//                    commandQueue.wait();
//                } catch (InterruptedException e) { }
//            }
//
//            // if queue is not empty, executing until get stop()
//            for (Command command : commandQueue)
//            {
//                if (command.isStop())                       // catch a stop command
//                {
//                    break;
//                }
//                else                                        // else keep execute command
//                {
//                    try {
//                        command.execute(bank);
//                    } catch (InsufficientFundsException e) { }
//                }
//            }
//        }
    }
}
