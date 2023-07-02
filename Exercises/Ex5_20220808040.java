import java.util.ArrayList;
public class Ex5_20220808040 {
    public static void main(String[] args)throws Exception{

    }
}
class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(double balance){
        super("Wrong balance:"+balance);
    }
    public InsufficientFundsException(double balance,double amount){
        super("Required amount is "+amount+" but only "+balance+" remaining");
    }
}
class AccountAlreadyExistsException extends RuntimeException{
    public AccountAlreadyExistsException(String accountNumber){
        super("Account number "+accountNumber+" already exists");
    }
}
class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String accountNumber){
        super("Account number "+accountNumber+" is not found");
    }
}
class InvalidTransactionException extends Exception{
    public InvalidTransactionException(double amount){
        super("Invalid amount:"+amount);
    }
    public InvalidTransactionException(Exception e,String message){
        super(message+":\n\t"+e.getMessage());
    }
}
class Account{
    private String accountNumber;
    private double balance;
    public Account(String accountNumber,double balance)throws Exception{
        this.accountNumber=accountNumber;
        if(balance<0){
            throw new InsufficientFundsException(balance);
        }
        else
            this.balance=balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount)throws Exception{
        balance+=amount;
        if(amount<0){
            throw new  InvalidTransactionException(amount);
        }
    }
    public void withdraw(double amount)throws Exception{
        balance-=amount;
        if(amount<0){
            throw new InvalidTransactionException(amount);
        }
        if(balance<amount){
            throw new InsufficientFundsException(balance);
        }
    }
    @Override
    public String toString(){
        return "Account: "+accountNumber+",Balance: "+balance;
    }
}
class Customer {
    private String name;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public Customer(String name) {
        this.name = name;
    }

    public Account getAccount(String accountNumber) throws Exception {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber))
                return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public void addAccount(Account account) throws Exception {
        String accountNumber = account.getAccountNumber();
        try {
            getAccount(accountNumber);
        } catch (AccountAlreadyExistsException e) {
            accounts.add(account);
            throw new AccountAlreadyExistsException(accountNumber);
        } finally {
            System.out.println(name);
            System.out.println("Added account: " + accountNumber + " with " + account.getBalance());
        }
    }

    public void removeAccount(String accountNumber) {
        try{ for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accounts.remove(account);
            }
        }
        }catch (AccountNotFoundException e){
            throw new AccountNotFoundException(accountNumber);
        }
    }
    public void transfer(Account fromAccount,Account toAccount,double amount)throws Exception{
        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }
        catch (InvalidTransactionException e){
            throw new InvalidTransactionException(e," cannot transfer"+"funds from account"+fromAccount+" to account "
                    +toAccount);
        }
    }
    @Override
    public String toString(){
        String string = "Customer " + name + ":\n";
        for (Account account : accounts) {
            string+= "\t" + account.toString() + "\n";
        }
        return string;
    }
}