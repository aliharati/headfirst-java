import java.util.Scanner;
import java.util.ArrayList;

public class StartupBurst {


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
class Startup{

    private ArrayList<String> locationCells;

    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput)

        if (index >= 0) {
            result = "hit";
            locationCells.remove(index);
        }
        if (locationCells.isEmpty()) {
            result = "kill";       
        }
        System.out.println(result);
        return result;
    }

    public void setLocationCells(ArrayList<String> locs){
        locationCells = locs;
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