package Labs6;

public class Lab6 {
    
    public static void main(String[] args) {
        
        PremiumCustomer john = new PremiumCustomer("John", 100.00, "", 0, 432123, 7);

        System.out.println(john.toString());

        john.addToCart("Car", 15.55);
        john.addToCart("Pizza", 5.32);
        
        System.out.println(john.toString());
        
        john.buy();

        System.out.println(john.toString());
    }
}
