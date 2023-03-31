class hello
{
    // public static void main(String args[]) {    
    //     System.out.println("hello world");
    //     int x = 34;
    //     System.out.println(x);
    // }
    public static void main(String[] args){
        int orig = 42;
        hello x = new hello();
        int y = x.go(orig);
        System.out.print(orig + " " + y);
    }
    int go(int arg){
        arg = arg * 2;
        return arg;
    }
}