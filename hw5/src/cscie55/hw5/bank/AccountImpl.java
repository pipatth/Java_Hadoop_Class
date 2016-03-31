package cscie55.hw5.bank;

/**
 * Created by pipat on 3/12/16.
 */
public class AccountImpl implements Account
{
    /* FIELDS */
    private int id;                                 // account id
    private long balance;                           // $ balance


    /* CONSTRUCTOR */
    public AccountImpl(int i)
    {
        // create a new account with id and zero balance
        id = i;
        balance = 0;
    }


    /* PUBLIC METHODS */

    // getter for id
    public int id()
    {
        return id;
    }

    // getter for balance
    public long balance()
    {
        return balance;
    }

    // method to deposit
    public void deposit(long amount)
    {
        // throw exception if amount <= 0
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Cannot deposit negative or zero amount");
        }
        else
        {
            balance += amount;
        }
    }

    // method to withdraw
    public void withdraw(long amount) throws InsufficientFundsException
    {
        // throw exception if amount <= 0
        if (amount <= 0)
        {
            throw new IllegalArgumentException("Cannot withdraw negative or zero amount");
        }
        // throw exception if amount > balance
        else if (amount > balance)
        {
            throw new InsufficientFundsException(this, amount);
        }
        else
        {
            balance -= amount;
        }
    }
}
