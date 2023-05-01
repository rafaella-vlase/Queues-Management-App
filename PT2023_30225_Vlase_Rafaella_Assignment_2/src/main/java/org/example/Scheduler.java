package org.example;
import java.util.ArrayList;

public class Scheduler
{
    ArrayList<Server> servers;
    ArrayList<Thread> threads;
    Strategy strategy;

    Scheduler(int numberOfServers)
    {
        servers = new ArrayList<>();
        threads = new ArrayList<>();
        strategy = new ConcreteStrategyTime();
        for(int i = 0; i < numberOfServers; i++) // create a new thread for each server
        {
            Server server = new Server();
            servers.add(server);
            threads.add(new Thread(server));
        }
    }

    void changeStrategy(SelectionPolicy policy)
    {
        // apply strategy pattern to instantiate the strategy with the
        // concrete strategy corresponding to policy
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new ConcreteStrategyTime();
        }
    }

    // method to add a task to one of the servers using the current strategy
    void dispatchTask(Task t)
    {
        // call the strategy addTask method
        strategy.addTask(servers, t);
    }

    void runThreads()
    {
        for(Thread thread : threads) // start all the threads in the threads array list
        {
            thread.start();
        }
    }
}
