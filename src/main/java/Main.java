import Bioreactor.*;

public class Main {
    public static void main(String[] args) {
        // Exponential Model Testing
//        Exponential exponential = new Exponential(200, 1.001,
//                100.00, "seconds",
//                1, "Test"  );
//        Exponential.printModelInformation();
//        exponential.startModel();
//        try {
//            exponential.printPopulation();
//        }
//        catch(Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        try {
//            exponential.printPopulation(5);
//        }
//        catch (Exception e) {
//            System.out.println("Exception occurred: " + e.getMessage());
//        }
//        exponential.plotPopulation("Exponential Growth Plot");

        // Logistic Model Testing
//        Logistic logistic = new Logistic(200, 2, 100.00, "seconds", 1, "Test");
//        Logistic.printModelInformation();
//        logistic.startModel();
//        logistic.printPopulation();
//        try {
//            logistic.printPopulation(5);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        logistic.plotPopulation("Logistic Model Growth");
//
//        // Allee Model Testing
//        Allee allee = new Allee(200, 1.001, 100.00, "seconds", 1, "Test");
//        Allee.printModelInformation();
//        allee.startModel();
//        allee.printPopulation();
//        try {
//            allee.printPopulation(5);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        allee.plotPopulation("Allee Model Growth");
//
//        // Baranyi Model Testing
        Baranyi baranyi = new Baranyi(200, 1.001, 100.00, "seconds", 1, "Test");
        Baranyi.printModelInformation();
        baranyi.startModel();
        baranyi.printPopulation();
        try {
            baranyi.printPopulation(5);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        baranyi.plotPopulation("Baranyi Model Growth");
    }
}
