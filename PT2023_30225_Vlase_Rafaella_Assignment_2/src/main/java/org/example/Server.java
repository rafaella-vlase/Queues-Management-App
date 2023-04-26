package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(LinkedBlockingQueue<Task> tasks, AtomicInteger waitingPeriod)
    {
        this.tasks = tasks;
        this.waitingPeriod = waitingPeriod;
    }

    public Server()
    {
        this(new LinkedBlockingQueue<>(), new AtomicInteger(0));
    }

    void addTask(Task newTask) throws InterruptedException
    {
        tasks.put(newTask);
        waitingPeriod.addAndGet(newTask.serviceTime);
    }

    public void run() {
        while (true) {
            try {
                // Sleep for 1 second to simulate the passage of time
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // If an exception occurs, print the stack trace and continue the loop
                e.printStackTrace();
                continue;
            }

            // If there are no tasks in the queue, continue the loop
            if (tasks.isEmpty()) {
                continue;
            }

            // Get the next task from the queue and decrement its processing time
            Task nextTask = tasks.element();
            nextTask.serviceTime--;

            // Decrement the waiting period by 1
            waitingPeriod.getAndDecrement();

            // If the task's processing time has reached 0, remove it from the queue
            if (nextTask.serviceTime == 0) {
                try {
                    tasks.take();
                } catch (InterruptedException e) {
                    // If an exception occurs, print the stack trace and continue the loop
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }
    public Task[] getTasks()
    {
        Task[] taskArray = new Task[tasks.size()];
        tasks.toArray(taskArray);
        return taskArray;
    }

}
