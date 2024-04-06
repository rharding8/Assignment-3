import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Rover {
    private int sensors;
    private int interval;
    private int[][] temperatures;
    private Random rand = new Random();

    public Rover(int s, int i) {
        sensors = s;
        interval = i;
        temperatures = new int[s][i];
    }

    public void readAndReport() {
        Sensor[] s = new Sensor[sensors];
        for (int i = 0; i < sensors; i++) {
            s[i] = new Sensor(i);
            s[i].start();
        }
        try {
            for (int i = 0; i < sensors; i++) {
                s[i].join();
            }
            report();
        } catch (Exception e) {
            System.out.println("ERROR! RETURNING!");
        }
    }

    public void report() {
        ArrayList<Integer> flatTemps = new ArrayList<>();
        for (int i = 0; i < sensors; i++) {
            for (int j = 0; j < interval; j++) {
                flatTemps.add(temperatures[i][j]);
            }
        }
        Collections.sort(flatTemps);
        System.out.println("Bottom 5 Temperatues:");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%dF ", flatTemps.get(i));
        }
        System.out.println();
        System.out.println("Top 5 Temperatures:");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("%dF ", flatTemps.get(flatTemps.size() - i));
        }
        System.out.println();

        int[] difference = {0, 0, 0, 0};
        for (int i = 0; i < sensors; i++) {
            for (int j = 0; j <= interval - 10; j++) {
                int d = Math.abs(temperatures[i][j] - temperatures[i][j + 9]);
                if (d > difference[0]) {
                    difference[0] = d;
                    difference[1] = i;
                    difference[2] = j;
                    difference[3] = j + 9;
                }
            }
        }
        System.out.printf(
            "The highest difference, of %d degrees F, was observed on Sensor %d between minute %d and %d.\n",
            difference[0], difference[1], difference[2], difference[3]
        );
    }

    class Sensor extends Thread {
        private int id;

        public Sensor(int i) {
            id = i;
        }

        @Override
        public void run() {
            for (int i = 0; i < interval; i++) {
                temperatures[id][i] = rand.nextInt(-100, 70);
            }
        }
    }
}
