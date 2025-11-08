package Bioreactor;

public class Exponential extends Model{

    public Exponential(long startingPopulation, double growthRate,
                       double time, String timeUnit, double timeStep,
                       String cellName) {
        super(startingPopulation, growthRate, time,
                timeUnit, timeStep, cellName);
    }

    public static void printModelInformation() {
        System.out.println("==== Exponential Model Information ====");
        System.out.println("This model is not very accurate as it does not take into account a max population,");
        System.out.println("competition, or death. The parameters of the model are as follows:");
        System.out.println("long startingPopulation: The starting population of the cell culture");
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
        // Start the Exponential Model and
        // terminate when population reaches
        // the max Long value
        long currentPopulation;

        for (double i = 0; i < time; i += timeStep) {
            currentPopulation = Math.round(startingPopulation * Math.exp(growthRate * i));
            population.put(i, currentPopulation);
            if (currentPopulation >= 9.223372036854776E18) {
                System.out.println("Terminating model as max population reached at " +
                        i + " " + timeUnit);
                break;
            }
        }
    }
}
