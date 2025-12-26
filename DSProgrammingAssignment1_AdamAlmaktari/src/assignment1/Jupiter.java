package assignment1;

public class Jupiter extends Currency {
    private int successfulTransactions = 0;

    public Jupiter(double initialFunds) {
        super("JupiterJingles", initialFunds);
    }

    @Override
    public double toEarthDollars(double amount) {
        return amount / Exchangeable.ED_TO_JJ;
    }

    @Override
    public double fromEarthDollars(double earthDollars) {
        return earthDollars * Exchangeable.ED_TO_JJ;
    }

    @Override
    protected double calculateExchangeFee(double amount) {
        return (successfulTransactions == 0) ? 0.0 : amount * 0.10;
    }

    @Override
    public void exchange(Currency other, double amount) {
        double beforeFunds = this.getTotalFunds();
        super.exchange(other, amount);

        // If funds decreased, it means success
        if (this.getTotalFunds() < beforeFunds) {
            successfulTransactions++;
        }
    }
}
