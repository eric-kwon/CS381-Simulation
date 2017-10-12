import java.util.Calendar;

public class LehmerRand {
    final static long a = 48271;                // Multiplier
    final static long m = 2147483647;           // Modulus
    final static long q = m / a;                // Quotient
    final static long r = m % a;                // Remainder
    static long state;                          // First Seed
    static long t;                              // Lehmer Generation

    // New Class Constructor
    // New random instantiation of initial state using the machine's current time in ms
    // Confirmed that if state is 1, the 10000th call results 399,268,537
    public LehmerRand() {
        state = Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    // Create new random value and return
    public double nextRandom() {
        t = a * (state % q) - r * (state / q);
        if (t > 0) {
            state = t;
        }
        else {
            state = t + m;
        }
        return ((double) state / m);
    }
}
