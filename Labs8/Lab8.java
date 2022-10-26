package Labs8;

public class Lab8 {
    
    public String reverse(String word){
        StringBuilder newWord = new StringBuilder();
        for(int i = word.length() - 1; i > -1; i--){
            newWord.append(word.charAt(i));
        }
        return newWord.toString();
    }

    // Missing JUnit
}
