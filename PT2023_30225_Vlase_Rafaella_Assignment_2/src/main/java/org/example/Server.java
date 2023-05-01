package org.example;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    BlockingQueue<Task> tasks;
    AtomicInteger waitingPeriod;

    Server()
    {
        // initialize queue and waitingPeriod
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    void addTask(Task newTask)
    {
        // add task to queue
        // increment the waitingPeriod
        try{
            tasks.put(newTask);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        waitingPeriod.set(waitingPeriod.intValue() + newTask.processingTime);
    }

    @Override
    public void run()
    {
        // take next task from queue
        // stop the thread for a time equal with the task's processing time
        // decrement the waitingPeriod
        while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!tasks.isEmpty())
            {
                tasks.element().processingTime--;
                waitingPeriod.set(waitingPeriod.decrementAndGet());
                if(tasks.element().processingTime == 0)
                {
                    try {
                        tasks.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String toString()
    {
        String string = "";
        for(Task task : tasks)
            string = string + task.toString() + " ";
        return string;
    }
}
