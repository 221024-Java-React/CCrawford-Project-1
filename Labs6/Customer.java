package Labs6;

public abstract class Customer {

    public String name;

    public double balance;

    public String items;

    public double cartCost;
    
    public Customer(String name, double balance, String items, double cartCost){
        this.name = name;
        this.balance = balance;
        this.items = items;
        this.cartCost = cartCost;
    }

    public void addToCart(String item, double cost){
        this.items = this.items + " " + item;
        this.cartCost = this.cartCost + cost;
    }

    public abstract void buy();
}
