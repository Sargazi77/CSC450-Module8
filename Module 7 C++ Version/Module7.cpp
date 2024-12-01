//============================================================================
// Name        : Module7.cpp
// Author      : MS
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <iostream>
#include <thread>  // For creating threads
#include <mutex>   // For thread safety
#include <string>  // For strings

std::mutex mtx; // Mutex to control access to shared resources

// Function for the first thread to count up to 20
void countUp() {
    mtx.lock();  // Locking the shared resource for thread safety
    std::cout << "Thread 1: Counting up to 20\n";
    for (int i = 0; i <= 20; ++i) {
        std::cout << "Count Up: " << i << std::endl;
    }
    mtx.unlock();  // Unlocking the shared resource
}

// Function for the second thread to count down to 0
void countDown() {
    mtx.lock();  // Locking the shared resource for thread safety
    std::cout << "Thread 2: Counting down to 0\n";
    for (int i = 20; i >= 0; --i) {
        std::cout << "Count Down: " << i << std::endl;
    }
    mtx.unlock();  // Unlocking the shared resource
}

int main() {
    // Create thread 1 to count up
    std::thread thread1(countUp);

    // Wait for thread 1 to finish before starting thread 2
    thread1.join();

    // Create thread 2 to count down
    std::thread thread2(countDown);

    // Wait for thread 2 to finish
    thread2.join();

    std::cout << "Both threads have completed their tasks.\n";

    return 0;
}
