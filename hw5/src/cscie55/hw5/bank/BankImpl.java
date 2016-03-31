package cscie55.hw5.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pipat on 3/12/16.
 */
public class BankImpl implements Bank
{
    /* FIELDS */
    private Map<Integer, Account> accounts;                 // map of account ids and accounts
//    private static Object lock = new Object();              // lock

    /* CONSTRUCTOR */
    public BankImpl()
    {
        accounts = new HashMap<Integer, Account>();
    }


    /* PUBLIC METHODS */

    // method to add new account to list
    public void addAccount(Account account) throws DuplicateAccountException
    {
        // throw exception if account number exists
        if (accounts.containsValue(account))
        {
            throw new DuplicateAccountException(account.id());
        }
        else
        {
            // add account
            accounts.put(account.id(), account);
        }
    }

    // method to transfer with locking two accounts
    public void transfer(int fromId, int toId, long amount) throws InsufficientFundsException
    {
        // throw exception if account number is invalid
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId))
        {
            throw new IllegalArgumentException("Account number is invalid");
        }
        else if (fromId == toId)
        {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        else {
            // transfer after locking accounts
            Account accFrom = accounts.get(fromId);
            Account accTo = accounts.get(toId);
            Account firstLock;
            Account secondLock;

            // create sequence of locks
            if (fromId < toId)
            {
                firstLock = accFrom;
                secondLock = accTo;
            }
            else
            {
                firstLock = accTo;
                secondLock = accFrom;
            }

            // synchronize two accounts that involves in the transaction, locking account according to sequence
            synchronized (firstLock)
            {
                synchronized (secondLock)
                {
                    // transfer only when there is a sufficient fund in source account
                    try {
                        accFrom.withdraw(amount);
                    } catch (InsufficientFundsException insufficientFundsException) {
                        return;
                    }
                    accTo.deposit(amount);
                }
            }
        }
    }

    // method to deposit
    public void deposit(int accountId, long amount)
    {
        // throw exception if account number is invalid
        if (!accounts.containsKey(accountId))
        {
            throw new IllegalArgumentException("Account number is invalid");
        }
        else
        // deposit
        {
            accounts.get(accountId).deposit(amount);
        }
    }

    // getter for sum of balances
    public long totalBalances()
    {
        long total = 0;
        for (Account account : accounts.values())
        {
            total += account.balance();
        }
        return total;
    }

    // getter for array list size
    public int numberOfAccounts()
    {
        return accounts.size();
    }

}
