package randomizedtest;

import static edu.princeton.cs.algs4.StdRandom.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> buglist = new BuggyAList<>();
        AListNoResizing<Integer> noresize = new AListNoResizing<>();

        buglist.addLast(4);
        noresize.addLast(4);

        buglist.addLast(5);
        noresize.addLast(5);

        buglist.addLast(6);
        noresize.addLast(6);
        assertEquals(noresize.size(), buglist.size());

        assertEquals(noresize.removeLast(),buglist.removeLast());
        assertEquals(noresize.removeLast(),buglist.removeLast());
        assertEquals(noresize.removeLast(),buglist.removeLast());

    }

    @Test
    public void randomTestAList() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;

        for (int i = 0; i < N; i += 1) {
            int operationNumber = uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2 && L.size() > 0) {
                // removeLast
                assertEquals(L.removeLast(), B.removeLast());
            } else if (operationNumber == 3 && L.size() > 0) {
                assertEquals(L.getLast(), B.getLast());
            }
        }
    }
}
