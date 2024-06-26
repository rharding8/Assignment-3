import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Minotaur {
    Random rand = new Random();
    private int presentCount;
    private int thankCount;
    private LinkedList<Present> presentList;

    public Minotaur(int p) {
        presentCount = p;
        thankCount = 0;
        presentList = new LinkedList<>();
    }

    public synchronized void addPresentToList(Present p) {
        if (presentCount == 0) {
            return;
        }
        presentList.add(p);
        presentList.sort(Comparator.comparingInt(Present::getTag));
        presentCount--;
    }

    public synchronized void thankAndRemovePresent() {
        if (presentList.size() == 0) {
            return;
        }
        presentList.removeFirst();
        thankCount++;
    }

    public synchronized void thankAndRemovePresentByTag(int t) {
        if (presentList.size() == 0) {
            return;
        }
        if (presentList.removeIf(p -> p.getTag() == t)) {
            thankCount++;
        }
    }

    public synchronized boolean findPresent(int t) {
        if (presentList.size() == 0) {
            return false;
        }
        for (Present p: presentList) {
            if (p.getTag() == t) return true;
        }
        return false;
    }

    public synchronized int getThankCount() {
        return thankCount;
    }

    public synchronized int getPresentCount() {
        return presentCount;
    }

    class Present {
        private int tag;

        public Present() {
            this.tag = rand.nextInt(2000000);
        }

        public int getTag() {
            return tag;
        }
    }

    class Servant extends Thread {
        @Override
        public void run() {
            boolean turn = true;
            do {
                if (turn) {
                    if (presentCount > 0) {
                        addPresentToList(new Present());
                    }
                    turn = false;
                }
                else {
                    int tag = rand.nextInt();
                    if (findPresent(tag)) {
                        thankAndRemovePresentByTag(tag);
                    }
                    else {
                        thankAndRemovePresent();
                    }
                    turn = true;
                }
            } while (presentCount > 0 | presentList.size() > 0);
        }
    }
}
