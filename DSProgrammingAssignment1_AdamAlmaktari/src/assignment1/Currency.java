package assignment1;

import java.text.DecimalFormat;

public abstract class Currency implements Exchangeable {
    private String currencyName;
    private double totalFunds;
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    protected Currency(String currencyName, double initialFunds) {
        this.currencyName = currencyName;
        this.totalFunds = initialFunds;
    }

    // Getters & setters
    public String getCurrencyName() { return currencyName; }
    public double getTotalFunds() { return totalFunds; }
    protected void setTotalFunds(double amount) { this.totalFunds = amount; }

    // Abstract conversions (implemented by each subclass)
    public abstract double toEarthDollars(double amount);
    public abstract double fromEarthDollars(double earthDollars);

    // Abstract fee (varies per planet)
    protected abstract double calculateExchangeFee(double amount);

    @Override
    public void exchange(Currency other, double amount) {
        System.out.println("Converting from " + this.getCurrencyName() + " to " + other.getCurrencyName() + " and initiating transfer...");

        double fee = calculateExchangeFee(amount);
        double required = amount + fee;

        // Check funds
        if (required > this.totalFunds) {
            System.out.println("Uh oh - " + this.getCurrencyName() + " only has an available balance of " +
                    DF.format(this.totalFunds) + ", which is less than " + DF.format(required) + "!");
            System.out.println();
            return;
        }

        // Conversion
        double earth = this.toEarthDollars(amount);
        double targetAmount = other.fromEarthDollars(earth);

        // Print conversion
        System.out.println(DF.format(amount) + " " + this.getCurrencyName() + " = " + DF.format(earth) +
                " EarthDollars = " + DF.format(targetAmount) + " " + other.getCurrencyName());

        // Update balances
        this.totalFunds -= (amount + fee);
        other.totalFunds += targetAmount;

        // Print results
        System.out.println(this.getCurrencyName() + " exchange fee is " + DF.format(fee) + " " + this.getCurrencyName());
        System.out.println(this.getCurrencyName() + " has a total of " + DF.format(this.totalFunds) + " " + this.getCurrencyName());
        System.out.println(other.getCurrencyName() + " has a total of " + DF.format(other.getTotalFunds()) + " " + other.getCurrencyName());
        System.out.println();
    }
}
