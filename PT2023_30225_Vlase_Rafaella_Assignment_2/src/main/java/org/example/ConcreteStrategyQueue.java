package org.example;
import java.util.*;

public class ConcreteStrategyQueue implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task t) throws InterruptedException
    {
        int minQueueSize = Integer.MAX_VALUE;
        Server minServer = null;
        for (Server server : servers) {
            int queueSize = server.getTasks().size();
            if (queueSize < minQueueSize) {
                minServer = server;
                minQueueSize = queueSize;
            }
        }
        if (minServer != null) {
            minServer.addTask(t);
        }
    }
}
