package Labs7;

import Labs6.PremiumCustomer;

public class Lab7 {
    public static void main(String[] args) {
        PremiumCustomer jill = new PremiumCustomer("Jill", 100, "", 0, 12341, 12);

        jill.addToCart("Pizza", 110);
        System.out.println(jill.toString());
        
        try {
            jill.buy();
        } catch (OverBalanceException e) {
            System.out.println(e);
        }

        System.out.println(jill.toString());
    }
}
