package Bioreactor;

import java.util.Scanner;

public class Baranyi extends Model{

    public Baranyi(long startingPopulation, double growthRate,
                 double time, String timeUnit, double timeStep,
                 String cellName) {
        super(startingPopulation, growthRate, time,
                timeUnit, timeStep, cellName);
    }

    public static void printModelInformation() {
        System.out.println("==== Baranyi Model Information ====");
        System.out.println("This model has an upper limit to population and considers the size of the population itself may affects " +
                "growth. It also takes into account lag time. But still does not take into account");
        System.out.println("death. The parameters of the model are as follows:");
        System.out.println("long startingPopulation: The starting population of the cell culture");
        System.out.println("long maxPopulation: Max population parameter of Baranyi model. User input taken when startModel() called");
        System.out.println("long physiologicalState: Physiological State of Baranyi model. User input taken when startModel() called");
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
        // Get user input for maxPopulation (N_max)
        // and initial physiologicalState (q_0)
        long currentPopulation = (long) Math.log(startingPopulation);
        long maxPopulation;
        double physiologicalState;


        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the max population / parameter N_max");
        maxPopulation = userInput.nextLong();
        Scanner userInput2 = new Scanner(System.in);
        System.out.println("Enter the initial physiological state / parameter q_0");
        physiologicalState = userInput2.nextDouble();

        // Calculate the initial physiological state variable
        double alphaZero = 1 / physiologicalState;
        // Define other variables used in the iteration
        double rateChangePopulation;
        double rateChangeAlpha;
        double alpha = alphaZero;

        // Start the iteration
        // Terminate when maxPopulation reached
        // or when population is extinct
        for (double i = 0; i < time; i += timeStep) {
            // Calculate derivative for population
            rateChangePopulation = growthRate * alpha * ((double) 1 / (1 + ((double) currentPopulation / maxPopulation)));
            // Update current population via Euler Method
            // Round to nearest whole number
            currentPopulation += Math.round(rateChangePopulation * timeStep);
            // Calculate derivative for alpha
            rateChangeAlpha = 1 / (1 + (Math.exp(-growthRate * i) / alphaZero));
            // Update current alpha via Euler Method
            alpha = alpha + rateChangeAlpha * timeStep;
            // Add populations to Map
            population.put(i, currentPopulation);

            // Check if any termination conditions are met
            if (currentPopulation >= maxPopulation) {
                System.out.println("Terminating model as max population reached at "
                + i + " " + timeUnit);
                break;
            } else if (currentPopulation <= 0) {
                System.out.println("Terminating model as population is extinct at"
                + i + " " + timeUnit);
                break;
            }
        }
    }
}


