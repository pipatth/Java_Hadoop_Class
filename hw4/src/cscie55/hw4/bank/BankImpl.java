package cscie55.hw4.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pipat on 3/12/16.
 */
public class BankImpl implements Bank
{
    /* FIELDS */
    private Map<Integer, Account> accounts;                    // map of accounts


    /* CONSTRUCTOR */
    public BankImpl()
    {
        accounts = new HashMap<Integer, Account>();
    }


    /* PUBLIC METHODS */

    // method to add new account to list
    @Override
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

    // method to transfer without locking
    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException
    {
        // throw exception if account number is invalid
        if (!accounts.containsKey(fromId) || !accounts.containsKey(toId))
        {
            throw new IllegalArgumentException("Account number is invalid");
        }
        else
        {
            // transfer without synchronizing
            Account accFrom = accounts.get(fromId);
            Account accTo = accounts.get(toId);

            // transfer only when there is a sufficient fund in source account
            try {
                accFrom.withdraw(amount);
            } catch (InsufficientFundsException insufficientFundsException) {
                System.out.println(insufficientFundsException);
                return;
            }
            accTo.deposit(amount);
        }
    }

    // method to transfer with locking all bank accounts *****
    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException
    {

    }

    // method to transfer with locking two accounts *****
    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException
    {

    }

    // getter for sum of balances
    @Override
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
    @Override
    public int numberOfAccounts()
    {
        return accounts.size();
    }

}
