package Labs8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lab8Tests {
    
    @Test
    public void positiveTestOne(){
        assertEquals("nekcihC", new Lab8().reverse("Chicken"));
    }

    @Test
    public void positiveTestTwo(){
        assertEquals("moorV og raC", new Lab8().reverse("Car go Vroom"));
    }

    @Test
    public void negativeTestOne(){
        assertEquals("Chicken", new Lab8().reverse("Chicken"));
    }

    @Test
    public void negativeTestTwo(){
        assertEquals("Car go Vroom", new Lab8().reverse("Car go Vroom"));
    }
}
