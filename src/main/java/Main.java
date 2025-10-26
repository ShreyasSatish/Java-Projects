public class Main {
    public static void main(String[] args) {
        Bioreactor reactor = new Bioreactor(200, 1.036, "test",
                "seconds", "allee", 100, 1);
        reactor.startModel();
        reactor.printPopulation();
        // reactor.printPopulation(5);
    }
}
