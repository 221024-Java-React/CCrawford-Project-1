package Labs9;

public class Lab9 {
    
    public int[] calculateBonuses(char[] bonuses){
        int i = 0;
        int[] newArray = new int[bonuses.length];
        do{
            switch (bonuses[i]) {
                case 'A':
                    newArray[i] = 5000;
                    break;

                case 'B':
                    newArray[i] = 2500;
                    break;

                case 'C':
                    newArray[i] = 1000;
                    break;

                case 'D':
                newArray[i] = 500;
                    break;

                case 'F':
                newArray[i] = 0;
                    break;

                default:
                    break;
            }
            i++;
        } while(i < bonuses.length);

        return newArray;
    }
}
