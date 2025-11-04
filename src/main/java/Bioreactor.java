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
    protected double physiologicalState;

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
                // Get user input for maxPopulation parameter (K)
                Scanner userInput = new Scanner(System.in);
                System.out.println("Enter the max population / parameter K");
                this.maxPopulation = userInput.nextDouble();

                // Iterate and terminate the programme
                // when the max Double or maxPopulation
                // has been reached
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

                // Iterate using Euler Method
                // Terminate the programme when
                // The max double value has been reached,
                // reaches the maxPopulation value, or
                // population <= 0 (extinct)
                for (int i = 0; i < time; i += timeStep) {
                    // Calculate derivative
                    rateChange = (growthRate * currentPopulation) *
                            (1 - currentPopulation / maxPopulation) *
                            (currentPopulation / criticalPopulation - 1);
                    // Euler Method
                    currentPopulation += rateChange * timeStep;
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

            case "baranyi":
                // Starts the Baranyi Model

                // Get user input for max population
                // and initial physiological state q_0
                Scanner userInput4 = new Scanner(System.in);
                System.out.println("Enter the max population / parameter N_max");
                this.maxPopulation = userInput4.nextDouble();
                Scanner userInput5 = new Scanner(System.in);
                System.out.println("Enter the initial physiological state q_0");
                this.physiologicalState = userInput5.nextDouble();

                // Calculate the initial physiological state variable
                double alphaZero = 1 / physiologicalState;
                // Calculate the initial log population
                currentPopulation = Math.log(startingPopulation);
                // Define variables used in the iteration
                double rateChangePopulation;
                double rateChangeAlpha;
                double alpha = alphaZero;

                // Start the iteration
                for (int i = 0; i < time; i += timeStep) {
                    // Calculate derivative for population
                    rateChangePopulation = growthRate * alpha * (1/ (1 + (currentPopulation / maxPopulation)));
                    // Update the current population via Euler Method
                    currentPopulation += rateChangePopulation * timeStep;
                    // Calculate derivative for alpha
                    rateChangeAlpha = 1/(1 + (Math.exp(-growthRate * i)/alphaZero));
                    // Update current alpha via Euler Method
                    alpha = alpha + rateChangeAlpha * timeStep;
                    // Add unlogged population to Map
                    population.put(i, currentPopulation);

                    // Check if any termination conditions are met
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
        // in time using method overloading

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
