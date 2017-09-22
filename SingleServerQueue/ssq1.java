//	        Name : Eric Kwon
//		    Single Server Queue Simulation
//		    Objective : Run 1M instance of job arrivals & departures
//          Arrivals and service times are on equi-likely basis

import java.util.*;
import java.text.*;

class Ssq1sum {
    double arrival;									// Arrival time
    double delay;									// Delay time
    double wait;									// Wait time
    double service;									// Service times
    int no_jobs;									// # of jobs processed

    void initSumParas() {							// Initializer
        arrival = 0.0;
        delay = 0.0;
        wait = 0.0;
        service = 0.0;
        no_jobs = 0;
    }

    double avgInterarrival() { return arrival / no_jobs; }		// Return average interarrival times
    double avgServicetime() { return service / no_jobs; }		// Return average service time
    double avgDelay() { return delay / no_jobs; }				// Return average delay time
    double avgWait() { return wait / no_jobs; }					// Return average wait time
}

class ssq1 {
    public static double getInterarrival () {		// "Equilikely" Distribution for Interarrival Times
        Random r = new Random();
        double randR = r.nextInt(10);
        if (randR < 1)								// 10% chance for 30 second interarrival time
            return 30.0;
        else if (randR < 4)							// 30% chance for 10 second interarrival time
            return 10.0;
        else										// 60% chance for 20 second interarrival time
            return 20.0;
    }

    public static double getService () {			// "Equilikely? Distribution for Service Times
        Random r = new Random();
        double randR = r.nextInt(10);
        if (randR < 1)								// 10% chance for 20 second service time
            return 20.0;
        else if (randR < 4)							// 30% chance for 8 second service time
            return 8.0;
        else										// 60% chance for 15 second service time
            return 15.0;
    }

    public static Ssq1sum runTests() {
        Ssq1sum sum = new Ssq1sum();
        sum.initSumParas();							// Initialize the class members
        double serviceTime;							// Service time placeholder
        double interarrivalTime;					// Interarrival time placeholder
        double departureTime = 0;					// Departure time placeholder
        double delay;								// Delay time placeholder
        double wait;								// Wait time placeholder
        sum.no_jobs = 1;

        sum.service += getService();				// Initial service time for first customer
        departureTime += sum.service;				// Initial departure time for first customer

        for (int i=2 ; i <= 1000000 ; i++){
            interarrivalTime = getInterarrival();	// Get arrival
            sum.arrival += interarrivalTime;
            if (sum.arrival < departureTime)
                delay = departureTime - sum.arrival;
            else
                delay = 0.0;
            serviceTime = getService();
            wait = delay + serviceTime;
            departureTime = sum.arrival + wait;
            sum.delay += delay;
            sum.wait += wait;
            sum.service += serviceTime;
            sum.no_jobs = i;
        }

        return sum;
    }

    public static void printResults(Ssq1sum sum) {
        DecimalFormat f = new DecimalFormat("###0.00");

        System.out.println("Jobs Processed				: " + sum.no_jobs + " jobs" );
        System.out.println("Average Interarrival Time	: " + f.format(sum.avgInterarrival()) + " seconds");
        System.out.println("Average Service Time		: " + f.format(sum.avgServicetime()) + " seconds");
        System.out.println("Average Delay				: " + f.format(sum.avgDelay()) + " seconds");
        System.out.println("Average Wait Time			: " + f.format(sum.avgWait()) + " seconds");
    }

    public static void main (String[] args) {
        Ssq1sum sum = runTests();
        printResults(sum);
    }
}