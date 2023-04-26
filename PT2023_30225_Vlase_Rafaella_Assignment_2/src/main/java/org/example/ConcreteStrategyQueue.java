package org.example;
import java.util.*;

public class ConcreteStrategyQueue implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task t) throws InterruptedException {
        int minQueueSize = servers.get(0).getTasks().size();
        Server minServer = servers.get(0);
        for (Server server : servers)
        {
            if (minQueueSize > server.getTasks().size())
            {
                minServer = server;
                minQueueSize = server.getTasks().size();
            }
        }
        minServer.addTask(t);
    }
}
