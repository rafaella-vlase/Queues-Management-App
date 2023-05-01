package org.example;
import view.View;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SimulationManager implements Runnable
{
    // data read from UI
    int timeLimit; //maximum processing time - read from UI
    int maxProcessingTime;
    int minProcessingTime;
    int maxArrivalTime;
    int minArrivalTime;
    int numberOfServers;
    int numberOfClients;
    double averageProcessingTime;
    double averageWaitingTime;
    String logOfEvents;
    SelectionPolicy selectionPolicy;

    //frame for displaying simulation
    View view;
    // entity responsible with queue management and client distribution
    Scheduler scheduler;
    // pool of tasks (client shopping in the store)
    ArrayList<Task> generatedTasks;

    public SimulationManager(int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime,
                             int minProcessingTime, int maxProcessingTime, int timeLimit, View view)
    {
        // initialize the scheduler
        // => create and start numberOfServers threads
        // => initialize selection strategy => createStrategy
        // initialize frame to display simulation
        // generate numberOfClients clients using generateNRandomTasks()
        // and store them to generatedTasks

        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.timeLimit = timeLimit;
        this.view = view;
        this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        this.averageWaitingTime = 0;
        this.averageProcessingTime = 0;
        this.logOfEvents = "";
        this.generatedTasks = null;
        this.scheduler = new Scheduler(numberOfServers);

        generateNRandomTasks();
    }

    void generateNRandomTasks()
    {
        // generate N random tasks:
        // - random processing time
        // minProcessingTime < processingTime < maxProcessingTime
        // - random arrival time
        // sort list with respect to arrivalTime

        generatedTasks = new ArrayList<>();
        int totalWaitingTime = 0;
        int totalProcessingTime = 0;
        for(int i = 0; i < numberOfClients; i++)
        {
            Task task = new Task();
            task.ID = i + 1;
            task.arrivalTime = minArrivalTime + (int) (Math.random() * (maxArrivalTime - minArrivalTime));
            task.processingTime = minProcessingTime + (int) (Math.random() * (maxProcessingTime - minProcessingTime));
            generatedTasks.add(task);
            totalWaitingTime += task.arrivalTime;
            totalProcessingTime += task.processingTime;
        }
        Collections.sort(generatedTasks);
        averageWaitingTime = (double) totalWaitingTime / numberOfClients;
        averageProcessingTime = (double) totalProcessingTime / numberOfClients;
    }

    boolean queuesFinished()
    {
        if(!generatedTasks.isEmpty())
            return false;
        for(Server s : scheduler.servers)
        {
            if(s.tasks.size() != 0)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run()
    {
        int currentTime = 0;
        int maxWaitingPeriod = 0;
        scheduler.runThreads();
        while(currentTime < timeLimit && !queuesFinished())
        {
            // iterate generatedTasks list and pick tasks that have the
            // arrivalTime equal with the current time
            // - send task to queue by calling the dispatchTask method from Scheduler
            // - delete client from list
            // update UI frame
            // wait an interval of 1 second

            currentTime++;
            String timeInfo = "\n Time: " + currentTime + "\n";
            ArrayList<Task> newGeneratedTasks = new ArrayList<>();
            for(Task task : generatedTasks)
            {
                if(task.arrivalTime == currentTime)
                {
                    task.processingTime++;
                    scheduler.dispatchTask(task);
                }
                else
                {
                    newGeneratedTasks.add(task);
                }
                generatedTasks = newGeneratedTasks;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int currentWaitingPeriod = 0;
            for(Server s : scheduler.servers)
            {
                currentWaitingPeriod += s.waitingPeriod.get();
            }

            if(maxWaitingPeriod < currentWaitingPeriod)
            {
                maxWaitingPeriod = currentWaitingPeriod;
            }
            timeInfo += getCurrentLog();
            logOfEvents += timeInfo;
            view.eventLogTextArea.setText(logOfEvents);
        }
        logOfEvents += "Average Waiting Time: " + averageWaitingTime + "\n";
        logOfEvents += "Average Processing Time: " + averageProcessingTime + "\n";
        view.eventLogTextArea.setText(logOfEvents);

        logToTxt(logOfEvents);
    }

    public String getCurrentLog()
    {
        StringBuilder log = new StringBuilder();
        log.append("Waiting Clients:\n");
        for (Task t : generatedTasks)
        {
            log.append(t.toString()).append(" ");
        }
        log.append("\n");
        int queueNo = 1;
        for (Server s : scheduler.servers)
        {
            log.append("Queue ").append(queueNo).append(": ");
            log.append(s.toString().isEmpty() ? "closed" : s.toString());
            queueNo++;
            log.append("\n");
        }
        log.append("\n");
        return log.toString();
    }

//    void writeToTextFile(String log)
//    {
//        String fileName = "SimulationLog_" + System.currentTimeMillis() + ".txt";
//        try {
//            FileWriter fileWriter = new FileWriter(fileName);
//            fileWriter.write(log);
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    void logToTxt(String logOfEvents)
    {
        new File("Simulation Log.txt");
        try {
            FileWriter fileWriter = new FileWriter("Simulation Log.txt");
            fileWriter.write(logOfEvents);
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
