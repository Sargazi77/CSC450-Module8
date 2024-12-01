import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Concurrency {

    // A lock to control access to shared resources for thread safety
    private static final Lock lock = new ReentrantLock();

    // Function for the first thread to count up to 20
    public static void countUp() {
        lock.lock(); // Acquiring the lock for thread safety
        try {
            System.out.println("Thread 1: Counting up to 20");
            for (int i = 0; i <= 20; i++) {
                System.out.println("Count Up: " + i);
            }
        } finally {
            lock.unlock(); // Releasing the lock
        }
    }

    // Function for the second thread to count down to 0
    public static void countDown() {
        lock.lock(); // Acquiring the lock for thread safety
        try {
            System.out.println("Thread 2: Counting down to 0");
            for (int i = 20; i >= 0; i--) {
                System.out.println("Count Down: " + i);
            }
        } finally {
            lock.unlock(); // Releasing the lock
        }
    }

    public static void main(String[] args) {
        // Creating the first thread to count up
        Thread thread1 = new Thread(Concurrency::countUp);

        // Creating the second thread to count down
        Thread thread2 = new Thread(Concurrency::countDown);

        // Starting thread 1 and waiting for it to finish
        thread1.start();
        try {
            thread1.join(); // Ensures thread 1 completes before thread 2 starts
        } catch (InterruptedException e) {
            System.out.println("Thread 1 interrupted: " + e.getMessage());
        }

        // Starting thread 2
        thread2.start();
        try {
            thread2.join(); // Ensures thread 2 completes execution
        } catch (InterruptedException e) {
            System.out.println("Thread 2 interrupted: " + e.getMessage());
        }

        System.out.println("Both threads have completed their tasks.");
    }
}