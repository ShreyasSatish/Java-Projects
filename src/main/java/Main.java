public class Main {
    public static void main(String[] args) {
        Bioreactor reactor = new Bioreactor(1, 2, "test",
                "seconds", "exponential", 100, 1);
        reactor.startModel();
        reactor.printPopulation();
    }
}
