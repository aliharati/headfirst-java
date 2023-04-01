// PSEUDO CODE:

// SimpleStartup class:
// Methods:

// METHOD checkYourself(int userGuess)
// 	GET an intiger equvelant from user input and copare it with values in locationCells array
// 	REPEAT with each item in locationCells:
// 		IF the user argument matches:
// 			INCREMENT the number of hits
// 			//find out if it was the last location cell:
// 			IF number of hits is 3, return "kill"
// 			ELSE return "hit"
// 			END IF
// 		ELSE the user did not guess correctly:
// 			RETURN "miss"
// 		END IF
// 	END REPEAT
// END METHOD

// METHOD SetLocationCells(int[] loc)
// 	Get the cell locations as an int array parameter
// 	Assign the cell location parameters to the cell location instance variable
// End Method

// SimpleStartupGame class:
// Methods:

// METHOD main()
// DECLARE an int variable to hold the number of user guesses, named numOfGuesses, and set it to 0
// MAKE an instance of SimpleStartup class and name it startup
// COMPUTE a random number between 0 and 4 that will be the starting location cell position
// MAKE an int array with 3 ints using the randomly generated number, that number incremented by 1, and that number incremented by 2 (example: 3,4,5)
// INVOKE the setLocationCells() method on the SimpleStartup instance
// DECLARE a boolean variable called IsAlive with value true.
// WHILE the IsAlive is true:
// 	GET user input from the command line
// 	INVOKE the checkYourself() method on the SimpleStartup instance using the user input as the argument to variable check.
// 	INCREMENT numOfGuesses variable
// 	IF check value is "kill"
// 		PRINT the number of user guesses 
// 		SET false to IsAlive variable
// 	END iF
// End WHILE
// END METHOD

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

