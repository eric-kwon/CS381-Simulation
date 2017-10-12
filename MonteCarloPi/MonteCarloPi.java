public class MonteCarloPi {

    // Instances where the point is inside the circle quadrant
    static int insideCircle = 0;

    // Method to determine if the given coordinates are within the quadrant of the circle
    // Pythagorean theorem : x^2 + y^2 = z^2
    // Therefore (x^2 + y^2) should be <= 1 in order to be within the quadrant
    static Boolean isInside (double x, double y) {
        if (Math.sqrt((x * x) + (y * y)) <= 1) {
            return true;
        }
        else
            return false;
    }

    // Method to run the test for x times instances
    // And then print the result
    static void runTest(double x) {
        final double instances = x;
        LehmerRand newRandom = new LehmerRand();

        // Create new coordinates for x times
        // If the coordinates land within the quadrant, increment the counter
        for (int i=0 ; i < instances ; i++) {
            if (isInside(newRandom.nextRandom(), newRandom.nextRandom()))
                insideCircle++;
        }

        // Count the instances and multiply by 4
        double approxPi = 4 * (insideCircle / instances);

        // Print the results
        System.out.println("Coordinates Inside Quadrant : " + insideCircle);
        System.out.println("Instances of Coordination   : " + instances);
        System.out.println("Pi Approximation            : " + approxPi);
    }

    public static void main (String[] args) {

        // Run the simulation for 1,000,000 times
        runTest(1000000);
    }
}
