package org.example;
import java.util.ArrayList;

public class ConcreteStrategyQueue implements Strategy
{
    // adds a task to the server with the shortest queue
    @Override
    public void addTask(ArrayList<Server> servers, Task t)
    {
        int minQueueSize = servers.get(0).tasks.size(); // start with the first server s queue size
        Server minServer = servers.get(0); // start with the first server
        for (Server server : servers) {
            if (minQueueSize > server.tasks.size()) {
                // if a server has a shorter queue, update minQueueSize and minServer
                minServer = server;
                minQueueSize = server.tasks.size();
            }
        }
        // add the task to the server with the shortest queue
        minServer.addTask(t);
    }
}
