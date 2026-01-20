import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest{

    @Test
    @DisplayName("Тест факториал 5")
    public void testFactorial() {
        assertEquals(120, Calculator.factorial(5));
    }

    @Test
    @DisplayName("Провальный тест")
    public void failureFactorial() {
        assertEquals(120, Calculator.factorial(0));
    }
}
