// public class SimpleStartupGame{
//     public static void main(String[] args){
//         Game newGame = new Game();
//         newGame.startGame();
//     }
    
// }

// class Game{    

//     public static void startGame(String[] args){
//         int guesses = 0;
//         Startup startup = new Startup(); 
//         startup.setCells(startup.createRow());
//         int[] cells = startup.giveCells();
//         while (cells.length > 0){
//             guesses += 1;
//             String guess = System.in.input()
//             if int(guess) is in cells{
//                 remove.guess
//                 System.out.println("hit")
//             }
//             else{
//                 System.out.println("miss")
//             }
        
//         }
//         System.out.print("You took " + guesses + "guesses." )
//     }

//     static void removeCell(){
        
//     }
//     static void removeStartup(){

//     }
// }

// class Startup{
//     private int[] cells;
//     public int[] createRow(){
//         java.util.Random randomGenerator= new java.util.Random();
//         int firstPlace = randomGenerator.nextInt(4);
//         int[] places = {firstPlace, firstPlace + 1, firstPlace + 2};
//         return places;
//     }

//     public int[] giveCells(){
//         return cells;
//     }

//     public void setCells(int[] args){
//         cells = args;
//     }
// }

public class SimpleStartupGame{
    int[] locationCells;
    int numOfHits;
    public static void main(String[] args){

    }
    
    public String checkYourself(int guess){
        return "hit";

    }

    public void setLocationCells(int[] loc){
        
    }
}