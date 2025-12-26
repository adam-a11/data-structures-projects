package hw2;

public class PostfixCalculator {

    public static double evaluate(String postfix) {
        ArrayStack<Double> stack = new ArrayStack<>();
        if (postfix == null || postfix.trim().isEmpty()) {
            return 0.0;
        }

        String[] tokens = postfix.trim().split("\\s+");
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Double.valueOf(token));
            } else {
                // Pop right, then left
                Double b = stack.pop();
                Double a = stack.pop();
                double r = 0.0;

                switch (token) {
                    case "+": r = a + b; break;
                    case "-": r = a - b; break;
                    case "*": r = a * b; break;
                    case "/": r = a / b; break;   // assignment assumes valid expressions
                    case "^": r = Math.pow(a, b); break; // per spec, use Math.pow
                    default:
                        throw new IllegalArgumentException("Unknown operator: " + token);
                }
                stack.push(r);
            }
        }
        // Final result on stack
        Double ans = stack.pop();
        return ans == null ? 0.0 : ans;
    }
}
