package assignment1;

public interface Exchangeable {
    // Exchange rate constants
    double ED_TO_MM = 1.25;  // EarthDollar → MarsMoney
    double ED_TO_SS = 0.55;  // EarthDollar → SaturnSilver
    double ED_TO_JJ = 2.00;  // EarthDollar → JupiterJingles

    // Exchange method
    void exchange(Currency other, double amount);
}
