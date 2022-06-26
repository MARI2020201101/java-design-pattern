package command;
class CommandV2 {
    enum Action {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;
    public Account account;

    public CommandV2(Action action, int amount) {
        this.action = action;
        this.amount = amount;
    }

    public CommandV2(Action action, int amount, Account account) {
        this.action = action;
        this.amount = amount;
        this.account = account;
    }

    public boolean call(){
        switch (action){
            case WITHDRAW:
                if(account.balance >= amount){
                    success = true;
                    account.process(this);
                }break;
            case DEPOSIT:
                success = true;
                account.process(this);
                break;
        }
        return success;
    }
}


class Account {
    public int balance;

    public void process(CommandV2 c) {
        if(c.success){
            switch (c.action){
                case DEPOSIT:deposit(c.amount);break;
                case WITHDRAW:withdraw(c.amount);break;
            }
        }

    }
    public void deposit(int amount){
        this.balance +=amount;
        System.out.println("deposit amount = "+amount +" , current balance = " +balance );
    }
    public void withdraw(int amount){
        this.balance -=amount;
        System.out.println("withdraw amount = "+amount +" , current balance = " +balance );

    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
class Exercise {
    public static void main(String[] args) {
        Account account = new Account();
        CommandV2 command = new CommandV2(CommandV2.Action.DEPOSIT,1000,account);
        CommandV2 command2 = new CommandV2(CommandV2.Action.WITHDRAW,1500,account);
        command.call();
        command2.call();
        System.out.println(account);
    }
}
