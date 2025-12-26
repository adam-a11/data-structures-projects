package assignment1;

public class Mars extends Currency {
    public Mars(double initialFunds) {
        super("MarsMoney", initialFunds);
    }

    @Override
    public double toEarthDollars(double amount) {
        return amount / Exchangeable.ED_TO_MM;
    }

    @Override
    public double fromEarthDollars(double earthDollars) {
        return earthDollars * Exchangeable.ED_TO_MM;
    }

    @Override
    protected double calculateExchangeFee(double amount) {
        return amount * 0.07; // 7%
    }
}
