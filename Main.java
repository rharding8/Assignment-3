public class Main {
    public static void main(String[] args) {
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
    }
}
