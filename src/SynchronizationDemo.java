class Table {
    // 1. The synchronized keyword locks this object when the method is called.
    // No other thread can enter this method on the same object until the lock is released.
    synchronized void printTable(int n) {
        System.out.println("Printing table of " + n);
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                // Sleep allows context switching, but the lock prevents other threads from entering.
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread extends Thread {
    Table t;
    int number;

    MyThread(Table t, int number) {
        this.t = t;
        this.number = number;
    }

    public void run() {
        t.printTable(number);
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) {
        // 2. Create a single shared resource (Table object)
        Table obj = new Table();

        // 3. Create two threads sharing the same Table object
        MyThread t1 = new MyThread(obj, 5);
        MyThread t2 = new MyThread(obj, 100);

        // 4. Start the threads
        t1.start();
        t2.start();
    }
}

