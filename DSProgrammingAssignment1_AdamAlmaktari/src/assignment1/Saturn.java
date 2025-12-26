package assignment1;

public class Saturn extends Currency {
    private static final double FLAT_FEE = 5.0;

    public Saturn(double initialFunds) {
        super("SaturnSilver", initialFunds);
    }

    @Override
    public double toEarthDollars(double amount) {
        return amount / Exchangeable.ED_TO_SS;
    }

    @Override
    public double fromEarthDollars(double earthDollars) {
        return earthDollars * Exchangeable.ED_TO_SS;
    }

    @Override
    protected double calculateExchangeFee(double amount) {
        return FLAT_FEE;
    }
}
