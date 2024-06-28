package eu.telecomnancy.labfx;

public class Counter {
    private int count;
    private static Counter instance;

    public static Counter getInstance() {
        if (instance == null) {
            instance = new Counter();
        }
        return instance;
    }
    public Counter() {
        this.count = 0;
    }

    public void increment() {
        this.count++;
    }

    public void decrement() {
        this.count--;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
