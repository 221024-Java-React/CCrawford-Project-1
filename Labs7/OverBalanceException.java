package Labs7;

public class OverBalanceException extends RuntimeException {
    public OverBalanceException(String errorMessage){
        super(errorMessage);
    }
    public OverBalanceException(){}
}
