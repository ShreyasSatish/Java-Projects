package Bioreactor;

import java.util.TreeMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.JFrame;
import java.util.Map;
import java.util.Set;

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

    private XYDataset createDataset() {
        // Internal method to make a data structure that
        // JFreeChart can interpret to make plots

        DefaultXYDataset dataset = new DefaultXYDataset();
        // Find the size of the dataset
        int size = population.size();
        // Make arrays for X (Time) and Y (Population) data
        double[] xData =  new double[size];
        double[] yData = new double[size];

        Set<Map.Entry<Double, Long>> entrySet = population.entrySet();
        int index = 0;
        // Populate the arrays
        for (Map.Entry<Double, Long> entry : entrySet) {
            xData[index] = entry.getKey(); // Time (Double)
            yData[index] = entry.getValue().doubleValue(); // Population (Long -> Double)
            index++;
        }
        // Convert into the format that JFreeChart expects
        double[][] data = new double[][] {xData, yData};
        dataset.addSeries(cellName + " Growth", data);
        return dataset;
    }

    public void plotPopulation(String chartTitle) {
        // Prep the dataset
        XYDataset dataset = createDataset();
        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Time (" + timeUnit + ")",
                "Population (CFU)",
                dataset
        );

        // Display the chart
        JFrame frame = new JFrame(chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public abstract void startModel();
}
