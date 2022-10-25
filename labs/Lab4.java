package Labs;

public class Lab4 {
    
    public static void main(String[] args) {
        
        int[] numbers = new int[100];

        for(int i = 0; i < numbers.length; i++){
            numbers[i] = i + 1;
        }
        
        int evenSum = 0;
        int oddSum = 0;

        int index = 0;
        while(index < numbers.length){
            if(numbers[index] % 2 == 0){
                evenSum += numbers[index];
            } else {
                oddSum += numbers[index];
            }
            index++;
        }

        System.out.println("evenSum: " + evenSum);
        System.out.println("oddSum: " + oddSum);
    }
}
