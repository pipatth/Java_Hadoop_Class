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
        Command command;
        while (true) {

            // execute inside synchronized block
            synchronized (commandQueue) {
                while (commandQueue.isEmpty()) {
                    try {
                        commandQueue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                command = commandQueue.remove();                // acquire queue when someone puts something on it

                if (command.isStop()) {                         // if command is not stop(), executing until get stop()
                    break;
                }

                if (executeCommandInsideMonitor) {
                    try {
                        command.execute(bank);
                    } catch (InsufficientFundsException e) {
                    }
                }
            }

            // execute outside synchronized block
            if (!executeCommandInsideMonitor) {
                try {
                    command.execute(bank);
                } catch (InsufficientFundsException e) {
                }
            }
        }
    }
}
