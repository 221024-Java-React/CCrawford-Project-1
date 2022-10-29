package Labs9;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Lab9Tests {

    char[] arrayOne = {'A', 'A', 'C', 'F'};
    char[] arrayTwo = {'A', 'B', 'C', 'D'};
    int[] bonusesOne = {5000, 5000, 1000, 0};
    int[] bonusesTwo = {5000, 2500, 1000, 500};
    
    @Test
    public void positiveTestOne(){
        assertArrayEquals(bonusesOne, new Lab9().calculateBonuses(arrayOne));
    }

    @Test
    public void positiveTestTwo(){
        assertArrayEquals(bonusesTwo, new Lab9().calculateBonuses(arrayTwo));
    }

    @Test
    public void negativeTestOne(){
        assertArrayEquals(bonusesTwo, new Lab9().calculateBonuses(arrayOne));
    }

    @Test
    public void negativeTestTwo(){
        assertArrayEquals(bonusesOne, new Lab9().calculateBonuses(arrayTwo));
    }
}
