package oop;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Progress implements AutoCloseable  {

    private final double maxValue;
    private final AtomicLong value = new AtomicLong();
    private volatile int prevPercentage;

    public Progress(double maxValue) {
        this.maxValue = maxValue;
    }

    public void tick() {
        final long t = value.incrementAndGet();
        final double percentage = (int) (t / maxValue * 100);
        if (prevPercentage != (int) percentage) {
            synchronized (this) {
                if (prevPercentage != percentage) {
                    prevPercentage = (int) percentage;
                    updateProgress(percentage / 100);
                }
            }
        }
    }

    public static Progress ofMax(double maxValue) {
        return new Progress(maxValue);
    }

    static void updateProgress(double progressPercentage) {
        final int width = 50; // progress bar width in chars

        System.out.print("\r[");
        int i = 0;
        for (; i < (int) (progressPercentage * width); i++) {
            System.out.print("=");
        }
        for (; i < width; i++) {
            System.out.print(" ");
        }
        System.out.print("]");
    }
    public static void func(){ // Main функція
        String[] ss = new String[250];
        try (Progress progress = Progress.ofMax(ss.length)){
            Arrays.stream(ss).forEach(s -> {
                Waiting(10);
                progress.tick();
            });
            System.out.println();
        }
    }
    public static void Waiting(int period) { // Sleep функція
        try {
            Thread.sleep(period);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void close() {
        System.out.println();
    }
}
