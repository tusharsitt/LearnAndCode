public class Calculator {
    public Calculator() {}
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("2 + 3 = " + calc.add(2, 3));
        System.out.println("5 - 2 = " + calc.subtract(5, 2));
    }
}