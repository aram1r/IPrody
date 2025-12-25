public class Calc implements CalcParent{

    static int a, b, c;

    @BeforeSuit
    public void init() {
        System.out.println("START");
        a = 20;
        b = 15;
        c = 30;
    }

    @Test(order = 1)
    public void sum() {
        System.out.println(a + b);
    }

    @Test(order = 2)
    public void multiply() {
        System.out.println(a * b);
    };

    @Test
    public void divide() {
        System.out.println(c / b);
    }

    @AfterSuite
    public void end() {
        System.out.println("END");
    }

}
