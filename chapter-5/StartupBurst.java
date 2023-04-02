import java.util.Scanner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class StartupBurst {

    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

    public static void main(String[] args){
        StartupBurst game = new StartupBurst();
        game.setUpGame();
        game.startPlaying();
    }

    void setUpGame(){

        Startup one = new Startup();
        one.setName("Puniez");
        startups.add(one);
        Startup two = new Startup();
        two.setName("Hacqi");
        startups.add(two);
        Startup three = new Startup();
        three.setName("Cabista");
        startups.add(three);

        System.out.println("Your goal is to sink three Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for(Startup item : startups){
            ArrayList<String> loc = helper.placeStartup(3);
            item.setLocationCells(loc);
        }

    }

    void startPlaying(){
        while (!startups.isEmpty()){
            String guess = helper.getUserInput("Make a guess:");
            checkUserGuess(guess);      
        }
        finishGame();

    }

    void checkUserGuess(String guess){
        numOfGuesses++;
        String result = "miss";
        for(Startup item : startups){
            result = item.checkYourself(guess);
            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                startups.remove(item);
                break;
            }
        }
        System.out.println(result);
    }

    void finishGame(){
        System.out.println("All Startups are dead! Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }

}
class Startup{

    private ArrayList<String> locationCells;
    private String name;

    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput);

        if (index >= 0) {
            locationCells.remove(index);

            if (locationCells.isEmpty()) {
                result = "kill";  
                System.out.println("Ouch! You sunk " + name + " : ( ");   
            } else {
                result = "hit";
            }
          
        }
        return result;
    }

    public void setLocationCells(ArrayList<String> locs){
        locationCells = locs;
    }
    public void setName(String newName){
        name = newName;
    }
}

class GameHelper{
    private static final String ALPHABET = "abcdefg";
    private static final int GRID_LENGTH = 7;
    private static final int GRID_SIZE = 49;
    private static final int MAX_ATTEMPTS = 200;
    static final int HORIZONTAL_INCREMENT = 1;
    static final int VERTICAL_INCREMENT = GRID_LENGTH;

    private final int[] grid = new int[GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String prompt){
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    public ArrayList<String> placeStartup(int startupSize){
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = getIncrement();

        while (!success & attempts++ < MAX_ATTEMPTS){
            int location = random.nextInt(GRID_SIZE);

            for (int i=0;i < startupCoords.length;i++){
                startupCoords[i] = location;
                location += increment;
            }
            // System.out.println("Trying: " + Arrays.toString(startupCoords));

            if (startupFits(startupCoords, increment)){
                success = coordsAvailable(startupCoords);
            }
        }
        savePositionToGrid(startupCoords);
        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        // System.out.println("Placed at: "+ alphaCells);
        return alphaCells;
    }
    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if(increment == HORIZONTAL_INCREMENT){
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < GRID_SIZE;
        }
    }
    private boolean coordsAvailable(int[] startCoords) {
        for (int coord : startCoords){
            if (grid[coord] != 0){
                // System.out.println("position: " + coord + " already taken.");
                return false;
            }
        }
        return true;
    }
    private void savePositionToGrid(int[] startCoords) {
        for (int index : startCoords){
            grid[index] = 1;

        }
    }
    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        for (int index : startupCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }
    private String getAlphaCoordsFromIndex(int index){
        int row = calcRowFromIndex(index);
        int column = index % GRID_LENGTH;
        String letter = ALPHABET.substring(column, column + 1);
        return letter + row;
    }
    private int calcRowFromIndex(int index){
        return index / GRID_LENGTH;
    }
    private int getIncrement() {
        if (startupCount % 2 == 0){
            return HORIZONTAL_INCREMENT;
        } else {
            return VERTICAL_INCREMENT;
        }
        
    }
}

class StartupGameTest{

    void test(){
        StartupBurst testGame = new StartupBurst();
        testGame.setUpGame();
        testGame.startPlaying();
        
    }
}