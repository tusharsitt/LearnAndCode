public class Calculator {
    public Calculator() {}
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * a; }
    public int divide(int a, int b) { 
        if (b == 0) throw new IllegalArgumentException("Cannot divide by zero");
        return a / b; 
    }
    public int modulo(int a, int b) { return a % b; }
    public double power(double base, double exponent) { return Math.pow(base, exponent); }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("2 + 3 = " + calc.add(2, 3));
        System.out.println("5 - 2 = " + calc.subtract(5, 2));
        System.out.println("4 * 3 = " + calc.multiply(4, 3));
        System.out.println("10 / 2 = " + calc.divide(10, 2));
        System.out.println("10 % 3 = " + calc.modulo(10, 3));
        System.out.println("2 ^ 3 = " + calc.power(2, 3));
    }
}