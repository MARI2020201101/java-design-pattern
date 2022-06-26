package command;

class BankAccount {

    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount){
        balance+=amount;
        System.out.println("deposited amount = " + amount + " , " +
                "current balance = "+ balance);
    }

    public void withdraw(int amount){
        if(balance - amount >= overdraftLimit){
            balance -= amount;
            System.out.println("withdrew amount = " + amount + " , " +
                    "current balance = "+ balance);
        }
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                ", overdraftLimit=" + overdraftLimit +
                '}';
    }
}
interface Command{
    void call();
}
class BankAccountCommand implements Command{
    private BankAccount account;

    public enum Action{
        DEPOSIT, WITHDRAW;
    }
    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {
        switch (action){
            case DEPOSIT:
                account.deposit(amount);
                break;
            case WITHDRAW:
                account.withdraw(amount);
                break;
        }
    }
}
class Demo{
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccountCommand command = new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 1000);
        BankAccountCommand command2 = new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 100);

        command.call();
        command2.call();
        System.out.println(bankAccount);
    }
}