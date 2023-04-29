import java.util.concurrent.*;

public class RyanAndMonicaTest{
    public static void main(String[] args) {
        BankAccount account = new BankAccount(140);
        RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", account, 50);
        RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", account, 100);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(ryan);
        executor.execute(monica);
        executor.shutdown();

    }
}

class RyanAndMonicaJob implements Runnable{
    private BankAccount account;
    private String name;
    private int amountToSpend;

    public RyanAndMonicaJob(String n,BankAccount ac,int sp){
        this.name = n;
        this.account = ac;
        this.amountToSpend = sp;
    }
    public void run(){
        goShopping(amountToSpend);
    }
    public void goShopping(int amount){
        if (amount <= account.getBalance()){
            System.out.println(name + " is about to spend");
            account.spend(amount);
            System.out.println(name + " finishes spending");
        }else{
            System.out.println("amount exceeds the allowed limit for " + name);
        }
    }

}

class BankAccount {
    private int balance;

    public BankAccount(int b){
        balance = b;
    }

    public int getBalance(){
        return balance;
    }
    public void spend(int amount){
        balance -= amount;
        if (balance < 0){
            System.out.println("Overdrawn!");
        }
    }
}