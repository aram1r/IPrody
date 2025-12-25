import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner{
    public static void start(Class<?> clazz) throws Exception {
        Object testInstance = clazz.getDeclaredConstructor().newInstance();

        Method before = null;
        List<Method> testMethods = new ArrayList<>();
        Method after = null;

        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuit.class)) {
                if (before != null) {
                    throw new IllegalStateException("Ещё один метод before");
                } else {
                    before = m;
                }
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (before != null) {
                    throw new IllegalStateException("Ещё один метод before");
                } else {
                    after = m;
                }
            } else  if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            }
        }
        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).order()));

        if (before != null && after != null) {
            before.invoke(testInstance);
            testMethods.forEach(m -> {
                try {
                    m.invoke(testInstance);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
            after.invoke(testInstance);
        }
    }
}
