public class Bioreactor {
    final int startingPopulation;
    int cellPopulation;
    final float growthRate;
    int time;
    final String cellName;
    final String timeUnit;
    String modelType;

    public Bioreactor(int startingPopulation, float growthRate, String cellName, String timeUnit, String modelType, int time) {
        this.startingPopulation = startingPopulation;
        this.cellPopulation = startingPopulation;
        this.growthRate = growthRate;
        this.cellName = cellName;
        this.time = time;
        this.timeUnit = timeUnit;
        this.modelType = modelType;
    }
}
