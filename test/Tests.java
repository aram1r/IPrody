import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class Tests{

    private static int[] array1;
    private static int[] array2;
    private static int[] array3;
    ClassForTests classForTests = new ClassForTests();
    ;

    @BeforeAll
    public static void setUpClass() {
        array1 = new int[10];
        Random random = new Random();
        int randomNum;
        for (int i = 0; i < array1.length; i++) {
            randomNum = random.nextInt(2);
            if (randomNum == 0) {
                array1[i] = 1;
            } else {
                array1[i] = 4;
            }
        }
        array2 = new int[10];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = random.nextInt(10);
        }

        array3 = new int[10];
        for (int i = 0; i < array3.length; i++) {
            randomNum = random.nextInt(10);
            while (randomNum == 1 || randomNum == 4) {
                randomNum = random.nextInt(10);
            }
            array3[i] = randomNum;
        }
    }

    //Тесты первого метода
    @Test
    @DisplayName("Метод не выбросит исключение")
    public void test1() {
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + " ");
        }
        assertDoesNotThrow(() -> classForTests.extractNewArray(array1));
    }

    @Test
    @DisplayName("Выкинет исключение")
    public void test2() {
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i] + " ");
        }
        assertThrowsExactly(RuntimeException.class, () -> classForTests.extractNewArray(array3));
    }

    @Test
    @DisplayName("Сработал после 1го вхождения")
    public void test3() {
        int[] arrayForTest = new int[6];
        arrayForTest[0] = 1;
        arrayForTest[1] = 4;
        arrayForTest[2] = 5;
        arrayForTest[3] = 7;
        arrayForTest[4] = 8;
        arrayForTest[5] = 9;

        int[] result = classForTests.extractNewArray(arrayForTest);
        assertEquals(5, result.length);
    }

    @Test
    @DisplayName("Проверка нулевым значением")
    public void test4() {
        int[] arrayForTest = null;
        assertThrowsExactly(NullPointerException.class, () -> classForTests.extractNewArray(arrayForTest));
    }

    //Тесты для второго метода
    @Test
    @DisplayName("Метод сработает с результатом true")
    public void checkForNumbersTest1() {
        assertTrue(classForTests.checkForNumbers(array1));
    }

    @Test
    @DisplayName("Метод сработает с результатом false")
    public void checkForNumbersTest2() {
        assertFalse(classForTests.checkForNumbers(array3));
    }

    @Test
    @DisplayName("Выкинет NullPointerException")
    public void checkForNumbersTest3() {
        assertThrowsExactly(NullPointerException.class, () -> classForTests.checkForNumbers(null));
    }

    @Test
    @DisplayName("Проверка другим массивом на false")
    public void checkForNumbersTest4() {
        assertFalse(classForTests.checkForNumbers(array2));
    }
}
