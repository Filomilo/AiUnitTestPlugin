import org.junit.Test ;
import java.util.Arrays ;
public class CalculatorTest{
@Test void divideDouble(){

        assertEquals(2.4, Calculator.divide(6, 2.5), 0.0001);
        assertThrows(ArithmeticException.class, () -> Calculator.divide(-1.0, 0.0));
    }
}

