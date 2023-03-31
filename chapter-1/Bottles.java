public class Bottles {
    
    public static void main(String[] args) {
        int bottles = 10;
        String word = "bottles";
        while (bottles > 0){
            if (bottles == 1){
                word = "bottle";
            }
            System.out.println(bottles + " green " + word + " hanging on the wall,");
            System.out.println(bottles + " green " + word + " hanging on the wall,");
            System.out.println("And if one green bottle should accidentally fall,");
            System.out.println("There'll be " + (bottles - 1) +" green bottles hanging on the wall.");
            bottles = bottles - 1;
        }
        
    }
}
