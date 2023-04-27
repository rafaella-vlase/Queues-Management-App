package org.example;
import java.util.*;

public class ConcreteStrategyTime implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task t) throws InterruptedException
    {
        int minWaitingPeriod = servers.get(0).waitingPeriod.get();
        Server minServer = servers.get(0);
        for (Server server : servers)
        {
            if (minWaitingPeriod > server.waitingPeriod.get())
            {
                minServer = server;
                minWaitingPeriod = server.waitingPeriod.get();
            }
        }
        minServer.addTask(t);
    }
}
