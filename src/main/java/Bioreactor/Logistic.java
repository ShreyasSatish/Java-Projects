package Bioreactor;

import java.util.Scanner;

public class Logistic extends Model{

    public Logistic(long startingPopulation, double growthRate,
                       double time, String timeUnit, double timeStep,
                       String cellName) {
        super(startingPopulation, growthRate, time,
                timeUnit, timeStep, cellName);
    }

    public static void printModelInformation() {
        System.out.println("==== Logistic Model Information ====");
        System.out.println("This model has an upper limit to population, but still does not take into account");
        System.out.println("competition, or death. The parameters of the model are as follows:");
        System.out.println("long startingPopulation: The starting population of the cell culture");
        System.out.println("long maxPopulation: Max population parameter of Logistic model. User input taken when startModel() called");
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
        // Start the Logistic Model and
        // terminate when the population
        // reaches the maxPopulation
        // parameter
        long currentPopulation;
        long maxPopulation;
        // Get user input for maxPopulation (K)
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the max population / parameter K");
        maxPopulation = userInput.nextLong();

        // Begin iterations
        for (double i = 0; i < time; i += timeStep) {
            // Calculate currentPopulation according to model
            // and round the value to the nearest whole number
            currentPopulation = Math.round(((maxPopulation * startingPopulation) / (startingPopulation +
                                (maxPopulation - startingPopulation) * Math.exp(-growthRate * i))));
            population.put(i, currentPopulation);
            if (currentPopulation >= maxPopulation) {
                System.out.println("Terminating model as max population has been reached at "
                + i + " " + timeUnit);
                break;
            }
        }
    }
}
