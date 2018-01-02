import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class AdderTest {
    @Test
    public void doRandomTest(){
        int a=10;
        int b=10;
        int realSum = 20;
        int expectedSum = new Addrer().add(a,b);
        assertEquals(realSum, expectedSum);
    }

    @Test
    public void doRandomTest1(){
        int a=20;
        int b=10;
        int realSum = 10;
        int expectedSum = new Addrer().sub(a,b);
        assertEquals(realSum, expectedSum);
    }
}
