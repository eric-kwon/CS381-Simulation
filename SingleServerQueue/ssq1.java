//          Name : Eric Kwon
//          Single Server Queue Simulation
//          Objective : Run 1M instance of job arrivals & departures
//          Arrivals and service times are on equi-likely basis

import java.util.*;
import java.text.*;

class Ssq1sum {

    // Class member variables that will retain the valeus for statistics
    double arrival;									
    double delay;		
    double wait;									
    double service;								
    int no_jobs;									

    // Method to initialize the variables
    void initSumParas() {			
        arrival = 0.0;
        delay = 0.0;
        wait = 0.0;
        service = 0.0;
        no_jobs = 0;
    }

    // Methods to return the averages of the collected values
    double avgInterarrival() { return arrival / no_jobs; }		
    double avgServicetime() { return service / no_jobs; }		
    double avgDelay() { return delay / no_jobs; }				
    double avgWait() { return wait / no_jobs; }					
}

class ssq1 {

    // Equi-likely distribution of inter-arrival times
    // Random will generate double values from 0 to 1
    public static double getInterarrival () {
        Random r = new Random();
        double randR = r.nextInt(10);
        if (randR < 1)								
            return 30.0;
        else if (randR < 4)							
            return 10.0;
        else					
            return 20.0;
    }

    // Equi-likely distribution of service times
    // Random will generate double values from 0 to 1
    public static double getService () {			
        Random r = new Random();
        double randR = r.nextInt(10);
        if (randR < 1)								
            return 20.0;
        else if (randR < 4)							
            return 8.0;
        else										
            return 15.0;
    }

    // Method to run the actual test
    public static Ssq1sum runTests() {
        Ssq1sum sum = new Ssq1sum();
        sum.initSumParas();							
        double serviceTime;							
        double interarrivalTime;					
        double departureTime = 0;					
        double delay;								
        double wait;								
        sum.no_jobs = 1;

    // Track the additions of the service times and the departures
        sum.service += getService();				
        departureTime += sum.service;				

    // Iteration to simulate arrivals based on randomly generated inter-arrival times
    // To be run for 1M instances
        for (int i=2 ; i <= 1000000 ; i++){
            interarrivalTime = getInterarrival();
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

        System.out.println("Jobs Processed                  : " + sum.no_jobs + " jobs" );
        System.out.println("Average Interarrival Time       : " + f.format(sum.avgInterarrival()) + " seconds");
        System.out.println("Average Service Time            : " + f.format(sum.avgServicetime()) + " seconds");
        System.out.println("Average Delay                   : " + f.format(sum.avgDelay()) + " seconds");
        System.out.println("Average Wait Time               : " + f.format(sum.avgWait()) + " seconds");
    }

    public static void main (String[] args) {
        Ssq1sum sum = runTests();
        printResults(sum);
    }
}