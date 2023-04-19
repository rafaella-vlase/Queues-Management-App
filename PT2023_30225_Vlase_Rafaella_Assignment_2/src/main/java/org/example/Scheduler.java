package org.example;

import java.awt.*;

public class Scheduler
{
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer)
    {

    }

    public void changeStrategy(SelectionPolicy policy)
    {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyTime();
    }

    public void dispatchTask(Task t)
    {

    }

    public List<Server> getServers()
    {
        return servers;
    }

}
