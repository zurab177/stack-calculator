// СРО №2
// Проект: Калькулятор выражений со стеком
// ФИО: Зураб Хунчиев

import java.util.Stack;
import java.util.Scanner;

public class StackCalculator {

    public static int priority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    public static double applyOperation(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }

    public static double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                i++;
                continue;
            }

            // если число
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();

                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i));
                    i++;
                }

                numbers.push(Double.parseDouble(sb.toString()));
                continue;
            }

            // если операция
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() &&
                        priority(operators.peek()) >= priority(ch)) {

                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();

                    numbers.push(applyOperation(a, b, op));
                }

                operators.push(ch);
            }

            i++;
        }

        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();

            numbers.push(applyOperation(a, b, op));
        }

        return numbers.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();

        double result = calculate(input);

        System.out.println("Результат: " + result);
    }
}