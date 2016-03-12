package cscie55.hw4.bank;

import java.util.ArrayList;

/**
 * Created by pipat on 3/12/16.
 */
public class BankImpl implements Bank
{
    /* FIELDS */
    private ArrayList<Account> accounts;                    // list of accounts


    /* CONSTRUCTOR */
    public BankImpl()
    {
        accounts = new ArrayList<Account>();

    }



    /* PUBLIC METHODS */

    // method to add new account to list
    public void addAccount(Account account) throws DuplicateAccountException
    {
        accounts.add(account);
    }

    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException
    {

    }

    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException
    {

    }

    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException
    {

    }

    // getter for sum of balances
    public long totalBalances()
    {
        long total = 0;
        for (Account account : accounts)
        {
            total = total + account.balance();
        }
        return total;
    }

    // getter for array list size
    public int numberOfAccounts()
    {
        return accounts.size();
    }

}
