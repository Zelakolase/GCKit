package classlib;

/**
 * A stop watch which measure elapsed time in nanoseconds
 * @author Morad A.
 */
public class stopWatch {
    public long nanoTimeElapsed = 0l;

    public void start() {
        nanoTimeElapsed = System.nanoTime();
    }

    public void stop() {
        nanoTimeElapsed = System.nanoTime() - nanoTimeElapsed;
    }
}
