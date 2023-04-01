public class SimpleStartupTestDrive{
    public static void main(String[] args){
        SimpleStartup dot = new SimpleStartup();
        int[] loc = {2,3,4};
        dot.setLocationCells(loc);

        int guess = 2;
        String result = dot.checkYourself(guess);
        String testResult = "failed";
        if(result == "hit"){
            testResult = "passed";
        }
        System.out.print(testResult);
    }
}


class SimpleStartup{
    int[] locationCells;
    int numOfHits;

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
}

