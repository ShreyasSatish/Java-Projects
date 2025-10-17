import java.util.HashMap;
import java.util.Map;

public class Bioreactor {
    int startingPopulation;
    int cellPopulation;
    float growthRate;
    int time;
    String timeUnit;
    int timeStep;
    String cellName;
    String modelType;
    Map<Integer, Double> population;

    public Bioreactor(int startingPopulation, float growthRate, String cellName,
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
        this.modelType = modelType;
    }

    public void startModel() {
         population = new HashMap<> ();
         double currentPopulation;
        for (int i = 0; i < time; i += timeStep) {
            currentPopulation = Math.round(startingPopulation * Math.exp(growthRate * i));
            population.put(i, currentPopulation);
            if (currentPopulation >= 9.223372036854776E18) {
                System.out.println("Terminating model as max population has been reached at " + i + " " + timeUnit);
                break;
            }
        }
    }

    public void printPopulation() {
        for (Map.Entry<Integer, Double> entry : population.entrySet()) {
                System.out.println("The population at " +  entry.getKey() + " " + timeUnit + " is: " + entry.getValue());
        }
    }
}
