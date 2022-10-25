package Labs;

public class Lab3 {
    
    public static void main(String[] args) {
        
        System.out.println(replaceAllSpaces("This is a test"));
        System.out.println(containsWord("This is test # 2", "is"));
        for(String i : splitPhoneNumber("111-222-333")){
            System.out.println(i);
        }

    }

    public static String replaceAllSpaces(String x){
        return x.replace(" ", "-");
    }

    public static boolean containsWord(String x, String y){
        return x.contains(y);
    }

    public static String[] splitPhoneNumber(String x){
        return x.split("-");
    }
}
