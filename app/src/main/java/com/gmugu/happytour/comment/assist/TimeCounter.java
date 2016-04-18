package com.gmugu.happytour.comment.assist;

/**
 * Created by mugu on 16-4-9 下午2:02.
 */
public class TimeCounter {

    private static final String TAG = TimeCounter.class.getSimpleName();
    private long t;
    private long offset = 0;
    boolean isPause = false;

    public TimeCounter() {
        start();
    }


    /**
     * Count start.
     */
    public long start() {
        t = System.currentTimeMillis();
        isPause = false;
        return t;
    }

    public long restart() {
        offset = 0;
        return start();
    }


    public long pause() {
        offset = duration();
        isPause = true;
        return offset;
    }

    public long resume() {
        start();
        isPause = false;
        return offset;
    }


    public long getStartTime() {
        return t;
    }

    /**
     * Get duration and restart.
     */
    public long durationRestart() {

        long now = System.currentTimeMillis();
        long d = now - t + offset;
        t = now;
        offset = 0;
        return isPause ? offset : d;
    }

    /**
     * Get duration.
     */
    public long duration() {
        if (isPause) {
            return offset;
        }
        return System.currentTimeMillis() - t + offset;
    }

    /**
     * Print duration.
     */
    public void printDuration(String tag) {
        Log.i(TAG, tag + " :  " + duration());
    }

    /**
     * Print duration.
     */
    public void printDurationRestart(String tag) {
        Log.i(TAG, tag + " :  " + durationRestart());
    }
}