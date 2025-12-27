package hw2;

import java.util.List;

public class Converter {
    private final String infix;

    public Converter(String infix) {
        // remove spaces to keep tokens clean
        this.infix = infix == null ? "" : infix.replaceAll("\\s+", "");
    }

    // Operator precedence
    private int precedence(String op) {
        switch (op) {
            case "^": return 3;
            case "*": case "/": return 2;
            case "+": case "-": return 1;
            default:  return -1;
        }
    }

    // Right-associativity: ^ is right-associative
    private boolean isRightAssociative(String op) {
        return "^".equals(op);
    }

    // Convert infix -> postfix (space-separated), using ArrayStack and ParserHelper
    public String toPostFix() {
        StringBuilder out = new StringBuilder();
        ArrayStack<String> ops = new ArrayStack<>();

        // ParserHelper takes a char[] and returns List<String> tokens
        List<String> tokens = ParserHelper.parse(infix.toCharArray());

        for (String token : tokens) {
            if (token.matches("\\d+")) { // operand
                out.append(token).append(" ");
            } else if ("(".equals(token)) {
                ops.push(token);
            } else if (")".equals(token)) {
                // pop until "("
                while (!ops.isEmpty() && !"(".equals(ops.top())) {
                    out.append(ops.pop()).append(" ");
                }
                // discard "("
                if (!ops.isEmpty()) ops.pop();
            } else {
                // token is an operator: pop while higher precedence (or equal & NOT right-assoc)
                while (!ops.isEmpty()
                        && !"(".equals(ops.top())
                        && (precedence(ops.top()) > precedence(token)
                           || (precedence(ops.top()) == precedence(token) && !isRightAssociative(token)))) {
                    out.append(ops.pop()).append(" ");
                } 

                ops.push(token);
            }
        }

        // pop remaining operators
        while (!ops.isEmpty()) {
            out.append(ops.pop()).append(" ");
        }

        return out.toString().trim();
    }
}
