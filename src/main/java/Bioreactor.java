import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static javax.management.Query.or;

public class Bioreactor {
    protected int startingPopulation;
    protected int cellPopulation;
    protected double growthRate;
    protected int time;
    protected String timeUnit;
    protected int timeStep;
    protected String cellName;
    protected String modelType;
    protected Map<Integer, Double> population;
    protected double maxPopulation;
    protected double criticalPopulation;

    public Bioreactor(int startingPopulation, double growthRate, String cellName,
                      String timeUnit, String modelType, int time, int timeStep) {
        this.startingPopulation = startingPopulation;
        this.cellPopulation = startingPopulation;
        this.growthRate = growthRate;
        this.cellName = cellName;
        this.time = time;
        this.timeUnit = timeUnit;
        this.modelType = modelType;
        this.timeStep = timeStep;
    }

    public void changeModelType(String modelType) {
        // Allows to change the model type
        this.modelType = modelType;
    }

    public void startModel() {
        // Starts the model depending on the
        // model type
        population = new HashMap<> ();
        double currentPopulation;
        switch (modelType) {

            case "exponential":
                // Starts the Exponential Model and
                // terminates when population reaches
                // the max Double value
                for (int i = 0; i < time; i += timeStep) {
                    currentPopulation = Math.round(startingPopulation * Math.exp(growthRate * i));
                    population.put(i, currentPopulation);
                    if (currentPopulation >= 9.223372036854776E18) {
                        System.out.println("Terminating model as max population has been reached at "
                                + i + " " + timeUnit);
                        break;
                    }
                }
                break;

            case "logistic":
                // Starts the Logistic Growth model
                // Get user input for maxPopulation value (K)
                Scanner userInput = new Scanner(System.in);
                System.out.println("Enter the max population / parameter K");
                this.maxPopulation = userInput.nextDouble();

                for (int i = 0; i < time; i += timeStep) {
                    currentPopulation = (maxPopulation * startingPopulation) / (startingPopulation +
                            (maxPopulation - startingPopulation) * Math.exp(-growthRate * i));
                    population.put(i, currentPopulation);
                    if ((currentPopulation >= 9.223372036854776E18) || (currentPopulation >= maxPopulation))  {
                        System.out.println("Terminating model as max population has been reached at "
                        + i + " " + timeUnit);
                        break;
                    }
                }
                break;

            case "allee":
                // Starts the Allee Effect model
                // Get user input for criticalPopulation value (N_c)
                // and the maxPopulation value (k)
                Scanner userInput2 = new Scanner(System.in);
                System.out.println("Enter the max population / parameter K");
                this.maxPopulation = userInput2.nextDouble();
                Scanner userInput3 = new Scanner(System.in);
                System.out.println("Enter the critical population / parameter N_c");
                this.criticalPopulation = userInput3.nextDouble();

                double rateChange;
                currentPopulation = startingPopulation;
                double previousPopulation;
                for (int i = 0; i < time; i += timeStep) {
                    previousPopulation = currentPopulation;
                    rateChange = (growthRate * currentPopulation) *
                            (1 - currentPopulation / maxPopulation) *
                            (currentPopulation / criticalPopulation - 1);
                    currentPopulation = previousPopulation + rateChange * timeStep;
                    population.put(i, currentPopulation);
                    if ((currentPopulation >= 9.223372036854776E18) ||
                            (currentPopulation >= maxPopulation)) {
                        System.out.println("Terminating model as max population has been reached at "
                                + i + " " + timeUnit);
                        break;
                    } else if (currentPopulation <= 0) {
                        System.out.println("Terminating model as the population is extinct (<= 0)");
                        break;
                    }
                }
                break;

            default:
                System.out.println("Error: Invalid model type");
        }
    }

    public void printPopulation() {
        // Prints a step by step recount of the population
        // as it was calculated
        for (Map.Entry<Integer, Double> entry : population.entrySet()) {
                System.out.println("The population at " +  entry.getKey()
                        + " " + timeUnit + " is: " + entry.getValue());
        }
    }

    public void printPopulation(int time) {
        // Prints the population at a singular moment
        // in time

        // Check if the entered time is valid
        if (population.containsKey(time)) {
            System.out.println("The population at " + time + " " + timeUnit
                    + " is: " + population.get(time));
        }
        else {
            System.out.println("Error: That time is not modelled");
        }
    }
}
