package framework.assertions;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class Assertions {

    public static <T> void equals(T expected, T actual) throws AssertException {
        boolean success = Objects.equals(expected, actual);
        throw new AssertException(new EqualsAssertResult<>(expected, actual, success));
    }

    public static void contains(String line, String textToFetch) throws AssertException {
        boolean success = line != null && textToFetch != null && line.contains(textToFetch);
        throw new AssertException(new ContainsAssertResult(line, textToFetch, success));
    }

    //a. Проверка нахождения подмассива в массиве.;
    public static <T> void contains(T[] current, T[] toContain) throws AssertException {
        boolean success = false;
        if (current != null && toContain != null) {
            if (toContain.length == 0) {
                success = true;
            } else {
                // Алгоритм поиска подмассива
                for (int i = 0; i <= current.length - toContain.length; i++) {
                    boolean mismatch = false;
                    for (int j = 0; j < toContain.length; j++) {
                        if (!Objects.equals(current[i + j], toContain[j])) {
                            mismatch = true;
                            break;
                        }
                    }
                    if (!mismatch) {
                        success = true;
                        break;
                    }
                }
            }
        }
        // Используем EqualsAssertResult для простоты или создайте специфичный ArrayContainsAssertResult
        throw new AssertException(new EqualsAssertResult<>(Arrays.toString(toContain), Arrays.toString(current), success));
    }

    //b. Рекурсивная проверка объектов по полям.
    public static <T> void equalRecursively(T expected, T actual) throws AssertException {
        boolean success = checkRecursive(expected, actual);
        throw new AssertException(new EqualsAssertResult<>(expected, actual, success));
    }

    private static boolean checkRecursive(Object expected, Object actual) {
        // Если оба null - ок, если один null - не ок
        if (expected == actual) return true;
        if (expected == null || actual == null) return false;

        // Если классы разные - не равны
        Class<?> clazz = expected.getClass();
        if (clazz != actual.getClass()) return false;

        // Если это примитивный тип, обертка или строка (где equals переопределен корректно)
        if (clazz.isPrimitive() || expected instanceof String || expected instanceof Number || expected instanceof Boolean) {
            return expected.equals(actual);
        }

        // Если это массив
        if (clazz.isArray()) {
            return Arrays.deepEquals(new Object[]{expected}, new Object[]{actual});
        }

        // Рекурсивный обход полей
        try {
            while (clazz != null && clazz != Object.class) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object valExpected = field.get(expected);
                    Object valActual = field.get(actual);

                    if (!checkRecursive(valExpected, valActual)) {
                        return false;
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {
            return false;
        }

        return true;
    }
}
