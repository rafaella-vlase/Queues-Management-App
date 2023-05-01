package org.example;
import java.util.ArrayList;

public class ConcreteStrategyTime implements Strategy
{
    //adds a task to the server with the smallest waiting period
    @Override
    public void addTask(ArrayList<Server> servers, Task t)
    {
        int minWaitingPeriod = servers.get(0).waitingPeriod.intValue(); // start with the first server s waiting period
        Server minServer = servers.get(0); // start with the first server
        for(Server server : servers)
        {
            if(minWaitingPeriod > server.waitingPeriod.intValue())
            {
                // if a server has a shorter waiting period, update minServer and minWaitingPeriod
                minServer = server;
                minWaitingPeriod = server.waitingPeriod.intValue();
            }
        }
        // add the task to the server with the shortest waiting period
        minServer.addTask(t);
    }
}
