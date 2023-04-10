public class TestBox{
    private Integer i;
    private int j;
    static int x;
    TestBox(){
        x++;
    }
    public static void main(String[] args) {
        TestBox t = new TestBox();
        t.go();
        TestBox t2 = new TestBox();
        t2.go();
        TestBox t3 = new TestBox();
        t3.go();
    }

    public static void go(){
        
        System.out.println(x);
 
    }
}