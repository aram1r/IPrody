public class Calculator{

    public static Integer factorial(Integer factorial) {
        Integer result;
        if (factorial >= 0) {
            result = 1;
            for (int i = factorial; i>0; i--) {
                result *= i;
            }
        } else {
            throw new RuntimeException("Factorial cannot be negative or zero");
        }
        return result;
    }

    public static Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

    public static Double sin(Double number) {
        return Math.sin(number);
    }

    public static Double arcSin(Double number) {
        return Math.cos(number);
    };

}
