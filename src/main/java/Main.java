public class Main {
    public static void main(String[] args) {
        Bioreactor reactor = new Bioreactor(200, 1.001, "test",
                "seconds", "baranyi", 100, 1);
        reactor.startModel();
        reactor.printPopulation();
        // reactor.printPopulation(5);
    }
}
