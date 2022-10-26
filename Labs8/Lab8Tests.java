package Labs8;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lab8Tests {
    
    @Test
    public void positiveTestOne(){
        assertEquals(new Lab8().reverse("Chicken"), "nekcihC");
    }

    @Test
    public void positiveTestTwo(){
        assertEquals(new Lab8().reverse("Car go Vroom"), "moorV og raC");
    }

    @Test
    public void negativeTestOne(){
        assertEquals(new Lab8().reverse("Chicken"), "Chicken");
    }

    @Test
    public void negativeTestTwo(){
        assertEquals(new Lab8().reverse("Car go Vroom"), "Car go Vroom");
    }
}
