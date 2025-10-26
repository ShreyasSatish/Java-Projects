public class Main {
    public static void main(String[] args) {
        Bioreactor reactor = new Bioreactor(1, 2, "test",
                "seconds", "logistic", 100, 1);
        reactor.startModel();
        reactor.printPopulation();
        reactor.printPopulation(5);
    }
}
