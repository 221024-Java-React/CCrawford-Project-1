package Labs15;

public class Lab2 {
    
    public static void main(String[] args) {
        
        System.out.println(add(15, 32));
        System.out.println(sub(15, 32));
        System.out.println(multiply(15.15, 32.32));
        System.out.println(divide(15.15, 32.32));

    }

    public static int add(int x, int y){
        return x + y;
    }

    public static int sub(int x, int y){
        return x - y;
    }

    public static double multiply(double x, double y){
        return x * y;
    }

    public static double divide(double x, double y){
        return x / y;
    }
}
