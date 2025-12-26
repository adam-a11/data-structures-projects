package assignment1;

public class Main {
    public static void main(String[] args) {
        Currency mars = new Mars(100.00);
        Currency jupiter = new Jupiter(100.00);
        Currency saturn = new Saturn(100.00);

        System.out.println("<-- Exchanges -->\n");

        mars.exchange(saturn, 25.00);
        jupiter.exchange(saturn, 10.00);
        saturn.exchange(mars, 122.00);
        jupiter.exchange(mars, 50.00);
    }
}
