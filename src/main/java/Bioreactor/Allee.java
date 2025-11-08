package Bioreactor;

import java.util.Scanner;

public class Allee extends Model {

    public Allee(long startingPopulation, double growthRate,
                 double time, String timeUnit, double timeStep,
                 String cellName) {
        super(startingPopulation, growthRate, time,
                timeUnit, timeStep, cellName);
    }

    public static void printModelInformation() {
        System.out.println("==== Allee Model Information ====");
        System.out.println("This model has an upper limit to population and considers the size of the population itself may affects " +
                "growth, but still does not take into account");
        System.out.println("death. The parameters of the model are as follows:");
        System.out.println("long startingPopulation: The starting population of the cell culture");
        System.out.println("long maxPopulation: Max population parameter of Allee model. User input taken when startModel() called");
        System.out.println("long criticalPopulation: Critical population parameter of Allee model. User input taken when startModel() called");
        System.out.println("double growthRate: The growth rate of the cell culture. Units are self-defined");
        System.out.println("int time: The total time that the model runs for");
        System.out.println("int timeUnit: The time unit. Unit depends on unit of growthRate");
        System.out.println("int timeStep: The time between samples of the cell culture population");
        System.out.println("String cellName: The name of the cell culture");
        System.out.println("Map<Integer, Long> population: A Hashmap of the times and their corresponding " +
                "population");
        System.out.println("=====================");
    }

    public void startModel() {
        // Start the Allee Effect model
        // Get user input for criticalPopulation (N_c)
        // and maxPopulation (K)
        long currentPopulation = startingPopulation;
        long maxPopulation;
        long criticalPopulation;
        double rateChange; // The rate of change of the population. Used for Euler Method

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the max population / parameter K");
        maxPopulation = userInput.nextLong();
        Scanner userInput2 = new Scanner(System.in);
        System.out.println("Enter the critical population / parameter N_c");
        criticalPopulation = userInput2.nextLong();

        // Iterate using Euler Method
        // Terminate programme when maxPopulation reached,
        // or population is extinct
        for (double i = 0; i < time; i += timeStep) {
            // Calculate derivative
            rateChange = (growthRate * currentPopulation) *
                    (1 - (double) currentPopulation / maxPopulation) *
                    ((double) currentPopulation / criticalPopulation - 1);
            // Euler Method. Round to nearest whole number
            currentPopulation += Math.round(rateChange * timeStep);
            population.put(i, currentPopulation);
            // Check termination conditions
            if (currentPopulation >= maxPopulation) {
                System.out.println("Terminating model as max population reached at "
                        + i + " " + timeUnit);
                break;
            } else if (currentPopulation <= 0) {
                System.out.println("Terminating model as the population is extinct at "
                        + i + " " + timeUnit);
                break;
            }
        }
    }
}

