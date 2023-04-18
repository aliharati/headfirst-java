import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class TestMap {
    private String title = "sd";
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Kathy", 42);
        scores.put("Bert", 343);
        scores.put("Skyler", 420);
        System.out.println(scores);
        System.out.println(scores.get("Bert"));
        List<Integer> nums = List.of(1,2,3,4,5,6,7,8);
        nums.sort((x,y) -> (int)x - (int) y);
        System.out.println(nums);

    }    

    public void kill(){

    }
}
