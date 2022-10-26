package Labs6;

import Labs7.OverBalanceException;

public class PremiumCustomer extends Customer implements Premium {

    private int vipCard;

    private int years;

    public PremiumCustomer(String name, double balance, String items, double cartCost, int vipCard, int years) {
        super(name, balance, items, cartCost);
        this.vipCard = vipCard;
        this.years = years;
    }

    @Override
    public double discountPrice(double startingPrice) {
        return startingPrice / 0.85;
    }

    @Override
    public void buy() {
        this.items = "";
        if((this.balance - this.discountPrice(this.cartCost)) < 0){
            throw new OverBalanceException("Not enough money");
        } else {
            this.balance = this.balance - this.discountPrice(this.cartCost);
        }
        this.cartCost = 0.0;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getItems(){
        return this.items;
    }

    public void setItems(String items){
        this.items = items;
    }

    public double getCartCost(){
        return this.cartCost;
    }

    public void setCartCost(double cartCost){
        this.cartCost = cartCost;
    }

    public int getVipCard(){
        return this.vipCard;
    }

    public void setVipCard(int vipCard){
        this.vipCard = vipCard;
    }

    public int getYears(){
        return this.years;
    }

    public void setYears(int years){
        this.years = years;
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() +
                ", Balance: $" + String.format("%.2f", this.getBalance()) +
                ", Cart: " + this.getItems() +
                ", Cart Cost: $" + String.format("%.2f", this.getCartCost()) +
                ", Title: " + Premium.title +
                ", Vip Card: " + this.getVipCard() +
                ", Years: " + this.getYears();
    }
    
}
