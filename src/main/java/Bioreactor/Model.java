package Bioreactor;

import java.util.TreeMap;
import java.util.Map;

public abstract class Model {
    protected long startingPopulation;
    protected double growthRate;
    protected double time; // The total time to run the model for
    protected String timeUnit;
    protected double timeStep; // The time between population samples
    protected String cellName; // Name identifier
    protected Map<Double, Long> population;

    public Model(long startingPopulation, double growthRate,
                 double time, String timeUnit, double timeStep,
                 String cellName) {
        this.startingPopulation = startingPopulation;
        this.growthRate = growthRate;
        this.time = time;
        this.timeUnit = timeUnit;
        this.timeStep = timeStep;
        this.cellName = cellName;
        this.population = new TreeMap<>();
    }

    public void printPopulation() {
        // Print a step by step recount of the population
        // as it was calculated
        System.out.println("==== " + cellName + " Populations ====");
        for (Map.Entry<Double, Long> entry : population.entrySet()) {
            System.out.println("The population at " + entry.getKey()
            + " " + timeUnit + " is: " + entry.getValue());
        }
        System.out.println("=====================");
    }

    public void printPopulation(double time) throws Exception {
        // Print the population at a singular moment
        // in time

        // Check if the entered time is valid
        if (population.containsKey(time)) {
            System.out.println("==== " + cellName + " Population at t = " + time + " ====");
            System.out.println("The population at " + time + " " + timeUnit
            + " is: " + population.get(time));
            System.out.println("=====================");
        }
        else {
            throw new Exception("Error: That time is not modelled");
        }
    }

    public abstract void startModel();
}
