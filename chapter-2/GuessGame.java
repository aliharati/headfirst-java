public class GuessGame {
    public void startGame(){
        java.util.Random randomNum= new java.util.Random();
        int number = randomNum.nextInt(10);
        System.out.println("let the game begin");
        System.out.println("the number is" + number);
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();

        boolean noRightNum = true;
        while (noRightNum == true) {
            int guessp1 = p1.guess();      
            int guessp2 = p2.guess();
            int guessp3 = p3.guess();
            System.out.println("player 1 guessed" + guessp1);
            System.out.println("player 2 guessed" + guessp2);
            System.out.println("player 3 guessed" + guessp3);
            
            
            if (guessp1 == number){
                System.out.println("Player 1 guessed correct" + guessp1);
                noRightNum = false;
            }
            else if (guessp2 == number){
                System.out.println("Player 2 guessed correct" + guessp2);
                noRightNum = false;
            }
            else if (guessp3 == number){
                System.out.println("Player 3 guessed correct" + guessp3);
                noRightNum = false;
            }
        }



    }
}

class GameLauncher {
    public static void main(String[] args){
        GuessGame instance = new GuessGame();
        instance.startGame();
    
    }
}

class Player{
    public int guess(){
        java.util.Random randomNum = new java.util.Random();
        int guess = randomNum.nextInt(10);
        return guess;
    }
}
