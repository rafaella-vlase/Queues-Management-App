package org.example;

import view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable
{
    //data read from ui
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    private Scheduler scheduler;
    //private SimulationFrame frame;
    private List<Task> generatedTasks;
    View view;

    public SimulationManager(int numberOfServers, int numberOfClients, int timeLimit, int maxProcessingTime, int minProcessingTime, SelectionPolicy selectionPolicy, View view)
    {
        // initialize the scheduler
        // => create and start numberOfServers threads
        // => initioalize selection strategy => createStrategy
        // initialize frame to display simulation
        // generate numberOfClients clients using generateNRandomTasks()
        //and store them to generatedtasks

        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.selectionPolicy = selectionPolicy;
        this.view = view;
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.generatedTasks = generateNRandomTasks(numberOfClients, minProcessingTime, maxProcessingTime);

        Collections.sort(this.generatedTasks);

    }

    private List<Task> generateNRandomTasks(int numberOfClients, int minProcessingTime, int maxProcessingTime)
    {
        // generate N random tasks
        // - random processing time
        //minProcessingTime < processingTime < maxProcessingTime
        // -random arrivalTime
        // sort list with respect to arrivalTime
        List<Task> tasks = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= numberOfClients; i++)
        {
            int serviceTime = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            Task task = new Task(i, serviceTime, 0);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void run()
    {
        int currentTime = 0;
        while (currentTime < timeLimit)
        {
            // iterate generatedTasks list and pick tasks that have the
            //arrivalTime equal to currentTime
            // - send task to queue by calling the dispatchTask method
            //from Scheduler
            // - delete client from list
            // update ui frame
            for (Task t : new ArrayList<>(generatedTasks))
            {
                if (t.getArrivalTime() == currentTime)
                {
                    try {
                        scheduler.dispatchTask(t);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    generatedTasks.remove(t);
                }
            }
            currentTime++;
            // wait an interval of one second
        }
    }

    public static void main(String[] args)
    {
        SimulationManager gen = new SimulationManager(numberOfServers, numberOfClients, timeLimit, maxProcessingTime, minProcessingTime, selectionPolicy, view);
        Thread t = new Thread(gen);
        t.start();
    }
}
