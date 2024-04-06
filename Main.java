public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println("Problem 1: MINOTAUR");
        Minotaur mino = new Minotaur(500000);
        Minotaur.Servant[] servants = new Minotaur.Servant[4];
        for (int i = 0; i < 4; i++) {
            servants[i] = mino. new Servant();
            servants[i].start();
        }
        try {
            for (int i = 0; i < 4; i++) {
                servants[i].join();
            }
            System.out.printf("The thankCount value should be 500,000. It is: %d.\n", mino.getThankCount());
        } catch (Exception e) {
            System.out.println("PROBLEM 1 FAILED!");
        }
        System.out.println("Problem 2: Mars Rover");
        Rover rov = new Rover(8, 60);
        rov.readAndReport();
        long execTime = System.nanoTime() - startTime;
        System.out.printf("Execution Time: %f seconds\n", execTime / 1e9);
    }
}
