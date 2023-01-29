package info.inpureprojects.core.API.Utils;

import java.util.concurrent.TimeUnit;

public class Timer {

    private long start;
    private long stop;
    private long diff;

    public void start() {
        this.start = System.nanoTime();
    }

    public void stop() {
        this.stop = System.nanoTime();
        this.diff = this.stop - this.start;
    }

    public long getMilli() {
        return TimeUnit.MILLISECONDS.convert(this.diff, TimeUnit.NANOSECONDS);
    }

    public void announce(String title) {
        System.out.println(
                title + " took " + TimeUnit.MILLISECONDS.convert(this.diff, TimeUnit.NANOSECONDS) + " milliseconds!");
    }
}
