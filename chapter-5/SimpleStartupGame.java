import java.util.Scanner;

public class SimpleStartupGame{
    

    public static void main(String[] args){
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        SimpleStartup startup = new SimpleStartup();
        int randomNum = (int) (Math.random() * 5);
        int[] numbers = {randomNum, randomNum +1, randomNum + 2};
        startup.setLocationCells(numbers);
        boolean isAlive = true;
        while(isAlive == true){
            int guess = helper.getUserInput("enter a number");
            String result = startup.checkYourself(guess);
            numOfGuesses++;
            if (result == "kill"){
                System.out.println("You made " + numOfGuesses + " 1Guesses");
                isAlive = false;
            }
        }
    }


}
class SimpleStartup{
    private int[] locationCells;
    private int numOfHits;

    public String checkYourself(int guess){
        String result = "miss";
        for (int cell : locationCells) {
            if (guess == cell) {
                result = "hit";
                numOfHits ++;
                break;
            }
        }
        if (numOfHits == locationCells.length) {
            result = "kill";       
        }
        System.out.println(result);
        return result;
    }

    public void setLocationCells(int[] loc){
        locationCells = loc;
    }
    public int getUserInput(String question){
        return 2;
    }
}

class GameHelper{
    public int getUserInput(String prompt){
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

