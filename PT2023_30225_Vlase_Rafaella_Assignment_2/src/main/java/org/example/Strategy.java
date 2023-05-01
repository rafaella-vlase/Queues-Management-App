package org.example;
import java.util.ArrayList;

public interface Strategy
{
    void addTask(ArrayList<Server> servers, Task t);
}
